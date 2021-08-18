package com.omalakhov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "WORDS")
public class Word {
	public interface Difficulty {
		Integer EASY = 1;
		Integer NORMAL = 2;
		Integer HARD = 3;
	}

	@Column(name = "WOR_ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WOR_ID")
	@SequenceGenerator(name = "SEQ_WOR_ID", sequenceName = "SEQ_WOR_ID", allocationSize = 1)
	private Long id;

	@Column(name = "WOR_WORD")
	private String word;

	@Column(name = "WOR_LANG_CODE")
	private String langCode;

	@Column(name = "WOR_DIFFICULTY")
	private Integer difficulty;

	public Word(String word, String langCode, Integer difficulty) {
		this.word = word;
		this.langCode = langCode;
		this.difficulty = difficulty;
	}
	public Word(String word, String langCode) {
		this(word, langCode, Difficulty.NORMAL);
	}

	public Word(String word) {
		this(word, "en");
	}
}
