package ee.itcollege.weblist.dao;

import ee.itcollege.weblist.entity.Person;

public class PersonDaoImpl extends AbstractDaoImpl<Person> implements PersonDao {

    public PersonDaoImpl() {
        super(Person.class);
    }
    
}
