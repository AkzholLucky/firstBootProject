package kz.akzhol.libraryBootProject.security;

import kz.akzhol.libraryBootProject.model.Librarian;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter
public class LibrarianDetails implements UserDetails {

    private final Librarian librarian;

    @Autowired
    public LibrarianDetails(Librarian librarian) {
        this.librarian = librarian;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(librarian.getRole()));
    }

    @Override
    public String getPassword() {
        return this.librarian.getPassword();
    }

    @Override
    public String getUsername() {
        return this.librarian.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
