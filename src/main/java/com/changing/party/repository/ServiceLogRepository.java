package com.changing.party.repository;

import com.changing.party.model.ServiceLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceLogRepository extends JpaRepository<ServiceLog, Integer> {
}