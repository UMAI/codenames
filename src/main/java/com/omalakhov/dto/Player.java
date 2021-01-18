package com.omalakhov.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

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
@Table(name = "PLAYERS")
@Data
public class Player {
	@AllArgsConstructor
	@Getter
	public enum Role {
		SPYMASTER("S"),
		PLAYER("P");

		private final String code;
	}

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PLAYERS_ID")
	@SequenceGenerator(name = "SEQ_PLAYERS_ID", sequenceName = "SEQ_PLAYERS_ID", allocationSize = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_TEAM_ID"))
	private Team team;

	@Column(name = "ROLE")
	private String role;

	public Player(String name) {
		this.name = name;
		this.role = Role.PLAYER.getCode();
	}
}
