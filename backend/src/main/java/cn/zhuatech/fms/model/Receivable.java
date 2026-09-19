/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity
@Table(name="fms_receivable")
public class Receivable extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String receivableNo;
    @Column(nullable=false,length=100) private String customerName;
    @Column(nullable=false,length=100) private String sourceDocument;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal amount;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal receivedAmount;
    @Column(nullable=false) private LocalDate dueDate;
    @Column(nullable=false,length=40) private String owner;
    @Column(nullable=false,length=20) private String status;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected Receivable() {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Receivable(String no,String customer,String source,BigDecimal amount,BigDecimal received,LocalDate due,String owner,String status){this.receivableNo=no;this.customerName=customer;this.sourceDocument=source;this.amount=amount;this.receivedAmount=received;this.dueDate=due;this.owner=owner;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getReceivableNo(){return receivableNo;} /**
                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                           */
public String getCustomerName(){return customerName;} /**
                                                                                                                 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                 */
public String getSourceDocument(){return sourceDocument;} /**
                                                                                                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                           */
public BigDecimal getAmount(){return amount;} /**
                                                                                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                         */
public BigDecimal getReceivedAmount(){return receivedAmount;} /**
                                                                                                                                                                                                                                                                                       * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                       */
public LocalDate getDueDate(){return dueDate;} /**
                                                                                                                                                                                                                                                                                                                                      * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                      */
public String getOwner(){return owner;} /**
                                                                                                                                                                                                                                                                                                                                                                              * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                              */
public String getStatus(){return status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public void recordReceipt(BigDecimal value){this.receivedAmount=this.receivedAmount.add(value);this.status=this.receivedAmount.compareTo(this.amount)>=0?"已结清":"部分收款";}
}
