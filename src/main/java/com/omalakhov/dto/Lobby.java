package com.omalakhov.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

import static com.omalakhov.dto.Team.Color.BLUE;
import static com.omalakhov.dto.Team.Color.RED;

@Entity
@Table(name = "LOBBIES")
@Getter
@Setter
@NoArgsConstructor
public class Lobby {
	public static final String WORDS_SEPARATOR = ",";

	@Column(name = "ID")
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_LOBBIES_ID")
	@SequenceGenerator(name = "SEQ_LOBBIES_ID", sequenceName = "SEQ_LOBBIES_ID", allocationSize = 1)
	private Long id;

	@Column(name = "JOIN_CODE")
	private String joinCode;

	@Column(name = "LANGUAGE")
	private String langCode;

	@OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "lobby-team")
	private List<Team> teams;

	@OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "lobby-player")
	private List<Player> undecidedPlayers;

	@OneToMany(mappedBy = "lobby", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "lobby-word")
	private List<LobbyWord> lobbyWords;

	public Lobby(String joinCode, String creatorName) {
		this.joinCode = joinCode;
		Player player = new Player(creatorName);
		addUndecidedPlayer(player);
		Team redTeam = new Team(RED.name(), RED.getColorHex());
		Team blueTeam = new Team(BLUE.name(), BLUE.getColorHex());
		addTeam(redTeam);
		addTeam(blueTeam);
	}

	public void addUndecidedPlayer(Player player) {
		if (undecidedPlayers == null) {
			undecidedPlayers = new ArrayList<>();
		}
		undecidedPlayers.add(player);
		player.setLobby(this);
	}

	public boolean removeUndecidedPlayer(Player player) {
		player.setLobby(null);
		return undecidedPlayers.remove(player);
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

	public void setLobbyWords(List<LobbyWord> lobbyWords) {
		this.lobbyWords = lobbyWords;
		lobbyWords.forEach(lobbyWord -> lobbyWord.setLobby(this));
	}
}
