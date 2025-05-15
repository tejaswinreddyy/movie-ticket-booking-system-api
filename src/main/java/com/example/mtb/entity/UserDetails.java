package com.example.mtb.entity;

import com.example.mtb.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Setter
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(unique = true, name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "user_role")
    private UserRole userRole;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "deleted_at",  columnDefinition = "TIMESTAMP(6)")
    private Instant deletedAt;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false,  columnDefinition = "TIMESTAMP(6)")
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false,  columnDefinition = "TIMESTAMP(6)")
    private Instant updatedAt;

}
