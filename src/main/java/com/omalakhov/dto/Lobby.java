package com.omalakhov.dto;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.omalakhov.dto.Team.Color.BLUE;
import static com.omalakhov.dto.Team.Color.RED;

@Entity
@Table(name = "LOBBIES")
@Data
public class Lobby {
	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOBBIES_ID")
	@SequenceGenerator(name = "SEQ_LOBBIES_ID", sequenceName = "SEQ_LOBBIES_ID", allocationSize = 1)
	private Long id;

	@Column(name = "JOIN_CODE")
	private String joinCode;

	@Column(name = "LANGUAGE")
	private String langCode;

	//@Column(name = "WORDS")
	//private String wordsJson;

	@OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL)
	private List<Team> teams;

	public Lobby(String joinCode, String creatorName) {
		this.joinCode = joinCode;
		Player player = new Player(creatorName);
		Team redTeam = new Team(RED.name(), RED.getColorHex());
		redTeam.addPlayer(player);
		Team blueTeam = new Team(BLUE.name(), BLUE.getColorHex());
		addTeam(redTeam);
		addTeam(blueTeam);
	}

	public void addTeam(Team team) {
		if (teams == null) {
			teams = new ArrayList<>();
		}
		teams.add(team);
		team.setLobby(this);
	}

	public void removeTeam(Team team) {
		teams.remove(team);
		team.setLobby(null);
	}

	public List<Player> getAllPlayers() {
		return teams
				.stream()
				.map(Team::getPlayers)
				.flatMap(List::stream)
				.collect(Collectors.toList());
	}
}
