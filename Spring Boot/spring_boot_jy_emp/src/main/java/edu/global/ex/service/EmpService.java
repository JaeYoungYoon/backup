package edu.global.ex.service;

import java.util.List;

import edu.global.ex.vo.EmpVO;

public interface EmpService {
	public abstract List<EmpVO> getList();
	public EmpVO get(int bno);
}
