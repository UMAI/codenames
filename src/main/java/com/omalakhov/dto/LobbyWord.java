package com.omalakhov.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Data
public class LobbyWord {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOBBY_WORDS_ID")
	@SequenceGenerator(name = "SEQ_LOBBY_WORDS_ID", sequenceName = "SEQ_LOBBY_WORDS_ID", allocationSize = 1)
	private Long id;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_LOBBY_ID"))
	private Lobby lobby;

	@Column(name = "WORD")
	private String word;

	@Column(name = "COLOR_HEX")
	private String colorHex;

	@Column(name = "OPEN")
	private boolean open;
}
