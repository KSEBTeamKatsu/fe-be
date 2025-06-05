package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BoardRepository {

    public void posts(BoardDTO boardDTO) {
        boardRepository.posts(boardDTO);
    }

    public List<BoardDTO> findAll(){
        return boardRepository.findAll();
    }

    public void updateView(Long postId){
        boardRepository.updateView(postId);
    }

    public BoardDTO findById(Long postId){
        return boardRepository.findById(postId);
    }

    public void update(BoardDTO boardDTO){
        boardRepository.update(boardDTO);
    }

    public void delete(long postId){
        boardRepository.delete(postId);
    }         // 아이디로 회원 찾기
}
