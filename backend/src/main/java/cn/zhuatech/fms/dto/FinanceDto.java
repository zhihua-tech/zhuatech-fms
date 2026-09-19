/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.dto;

import cn.zhuatech.fms.model.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public final class FinanceDto {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private FinanceDto() {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record AccountView(Long id,String accountCode,String accountName,String bankName,String accountType,BigDecimal balance,BigDecimal availableBalance,String currency,String status){/**
                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                              */
public static AccountView from(CashAccount x){return new AccountView(x.getId(),x.getAccountCode(),x.getAccountName(),x.getBankName(),x.getAccountType(),x.getBalance(),x.getAvailableBalance(),x.getCurrency(),x.getStatus());}}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ReceivableView(Long id,String receivableNo,String customerName,String sourceDocument,BigDecimal amount,BigDecimal receivedAmount,LocalDate dueDate,String owner,String status){/**
                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                  */
public static ReceivableView from(Receivable x){return new ReceivableView(x.getId(),x.getReceivableNo(),x.getCustomerName(),x.getSourceDocument(),x.getAmount(),x.getReceivedAmount(),x.getDueDate(),x.getOwner(),x.getStatus());}}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record PayableView(Long id,String payableNo,String supplierName,String sourceDocument,BigDecimal amount,BigDecimal paidAmount,LocalDate dueDate,String applicant,String status){/**
                                                                                                                                                                                            * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                            */
public static PayableView from(Payable x){return new PayableView(x.getId(),x.getPayableNo(),x.getSupplierName(),x.getSourceDocument(),x.getAmount(),x.getPaidAmount(),x.getDueDate(),x.getApplicant(),x.getStatus());}}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ExpenseView(Long id,String claimNo,String claimant,String department,String category,String purpose,BigDecimal amount,LocalDate expenseDate,String status){/**
                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                              */
public static ExpenseView from(ExpenseClaim x){return new ExpenseView(x.getId(),x.getClaimNo(),x.getClaimant(),x.getDepartment(),x.getCategory(),x.getPurpose(),x.getAmount(),x.getExpenseDate(),x.getStatus());}}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record BudgetView(Long id,String budgetNo,String department,String subjectName,Integer fiscalYear,BigDecimal annualAmount,BigDecimal occupiedAmount,BigDecimal actualAmount,String status){/**
                                                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                       */
public static BudgetView from(Budget x){return new BudgetView(x.getId(),x.getBudgetNo(),x.getDepartment(),x.getSubjectName(),x.getFiscalYear(),x.getAnnualAmount(),x.getOccupiedAmount(),x.getActualAmount(),x.getStatus());}}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Dashboard(BigDecimal cashBalance,BigDecimal availableCash,BigDecimal receivableAmount,BigDecimal overdueReceivable,BigDecimal payableAmount,BigDecimal expensePending,BigDecimal budgetExecutionRate,List<ReceivableView> upcomingReceivables,List<PayableView> upcomingPayables){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record CreateReceivableRequest(@NotBlank String receivableNo,@NotBlank @Size(max=100) String customerName,@NotBlank String sourceDocument,@NotNull @Positive BigDecimal amount,@NotNull @FutureOrPresent LocalDate dueDate,@NotBlank String owner){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record RecordReceiptRequest(@NotNull @Positive BigDecimal amount){}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record SubmitExpenseRequest(@NotBlank String claimant,@NotBlank String department,@NotBlank String category,@NotBlank @Size(max=160) String purpose,@NotNull @Positive BigDecimal amount,@NotNull @PastOrPresent LocalDate expenseDate){}
}
