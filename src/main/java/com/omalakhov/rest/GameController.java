package com.omalakhov.rest;

import com.omalakhov.dao.LobbiesRepository;
import com.omalakhov.dao.WordsRepository;
import com.omalakhov.dto.Lobby;
import com.omalakhov.dto.LobbyWord;
import com.omalakhov.dto.Player;
import com.omalakhov.dto.Team;
import com.omalakhov.dto.WSWordsResponse;
import com.omalakhov.dto.Word;
import com.omalakhov.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@RestController
@RequestMapping("/api")
public class GameController {
	@Autowired
	private WordsRepository wordsRepository;

	@Autowired
	private LobbiesRepository lobbiesRepository;

	@PostMapping("/player/create")
	@ResponseBody
	public ResponseEntity createPlayer() {
		return ResponseEntity.ok().build();
	}

	@PostMapping("/player/rename")
	@ResponseBody
	public ResponseEntity renamePlayer() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/lobby/{lobbyId}")
	@ResponseBody
	public Lobby getLobby(@PathVariable("lobbyId") Long lobbyId) {
		return lobbiesRepository.findById(lobbyId).orElseThrow();
	}

	@PutMapping("/lobby")
	@ResponseBody
	public Lobby createLobby(@QueryParam(value = "creatorName") String creatorName, @QueryParam(value = "langCode") String langCode) {
		Lobby lobby = new Lobby(CodeGenerator.generateLobbyJoinCode(), creatorName);
		setLobbyWords(lobby);
		lobbiesRepository.save(lobby);
		return lobby;
	}

	private void setLobbyWords(Lobby lobby) {
		List<Word> words = wordsRepository.findByIdIn(LongStream
				.rangeClosed(1, 25)
				.boxed()
				.collect(Collectors.toList()));
		List<LobbyWord> lobbyWords = words
				.stream()
				.map(LobbyWord::new)
				.collect(Collectors.toList());
		lobbyWords.stream().limit(9).forEach(lobbyWord -> lobbyWord.setColorHex(lobby.getTeams().get(0).getColorHex()));
		lobbyWords.stream().skip(9).limit(8).forEach(lobbyWord -> lobbyWord.setColorHex(lobby.getTeams().get(1).getColorHex()));
		lobbyWords.stream().skip(17).limit(1).forEach(lobbyWord -> lobbyWord.setColorHex(Team.Color.BLACK.getColorHex()));
		lobbyWords.stream().skip(18).forEach(lobbyWord -> lobbyWord.setColorHex(Team.Color.GREY.getColorHex()));
		lobby.setLobbyWords(lobbyWords);
	}

	@PostMapping("/lobby")
	@ResponseBody
	public Lobby joinLobby(@QueryParam(value = "joinCode") String joinCode, @QueryParam(value = "playerName") String playerName) {
		Lobby lobby = lobbiesRepository.findFirstByJoinCode(joinCode);
		Player player = new Player(playerName);
		lobby.addPlayer(player);
		lobbiesRepository.save(lobby);
		return lobby;
	}

	@DeleteMapping("/lobby/{lobbyId}")
	@ResponseBody
	public ResponseEntity deleteLobby(@PathVariable Long lobbyId) {
		lobbiesRepository.deleteById(lobbyId);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/lobby/{lobbyId}/{teamId}")
	@ResponseBody
	public ResponseEntity<Lobby> joinTeam(@PathVariable Long lobbyId, @PathVariable Long teamId, Player player) {
		Lobby lobby = lobbiesRepository.findById(lobbyId).orElse(null);
		if (lobby == null) {
			return ResponseEntity.badRequest().build();
		}
		lobby
				.getTeams()
				.stream()
				.filter(team -> team.getId().equals(teamId))
				.findFirst()
				.ifPresent(team -> team.addPlayer(player));
		lobbiesRepository.save(lobby);
		return ResponseEntity.ok(lobby);
	}

	@GetMapping("/words")
	public WSWordsResponse words(@RequestParam(name = "langCode", defaultValue = "en") String langCode) {
		WSWordsResponse response = new WSWordsResponse();
		response.setWords(wordsRepository
				.findByLangCode(langCode)
				.stream()
				.map(Word::getWord)
				.collect(Collectors.toList()));
		return response;
	}
}
