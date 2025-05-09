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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                .usingGeneratedKeyColumns("scheduleId")
                .usingColumns("userName", "toDoContent"); // 자동 생성된 키

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userName", schedule.getUserName());
        parameters.put("toDoContent", schedule.getTodoContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        long scheduleId = key.longValue();

        String sql = "SELECT userName, toDoContent, updateAt FROM schedule WHERE scheduleId = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
                new ScheduleResponseDto(
                        rs.getString("userName"),
                        rs.getString("toDoContent"),
                        rs.getDate("updateAt")
                )
                ,scheduleId
        );
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule", scheduleRowMapper());
    }

    @Override
    public Schedule findScheduleByIdElseThrow(long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapper2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exists id = " + id));
    }

    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return (rs, rowNum) -> new ScheduleResponseDto(
                rs.getString("userName"),
                rs.getString("todoContent"),
                rs.getDate("updateAt")
        );
    }

    private RowMapper<Schedule> scheduleRowMapper2() {
        return (rs, rowNum) -> new Schedule(
                rs.getString("userName"),
                rs.getString("todoContent"),
                rs.getDate("updateAt")
        );
    }
}
