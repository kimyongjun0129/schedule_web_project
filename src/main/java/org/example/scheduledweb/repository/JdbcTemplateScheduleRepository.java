package org.example.scheduledweb.repository;

import org.example.scheduledweb.dto.ScheduleResponseDto;
import org.example.scheduledweb.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("schedule") // 테이블명
                .usingGeneratedKeyColumns("scheduleId"); // 자동 생성된 키

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", schedule.getUserName());
        parameters.put("toDoContent", schedule.getTodoContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        return new ScheduleResponseDto(key.longValue(), schedule.getUserName(), schedule.getTodoContent());
    }

    @Override
    public Schedule findScheduleByIdElseThrow(long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapper(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return (rs, rowNum) -> new Schedule(
                rs.getLong("scheduleId"),
                rs.getString("userName"),
                rs.getString("todoContent")
        );
    }
}
