package kz.akzhol.libraryBootProject.services;

import kz.akzhol.libraryBootProject.model.Librarian;
import kz.akzhol.libraryBootProject.repositories.LibrarianRepository;
import kz.akzhol.libraryBootProject.security.LibrarianDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Component
public class LibrarianDetailsService implements UserDetailsService {
    private final LibrarianRepository librarianRepository;

    @Autowired
    public LibrarianDetailsService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Librarian> librarian = librarianRepository.findByUsername(username);

        if (librarian.isEmpty()){
            throw new UsernameNotFoundException("Username not found");
        }

        return new LibrarianDetails(librarian.get());
    }
}
