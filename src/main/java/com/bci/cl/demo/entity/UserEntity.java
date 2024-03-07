package com.bci.cl.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "users", schema = "public")
public class UserEntity {

    @Id
    @GeneratedValue(generator  = "uuid")
    @Column(name = "user_id", columnDefinition = "uuid", length = 32)
    private UUID id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password",  length = 100)
    private String password;

    @Column(name = "token",  length = 200)
    private String token;

    @Column(name = "created_at" , nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false, updatable = true)
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    @Column(name = "last_login", nullable = false, updatable = true)
    @UpdateTimestamp
    private LocalDateTime lastLogin;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private RolEntity rol;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhoneEntity> phones;

    public static UserEntity buildUserEntity(UserEntity user){
        UserEntity entity = UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .rol(user.getRol())
                .token(user.getToken())
                .phones(new ArrayList<>())
                .build();
        user.getPhones().forEach(t -> {
            PhoneEntity phone = PhoneEntity.builder()
                    .number(t.getNumber())
                    .citycode(t.getCitycode())
                    .countrycode(t.getCountrycode())
                    .user(entity)
                    .build();
            entity.getPhones().add(phone);});
            return entity;
    }

    public static UserEntity buildUserEntityfromDto(UserEntity user){
        UserEntity entity = UserEntity.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .token(user.getToken())
                .phones(new ArrayList<>())
                .rol(user.getRol())
                .build();
        user.getPhones().forEach(t -> {
            PhoneEntity phone = PhoneEntity.builder()
                    .number(t.getNumber())
                    .citycode(t.getCitycode())
                    .countrycode(t.getCountrycode())
                    .user(entity)
                    .build();
            entity.getPhones().add(phone);});
        return entity;
    }

}
