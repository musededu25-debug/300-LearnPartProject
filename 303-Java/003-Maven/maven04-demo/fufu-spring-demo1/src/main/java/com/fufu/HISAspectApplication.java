package com.fufu;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;

interface HisTradeService {
    String pay(String operator, String patientName, double amount);
    String refund(String operator, String registrationId);
}

@Service
class HisTradeServiceImpl implements HisTradeService {
    @Override
    public String pay(String operator, String patientName, double amount) {
        return String.format("【缴费成功】患者：%s，缴费金额：%.2f 元，收费员：%s", patientName, amount, operator);
    }

    @Override
    public String refund(String operator, String registrationId) {
        return String.format("【退号成功】挂号单号：%s，审核员：%s", registrationId, operator);
    }
}

@Aspect
@Component
class HisSecurityAndLogAspect {

    @Pointcut("execution(* com.fufu.HisTradeService.*(..))")
    public void tradePointcut() {
    }

    @Before("tradePointcut()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("[日志系统-前置留痕] 正在调用方法: " + methodName + "，传入参数: " + Arrays.toString(args));
    }

    @Around("tradePointcut()")
    public Object checkPermissionAndLog(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        String operator = (args != null && args.length > 0) ? String.valueOf(args[0]) : "unknown";

        if ("intern".equalsIgnoreCase(operator) || "guest".equalsIgnoreCase(operator)) {
            System.out.println("[安全控制-权限拦截] 操作员 [" + operator + "] 无权执行业务: " + pjp.getSignature().getName());
            return "【操作失败】权限不足，拒绝执行高危操作！";
        }

        System.out.println("[安全控制-校验通过] 操作员: " + operator);
        Object result = pjp.proceed();
        System.out.println("[日志系统-后置留痕] 业务执行完毕，返回结果: " + result);
        return result;
    }
}

@Configuration
@ComponentScan(basePackages = "com.fufu")
@EnableAspectJAutoProxy
class SpringConfig {
}

public class HISAspectApplication {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        HisTradeService tradeService = context.getBean(HisTradeService.class);

        System.out.println("====== 测试场景 1：合法收费员执行缴费业务 ======");
        String payResult = tradeService.pay("cashier01", "张三", 158.00);
        System.out.println("调用返回 -> " + payResult + "\n");

        System.out.println("====== 测试场景 2：未授权操作员（实习生）执行退号业务 ======");
        String refundResult = tradeService.refund("intern", "REG202609300001");
        System.out.println("调用返回 -> " + refundResult + "\n");

        System.out.println("====== 测试场景 3：管理员执行退号业务 ======");
        String adminRefundResult = tradeService.refund("admin", "REG202609300001");
        System.out.println("调用返回 -> " + adminRefundResult);
    }
}