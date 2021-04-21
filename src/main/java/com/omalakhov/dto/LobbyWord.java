package com.omalakhov.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "LOBBY_WORDS")
@Getter
@Setter
@NoArgsConstructor
public class LobbyWord {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOBBY_WORDS_ID")
	@SequenceGenerator(name = "SEQ_LOBBY_WORDS_ID", sequenceName = "SEQ_LOBBY_WORDS_ID", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_LOBBY_ID"))
	@JsonBackReference
	private Lobby lobby;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_WORD_ID"))
	private Word word;

	@Column(name = "COLOR_HEX")
	private String colorHex;

	@Column(name = "OPEN")
	private boolean open;

	public LobbyWord(Word word) {
		this.word = word;
	}
}
