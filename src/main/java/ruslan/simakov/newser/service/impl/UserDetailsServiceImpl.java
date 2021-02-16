package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.simakov.newser.exeption.SpringUserNotFoundException;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userFromDb = userRepository.findByUserName(username)
                .orElseThrow(() -> new SpringUserNotFoundException("Can't find user: " + username));
        return new org.springframework.security.core.userdetails.User(
                userFromDb.getUserName(),
                userFromDb.getPassword(),
                userFromDb.isEnabled(),
                true,
                true,
                true,
                getAuthorities("USER"));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }
}
