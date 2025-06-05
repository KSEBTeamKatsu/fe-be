package Katsu.Katsu_spring.repository;

import Katsu.Katsu_spring.domain.Member;
import Katsu.Katsu_spring.dto.BoardDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcBoardRepository implements BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcBoardRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(Member member) {
        String sql = "INSERT INTO member_table(id, pw) VALUES (?, ?)";
        jdbcTemplate.update(sql, member.getId(), member.getPw());
    }

}
