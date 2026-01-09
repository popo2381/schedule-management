package com.popo2381.schedule.user.repository;

import com.popo2381.schedule.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
