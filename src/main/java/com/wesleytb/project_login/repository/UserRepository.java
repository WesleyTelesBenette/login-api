package com.wesleytb.project_login.repository;

import com.wesleytb.project_login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
