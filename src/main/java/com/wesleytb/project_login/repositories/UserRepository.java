package com.wesleytb.project_login.repositories;

import com.wesleytb.project_login.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
