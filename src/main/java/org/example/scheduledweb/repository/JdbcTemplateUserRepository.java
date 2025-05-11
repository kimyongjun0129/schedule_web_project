package org.example.scheduledweb.repository;

import org.example.scheduledweb.dto.ScheduleResponseDto;
import org.example.scheduledweb.dto.UserResponseDto;
import org.example.scheduledweb.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserResponseDto saveUser(User user) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("`user`") // 테이블명
                .usingGeneratedKeyColumns("userId") // 자동 생성된 키
                .usingColumns("email", "userName");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("email", user.getEmail());
        parameters.put("userName", user.getUserName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        long userId = key.longValue();

        String sql = "SELECT userId, email, userName, updateAt FROM user WHERE userId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                        new UserResponseDto(
                                rs.getLong("userId"),
                                rs.getString("email"),
                                rs.getString("userName"),
                                rs.getDate("updateAt")
                        )
                ,userId
        );
    }
}
