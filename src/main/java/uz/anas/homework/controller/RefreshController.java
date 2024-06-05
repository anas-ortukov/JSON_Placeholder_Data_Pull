package uz.anas.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.anas.homework.config.CustomUserDetailsService;
import uz.anas.homework.config.JwtUtil;

@RestController
@RequestMapping("/api/refresh")
@RequiredArgsConstructor
public class RefreshController {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @GetMapping
    public String refresh() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
        return jwtUtil.genToken(userDetails);
    }

}
