//package com.fufu.exp4;
//
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ExtendedModelMap;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//
//class Schedule implements Serializable {
//    private String doctorName;
//    private String title;
//    private String shiftTime;
//    private int remainingQuota;
//
//    public Schedule(String doctorName, String title, String shiftTime, int remainingQuota) {
//        this.doctorName = doctorName;
//        this.title = title;
//        this.shiftTime = shiftTime;
//        this.remainingQuota = remainingQuota;
//    }
//
//    public String getDoctorName() { return doctorName; }
//    public String getTitle() { return title; }
//    public String getShiftTime() { return shiftTime; }
//    public int getRemainingQuota() { return remainingQuota; }
//}
//
//class TestReport implements Serializable {
//    private String reportId;
//    private String patientName;
//    private String itemName;
//    private String result;
//    private String status;
//
//    public TestReport(String reportId, String patientName, String itemName, String result, String status) {
//        this.reportId = reportId;
//        this.patientName = patientName;
//        this.itemName = itemName;
//        this.result = result;
//        this.status = status;
//    }
//
//    public String getReportId() { return reportId; }
//    public String getPatientName() { return patientName; }
//    public String getItemName() { return itemName; }
//    public String getResult() { return result; }
//    public String getStatus() { return status; }
//}
//
//@Controller
//class ScheduleController {
//
//    @GetMapping("/schedule/list")
//    @ResponseBody
//    public List<Schedule> getSchedules(@RequestParam("dept") String dept) {
//        List<Schedule> list = new ArrayList<>();
//        if ("心血管内科".equals(dept)) {
//            list.add(new Schedule("张建军", "主任医师", "2026-10-11 上午", 8));
//            list.add(new Schedule("李秀梅", "副主任医师", "2026-10-11 下午", 15));
//        } else {
//            list.add(new Schedule("王医师", "主治医师", "2026-10-11 全天", 25));
//        }
//        return list;
//    }
//}
//
//@Controller
//class ReportController {
//
//    @GetMapping("/report/detail")
//    public String getReportDetail(@RequestParam("reportId") String reportId, Model model) {
//        if (reportId == null || !reportId.startsWith("R")) {
//            return "forward:/report/error";
//        }
//        TestReport report = new TestReport(reportId, "程锦福", "全血细胞分析(五分类)", "白细胞计数 6.5×10^9/L (正常)", "已审核");
//        model.addAttribute("report", report);
//        return "reportDetail";
//    }
//
//    @GetMapping("/report/error")
//    public String handleError(Model model) {
//        model.addAttribute("errorMsg", "【转发提示】报告单号格式不正确，请重新核对！");
//        return "errorPage";
//    }
//
//    @GetMapping("/report/invalidRedirect")
//    public String handleInvalidRedirect() {
//        return "redirect:/report/index?error=invalid_id";
//    }
//}
//
//@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.fufu.exp4")
//class WebMvcConfig implements WebMvcConfigurer {
//
//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
//}
//
//public class HISRequestAndViewController {
//    public static void main(String[] args) throws Exception {
//        ScheduleController scheduleController = new ScheduleController();
//        ReportController reportController = new ReportController();
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        System.out.println("====== 测试场景 1：调用 REST 接口返回排班 JSON 数据 ======");
//        List<Schedule> scheduleList = scheduleController.getSchedules("心血管内科");
//        String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(scheduleList);
//        System.out.println("HTTP Response (application/json):\n" + jsonOutput);
//
//        System.out.println("\n====== 测试场景 2：请求报告详情，视图解析器解析页面并填充 Model ======");
//        Model model1 = new ExtendedModelMap();
//        String viewName1 = reportController.getReportDetail("R2026101001", model1);
//        TestReport report = (TestReport) model1.asMap().get("report");
//        System.out.println("返回逻辑视图名: " + viewName1);
//        System.out.println("目标物理视图路径: /WEB-INF/jsp/" + viewName1 + ".jsp");
//        System.out.println("Model 渲染数据: 报告ID=" + report.getReportId() + ", 患者=" + report.getPatientName() + ", 项目=" + report.getItemName() + ", 状态=" + report.getStatus());
//
//        System.out.println("\n====== 测试场景 3：服务器内部请求转发 (Forward) 演示 ======");
//        Model model2 = new ExtendedModelMap();
//        String forwardView = reportController.getReportDetail("INVALID_NO", model2);
//        System.out.println("单号非法，内部转发: " + forwardView);
//        reportController.handleError(model2);
//        System.out.println("转发目标视图: /WEB-INF/jsp/errorPage.jsp, 共享Model提示: " + model2.asMap().get("errorMsg"));
//
//        System.out.println("\n====== 测试场景 4：客户端重定向 (Redirect) 演示 ======");
//        String redirectUrl = reportController.handleInvalidRedirect();
//        System.out.println("响应 302 客户端重定向指令: " + redirectUrl);
//    }
//}
//
//
