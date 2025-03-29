package br.com.escorpion.task_master.config.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final List<UserDetails> users = List.of(
            User.withUsername("admin")
                    .password(passwordEncoder.encode("1234")) // Senha criptografada
                    .roles("ADMIN")
                    .build(),
            User.withUsername("user")
                    .password(passwordEncoder.encode("1234")) // Senha criptografada
                    .roles("USER")
                    .build()
    );

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + username));
    }
}
