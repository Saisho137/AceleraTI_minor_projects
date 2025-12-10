package com.arca.spring_data.infrastructure.adapter.persistence.mapper;

import com.arca.spring_data.domain.model.User;
import com.arca.spring_data.infrastructure.adapter.persistence.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity entity) {
        if (entity == null) {
            return null;
        }

        User user = new User();
        user.setId(entity.getId());
        user.setName(entity.getName());
        user.setEmail(entity.getEmail());
        user.setRole(entity.getRole());

        return user;
    }

    public UserEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }

        UserEntity entity = new UserEntity();
        entity.setId(domain.getId());
        entity.setName(domain.getName());
        entity.setEmail(domain.getEmail());
        entity.setRole(domain.getRole());

        return entity;
    }
}

