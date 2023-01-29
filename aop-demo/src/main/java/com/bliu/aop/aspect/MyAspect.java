package com.bliu.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * <ul>
 *     <li>连接点（Joinpoint） ：程序能够应用通知的一个“时机”，这些“时机”就是连接点，例如方法被调用时、异常被抛出时等等。——可以理解为被aop拦截的类或者方法就是连接点。</li>
 *     <li>通知（Advice） ：通知定义了切面是什么以及何时使用。描述了切面要完成的工作和何时需要执行这个工作。——可以理解为被注解有@Before等advice注解的安全校验的方法，拦截了过来的请求要做什么逻辑的校验。</li>
 *     <li>切入点（Pointcut） ：通知定义了切面要发生的“故事”和时间，那么切入点就定义了“故事”发生的地点，例如某个类或方法的名称。——可以理解为切面切向哪里？是个类或者某层的包路径。</li>
 *     <li>目标对象（Target Object） ：即被通知的对象。</li>
 *     <li>AOP代理（AOP Proxy） 在Spring AOP中有两种代理方式，JDK动态代理和CGLIB代理。默认情况下，TargetObject实现了接口时，则采用JDK动态代理；反之，采用CGLIB代理。</li>
 *     <li>织入（Weaving）把切面应用到目标对象来创建新的代理对象的过程</li>
 * </ul>
 */
@Component
@Aspect
public class MyAspect {
    // 第一个*代表任意返回值 com.bliu.aop.service 包及子包下所有方法 (..) 任意方法参数
    @Pointcut("execution(* com.bliu.aop.service.*.*(..))")
    public void logPointcut() {}
    // com.bliu.aop.service 包及子包下所有方法
    @Pointcut("within(com.bliu.aop.service..*)")
    public void logPointcut1() {}
    // 包含NeedLog注解的方法
    @Pointcut("@annotation(com.bliu.aop.NeedLog)")
    public void logPointcut2() {}
    // @target 执行对象 @within 声明类 是匹配类上包含注解
    @Pointcut("@target(com.bliu.aop.AllNeedLog)")
    public void logPointcut3() {}

    @Before("logPointcut3()")
    public void logAllMethodCallsAdvice(JoinPoint joinPoint){
        System.out.println("In Aspect Before," +
                " Type:" + joinPoint.getSignature().getDeclaringTypeName() +
                ", name:" + joinPoint.getSignature().getName() +
                ", Kind:" + joinPoint.getKind() +
                ", args: " + Arrays.toString(joinPoint.getArgs()));
    }

//    @After("logPointcut()")
//    public void afterLogAllMethodCallsAdvice(JoinPoint joinPoint){
//        System.out.println("In Aspect after," +
//                " Type:" + joinPoint.getSignature().getDeclaringTypeName() +
//                ", name:" + joinPoint.getSignature().getName() +
//                ", Kind:" + joinPoint.getKind() +
//                ", args: " + Arrays.toString(joinPoint.getArgs()));
//    }
//
//    @AfterReturning(pointcut = "logPointcut()", returning = "sum")
//    public void afterReturningLogAllMethodCallsAdvice(JoinPoint joinPoint, long sum){
//        System.out.println("In Aspect after returning," +
//                " Type:" + joinPoint.getSignature().getDeclaringTypeName() +
//                ", name:" + joinPoint.getSignature().getName() +
//                ", Kind:" + joinPoint.getKind() +
//                ", args: " + Arrays.toString(joinPoint.getArgs()) +
//                ", return value: " + sum);
//    }
//
//    @Around("logPointcut()")
//    public Object aroundLogAllMethodCallsAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println("In Aspect Around, before" +
//                " Type:" + joinPoint.getSignature().getDeclaringTypeName() +
//                ", name:" + joinPoint.getSignature().getName() +
//                ", Kind:" + joinPoint.getKind() +
//                ", args: " + Arrays.toString(joinPoint.getArgs()));
//
////        Object result = joinPoint.proceed(new Object[] {2,2}); // 在这修改参数
//        Object result = joinPoint.proceed();
//
//        System.out.println("In Aspect Around, after" +
//                " Type:" + joinPoint.getSignature().getDeclaringTypeName() +
//                ", name:" + joinPoint.getSignature().getName() +
//                ", Kind:" + joinPoint.getKind() +
//                ", result:" + result +
//                ", args: " + Arrays.toString(joinPoint.getArgs()));
//        return result;
//    }
//
//    @AfterThrowing(pointcut = "logPointcut()", throwing = "ex")
//    public void afterThrowingLogAllMethodCallsAdvice(JoinPoint joinPoint, Throwable ex){
//
//        // 并不相当于捕获异常， 异常依然抛出， 只是在抛出异常时做一些事情
//        System.out.println("In Aspect AfterThrowing," +
//                " Type:" + joinPoint.getSignature().getDeclaringTypeName() +
//                ", name:" + joinPoint.getSignature().getName() +
//                ", Kind:" + joinPoint.getKind() +
//                ", args: " + Arrays.toString(joinPoint.getArgs()) +
//                ", ex: " + ex.getLocalizedMessage());

//    }
}
