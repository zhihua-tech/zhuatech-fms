/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ExpectedCreditLossGovernanceServiceTest {
    private final ExpectedCreditLossGovernanceService service = new ExpectedCreditLossGovernanceService();

    @Test void booksApprovedAllowance() {
        var result = service.assess(new ExpectedCreditLossGovernanceService.Request(
                "CUS-001", 1_000_000, 0, 30, 1000, 4000, false, true, true));
        assertThat(result.allowanceCents()).isEqualTo(40_000);
        assertThat(result.decision()).isEqualTo(ExpectedCreditLossGovernanceService.Decision.BOOK);
    }

    @Test void holdsUnreviewedOverdueExposure() {
        var result = service.assess(new ExpectedCreditLossGovernanceService.Request(
                "CUS-002", 2_000_000, 500_000, 120, 0, 6000, true, false, false));
        assertThat(result.decision()).isEqualTo(ExpectedCreditLossGovernanceService.Decision.HOLD);
        assertThat(result.blockers()).hasSize(2);
        assertThat(result.actions()).hasSize(3);
    }
}
