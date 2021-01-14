package com.omalakhov.rest;

import com.omalakhov.dao.WordsRepository;
import com.omalakhov.dto.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {
	@Autowired
	private WordsRepository wordsRepository;

	@GetMapping("/words")
	@ResponseBody
	public List<String> words(@RequestParam(name = "langCode", defaultValue = "en") String langCode) {
		return wordsRepository.findByLangCode(langCode).stream().map(Word::getWord).collect(Collectors.toList());
	}
}
