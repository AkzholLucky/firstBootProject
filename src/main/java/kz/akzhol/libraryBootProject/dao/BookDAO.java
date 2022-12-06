package kz.akzhol.libraryBootProject.dao;

import kz.akzhol.libraryBootProject.model.Book;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findBookByPersonId(int personId){
        return jdbcTemplate.query("SELECT * FROM books WHERE person_id=?", new Object[]{personId}, new BeanPropertyRowMapper<>(Book.class));
    }

    public void setPersonId(int id, int personId){
        jdbcTemplate.update("UPDATE books SET person_id=? WHERE id=?", personId, id);
    }

    public void releasePersonId(int id){
        jdbcTemplate.update("UPDATE books SET person_id=? WHERE id=?", null, id);
    }
}
