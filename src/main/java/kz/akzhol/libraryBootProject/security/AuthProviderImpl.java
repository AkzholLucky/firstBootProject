//package kz.akzhol.libraryBootProject.security;
//
//import kz.akzhol.libraryBootProject.services.LibrarianDetailsService;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//    //todo ЭТОТ КЛАСС БОЛЬШЕ НЕ ИСПОЛЬЗУЕТСЯ!!!
//    //todo РАНЬШЕ ИСПОЛЬЗОВАЛСЯ: SecurityConfig -> AuthProviderImpl -> LibrarianDetailsService
//    //todo СЕЙЧАС: SecurityConfig -> LibrarianDetailsService. Впрямую, остальное спринг берет на себя
//    private final LibrarianDetailsService librarianDetailsService;
//
//    public AuthProviderImpl(LibrarianDetailsService librarianDetailsService) {
//        this.librarianDetailsService = librarianDetailsService;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//
//        UserDetails userDetails = librarianDetailsService.loadUserByUsername(username);
//
//        String password = authentication.getCredentials().toString();
//
//        if (!password.equals(userDetails.getPassword())){
//            throw new BadCredentialsException("Incorrect password!!!");
//        }
//
//        return new UsernamePasswordAuthenticationToken(userDetails, password, Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return true;
//    }
//}
