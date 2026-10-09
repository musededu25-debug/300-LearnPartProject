//package com.fufu;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//interface PatientService {
//    boolean checkPatient(String patientName);
//}
//
//@Service
//class PatientServiceImpl implements PatientService {
//    @Override
//    public boolean checkPatient(String patientName) {
//        return patientName != null && !patientName.trim().isEmpty();
//    }
//}
//
//interface ScheduleService {
//    String checkDoctorSchedule(String department);
//}
//
//@Service
//class ScheduleServiceImpl implements ScheduleService {
//    @Override
//    public String checkDoctorSchedule(String department) {
//        if ("心血管内科".equals(department)) {
//            return "张主任医师（门诊1诊室）";
//        } else if ("神经内科".equals(department)) {
//            return "李副主任医师（门诊3诊室）";
//        }
//        return "普通轮班医师（全科诊室）";
//    }
//}
//
//interface RegistrationService {
//    String register(String patientName, String department);
//}
//
//@Service
//class RegistrationServiceImpl implements RegistrationService {
//
//    @Autowired
//    private PatientService patientService;
//
//    @Autowired
//    private ScheduleService scheduleService;
//
//    @Override
//    public String register(String patientName, String department) {
//        if (!patientService.checkPatient(patientName)) {
//            return "【挂号失败】患者信息校验未通过！";
//        }
//        String doctor = scheduleService.checkDoctorSchedule(department);
//        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        return String.format("【挂号成功】\n患者姓名：%s\n就诊科室：%s\n接诊医生：%s\n挂号时间：%s",
//                patientName, department, doctor, time);
//    }
//}
//
//@Configuration
//@ComponentScan(basePackages = "com.fufu")
//class SpringConfig {
//}
//
//public class HISApplication {
//    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
//        RegistrationService registrationService = context.getBean(RegistrationService.class);
//
//        String result = registrationService.register("王小明", "心血管内科");
//        System.out.println(result);
//    }
//}