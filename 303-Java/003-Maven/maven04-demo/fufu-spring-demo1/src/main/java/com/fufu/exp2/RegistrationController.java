package com.fufu.exp2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.Filter;

class RegistrationForm {
    private String patientName;
    private String idCard;
    private String department;
    private String regType;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRegType() {
        return regType;
    }

    public void setRegType(String regType) {
        this.regType = regType;
    }
}

@Controller
public class RegistrationController {

    @PostMapping("/registerByParam")
    public String registerByParam(
            @RequestParam("patientName") String patientName,
            @RequestParam("idCard") String idCard,
            @RequestParam("department") String department,
            @RequestParam("regType") String regType,
            Model model) {

        model.addAttribute("mode", "@RequestParam 绑定");
        model.addAttribute("patientName", patientName);
        model.addAttribute("idCard", idCard);
        model.addAttribute("department", department);
        model.addAttribute("regType", regType);
        model.addAttribute("status", "挂号预约成功");

        return "registrationResult";
    }

    @PostMapping("/registerByPojo")
    public String registerByPojo(RegistrationForm form, Model model) {
        model.addAttribute("mode", "POJO 对象自动绑定");
        model.addAttribute("patientName", form.getPatientName());
        model.addAttribute("idCard", form.getIdCard());
        model.addAttribute("department", form.getDepartment());
        model.addAttribute("regType", form.getRegType());
        model.addAttribute("status", "挂号预约成功");

        return "registrationResult";
    }

    public static void main(String[] args) {
        RegistrationController controller = new RegistrationController();

        System.out.println("====== 测试 1：@RequestParam 方式接收数据 ======");
        Model model1 = new ExtendedModelMap();
        String view1 = controller.registerByParam("李秀荣", "110101199003072345", "心血管内科", "专家门诊", model1);
        System.out.println("返回视图逻辑名: " + view1);
        System.out.println("Model 回显数据: " + model1.asMap());

        System.out.println("\n====== 测试 2：POJO 实体对象自动绑定方式接收数据 ======");
        RegistrationForm form = new RegistrationForm();
        form.setPatientName("王建国");
        form.setIdCard("310104198512143612");
        form.setDepartment("呼吸与危重症医学科");
        form.setRegType("普通门诊");

        Model model2 = new ExtendedModelMap();
        String view2 = controller.registerByPojo(form, model2);
        System.out.println("返回视图逻辑名: " + view2);
        System.out.println("Model 回显数据: " + model2.asMap());
    }
}

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.fufu.exp2")
class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        return new Filter[]{encodingFilter};
    }
}