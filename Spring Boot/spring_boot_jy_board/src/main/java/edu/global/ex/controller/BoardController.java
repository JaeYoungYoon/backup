package edu.global.ex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.global.ex.service.BoardService;
import edu.global.ex.vo.BoardVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/jboard/*")
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/list")
	public String view_list(Model model) {
		log.info("view_list()...");

		model.addAttribute("boards", boardService.getList());
		return "/board/list";
	}

	@GetMapping("/content_view")
	public String content_view(BoardVO boardVO, Model model) {
		log.info("content_view()...");

		int bid = boardVO.getBid();

		boardVO = boardService.get(bid);

		model.addAttribute("content_view", boardVO);
		return "/board/content_view";
	}

	@PostMapping("/modify")
	public String modify(BoardVO boardVO, Model model) {
		log.info("modify()...");

		int rn = boardService.modify(boardVO);

		log.info("modify()... result number : " + rn);

		return "redirect:list";
	}

	@GetMapping("/delete")
	public String delete(BoardVO boardVO, Model model) {
		log.info("delete()...");

		int bid = boardVO.getBid();

		int rn = boardService.delete(bid);

		log.info("delete()... result number : " + rn);
		return "redirect:list";
	}
	
	@GetMapping("/write_view")
	public String write_view(BoardVO boardVO, Model model) {
		log.info("write_view()...");

		return "/board/write_view";
	}

	@PostMapping("/write")
	public String write(BoardVO boardVO) {
		log.info("write()...");

		int rn = boardService.register(boardVO);
		log.info("register()... result number : " + rn);
		return "redirect:list";
	}
	
	@GetMapping("/reply_view")
	public String reply_view(BoardVO boardVO, Model model) {
		log.info("reply_view()...");

		model.addAttribute("reply_view", boardService.get(boardVO.getBid()));
		
		return "/board/reply_view";
	}
	
	@PostMapping("/reply")
	public String reply(BoardVO boardVO) {
		log.info("reply()...");

		boardService.registerReply(boardVO);
		return "redirect:list";
	}
	
}
