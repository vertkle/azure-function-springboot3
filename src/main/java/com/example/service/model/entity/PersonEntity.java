package com.example.service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "tb_person")
@Builder
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PersonEntity {

    @Id
    @Column(name = "id_person", nullable = false, length = 36, unique = true)
    private String idPerson;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "first_name", nullable = false, length = 80)
    private String firstName;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

}
