package com.les.graphqldemo.repository;

import com.les.graphqldemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
