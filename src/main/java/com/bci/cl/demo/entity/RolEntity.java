package com.bci.cl.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "rol", schema = "public")
public class RolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rol_id;

    @Column(name="rol")
    private String name;

    @Column(name = "last_login")
    @CreationTimestamp
    private LocalDateTime lastLogin;

    @Column(name = "is_active")
    private Boolean isActive;

}
