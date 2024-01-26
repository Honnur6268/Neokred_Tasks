package in.nk.tech.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.nk.tech.aop.entity.Employee;
import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
//@Order(2)
public class EmployeeLoggingAspect /* implements Ordered */ {

	@Autowired
	private ObjectMapper objectMapper;

//	@Pointcut("execution(* in.nk.tech.aop.*.*.*(..))")
	@Pointcut("execution(* in.nk.tech.aop.service.EmployeeService.saveEmployee(..))")
	public void employeeLoggingPointCut() {
	}
	
//	@Before("within(in.nk.tech.aop.service.*)")
	@Before("within(in.nk.tech.aop.service..*)")
	public void before() {
		log.info("Before Advice Using within()...............");
	}
	
	@Before("this(in.nk.tech.aop.service.EmployeeService)")
	public void before2() {
		log.info("Before Advice Using this()...............");
	}
	
	@After("target(in.nk.tech.aop.service.EmployeeService)")
	public void after() {
		log.info("After Advice Using target()...............");
	}


	@Around("employeeLoggingPointCut()")
	public void employeeLoggingAdvice(ProceedingJoinPoint jp) {
		String methodName = jp.getSignature().getName();
		String className = jp.getTarget().getClass().toString();
		Object[] args = jp.getArgs();
		log.info("Method Invoked from - {}.{} - with args {}", className, methodName, args);

		try {
			Employee result = (Employee) jp.proceed();
			log.info("Result From - {}.{} - Response {}", className, methodName,
					objectMapper.writeValueAsString(result));
		} catch (Throwable e) {
			e.printStackTrace();
		}

		log.info("Completed");

	}
	
	@Around("@annotation(in.nk.tech.aop.annotations.TrackExecutionTime)")
	public Object executionTimeTracker(ProceedingJoinPoint jp) throws Throwable {
		log.info("{} started execution...",jp.getSignature());
		long startTime = System.currentTimeMillis();
		Object obj = jp.proceed();
		long endTime = System.currentTimeMillis();
		log.info("{} time taken to execute : {}ms",jp.getSignature(), (endTime - startTime));
		return obj;
	}
	
	//Based on condition
//	@Around("execution(* in.nk.tech.aop.service.EmployeeService.getAllEmployees()) || execution(* in.nk.tech.aop.restcontroller.EmployeeRestController.getEmployeeById(..)) || @annotation(in.nk.tech.aop.annotations.TrackExecutionTime)")
//	public Object executionTimeTrackerForController(ProceedingJoinPoint jp) throws Throwable {
//		log.info("{} started execution...",jp.getSignature());
//		long startTime = System.currentTimeMillis();
//		Object obj = jp.proceed();
//		long endTime = System.currentTimeMillis();
//		log.info("{} time taken to execute : {}ms",jp.getSignature(), (endTime - startTime));
//		return obj;
//	}


//	@Override
//	public int getOrder() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
}
