package springBoard.springboard.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import springBoard.springboard.domain.Member;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemberJdbcTemplate implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MemberJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("springmember").usingGeneratedKeyColumns("memnum");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("memnum", member.getMemnum());
        parameters.put("memberid", member.getMemberId());
        parameters.put("name", member.getName());
        parameters.put("email", member.getEmail());
        parameters.put("password", member.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setMemberId(key.toString());
        return member;
    }

    @Override
    public Optional<Member> findByMemberId(String MemberId) {
        List<Member> result = jdbcTemplate.query("select * from springmember where memberid = ?", memberRowMapper(), MemberId);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result =  jdbcTemplate.query("select * from springmember where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from springmember", memberRowMapper());
    }

    @Override
    public void deleteById(String id) {
        List<Member> result = jdbcTemplate.query("delete from springmember where id = ?", memberRowMapper(), id);
    }

    private RowMapper<Member> memberRowMapper() {
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setMemnum(rs.getLong("memnum"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
                member.setMemberId(rs.getString("memberid"));
                member.setEmail(rs.getString("email"));
                return member;
            }
        };
    }
}
