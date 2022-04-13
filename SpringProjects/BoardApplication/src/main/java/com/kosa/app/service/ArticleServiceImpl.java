package com.kosa.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.kosa.app.dto.ArticleDTO;
import com.kosa.app.dto.AttachDTO;
import com.kosa.app.mapper.ArticleMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class ArticleServiceImpl implements ArticleService {
	@Value("${articlePerPage}")
	private int articlePerPage;

	// 생성자 주입
	private ArticleMapper dao;
	public ArticleServiceImpl(ArticleMapper dao) {
		this.dao = dao;
	}
	
	@Override
	public long getArticleCount() throws Exception {
		return dao.getArticleCount();
	}

	@Override
	public List<ArticleDTO> getArticleList(long page) throws Exception {
		long startNum = (page-1) * articlePerPage + 1;
		long endNum = page * articlePerPage;
		return dao.getArticleList(startNum, endNum);
	}

	@Override
	public ArticleDTO getArticleDetail(long ano) throws Exception {
		if(ano<1) throw new RuntimeException("잘못된 접근입니다.");
		ArticleDTO dto = dao.getArticleDetail(ano);
		dao.updateViewCount(ano);
		return dto;
	}

	@Override
	public List<AttachDTO> getAttachList(long ano) throws Exception {
		return dao.getAttachList(ano); // 게시물 번호(ano)에 대한 첨부파일 리스트 반환
	}

	@Override
	public void insertArticle(ArticleDTO dto) throws Exception {
		dao.insertArticle(dto);
	}
	
	@Override
	public void insertAttach(AttachDTO dto) throws Exception {
		dao.insertAttach(dto);
	}

	@Override
	public void updateArticle(ArticleDTO dto) throws Exception {
		// 수정한 게시물이 0개면 오류 발생
		if(dao.updateArticle(dto)==0) {
			throw new RuntimeException("해당 게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
		}
	}

	@Override
	public void deleteArticle(ArticleDTO dto) throws Exception {
		// 삭제한 게시물이 0개면 오류 발생
		if(dao.deleteArticle(dto)==0) {
			throw new RuntimeException("해당 게시물이 존재하지 않거나 비밀번호가 일치하지 않습니다.");
		}
	}
}
