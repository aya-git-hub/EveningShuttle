package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Repository is a Spring concept that simplifies data access.
 * It is often used to interact with databases.
 * Spring Data JPA provides a powerful and convenient way to work with databases by automatically generating the necessary boilerplate code,
 * without SQL statements.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student,Long>, CrudRepository<Student, Long> {
    List<Student> findBySUID(long id);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Student s SET s.eta = NULL WHERE s.eta IS NOT NULL")
    void resetEtaValues();

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Student s SET s.eta = NULL WHERE s.SUID = :SUID")
    void dropHim(@Param("SUID") long SUID);
}
