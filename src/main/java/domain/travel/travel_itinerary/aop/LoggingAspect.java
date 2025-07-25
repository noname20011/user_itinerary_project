package domain.travel.travel_itinerary.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LoggingAspect {

    @Around("execution(* domain.travel.travel_itinerary.service..*(..))")
    public Object logServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        Get ClassName, Method Executing
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        //        Get Parameter
        Object[] args = joinPoint.getArgs();

        log.info("➡️  [START]{}.{} with args: {}", className, methodName, Arrays.toString(args));

        long startTime = System.currentTimeMillis();
        try {
            Object proceed = joinPoint.proceed();
            long duration = System.currentTimeMillis() - startTime;

            log.info("✅  [END]{}.{} with args: {}({} ms)", className, methodName, args, duration);
            return proceed;

        } catch (Throwable ex) {
            long duration = System.currentTimeMillis() - startTime;

            log.error("❌ [EXCEPTION] {}.{} threw: {} ({} ms)", className, methodName, ex.getMessage(), duration);
            throw ex;
        }
    }
}
