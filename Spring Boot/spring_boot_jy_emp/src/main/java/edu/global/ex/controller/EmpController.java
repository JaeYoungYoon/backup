package edu.global.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.global.ex.service.EmpService;
import edu.global.ex.vo.EmpVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/emp/*")
@RequiredArgsConstructor
public class EmpController {

	@Autowired
	private EmpService empService;

	@GetMapping("/list")
	public String view_list(Model model) {
		log.info("view_list()...");

		model.addAttribute("emps", empService.getList());
		return "/emp/emp_list";
	}

	@GetMapping("/emp_view")
	public String emp_view(EmpVO empVO, Model model) {
		log.info("emp_view()...");

		int empno = empVO.getEmpno();

		empVO = empService.get(empno);

		model.addAttribute("emp_view", empVO);

		return "/emp/emp_view";
	}

}
