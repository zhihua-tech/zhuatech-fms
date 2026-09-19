/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.service;

import cn.zhuatech.fms.common.BusinessException;
import cn.zhuatech.fms.dto.FinanceDto.*;
import cn.zhuatech.fms.model.*;
import cn.zhuatech.fms.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
@Transactional(readOnly=true)
public class FinanceService {
    private final CashAccountRepository accounts; private final ReceivableRepository receivables; private final PayableRepository payables; private final ExpenseClaimRepository expenses; private final BudgetRepository budgets;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public FinanceService(CashAccountRepository accounts,ReceivableRepository receivables,PayableRepository payables,ExpenseClaimRepository expenses,BudgetRepository budgets){this.accounts=accounts;this.receivables=receivables;this.payables=payables;this.expenses=expenses;this.budgets=budgets;}

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Dashboard dashboard(){
        var accountList=accounts.findAll(); var receivableList=receivables.findAllByOrderByDueDateAsc(); var payableList=payables.findAllByOrderByDueDateAsc(); var expenseList=expenses.findAllByOrderByExpenseDateDesc(); var budgetList=budgets.findByFiscalYearOrderByDepartmentAsc(LocalDate.now().getYear());
        BigDecimal cash=sum(accountList.stream().map(CashAccount::getBalance).toList());
        BigDecimal available=sum(accountList.stream().map(CashAccount::getAvailableBalance).toList());
        BigDecimal ar=sum(receivableList.stream().map(x->x.getAmount().subtract(x.getReceivedAmount())).toList());
        BigDecimal overdue=sum(receivableList.stream().filter(x->x.getDueDate().isBefore(LocalDate.now())&&!"已结清".equals(x.getStatus())).map(x->x.getAmount().subtract(x.getReceivedAmount())).toList());
        BigDecimal ap=sum(payableList.stream().map(x->x.getAmount().subtract(x.getPaidAmount())).toList());
        BigDecimal pending=sum(expenseList.stream().filter(x->"待审批".equals(x.getStatus())||"待付款".equals(x.getStatus())).map(ExpenseClaim::getAmount).toList());
        BigDecimal annual=sum(budgetList.stream().map(Budget::getAnnualAmount).toList()); BigDecimal actual=sum(budgetList.stream().map(Budget::getActualAmount).toList());
        BigDecimal rate=annual.signum()==0?BigDecimal.ZERO:actual.multiply(new BigDecimal("100")).divide(annual,1,RoundingMode.HALF_UP);
        return new Dashboard(cash,available,ar,overdue,ap,pending,rate,receivableList.stream().limit(6).map(ReceivableView::from).toList(),payableList.stream().limit(6).map(PayableView::from).toList());
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    private BigDecimal sum(List<BigDecimal> values){return values.stream().reduce(BigDecimal.ZERO,BigDecimal::add);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<AccountView> accounts(){return accounts.findAll().stream().map(AccountView::from).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ReceivableView> receivables(){return receivables.findAllByOrderByDueDateAsc().stream().map(ReceivableView::from).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<PayableView> payables(){return payables.findAllByOrderByDueDateAsc().stream().map(PayableView::from).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ExpenseView> expenses(){return expenses.findAllByOrderByExpenseDateDesc().stream().map(ExpenseView::from).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<BudgetView> budgets(){return budgets.findByFiscalYearOrderByDepartmentAsc(LocalDate.now().getYear()).stream().map(BudgetView::from).toList();}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Transactional public ReceivableView createReceivable(CreateReceivableRequest request){if(receivables.findByReceivableNo(request.receivableNo()).isPresent())throw new BusinessException("应收单号已存在");return ReceivableView.from(receivables.save(new Receivable(request.receivableNo(),request.customerName(),request.sourceDocument(),request.amount(),BigDecimal.ZERO,request.dueDate(),request.owner(),"待收款")));}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Transactional public ReceivableView recordReceipt(Long id,RecordReceiptRequest request){var item=receivables.findById(id).orElseThrow(()->new BusinessException("应收记录不存在"));if("已结清".equals(item.getStatus()))throw new BusinessException("该应收已结清");if(item.getReceivedAmount().add(request.amount()).compareTo(item.getAmount())>0)throw new BusinessException("收款金额不能超过剩余应收");item.recordReceipt(request.amount());return ReceivableView.from(item);}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Transactional public ExpenseView submitExpense(SubmitExpenseRequest request){String no="BX-"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));return ExpenseView.from(expenses.save(new ExpenseClaim(no,request.claimant(),request.department(),request.category(),request.purpose(),request.amount(),request.expenseDate(),"待审批")));}
}
