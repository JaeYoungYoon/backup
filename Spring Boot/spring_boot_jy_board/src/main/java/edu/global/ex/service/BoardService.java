package edu.global.ex.service;

import java.util.List;

import edu.global.ex.vo.BoardVO;

public interface BoardService {
	public List<BoardVO> getList();

	public BoardVO get(int bid);

	public int modify(BoardVO boardVO);

	public int delete(int bid);

	public int register(BoardVO boardVO);

	public void registerReply(BoardVO boardVO);
}
