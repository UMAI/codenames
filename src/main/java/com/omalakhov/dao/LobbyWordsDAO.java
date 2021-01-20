package com.omalakhov.dao;

import com.omalakhov.dto.LobbyWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LobbyWordsDAO extends JpaRepository<LobbyWord, Long> {
}
