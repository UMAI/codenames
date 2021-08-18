package com.omalakhov.dao;

import com.omalakhov.dto.Word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WordsRepository extends JpaRepository<Word, Long> {
	List<Word> findByLangCodeAndDifficulty(String langCode, Integer difficulty);
}
