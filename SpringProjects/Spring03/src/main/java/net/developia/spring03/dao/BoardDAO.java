package net.developia.spring03.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.developia.spring03.dto.BoardDTO;

public interface BoardDAO {
	public void insertBoard(BoardDTO boardDTO) throws Exception;

//	public List<BoardDTO> getBoardList() throws Exception;
	
	// 페이징을 통한 글 목록 불러오기
	public List<BoardDTO> getBoardListWithPaging(@Param("startNum") long startNum, @Param("endNum") long endNum) throws Exception; 

	public BoardDTO getDetails(long no) throws Exception;

	public void updateReadCount(long no);

	public int deleteBoard(BoardDTO boardDTO);
	
	public int updateBoard(BoardDTO boardDTO);

	public long getBoardCount() throws Exception;
}
