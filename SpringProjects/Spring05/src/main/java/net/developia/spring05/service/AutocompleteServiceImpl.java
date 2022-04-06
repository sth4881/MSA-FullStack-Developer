package net.developia.spring05.service;

import java.util.List;

import org.springframework.stereotype.Service;

import net.developia.spring05.dao.AutocompleteDAO;
import net.developia.spring05.dto.ItemDTO;

@Service
public class AutocompleteServiceImpl implements AutocompleteService {
	private AutocompleteDAO dao;
	
	// 생성자 주입
	public AutocompleteServiceImpl(AutocompleteDAO dao) {
		this.dao = dao;
	}

	@Override
	public List<ItemDTO> getWords(String term) throws Exception {
		return dao.getWords(term);
	}
}
