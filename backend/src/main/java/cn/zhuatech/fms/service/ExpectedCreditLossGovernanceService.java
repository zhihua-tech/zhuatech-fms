/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class ExpectedCreditLossGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        long exposure = Math.max(0, request.receivableCents() - request.collateralCents());
        long allowance = BigDecimal.valueOf(exposure)
                .multiply(BigDecimal.valueOf(request.probabilityOfDefaultBps()))
                .multiply(BigDecimal.valueOf(request.lossGivenDefaultBps()))
                .divide(BigDecimal.valueOf(100_000_000L), 0, RoundingMode.HALF_UP)
                .longValueExact();
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.creditReviewComplete()) blockers.add("客户信用复核未完成");
        if (request.probabilityOfDefaultBps() == 0 && request.overdueDays() > 0) blockers.add("逾期应收缺少违约概率参数");
        if (request.disputed()) actions.add("对争议应收单独评估回收概率与法律状态");
        if (request.overdueDays() >= 90) actions.add("启动重大逾期专项催收和减值复核");
        if (!request.ownerApproved()) actions.add("取得财务负责人对减值结果的批准");

        Decision decision = !blockers.isEmpty() ? Decision.HOLD
                : !actions.isEmpty() ? Decision.REVIEW : Decision.BOOK;
        return new Assessment(request.customerCode(), exposure, allowance, decision,
                List.copyOf(blockers), List.copyOf(actions));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String customerCode, @Min(0) long receivableCents,
                          @Min(0) long collateralCents, @Min(0) int overdueDays,
                          @Min(0) @Max(10000) int probabilityOfDefaultBps,
                          @Min(0) @Max(10000) int lossGivenDefaultBps,
                          boolean disputed, boolean creditReviewComplete,
                          boolean ownerApproved) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(String customerCode, long exposureCents, long allowanceCents,
                             Decision decision, List<String> blockers, List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { BOOK, REVIEW, HOLD }
}
