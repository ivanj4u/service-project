package com.aribanilia.service.model;

import com.aribanilia.service.entity.TblCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerDao extends CrudRepository<TblCustomer, Long> {

    @Query("select cus from TblCustomer cus where cus.name like :name")
    List<TblCustomer> findByUsername(@Param("name") String name);

    @Query("select cus from TblCustomer cus where cus.name like :name and cus.birthDate = :birthDate")
    List<TblCustomer> findByUsernameAndBirthDate(@Param("name") String name, @Param("birthDate") String birthDate);

    @Override
    List<TblCustomer> findAll();
}
