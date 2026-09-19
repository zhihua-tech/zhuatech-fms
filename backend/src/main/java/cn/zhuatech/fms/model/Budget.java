/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Entity
@Table(name="fms_budget")
public class Budget extends BaseEntity {
    @Column(nullable=false,unique=true,length=32) private String budgetNo;
    @Column(nullable=false,length=60) private String department;
    @Column(nullable=false,length=60) private String subjectName;
    @Column(nullable=false) private int fiscalYear;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal annualAmount;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal occupiedAmount;
    @Column(nullable=false,precision=16,scale=2) private BigDecimal actualAmount;
    @Column(nullable=false,length=20) private String status;

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    protected Budget() {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Budget(String no,String department,String subject,int year,BigDecimal annual,BigDecimal occupied,BigDecimal actual,String status){this.budgetNo=no;this.department=department;this.subjectName=subject;this.fiscalYear=year;this.annualAmount=annual;this.occupiedAmount=occupied;this.actualAmount=actual;this.status=status;}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String getBudgetNo(){return budgetNo;} /**
                                                   * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                   */
public String getDepartment(){return department;} /**
                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                     */
public String getSubjectName(){return subjectName;} /**
                                                                                                                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                         */
public int getFiscalYear(){return fiscalYear;} /**
                                                                                                                                                                                                        * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                        */
public BigDecimal getAnnualAmount(){return annualAmount;} /**
                                                                                                                                                                                                                                                                  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                  */
public BigDecimal getOccupiedAmount(){return occupiedAmount;} /**
                                                                                                                                                                                                                                                                                                                                * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                */
public BigDecimal getActualAmount(){return actualAmount;} /**
                                                                                                                                                                                                                                                                                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                          */
public String getStatus(){return status;}
}
