package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.dto.BoardDTO;
import Katsu.Katsu_spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/posts")
    public String posts(){
        return "posts";
    }

    @PostMapping("/posts")
    public String posts(BoardDTO boardDTO) {
        boardService.posts(boardDTO);
        return "redirect:/list"; //반환타입을 String으로 바꾸고 /list url로 리다이렉션을 해준다.
        //글작성이 성공하면 localhost:8080/list로 페이지가 넘어간다.
    }

    @GetMapping("/list")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = (List<BoardDTO>) boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/board/{postId}")
    public String findById(@PathVariable Long postId, Model model){
        //조회수 처리
        boardService.updateView(postId);
        //상세 내용 가져오기
        BoardDTO boardDTO = boardService.findById(postId);
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    //수정 버튼 클릭시 수정 화면으로 넘어가도록 하는 메소드(GET)
    @GetMapping("/update/{postId}")
    public String update(@PathVariable Long postId, Model model){
        BoardDTO boardDTO = boardService.findById(postId);
        model.addAttribute("board", boardDTO);
        return "update";
    }

    //DB에 실질적으로 수정 내용을 요청하는 메소드(POST)
    @PostMapping("/update/{postId}")
    public String update(BoardDTO boardDTO, Model model){
        boardService.update(boardDTO);
        //수정된 글 상세 페이지로 리다이렉트
        return "redirect:/board/" + boardDTO.getPostId();
    }

    @GetMapping("/delete/{postId}")
    public String delete(@PathVariable long postId){
        boardService.delete(postId);
        return "redirect:/list";
    }

}