package com.omalakhov.rest;

import com.omalakhov.dao.WordsRepository;
import com.omalakhov.dto.Word;
import com.omalakhov.services.WordsImportService;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/words")
public class WordsResource {
	private final WordsRepository wordsRepository;

	private final WordsImportService importService;

	public WordsResource(WordsRepository wordsRepository, WordsImportService importService) {
		this.wordsRepository = wordsRepository;
		this.importService = importService;
	}

	@GetMapping("/wordSet")
	@ResponseBody
	public List<Word> getRandomWordSet(@RequestParam("size") Integer size, @RequestParam("langCode") String langCode, @RequestParam("difficulty") Integer difficulty) {
		List<Word> allWords = wordsRepository.findByLangCodeAndDifficulty(langCode, difficulty);
		List<Word> wordSet = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < size; i++) {
			wordSet.add(allWords.get(random.nextInt(allWords.size())));
		}
		return wordSet;
	}

	@PostMapping("/import")
	public ResponseEntity<Void> importPlainFile(@RequestParam("path") String path, @RequestParam("langCode") String langCode) {
		importService.importPlainFile(path, langCode);
		return ResponseEntity.ok().build();
	}
}
