package com.campus.uclaplaybackservice.domain.repository;

import com.campus.uclaplaybackservice.domain.entity.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventLogRepository extends JpaRepository<EventLog, String> {
}