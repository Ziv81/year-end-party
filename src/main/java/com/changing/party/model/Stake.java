package com.changing.party.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "stack")
@Getter
@Setter
@RequiredArgsConstructor
public class Stake {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "title", length = 32)
    private String title;
}