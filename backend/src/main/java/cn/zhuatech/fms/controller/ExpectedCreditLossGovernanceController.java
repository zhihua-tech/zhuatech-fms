/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.controller;

import cn.zhuatech.fms.common.ApiResponse;
import cn.zhuatech.fms.service.ExpectedCreditLossGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enterprise/fms")
public class ExpectedCreditLossGovernanceController {
    private final ExpectedCreditLossGovernanceService service;
    public ExpectedCreditLossGovernanceController(ExpectedCreditLossGovernanceService service) { this.service = service; }

    @PostMapping("/expected-credit-loss")
    public ApiResponse<ExpectedCreditLossGovernanceService.Assessment> assess(
            @Valid @RequestBody ExpectedCreditLossGovernanceService.Request request) {
        return ApiResponse.ok("应收信用减值评估完成", service.assess(request));
    }
}
