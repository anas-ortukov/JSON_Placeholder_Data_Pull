package uz.anas.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.anas.homework.config.JwtUtil;
import uz.anas.homework.dto.LoginDto;
import uz.anas.homework.dto.TokenDto;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDto loginDto) {
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.password()));
        return new TokenDto(
                jwtUtil.genToken((UserDetails)auth.getPrincipal()),
                jwtUtil.genRefreshToken((UserDetails)auth.getPrincipal()));
    }

}
