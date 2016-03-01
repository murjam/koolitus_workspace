package ee.itcollege.weblist.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ee.itcollege.weblist.annotation.LogUsage;
import ee.itcollege.weblist.dao.PersonDao;
import ee.itcollege.weblist.entity.Person;

@LogUsage("testing class message")
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDao personDao;
    
    @Override
    public List<Person> getPersons() {
        return personDao.getAll();
    }
    
    @Override
    @Transactional
    @LogUsage("test123")
    public Person save(Person p) {
        return personDao.persist(p);
    }

	@Override
	@Transactional
	public void delete(Person person) {
		personDao.delete(person);
	}
    
}
