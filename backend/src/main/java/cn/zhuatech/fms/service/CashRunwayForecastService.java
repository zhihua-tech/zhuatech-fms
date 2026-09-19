/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class CashRunwayForecastService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result forecast(Request request) {
        BigDecimal expectedCollections = request.receivablesExpected()
            .multiply(request.collectionRate()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal monthlyNetBurn = request.committedOutflow().add(request.variableOutflow())
            .subtract(request.monthlyInflow()).subtract(expectedCollections)
            .setScale(2, RoundingMode.HALF_UP);
        BigDecimal availableLiquidity = request.openingCash().subtract(request.minimumReserve())
            .max(BigDecimal.ZERO);
        BigDecimal runwayMonths = monthlyNetBurn.signum() <= 0 ? new BigDecimal("999.0")
            : availableLiquidity.divide(monthlyNetBurn, 1, RoundingMode.DOWN);
        BigDecimal quarterEndCash = request.openingCash()
            .subtract(monthlyNetBurn.multiply(BigDecimal.valueOf(3)))
            .setScale(2, RoundingMode.HALF_UP);
        String status = monthlyNetBurn.signum() <= 0 ? "POSITIVE_CASHFLOW"
            : runwayMonths.compareTo(new BigDecimal("3")) < 0 ? "CRITICAL"
            : runwayMonths.compareTo(new BigDecimal("6")) < 0 ? "WATCH" : "HEALTHY";

        List<String> actions = new ArrayList<>();
        if (request.collectionRate().compareTo(new BigDecimal("0.80")) < 0) actions.add("提升重点应收账款催收优先级");
        if ("CRITICAL".equals(status)) actions.add("冻结非必要支出并启动周度现金流作战会议");
        if (quarterEndCash.compareTo(request.minimumReserve()) < 0) actions.add("准备融资、股东借款或付款节奏调整方案");
        if (actions.isEmpty()) actions.add("保持月度滚动预测并监控预算偏差");
        return new Result(request.businessUnit(), expectedCollections, monthlyNetBurn,
            runwayMonths, quarterEndCash, status, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String businessUnit,
                          @DecimalMin("0") BigDecimal openingCash,
                          @DecimalMin("0") BigDecimal monthlyInflow,
                          @DecimalMin("0") BigDecimal committedOutflow,
                          @DecimalMin("0") BigDecimal variableOutflow,
                          @DecimalMin("0") BigDecimal receivablesExpected,
                          @DecimalMin("0") @DecimalMax("1") BigDecimal collectionRate,
                          @DecimalMin("0") BigDecimal minimumReserve) {}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String businessUnit, BigDecimal expectedCollections,
                         BigDecimal monthlyNetBurn, BigDecimal runwayMonths,
                         BigDecimal projectedQuarterEndCash, String status,
                         List<String> actions) {}
}
