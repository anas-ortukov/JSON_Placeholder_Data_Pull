package uz.anas.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.homework.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

  User findByUsername(String username);

}