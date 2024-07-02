package com.campus.uclauserservice.domain.user.repository;

import com.campus.uclauserservice.domain.user.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLoginHistoryRepository extends JpaRepository<UserLoginHistory, Integer> {
}

