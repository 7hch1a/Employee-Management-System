/*
 * package com.ems.demo.auditLog;
 * 
 * import java.io.IOException;
 * 
 * import org.aspectj.lang.ProceedingJoinPoint; import
 * org.aspectj.lang.annotation.Around; import
 * org.aspectj.lang.annotation.Aspect; import
 * org.aspectj.lang.annotation.Pointcut; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.EnableAspectJAutoProxy; import
 * org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
 * import org.springframework.stereotype.Component;
 * 
 * import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility; import
 * com.fasterxml.jackson.annotation.JsonIgnoreProperties; import
 * com.fasterxml.jackson.annotation.PropertyAccessor; import
 * com.fasterxml.jackson.databind.ObjectMapper;
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory;
 * 
 * @Aspect
 * 
 * @Component
 * 
 * @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
 * 
 * @EnableAspectJAutoProxy public class AuditLogging {
 * 
 * Logger log = LoggerFactory.getLogger(AuditLogging.class);
 * 
 * 
 * @Pointcut("execution(* com.ems.demo.services.DepartmentsRepository.*(..))")
 * public void myPointcut() {
 * 
 * }
 * 
 * @Around("myPointcut()") public Object applicationLogger(ProceedingJoinPoint
 * pjp) throws Throwable {
 * 
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY); String
 * methodName = pjp.getSignature().getName(); String className =
 * pjp.getTarget().getClass().toString(); Object[] array = pjp.getArgs();
 * log.info("Method invoke " + className + " : " + methodName + "()" +
 * "arguments : " + mapper.writeValueAsString(array)); Object object =
 * pjp.proceed();
 * 
 * log.info(className + " : " + methodName + "()" + "Response : " +
 * mapper.writeValueAsString(object));
 * 
 * return object; }
 * 
 * }
 */