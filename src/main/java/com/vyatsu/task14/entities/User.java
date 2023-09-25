package com.vyatsu.task14.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id", nullable = false)
    private Long id;

    @Getter
    @Setter
    @Column(name = "username", nullable = false)
    private String username;

    @Getter
    @Setter
    @Column(name = "password", nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Getter
    @Setter
    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "is_admin")
    @Getter
    @Setter
    private boolean is_admin;

}

