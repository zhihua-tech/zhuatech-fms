/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class ReceivableCollectionForecastService {

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result forecast(Request request) {
        double expectedDueCollection = request.dueWithin30Days() * request.historicalCollectionRate();
        double disputeDiscount = request.disputedAmount() * 0.7;
        double expectedCollection = Math.min(request.totalReceivable(),
                Math.max(0, expectedDueCollection + request.promisedCollection() - disputeDiscount));
        double liquidityGap = Math.max(0, request.targetCollection() - expectedCollection);
        double overdueRate = request.overdueAmount() * 100.0 / request.totalReceivable();

        int riskScore = 0;
        List<String> actions = new ArrayList<>();
        if (liquidityGap > request.targetCollection() * 0.2) { riskScore += 45; actions.add("启动重点客户催收战情和现金缺口预案"); }
        else if (liquidityGap > 0) { riskScore += 20; actions.add("逐笔确认承诺回款日期与责任人"); }
        if (overdueRate > 30) { riskScore += 30; actions.add("暂停高风险客户新增信用敞口"); }
        if (request.weightedOverdueDays() > 60) { riskScore += 25; actions.add("升级长期逾期至法务或商务谈判"); }
        if (request.disputedAmount() > 0) actions.add("闭环发票、验收和合同争议证据");

        String status = riskScore >= 60 ? "CRITICAL" : riskScore >= 25 ? "WATCH" : "HEALTHY";
        if (actions.isEmpty()) actions.add("维持当前回款节奏并按周滚动预测");
        return new Result(round(expectedCollection), round(liquidityGap), round(overdueRate), riskScore, status, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private double round(double value) { return Math.round(value * 100.0) / 100.0; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(
            @DecimalMin("0.01") double totalReceivable,
            @DecimalMin("0") double dueWithin30Days,
            @DecimalMin("0") double overdueAmount,
            @DecimalMin("0") double weightedOverdueDays,
            @DecimalMin("0") double promisedCollection,
            @DecimalMin("0") double disputedAmount,
            @DecimalMin("0") @DecimalMax("1") double historicalCollectionRate,
            @DecimalMin("0.01") double targetCollection
    ) {}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(double expectedCollection30Days, double liquidityGap, double overdueRate,
                         int riskScore, String status, List<String> actions) {}
}
