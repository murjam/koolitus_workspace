package ee.itcollege.weblist.aspect;

import java.lang.annotation.Annotation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.ClassAnnotationValue;
import org.springframework.stereotype.Service;

import ee.itcollege.weblist.annotation.LogUsage;

@Aspect
@Service
public class UsageLogging {

	private static final Logger LOG = LogManager.getLogger(UsageLogging.class);
	
	@Pointcut("@annotation(ee.itcollege.weblist.annotation.LogUsage)")
	public void logged() {}

	@Before("logged()")
	public void logUsage(JoinPoint jp) {
	    LOG.info("the method was run: " + jp.getSignature());
	    
	    Annotation[] classAnnotations = jp.getTarget().getClass().getAnnotationsByType(LogUsage.class);
	    if (classAnnotations.length > 0) {
	    	LogUsage first = (LogUsage) classAnnotations[0];
	    	LOG.info("LogUsage annotation message: " + first.value());
	    }
	    
	    
	}
	
}
