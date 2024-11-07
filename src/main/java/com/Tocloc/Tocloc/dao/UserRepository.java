package com.Tocloc.Tocloc.dao;

import com.Tocloc.Tocloc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
