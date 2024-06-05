package uz.anas.homework.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.anas.homework.entity.Role;
import uz.anas.homework.entity.User;
import uz.anas.homework.entity.enums.RoleName;
import uz.anas.homework.repo.RoleRepository;
import uz.anas.homework.repo.UserRepository;

import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    public Runner(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (init.equals("create")) {
            Role admin = roleRepository.save(Role.builder()
                    .roleName(RoleName.ROLE_ADMIN)
                    .build());
            Role user = roleRepository.save(Role.builder()
                    .roleName(RoleName.ROLE_USER)
                    .build());
            userRepository.save(User.builder()
                    .age(20)
                    .username("admin")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(admin))
                    .build());
            userRepository.save(User.builder()
                    .age(25)
                    .username("user")
                    .password(passwordEncoder.encode("123"))
                    .roles(List.of(user))
                    .build());
        }
    }
}
