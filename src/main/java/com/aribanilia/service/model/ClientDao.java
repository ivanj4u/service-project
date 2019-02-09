package com.aribanilia.service.model;

import com.aribanilia.service.entity.TblRestCA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ClientDao extends CrudRepository<TblRestCA, Long> {

    @Query("select ca from TblRestCA ca where ca.username = :username")
    TblRestCA findByUsername(@Param("username") String username);

}
