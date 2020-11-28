package com.example.demo.entity;




import com.example.demo.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@Data

@Entity
@Table(name = "user", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId", unique = true, nullable = false)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String surName;


    @Email
    private String email;

    @NotNull
    private String password;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    Set<Card> cards;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    Set<Transaction> transactions;

    @Enumerated(EnumType.STRING)
    RoleName roleName;

    public RoleName getRoleName() {
        return roleName;
    }

    public void setRoleName(RoleName roleName) {
        this.roleName = roleName;
    }

    public User() {
    }

    public User(Integer id, String name, String surName, @Email String email, String password) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
    }

    public User(String name, String surName, @Email String email, String password) {
        this.name = name;
        this.surName = surName;
        this.email = email;
        this.password = password;
    }
}
