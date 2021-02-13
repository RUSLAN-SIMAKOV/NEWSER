package ruslan.simakov.newser.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ruslan.simakov.newser.dto.UserDto;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @PostMapping("/signup")
    public void signup(@RequestBody UserDto userDto){}
}
