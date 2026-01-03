package hu.mase.mase_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.mase.mase_backend.models.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
