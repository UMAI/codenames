package com.omalakhov.dao;

import com.omalakhov.dto.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsDAO extends JpaRepository<Team, Long> {
}
