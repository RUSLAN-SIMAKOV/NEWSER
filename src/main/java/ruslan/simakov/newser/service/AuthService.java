package ruslan.simakov.newser.service;

import ruslan.simakov.newser.dto.AuthenticationResponse;
import ruslan.simakov.newser.dto.LoginDto;
import ruslan.simakov.newser.dto.RefreshTokenRequest;
import ruslan.simakov.newser.dto.UserDto;
import ruslan.simakov.newser.model.User;

public interface AuthService {

    void signup(UserDto userDto);

    void verifyAccount(String token);

    AuthenticationResponse login(LoginDto loginDto);

    User getCurrentUser();

    AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
