package uz.anas.homework.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.anas.homework.entity.Role;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}