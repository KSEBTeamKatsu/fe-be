package Katsu.Katsu_spring.controller;

import Katsu.Katsu_spring.dto.BoardDTO;
import Katsu.Katsu_spring.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class BoardController {
    private final BoardService boardService;

    // 게시글 목록 조회
    @GetMapping("/posts")
    public List<BoardDTO> getPosts(){
        return boardService.findAll();
    }

    // 게시글 작성
    @PostMapping("/posts")
    public BoardDTO createPosts(@RequestBody BoardDTO boardDTO) {
        boardService.posts(boardDTO);
        return boardDTO;
    }

    // 게시글 상세 조회
    @GetMapping("/posts/{postId}")
    public BoardDTO findById(@PathVariable long postId){
        // 조회수 증가
        boardService.updateView(postId);
        // 상세 내용 가져오기
        return boardService.findById(postId);
    }

    //수정 버튼 클릭시 수정 화면으로 넘어가도록 하는 메소드(GET)
    @PutMapping("/posts/{postId}")
    public BoardDTO update(@PathVariable Long postId, @RequestBody BoardDTO boardDTO) {
        // postId로 수정할 게시글 찾기 → boardDTO에 담긴 데이터로 업데이트
        boardDTO.setPostId(Math.toIntExact(postId)); // 혹시 필요할 경우 id 주입
        boardService.update(boardDTO);
        return boardService.findById(postId); // 업데이트된 결과 반환
    }

    // 게시글 삭제
    @DeleteMapping("/posts/{postId}")
    public void delete(@PathVariable Long postId) {
        boardService.delete(postId);
    }

}