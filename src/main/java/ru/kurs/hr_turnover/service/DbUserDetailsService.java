package ru.kurs.hr_turnover.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kurs.hr_turnover.model.User;
import ru.kurs.hr_turnover.repo.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DbUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        String dbRole = u.getRole();

        String springRole = "ROLE_" + dbRole;

        boolean enabled = u.getEnabled() != null && u.getEnabled() == 1;
        boolean active = u.getActive() != null && u.getActive() == 1;

        return org.springframework.security.core.userdetails.User.builder()
                .username(u.getUsername())
                .password(u.getPasswordHash())
                .authorities(List.of(new SimpleGrantedAuthority(springRole)))
                .accountLocked(!enabled)
                .disabled(!active)
                .build();
    }
}
