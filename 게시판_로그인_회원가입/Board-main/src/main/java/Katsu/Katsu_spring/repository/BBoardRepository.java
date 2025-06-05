package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BBoardRepository {
    private final SqlSessionTemplate sql;

    public void posts(BoardDTO boardDTO) {
        sql.insert("Katsu.Katsu_spring.repository.BoardRepository.posts", boardDTO);
    }

    public List<BoardDTO> findAll(){
        log.info("findAll called");
        return sql.selectList("Katsu.Katsu_spring.repository.BoardRepository.findAll");
    }

    public void updateView(@Param("postId") Long postId){
        log.info("updateView");
        sql.update("Katsu.Katsu_spring.repository.BoardRepository.updateView", postId);
    }

    public BoardDTO findById(@Param("postId") Long postId){
        log.info("findById");
        return sql.selectOne("Katsu.Katsu_spring.repository.BoardRepository.findById", postId);
    }

    public void update(BoardDTO boardDTO){
        sql.update("Katsu.Katsu_spring.repository.BoardRepository.update", boardDTO);
    }

    public void delete(Long postId){
        sql.delete("Katsu.Katsu_spring.repository.BoardRepository.delete", postId);
    }
}
