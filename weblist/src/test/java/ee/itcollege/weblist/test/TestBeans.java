package ee.itcollege.weblist.test;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ee.itcollege.weblist.dao.PersonDao;
import ee.itcollege.weblist.entity.Person;
import ee.itcollege.weblist.service.PersonService;
import ee.itcollege.weblist.service.PersonServiceImpl;

@Configuration
public class TestBeans {
	
	private List<Person> persons = new ArrayList<Person>();
    
    @Bean
    public PersonDao personDao() {
        
        Person person = new Person();
        person.setName("test-person");
        persons.add(person);
        
        person = new Person();
        person.setName("Mati");
        persons.add(person);
        
        PersonDao dao = Mockito.mock(PersonDao.class);
        
        Mockito.when(dao.getAll()).thenReturn(persons);
        Mockito.when(dao.persist(Mockito.any(Person.class))).then(new Answer<Person>() {
			@Override
			public Person answer(InvocationOnMock invocation) throws Throwable {
				Person argument = (Person)invocation.getArguments()[0];
				persons.add(argument);
				return argument;
			}
        });
        
        return dao;
    }
    
    @Bean
    public PersonService personService() {
        return new PersonServiceImpl();
    }
    
}
