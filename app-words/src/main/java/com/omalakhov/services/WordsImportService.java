package com.omalakhov.services;

import com.omalakhov.dao.WordsRepository;
import com.omalakhov.dto.Word;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class WordsImportService {
	private final WordsRepository wordsRepository;

	public WordsImportService(WordsRepository wordsRepository) {
		this.wordsRepository = wordsRepository;
	}

	public void importPlainFile(String path, String langCode) {
		try {
			Files
					.lines(Path.of(path))
					.map(line -> new Word(line, langCode))
					.forEach(wordsRepository::save);
		}
		catch (IOException e) {
			// TODO: handle
		}
	}
}
