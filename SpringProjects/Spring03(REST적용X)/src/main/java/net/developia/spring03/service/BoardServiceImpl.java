package net.developia.spring03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import net.developia.spring03.dao.BoardDAO;
import net.developia.spring03.dto.BoardDTO;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	private BoardDAO boardDAO;
	
	@Value("${pageSize}")
	private int pageSize;

	public BoardServiceImpl(BoardDAO boardDAO) {
		this.boardDAO = boardDAO;
	}

	@Override
	public void insertBoard(BoardDTO boardDTO) throws Exception {
		try {
			boardDAO.insertBoard(boardDTO);
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}

//	@Override
//	public List<BoardDTO> getBoardList() throws Exception {
//		try {
//			return boardDAO.getBoardList();
//		} catch (Exception e) {
//			log.info(e.toString());
//			throw e;
//		}
//	}

	// 페이징을 통한 글 목록 불러오기
	@Override
	public List<BoardDTO> getBoardListWithPaging(long page) throws Exception {
		try {
			// 시작페이지 계산법 : (현재 페이지(page) -1) * 현재 페이지에서 보여줄 페이지의 개수(pageSize) + 1
			long startNum = (page-1) * pageSize + 1; // 이전 페이지 * 이전 페이지의 마지막 번호 + 1
			long endNum = page * pageSize;
			return boardDAO.getBoardListWithPaging(startNum, endNum);
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}
	
	@Override
	public BoardDTO getDetails(long no) throws Exception {
		try {
			if(no==-1) {
				throw new RuntimeException("잘못된 접근입니다.");
			}
			BoardDTO boardDTO = boardDAO.getDetails(no);
			boardDAO.updateReadCount(no);
			return boardDTO;
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}

	@Override
	public void deleteBoard(BoardDTO boardDTO) throws Exception {
		try {
			if(boardDAO.deleteBoard(boardDTO)==0) {
				throw new RuntimeException("해당 게시물이 없거나 비밀번호가 틀립니다.");
			}
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) throws Exception {
		try {
			if(boardDAO.updateBoard(boardDTO)==0) {
				throw new RuntimeException("해당 게시물이 없거나 비밀번호가 틀립니다.");
			}
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}

	@Override
	public long getBoardCount() throws Exception {
		try {
			return boardDAO.getBoardCount();
		} catch (Exception e) {
			log.info(e.toString());
			throw e;
		}
	}
}
