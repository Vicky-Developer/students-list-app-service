package com.students.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.students.db.entities.Students;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long>{

}
