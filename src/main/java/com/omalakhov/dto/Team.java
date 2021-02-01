package com.omalakhov.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAMS")
@Data
public class Team {
	@AllArgsConstructor
	@Getter
	public enum Color {
		RED("#ff3333"),
		BLUE("#3380ff");

		private final String colorHex;
	}

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TEAMS_ID")
	@SequenceGenerator(name = "SEQ_TEAMS_ID", sequenceName = "SEQ_TEAMS_ID", allocationSize = 1)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "COLOR_HEX")
	private String colorHex;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "FK_LOBBY_ID"))
	@JsonBackReference
	private Lobby lobby;

	@OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Player> players;

	public Team(String name, String colorHex) {
		this.name = name;
		this.colorHex = colorHex;
	}

	public void addPlayer(Player player) {
		if (players == null) {
			players = new ArrayList<>();
		}
		players.add(player);
		player.setTeam(this);
	}

	public void removePlayer(Player player) {
		players.remove(player);
		player.setTeam(null);
	}
}
