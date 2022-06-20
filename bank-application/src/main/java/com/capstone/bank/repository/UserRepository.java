package com.capstone.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capstone.bank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
