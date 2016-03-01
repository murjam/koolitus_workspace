package ee.itcollege.weblist.aspect;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Service;

@Aspect
@Service
public class DatabaseLogging {
	
	private static final int MAX_TIME = 100;
	private static final Logger LOG = LogManager.getLogger("databaseLogger");
	
	@Pointcut("within(ee.itcollege.weblist.dao.*)")
	public void dao() {}
	
	@Pointcut("execution(* *.get*())")
	public void getter() {}

	@Around("dao()")
	public Object loggingQueryTime(ProceedingJoinPoint jp) throws Throwable {

		long time = System.currentTimeMillis();
		
		Object result = jp.proceed();
		
		time = System.currentTimeMillis() - time;

		if (time > MAX_TIME) {
			String msg = String.format("Slow query: %s.%s took %dms",
					jp.getTarget().getClass().getSimpleName(),
					jp.getSignature().getName(),
					time);
			
			System.out.println(msg);
			LOG.warn(msg);	
		}

		return result;
	}

}
