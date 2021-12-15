package springBoard.springboard;

import springBoard.springboard.Repository.*;
import springBoard.springboard.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springBoard.springboard.Repository.MemberJdbcTemplate;
import springBoard.springboard.Repository.MemberRepository;
import springBoard.springboard.Service.MemberService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }
    @Bean
    public MemberRepository memberRepository() {
// return new MemoryMemberRepository();
// return new JdbcMemberRepository(dataSource);
        return new MemberJdbcTemplate(dataSource);
    }
}

