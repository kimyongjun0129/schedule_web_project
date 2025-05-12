package org.example.scheduledweb.schedule.controller;

import org.example.scheduledweb.schedule.dto.ScheduleRequestDto;
import org.example.scheduledweb.schedule.dto.ScheduleResponseDto;
import org.example.scheduledweb.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /**
     * 스케쥴 생성 API
     * param : {@link ScheduleRequestDto} 스케쥴 생성 요청 객체
     * return : {@link ResponseEntity<ScheduleResponseDto>} JSON 응답
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    /**
     * 스케쥴 다건 조회 API
     * return : {@link  List<ScheduleResponseDto>} JSON 응답
     */
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedule() {
        return scheduleService.findAllSchedules();
    }

    /**
     * 스케쥴 단건 조회 API
     * param id 식별자
     * return : {@link ResponseEntity<ScheduleResponseDto>} JSON 응답
     * exception ResponseStatusException 식별자로 조회된 Schedule이 없는 경우 404 Not Found
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

    /**
     * 스케줄 단건 수정 API
     * Param id 식별자
     * Return : {@link ResponseEntity<ScheduleResponseDto>} JSON 응답
     * @exception ResponseStatusException 식별자로 조회된 Schedule이 없는 경우 404 Not Found
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updatetoDoContent(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        return new ResponseEntity<>(scheduleService.updateToDoContent(id, requestDto.getUserId(), requestDto.getToDoContent()), HttpStatus.OK);
    }

    /**
     * 스케줄 단건 수정 API
     * Param id 식별자
     * return {@link ResponseEntity<Void>} 성공시 Data 없이 200OK 상태코드만 응답.
     * @exception ResponseStatusException 식별자로 조회된 Schedule이 없는 경우 404 Not Found
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable long id,
            @RequestBody ScheduleRequestDto requestDto
    ) {
        scheduleService.deleteSchedule(id, requestDto.getUserId());
        // 성공한 경우
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 스케줄 페이지 조회 API
     * return : {@link List<ScheduleResponseDto>} JSON 응답
     */
    @GetMapping("/pages")
    @ResponseBody
    public List<ScheduleResponseDto> pagination (
        @RequestParam("pageNum") int pageNum,
        @RequestParam("pageSize") int pageSize
    ) {
        return scheduleService.pagination(pageNum, pageSize);
    }
}
