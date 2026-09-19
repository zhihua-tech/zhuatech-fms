/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity
@Table(name = "fms_cash_account")
public class CashAccount extends BaseEntity {
    @Column(nullable=false, unique=true, length=32) private String accountCode;
    @Column(nullable=false, length=80) private String accountName;
    @Column(nullable=false, length=80) private String bankName;
    @Column(nullable=false, length=40) private String accountType;
    @Column(nullable=false, precision=16, scale=2) private BigDecimal balance;
    @Column(nullable=false, precision=16, scale=2) private BigDecimal availableBalance;
    @Column(nullable=false, length=16) private String currency;
    @Column(nullable=false, length=20) private String status;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected CashAccount() {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public CashAccount(String code,String name,String bank,String type,BigDecimal balance,BigDecimal available,String currency,String status){this.accountCode=code;this.accountName=name;this.bankName=bank;this.accountType=type;this.balance=balance;this.availableBalance=available;this.currency=currency;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getAccountCode(){return accountCode;} /**
                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                         */
public String getAccountName(){return accountName;} /**
                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                             */
public String getBankName(){return bankName;} /**
                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                           */
public String getAccountType(){return accountType;} /**
                                                                                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                               */
public BigDecimal getBalance(){return balance;} /**
                                                                                                                                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                               */
public BigDecimal getAvailableBalance(){return availableBalance;} /**
                                                                                                                                                                                                                                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                 */
public String getCurrency(){return currency;} /**
                                                                                                                                                                                                                                                                                                                                                                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                               */
public String getStatus(){return status;}
}
