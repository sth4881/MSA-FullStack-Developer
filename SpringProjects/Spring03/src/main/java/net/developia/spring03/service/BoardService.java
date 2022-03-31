package net.developia.spring03.service;

import java.util.List;

import net.developia.spring03.dto.BoardDTO;

public interface BoardService {
	public void insertBoard(BoardDTO boardDTO) throws Exception;
	
//	public List<BoardDTO> getBoardList() throws Exception; // throws로 예외를 처리하지 않고 내부에서만 처리하면 사용자에게 예외를 보여주는 작업을 별도로 처리해줘야 한다.
	
	public List<BoardDTO> getBoardListWithPaging(long page) throws Exception; // 페이징을 통한 글 목록 불러오기
	
	public BoardDTO getDetails(long no) throws Exception;
	
	void deleteBoard(BoardDTO boardDTO) throws Exception;
	
	public void updateBoard(BoardDTO boardDTO) throws Exception;
	
	public long getBoardCount() throws Exception;
}
