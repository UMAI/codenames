package com.omalakhov.dao;

import com.omalakhov.dto.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersDAO extends JpaRepository<Player, Long> {
}
