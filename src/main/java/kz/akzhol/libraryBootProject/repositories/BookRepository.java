package kz.akzhol.libraryBootProject.repositories;

import kz.akzhol.libraryBootProject.model.Book;
import kz.akzhol.libraryBootProject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    void getBooksByPerson(Person person);
}
