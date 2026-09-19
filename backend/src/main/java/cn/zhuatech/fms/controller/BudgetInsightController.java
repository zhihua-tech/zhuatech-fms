/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.fms.controller;

import cn.zhuatech.fms.common.ApiResponse;
import cn.zhuatech.fms.service.BudgetForecastService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/fms")
public class BudgetInsightController {
    private final BudgetForecastService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public BudgetInsightController(BudgetForecastService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/budget-forecast")
    public ApiResponse<BudgetForecastService.Result> forecast(@Valid @RequestBody BudgetForecastService.Request request) {
        return ApiResponse.ok(service.forecast(request));
    }
}
