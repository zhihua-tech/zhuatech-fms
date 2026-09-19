/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class BudgetForecastService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result forecast(Request request) {
        BigDecimal progress = BigDecimal.valueOf(request.progressPercent()).divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP);
        BigDecimal forecastTotal = request.actualToDate().divide(progress, 2, RoundingMode.HALF_UP).add(request.committedAmount());
        BigDecimal variance = forecastTotal.subtract(request.budgetAmount());
        BigDecimal overrunPercent = request.budgetAmount().signum() == 0 ? BigDecimal.ZERO
            : variance.divide(request.budgetAmount(), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        String level = overrunPercent.compareTo(BigDecimal.valueOf(20)) >= 0 ? "HIGH"
            : overrunPercent.compareTo(BigDecimal.valueOf(5)) >= 0 ? "WATCH" : "CONTROLLED";
        List<String> actions = new ArrayList<>();
        if (variance.signum() > 0) actions.add("冻结非必要支出并复核剩余采购承诺");
        if ("HIGH".equals(level)) actions.add("提交预算调整或降本方案至财务负责人");
        if (variance.signum() > 0 && request.remainingMonths() <= 2) actions.add("在剩余执行期内按周跟踪降本措施");
        if (actions.isEmpty()) actions.add("保持月度预算滚动预测");
        return new Result(forecastTotal, variance, overrunPercent.setScale(2, RoundingMode.HALF_UP), level, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@DecimalMin("0") BigDecimal budgetAmount,
                          @DecimalMin("0") BigDecimal actualToDate,
                          @DecimalMin("0") BigDecimal committedAmount,
                          @DecimalMin("0.01") @DecimalMax("100") double progressPercent,
                          @Min(0) int remainingMonths) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(BigDecimal forecastTotal, BigDecimal variance,
                         BigDecimal overrunPercent, String level, List<String> actions) {}
}
