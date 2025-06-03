package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberRepository {
    void save(Member member);                 // 회원 저장
    Member findById(String id);              // 아이디로 회원 찾기
}