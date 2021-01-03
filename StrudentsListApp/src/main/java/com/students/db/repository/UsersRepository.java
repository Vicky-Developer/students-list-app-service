package com.students.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.students.db.entities.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

	Users findByUserName(String name);
}
