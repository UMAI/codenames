package com.omalakhov.rest;

import com.omalakhov.dao.LobbiesDAO;
import com.omalakhov.dao.WordsDAO;
import com.omalakhov.dto.Lobby;
import com.omalakhov.dto.Player;
import com.omalakhov.dto.WSWordsResponse;
import com.omalakhov.dto.Word;
import com.omalakhov.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class GameController {
	@Autowired
	private WordsDAO wordsDAO;

	@Autowired
	private LobbiesDAO lobbiesDAO;

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

	@PostMapping("/lobby/create")
	@ResponseBody
	public Lobby createLobby(@QueryParam(value = "creatorName") String creatorName, @QueryParam(value = "langCode") String langCode) {
		Lobby lobby = new Lobby(CodeGenerator.generateLobbyJoinCode(), creatorName);
		lobbiesDAO.save(lobby);
		return lobby;
	}

	@PostMapping("/lobby/join")
	@ResponseBody
	public Lobby joinLobby(@QueryParam(value = "joinCode") String joinCode, @QueryParam(value = "playerName") String playerName) {
		Lobby lobby = lobbiesDAO.findFirstByJoinCode(joinCode);
		Player player = new Player(playerName);
		lobby.addPlayer(player);
		lobbiesDAO.save(lobby);
		return lobby;
	}

	@GetMapping("/words")
	public WSWordsResponse words(@RequestParam(name = "langCode", defaultValue = "en") String langCode) {
		WSWordsResponse response = new WSWordsResponse();
		response.setWords(wordsDAO
				.findByLangCode(langCode)
				.stream()
				.map(Word::getWord)
				.collect(Collectors.toList()));
		return response;
	}
}
