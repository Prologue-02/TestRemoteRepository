package me.zhengjie.modules.courseTimeInfo.rest;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.courseTimeInfo.domain.CourseTimeInfo;
import me.zhengjie.modules.courseTimeInfo.service.CourseTimeInfoService;
import me.zhengjie.modules.courseTimeInfo.service.dto.CourseTimeInfoDto;
import me.zhengjie.modules.courseTimeInfo.service.dto.CourseTimeInfoQueryParam;
import me.zhengjie.utils.SecurityUtils;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
**/
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "课程安排管理")
@RequestMapping("/api/courseTimeInfo")
public class CourseTimeInfoController {

    private final CourseTimeInfoService courseTimeInfoService;

    @GetMapping
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    @PreAuthorize("@el.check('courseTimeInfo:list')")
    public ResponseEntity query(CourseTimeInfoQueryParam query, Pageable pageable){
        return new ResponseEntity<>(courseTimeInfoService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程安排")
    @ApiOperation("新增课程安排")
    @PreAuthorize("@el.check('courseTimeInfo:add')")
    public ResponseEntity create(@Validated @RequestBody CourseTimeInfoDto resources){
        return new ResponseEntity<>(courseTimeInfoService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程安排")
    @ApiOperation("修改课程安排")
    @PreAuthorize("@el.check('courseTimeInfo:edit')")
    public ResponseEntity update(@Validated @RequestBody CourseTimeInfoDto resources){
        courseTimeInfoService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除课程安排")
    @ApiOperation("删除课程安排")
    @PreAuthorize("@el.check('courseTimeInfo:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        courseTimeInfoService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出课程安排")
    @ApiOperation("导出课程安排")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('courseTimeInfo:list')")
    public void download(HttpServletResponse response, CourseTimeInfoQueryParam query) throws IOException {
        courseTimeInfoService.download(courseTimeInfoService.queryAll(query), response);
    }*/
    @GetMapping(value = "/getCourseInfo")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getCourseInfo() {
        return new ResponseEntity<>(courseTimeInfoService.getCourseInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentCourseInfo")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getStudentCourseInfo() {
        return new ResponseEntity<>(courseTimeInfoService.getStudentCourseInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentCourseWeek")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getStudentCourseWeek() {
        return new ResponseEntity<>(courseTimeInfoService.getStudentCourseWeek(), HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentCourseInfoByWeek")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getStudentCourseInfoByWeek(@RequestParam("week") String week) {
        return new ResponseEntity<>(courseTimeInfoService.getStudentCourseInfoByWeek(week), HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherCourseInfo")
    @Log("教师课程信息")
    @ApiOperation("教师课程信息")
    public ResponseEntity<Object> getTeacherCourseInfo(
    ) {
        return new ResponseEntity<>(courseTimeInfoService.getTeacherCourseInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherCourseWeek")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getTeacherCourseWeek() {
        return new ResponseEntity<>(courseTimeInfoService.getTeacherCourseWeek(), HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherCourseInfoByWeek")
    @Log("查询课程安排")
    @ApiOperation("查询课程安排")
    public ResponseEntity<Object> getTeacherCourseInfoByWeek(@RequestParam("week") String week) {
        return new ResponseEntity<>(courseTimeInfoService.getTeacherCourseInfoByWeek(week), HttpStatus.OK);
    }

    @PostMapping(value = "/updateCourseInfo")
    @Log("更新至学生课程表")
    @ApiOperation("更新至学生课程表")
    public ResponseEntity<Object> updateCourseInfo(
            @RequestParam("timeid") Integer timeid,
            @RequestParam("week") String week,
            @RequestParam("day") String day,
            @RequestParam("trap") String trap,
            @RequestParam("address") String address
    ) {
        Integer cid = courseTimeInfoService.getCourseId(timeid);
        String cname = courseTimeInfoService.getCourseName(cid);
        String tid = SecurityUtils.getCurrentUsername();
        String tname = courseTimeInfoService.getTeacherName(Integer.valueOf(tid));
        String title = "课程变动通知";
        String oldweek = courseTimeInfoService.getOldWeek(timeid);
        String oldday = courseTimeInfoService.getOldDay(timeid);
        String oldtrap = courseTimeInfoService.getOldTrap(timeid);
        String oldaddress = courseTimeInfoService.getOldAddress(timeid);
        String content = cname+" 课程由"+oldweek+"的"+oldday+" "+oldtrap+" "+oldaddress+"  变动为  "+week+"的"+day+" "+trap+" "+address;
        Date current = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String createtime = formatter.format(current);
        String author = tname;
        courseTimeInfoService.insertMessageInfo(title,content,createtime,author);
        courseTimeInfoService.updateCourseInfo(timeid,week,day,trap,address);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
