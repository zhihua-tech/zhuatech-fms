/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity
@Table(name="fms_expense_claim")
public class ExpenseClaim extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String claimNo;
    @Column(nullable=false,length=40) private String claimant;
    @Column(nullable=false,length=60) private String department;
    @Column(nullable=false,length=40) private String category;
    @Column(nullable=false,length=160) private String purpose;
    @Column(nullable=false,precision=14,scale=2) private BigDecimal amount;
    @Column(nullable=false) private LocalDate expenseDate;
    @Column(nullable=false,length=20) private String status;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected ExpenseClaim() {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ExpenseClaim(String no,String claimant,String department,String category,String purpose,BigDecimal amount,LocalDate date,String status){this.claimNo=no;this.claimant=claimant;this.department=department;this.category=category;this.purpose=purpose;this.amount=amount;this.expenseDate=date;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getClaimNo(){return claimNo;} /**
                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                 */
public String getClaimant(){return claimant;} /**
                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                               */
public String getDepartment(){return department;} /**
                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                 */
public String getCategory(){return category;} /**
                                                                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                               */
public String getPurpose(){return purpose;} /**
                                                                                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                           */
public BigDecimal getAmount(){return amount;} /**
                                                                                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                         */
public LocalDate getExpenseDate(){return expenseDate;} /**
                                                                                                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                */
public String getStatus(){return status;}
}
