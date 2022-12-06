package kz.akzhol.libraryBootProject.services;

import kz.akzhol.libraryBootProject.model.Book;
import kz.akzhol.libraryBootProject.model.Person;
import kz.akzhol.libraryBootProject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> indexBooks(){
        return bookRepository.findAll();
    }

    public Book findBookById(int id){
        return bookRepository.getReferenceById(id);
    }

    @Transactional
    public void saveBook(Book book){
        bookRepository.save(book);
    }

    @Transactional
    public void updateBook(Book book, int id){
        book.setId(id);
        bookRepository.save(book);
    }

    @Transactional
    public void deleteBook(int id){
        bookRepository.deleteById(id);
    }

    @Transactional
    public void setBook(int id, Person person){
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setTakenAt(new Date());
                    book.setPerson(person);
                }
        );
    }
}
