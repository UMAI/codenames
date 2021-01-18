package com.omalakhov.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "WORDS")
@Data
public class Word {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WORDS_ID")
	@SequenceGenerator(name = "SEQ_WORDS_ID", sequenceName = "SEQ_WORDS_ID", allocationSize = 1)
	private Long id;

	@Column(name = "WORD")
	private String word;

	@Column(name = "LANGUAGE")
	private String langCode;
}
