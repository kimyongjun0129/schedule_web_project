package org.example.scheduledweb.schedule.repository;

import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;
import org.example.scheduledweb.schedule.entity.Paging;
import org.example.scheduledweb.schedule.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
                .usingGeneratedKeyColumns("scheduleId") // 자동 생성된 키
                .usingColumns("userId", "userName", "toDoContent");

        String userNameSql = "SELECT userName FROM user WHERE userId = ?";
        String userName = jdbcTemplate.queryForObject(userNameSql, String.class, schedule.getUserId());

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("userId", schedule.getUserId());
        parameters.put("userName", userName);
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
        return jdbcTemplate.query("select * from schedule ORDER BY updateAt DESC", scheduleRowMapper());
    }

    @Override
    public Schedule findScheduleById(long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where scheduleId = ?", scheduleRowMapper2(), id);
        return result.stream().findAny().orElse(null);
    }

    @Override
    public Schedule findScheduleByUserId(long id) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where userId = ?", scheduleRowMapper3(), id);
        return result.stream().findAny().orElse(null);
    }

    @Override
    public int updateToDoContent(long id, String toDoContent) {
        return jdbcTemplate.update("update schedule set toDoContent = ? where scheduleId = ?", toDoContent, id);
    }

    @Override
    public int deleteSchedule(long id) {
        return jdbcTemplate.update("delete from schedule where scheduleId = ?", id);
    }

    @Override
    public List<ScheduleResponseDto> pagination(Paging paging) {
        String sql = "select * from schedule ORDER BY updateAt DESC LIMIT ? OFFSET ?";
        int pageLimit = paging.getPageSize();
        int pageOffset = (paging.getPageNum()-1) * paging.getPageSize();
        return jdbcTemplate.query(sql, scheduleRowMapper(), pageLimit, pageOffset);
    }

    @Override
    public Integer findUserByUserId(long userId) {
        String userCheckSql = "SELECT COUNT(*) FROM user WHERE userId = ?";
        return jdbcTemplate.queryForObject(userCheckSql, Integer.class, userId);
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

    private RowMapper<Schedule> scheduleRowMapper3() {
        return (rs, rowNum) -> new Schedule(
                rs.getLong("userId")
        );
    }
}
