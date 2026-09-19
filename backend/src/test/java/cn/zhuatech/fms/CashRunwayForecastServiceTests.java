/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms;

import cn.zhuatech.fms.service.CashRunwayForecastService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class CashRunwayForecastServiceTests {
    private final CashRunwayForecastService service = new CashRunwayForecastService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void flagsCriticalShortCashRunway() {
        var result = service.forecast(new CashRunwayForecastService.Request(
            "华东交付中心", new BigDecimal("500000"), new BigDecimal("200000"),
            new BigDecimal("450000"), new BigDecimal("50000"), new BigDecimal("100000"),
            new BigDecimal("0.80"), new BigDecimal("100000")));

        assertEquals(new BigDecimal("220000.00"), result.monthlyNetBurn());
        assertEquals(new BigDecimal("1.8"), result.runwayMonths());
        assertEquals("CRITICAL", result.status());
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test
    void recognizesPositiveMonthlyCashFlow() {
        var result = service.forecast(new CashRunwayForecastService.Request(
            "华南产品中心", new BigDecimal("800000"), new BigDecimal("500000"),
            new BigDecimal("300000"), new BigDecimal("80000"), new BigDecimal("50000"),
            new BigDecimal("0.90"), new BigDecimal("150000")));

        assertEquals("POSITIVE_CASHFLOW", result.status());
        assertEquals(new BigDecimal("999.0"), result.runwayMonths());
    }
}
