package br.com.escorpion.task_master.adapters.web.controller;

import br.com.escorpion.task_master.config.security.jwt.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testLogin() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        UserDetails userDetails = User.withUsername(username)
                .password(password)
                .roles("USER")
                .build();
        String token = "testToken";
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .thenReturn(null); // No exception is thrown
        when(jwtService.generateToken(userDetails)).thenReturn(token);

        // Act
        ResponseEntity<String> response = authController.login(username, password);

        // Assert
        assertEquals(ResponseEntity.ok(token), response);
    }

    @Test
    public void testLogin_InvalidCredentials() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        when(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)))
                .thenThrow(new RuntimeException("Invalid credentials")); // Exception is thrown

        // Act and Assert
        try {
            authController.login(username, password);
        } catch (RuntimeException e) {
            assertEquals("Invalid credentials", e.getMessage());
        }
    }
}