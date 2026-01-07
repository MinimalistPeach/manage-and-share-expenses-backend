package hu.mase.mase_backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.mase.mase_backend.models.entity.User;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
