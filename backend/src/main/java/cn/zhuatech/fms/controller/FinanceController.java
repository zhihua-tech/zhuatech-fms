/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.controller;

import cn.zhuatech.fms.common.ApiResponse;import cn.zhuatech.fms.dto.FinanceDto.*;import cn.zhuatech.fms.service.FinanceService;import jakarta.validation.Valid;import org.springframework.security.access.prepost.PreAuthorize;import org.springframework.web.bind.annotation.*;import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/fms")
public class FinanceController {
    private final FinanceService service; /**
                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                           */
public FinanceController(FinanceService service){this.service=service;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/dashboard") public ApiResponse<Dashboard> dashboard(){return ApiResponse.ok(service.dashboard());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/accounts") public ApiResponse<List<AccountView>> accounts(){return ApiResponse.ok(service.accounts());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/receivables") public ApiResponse<List<ReceivableView>> receivables(){return ApiResponse.ok(service.receivables());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/payables") public ApiResponse<List<PayableView>> payables(){return ApiResponse.ok(service.payables());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/expenses") public ApiResponse<List<ExpenseView>> expenses(){return ApiResponse.ok(service.expenses());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @GetMapping("/budgets") public ApiResponse<List<BudgetView>> budgets(){return ApiResponse.ok(service.budgets());}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/receivables") @PreAuthorize("hasAnyRole('ADMIN','FINANCE_MANAGER')") public ApiResponse<ReceivableView> createReceivable(@Valid @RequestBody CreateReceivableRequest request){return ApiResponse.ok("应收记录创建成功",service.createReceivable(request));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PatchMapping("/receivables/{id}/receipt") @PreAuthorize("hasAnyRole('ADMIN','FINANCE_MANAGER')") public ApiResponse<ReceivableView> recordReceipt(@PathVariable Long id,@Valid @RequestBody RecordReceiptRequest request){return ApiResponse.ok("收款登记成功",service.recordReceipt(id,request));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/expenses") @PreAuthorize("hasAnyRole('ADMIN','FINANCE_MANAGER','EMPLOYEE')") public ApiResponse<ExpenseView> submitExpense(@Valid @RequestBody SubmitExpenseRequest request){return ApiResponse.ok("报销申请已提交",service.submitExpense(request));}
}
