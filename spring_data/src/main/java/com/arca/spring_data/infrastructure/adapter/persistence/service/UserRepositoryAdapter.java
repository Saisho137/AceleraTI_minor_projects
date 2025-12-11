package com.arca.spring_data.infrastructure.adapter.persistence.service;

import com.arca.spring_data.domain.model.User;
import com.arca.spring_data.domain.port.UserRepository;
import com.arca.spring_data.infrastructure.adapter.persistence.mapper.UserMapper;
import com.arca.spring_data.infrastructure.adapter.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRepositoryAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;
    private final UserMapper mapper;

    public UserRepositoryAdapter(UserJpaRepository jpaRepository, UserMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        return mapper.toDomain(jpaRepository.save(mapper.toEntity(user)));
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
