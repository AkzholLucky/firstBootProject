package kz.akzhol.libraryBootProject.services;

import kz.akzhol.libraryBootProject.model.Librarian;
import kz.akzhol.libraryBootProject.repositories.LibrarianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Component
public class LibrarianService {
    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LibrarianService(LibrarianRepository librarianRepository, PasswordEncoder passwordEncoder) {
        this.librarianRepository = librarianRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void save(Librarian librarian){
        librarian.setPassword(passwordEncoder.encode(librarian.getPassword()));
        librarian.setRole("ROLE_USER");
        librarianRepository.save(librarian);
    }
}
