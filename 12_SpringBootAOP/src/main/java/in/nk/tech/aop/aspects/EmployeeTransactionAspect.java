package in.nk.tech.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import in.nk.tech.aop.entity.Employee;

@Component
@Aspect
//@Order(1)
public class EmployeeTransactionAspect {

	@Pointcut("execution(public in.nk.tech.aop.entity.Employee in.nk.tech.aop.service.EmployeeService.saveEmployee(in.nk.tech.aop.entity.Employee))")
//	@Pointcut("execution(public * in.nk.tech.aop.service.EmployeeService.saveEmployee(..))")
	public void employeePointCut() {
	}

	@Before(value = "employeePointCut()")
	public void beforeSaveEmployee() {
		System.out.println("beginTransaction().....");

	}

	@After(value = "employeePointCut()")
	public void afterSaveEmployee() {
		System.out.println("commit() transaction.....");

	}

	@AfterReturning(value = "employeePointCut()", returning = "emp")
	public void afterReturningSaveEmployee(Employee emp) {
		System.out.println("After Success Execution: " + emp);

	}

	@AfterThrowing(value = "employeePointCut()", throwing = "ex")
	public void afterThrowingSaveEmployee(Throwable ex) {
		System.out.println("After Exception Occured: " + ex.getMessage());

	}

	@Around(value = "employeePointCut()")
	public Object aroundAdviceSaveEmployee(ProceedingJoinPoint jp) {
		System.out.println("Around Advice: Before...");
		Object proceed = null;
		try {
			 proceed = jp.proceed();
			System.out.println("Around Advice Result: " + proceed);
		} catch (Throwable e) {
			System.out.println("Around Advice Exception: " + e.getMessage());
		}
		System.out.println("Around Advice: After...");
		return proceed;
	}
	
}
