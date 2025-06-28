package com.harshal.beniwal.fairshare.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Double amount;

    @Enumerated(EnumType.STRING)
    private SPLITMODE split_mode;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UserGroup group;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToMany
    @JoinTable(
            name="expense_participants",
            joinColumns=@JoinColumn(name="expense_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    List<User> participants;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<ExpenseSplit> splits;
}

