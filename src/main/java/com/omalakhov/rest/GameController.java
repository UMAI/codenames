package com.omalakhov.rest;

import com.omalakhov.dao.LobbiesDAO;
import com.omalakhov.dao.WordsDAO;
import com.omalakhov.dto.Lobby;
import com.omalakhov.dto.Word;
import com.omalakhov.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
	public ResponseEntity createLobby(@QueryParam(value = "creatorName") String creatorName) {
		Lobby lobby = new Lobby(CodeGenerator.generateLobbyJoinCode(), creatorName);
		lobbiesDAO.save(lobby);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/lobby/join")
	@ResponseBody
	public ResponseEntity joinLobby() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/words")
	@ResponseBody
	public List<String> words(@RequestParam(name = "langCode", defaultValue = "en") String langCode) {
		return wordsDAO
				.findByLangCode(langCode)
				.stream()
				.map(Word::getWord)
				.collect(Collectors.toList());
	}
}
