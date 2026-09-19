/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.config;

import cn.zhuatech.fms.model.*;import cn.zhuatech.fms.repository.*;import org.springframework.boot.CommandLineRunner;import org.springframework.context.annotation.*;import org.springframework.security.crypto.password.PasswordEncoder;import java.math.BigDecimal;import java.time.LocalDate;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration
public class DataInitializer {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Bean CommandLineRunner seed(UserRepository users,CashAccountRepository accounts,ReceivableRepository receivables,PayableRepository payables,ExpenseClaimRepository expenses,BudgetRepository budgets,PasswordEncoder encoder){return args->{
        if(users.count()>0)return;
        users.save(new UserAccount("admin",encoder.encode("admin123"),"财务平台主管",UserAccount.Role.ADMIN));
        users.save(new UserAccount("finance",encoder.encode("finance123"),"资金经理",UserAccount.Role.FINANCE_MANAGER));
        users.save(new UserAccount("employee",encoder.encode("employee123"),"业务员工",UserAccount.Role.EMPLOYEE));
        accounts.save(new CashAccount("ACC-CMB-001","招商银行基本户","招商银行上海张江支行","基本户",new BigDecimal("2864500.28"),new BigDecimal("2788300.28"),"CNY","正常"));
        accounts.save(new CashAccount("ACC-ICBC-002","工商银行一般户","工商银行上海浦东支行","一般户",new BigDecimal("1286200.00"),new BigDecimal("1286200.00"),"CNY","正常"));
        accounts.save(new CashAccount("ACC-ALIPAY-003","支付宝企业账户","支付宝","第三方支付",new BigDecimal("186420.73"),new BigDecimal("175660.73"),"CNY","正常"));
        receivables.save(new Receivable("AR-2607-018","澄川智能制造有限公司","HT-2026-031 项目进度款",new BigDecimal("860000"),new BigDecimal("500000"),LocalDate.now().plusDays(3),"顾清禾","部分收款"));
        receivables.save(new Receivable("AR-2607-023","启衡实业集团有限公司","HT-2026-044 软件实施款",new BigDecimal("1260000"),BigDecimal.ZERO,LocalDate.now().plusDays(8),"许知遥","待收款"));
        receivables.save(new Receivable("AR-2606-097","锐恒装备股份有限公司","HT-2026-019 运维服务费",new BigDecimal("238000"),BigDecimal.ZERO,LocalDate.now().minusDays(5),"林清越","已逾期"));
        receivables.save(new Receivable("AR-2607-036","云岱精工有限公司","HT-2026-052 咨询首付款",new BigDecimal("320000"),new BigDecimal("160000"),LocalDate.now().plusDays(15),"唐予安","部分收款"));
        payables.save(new Payable("AP-2607-112","上海云舟科技有限公司","CG-2026-088 云资源采购",new BigDecimal("186500"),BigDecimal.ZERO,LocalDate.now().plusDays(2),"江叙","待付款"));
        payables.save(new Payable("AP-2607-108","杭州启明咨询有限公司","FW-2026-024 专家服务",new BigDecimal("98000"),new BigDecimal("49000"),LocalDate.now().plusDays(6),"苏景行","部分付款"));
        payables.save(new Payable("AP-2607-119","上海简印办公用品有限公司","CG-2026-096 办公采购",new BigDecimal("28640"),BigDecimal.ZERO,LocalDate.now().plusDays(12),"温书屿","审批中"));
        expenses.save(new ExpenseClaim("BX-260731-034","温书屿","咨询交付部","差旅费","南京客户现场蓝图调研",new BigDecimal("2860.50"),LocalDate.now().minusDays(1),"待审批"));
        expenses.save(new ExpenseClaim("BX-260731-029","江叙","研发中心","业务招待费","仓储项目接口联合评审",new BigDecimal("1280.00"),LocalDate.now().minusDays(2),"待付款"));
        expenses.save(new ExpenseClaim("BX-260730-086","顾呈","产品中心","交通费","客户现场原型访谈往返",new BigDecimal("368.00"),LocalDate.now().minusDays(3),"已完成"));
        budgets.save(new Budget("BG-2026-RD","研发中心","研发投入",2026,new BigDecimal("3200000"),new BigDecimal("360000"),new BigDecimal("1840000"),"执行中"));
        budgets.save(new Budget("BG-2026-DLV","咨询交付部","项目交付",2026,new BigDecimal("2680000"),new BigDecimal("280000"),new BigDecimal("1520000"),"执行中"));
        budgets.save(new Budget("BG-2026-MKT","市场中心","市场推广",2026,new BigDecimal("1200000"),new BigDecimal("168000"),new BigDecimal("886000"),"预警"));
        budgets.save(new Budget("BG-2026-ADM","综合管理部","行政运营",2026,new BigDecimal("960000"),new BigDecimal("92000"),new BigDecimal("548000"),"执行中"));
    };}
}
