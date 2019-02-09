package com.aribanilia.service.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_customer")
public class TblCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String birthDate;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
