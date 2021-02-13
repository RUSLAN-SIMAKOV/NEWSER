package ruslan.simakov.newser.service;

import lombok.AllArgsConstructor;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ruslan.simakov.newser.dto.UserDto;
import ruslan.simakov.newser.model.User;
import ruslan.simakov.newser.model.VerificationToken;
import ruslan.simakov.newser.repository.UserRepository;
import ruslan.simakov.newser.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public User signup(UserDto userDto){
        User user = new User();
        user.setUserName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setCreatedDate(Instant.now());
        user.setEnabled(false);

        String token = generateVerificationToken(user);
        return userRepository.save(user);
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }
}
