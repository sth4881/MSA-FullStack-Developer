package com.kosa.app.service;

import java.util.List;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.dto.AttachDTO;

public interface AppService {
	long getArticleCount() throws Exception;

	List<ArticleDTO> getArticleList(long page) throws Exception;

	ArticleDTO getArticleDetail(long ano) throws Exception;
	
	void insertArticle(ArticleDTO dto) throws Exception;

	void insertAttach(AttachDTO dto) throws Exception;
	
	void updateArticle(ArticleDTO dto) throws Exception;

	void deleteArticle(ArticleDTO dto) throws Exception;
}
