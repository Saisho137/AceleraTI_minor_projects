package com.arca.spring_data.infrastructure.adapter.persistence.model;

import com.arca.spring_data.domain.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String name;
    String email;

    @Enumerated(EnumType.STRING)
    Role role;
}
