package com.omalakhov.dao;

import com.omalakhov.dto.Lobby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobbiesDAO extends JpaRepository<Lobby, Long> {
	Lobby findFirstByJoinCode(String joinCode);
}
