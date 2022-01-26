package com.lohika.demoGradleAppJava17.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyUserRole {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "rolesIdSeq", sequenceName = "roles_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rolesIdSeq")
    private Long id;

    @Column(name = "name")
    private String name;
}