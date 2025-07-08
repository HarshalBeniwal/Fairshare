package com.harshal.beniwal.fairshare.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_gen")
    @SequenceGenerator(name = "user_seq_gen", sequenceName = "users_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false, length = 100, name = "user_name")
    @NotBlank(message = "Name is required")
    private String displayName;

    @Column(nullable = false, unique = true, length = 150)
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "Full name is required")
    private String fullName;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @JsonIgnore
    private String password;

    @ManyToMany(mappedBy = "users")
    @JsonManagedReference
    private List<UserGroup> groups;

    @OneToMany(mappedBy = "payer")
    private List<Expense> paidExpenses;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
    @Override
    public String getUsername() {
        return this.email;
    }
}

