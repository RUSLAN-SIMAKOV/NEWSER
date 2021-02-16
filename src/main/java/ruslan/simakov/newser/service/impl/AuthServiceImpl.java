package ruslan.simakov.newser.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ruslan.simakov.newser.dto.AuthenticationResponse;
import ruslan.simakov.newser.dto.LoginDto;
import ruslan.simakov.newser.dto.UserDto;
import ruslan.simakov.newser.exeption.SpringUserNotFoundException;
import ruslan.simakov.newser.exeption.SpringVerificationTokenException;
import ruslan.simakov.newser.model.NotificationEmail;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.model.VerificationToken;
import ruslan.simakov.newser.repository.UserRepository;
import ruslan.simakov.newser.repository.VerificationTokenRepository;
import ruslan.simakov.newser.security.JwtProvider;
import ruslan.simakov.newser.service.AuthService;
import ruslan.simakov.newser.service.MailService;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public void signup(UserDto userDto) {
        User user = saveUser(userDto);
        String token = generateVerificationToken(user);
        mailService.sendEmail(new NotificationEmail(userDto.getEmail(), token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    private User saveUser(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);
        return userRepository.save(user);
    }

    public void verifyAccount(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringVerificationTokenException("Can't verify user with token: " + token));
        enableUser(verificationToken);
    }

    @Transactional
    private void enableUser(VerificationToken verificationToken) {
        User userFromDb = userRepository.findByUserName(verificationToken.getUser().getUserName())
                .orElseThrow(() -> new SpringUserNotFoundException("Can't find user: " + verificationToken.getUser()));
        userFromDb.setEnabled(true);
        userRepository.save(userFromDb);
    }

    public AuthenticationResponse login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        return new AuthenticationResponse(token, loginDto.getUsername());
    }
}
