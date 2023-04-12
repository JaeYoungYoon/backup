package edu.global.ex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.global.ex.mapper.EmpMapper;

import edu.global.ex.vo.EmpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService {


	@Autowired
	private final EmpMapper mapper;
	
	@Override
	public List<EmpVO> getList() {
		log.info("getList()..");
		return mapper.getList();
	}

	@Override
	public EmpVO get(int bid) {
		log.info("get()..");
		return mapper.read(bid);
	}

}
