package com.omalakhov.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WSWordsResponse {
	private List<String> words;
}
