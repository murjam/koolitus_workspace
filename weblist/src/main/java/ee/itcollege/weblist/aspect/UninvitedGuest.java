package ee.itcollege.weblist.aspect;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import ee.itcollege.weblist.entity.Person;

@Aspect
@Service
public class UninvitedGuest {

	@Around("execution(* *.getPersons())")
	public Object addUninvitedGuest(ProceedingJoinPoint jp) throws Throwable {
		Object result = jp.proceed();
		
		List<Person> persons = (List<Person>) result;
		
		if (Math.random() > .7) {
			
			Person mati = new Person();
			mati.setName("Mati");
			mati.setId(-5);
			persons.add(mati);
			
		}

		return persons;
	}
	
}
