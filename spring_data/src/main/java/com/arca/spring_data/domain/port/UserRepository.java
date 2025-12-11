package com.arca.spring_data.domain.port;
import com.arca.spring_data.domain.model.User;
import java.util.Optional;
public interface UserRepository {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    void deleteById(Long id);
}
