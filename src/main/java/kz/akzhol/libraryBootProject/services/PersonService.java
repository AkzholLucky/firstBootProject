package kz.akzhol.libraryBootProject.services;

import kz.akzhol.libraryBootProject.model.Person;
import kz.akzhol.libraryBootProject.repositories.BookRepository;
import kz.akzhol.libraryBootProject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonService {
    private final PersonRepository personRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, BookRepository bookRepository) {
        this.personRepository = personRepository;
        this.bookRepository = bookRepository;
    }

    public List<Person> getAllPerson(){
        return personRepository.findAll();
    }

    public Person getOnePerson(int id){
        return personRepository.getReferenceById(id);
    }

    @Transactional
    public void savePerson(Person person){
        personRepository.save(person);
    }

    @Transactional
    public void deletePerson(int id){
        personRepository.deleteById(id);
    }

    @Transactional
    public void updatePerson(int id, Person person){
        person.setId(id);
        personRepository.save(person);
    }

    public Person findPerson(int id){
        int personId;
        Person person = null;
        if (bookRepository.findById(id).get().getPerson() != null){
            personId = bookRepository.findById(id).get().getPerson().getId();
            person = personRepository.findById(personId).stream().findAny().orElse(null);
        }
        return person;
    }
}
