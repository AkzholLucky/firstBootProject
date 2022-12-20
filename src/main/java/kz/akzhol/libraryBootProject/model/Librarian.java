package kz.akzhol.libraryBootProject.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "Librarian")
@Getter
@Setter
@NoArgsConstructor
@ToString
@Component
public class Librarian {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public Librarian(String username, int yearOfBirth, String role, String password) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.role = role;
        this.password = password;
    }
}
