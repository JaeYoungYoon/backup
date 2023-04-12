package edu.global.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.global.ex.mapper.BoardMapper;
import edu.global.ex.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

	@Autowired
	private final BoardMapper mapper;

	@Override
	public List<BoardVO> getList() {
		log.info("getList()..");

		return mapper.getList();
	}

	@Override
	public BoardVO get(int bid) {
		log.info("get(int bid)..");
		return mapper.read(bid);
	}

	@Override
	public int modify(BoardVO board) {
		log.info("modify()..");

		return mapper.update(board);
	}

	@Override
	public int delete(int bid) {
		log.info("delete(int bid)..");
		return mapper.remove(bid);
	}

	@Override
	public int register(BoardVO board) {
		log.info("register()..");
		
		return mapper.insert(board);
	}

	@Transactional
	@Override
	public void registerReply(BoardVO board) {
		log.info("registerReply()..");
		
		mapper.updateShape(board);
		mapper.insertReply(board);
	}


}