package com.omalakhov.dao;

import com.omalakhov.dto.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordsRepository extends JpaRepository<Word, Long> {
	public List<Word> findByLangCode(String langCode);
}
