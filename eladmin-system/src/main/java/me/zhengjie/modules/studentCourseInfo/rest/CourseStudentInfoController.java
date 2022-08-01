package me.zhengjie.modules.studentCourseInfo.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.studentCourseInfo.service.CourseStudentInfoService;
import me.zhengjie.modules.studentCourseInfo.service.dto.CourseStudentInfoDto;
import me.zhengjie.modules.studentCourseInfo.service.dto.CourseStudentInfoQueryParam;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-26
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "学生课程关系表管理")
@RequestMapping("/api/studentCourseInfo")
public class CourseStudentInfoController {

    private final CourseStudentInfoService courseStudentInfoService;

    @GetMapping
    @Log("查询学生课程关系表")
    @ApiOperation("查询学生课程关系表")
    @PreAuthorize("@el.check('studentCourseInfo:list')")
    public ResponseEntity query(CourseStudentInfoQueryParam query, Pageable pageable){
        return new ResponseEntity<>(courseStudentInfoService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增学生课程关系表")
    @ApiOperation("新增学生课程关系表")
    @PreAuthorize("@el.check('studentCourseInfo:add')")
    public ResponseEntity create(@Validated @RequestBody CourseStudentInfoDto resources){
        return new ResponseEntity<>(courseStudentInfoService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改学生课程关系表")
    @ApiOperation("修改学生课程关系表")
    @PreAuthorize("@el.check('studentCourseInfo:edit')")
    public ResponseEntity update(@Validated @RequestBody CourseStudentInfoDto resources){
        courseStudentInfoService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除学生课程关系表")
    @ApiOperation("删除学生课程关系表")
    @PreAuthorize("@el.check('studentCourseInfo:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        courseStudentInfoService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出学生课程关系表")
    @ApiOperation("导出学生课程关系表")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('courseStudentInfo:list')")
    public void download(HttpServletResponse response, CourseStudentInfoQueryParam query) throws IOException {
        courseStudentInfoService.download(courseStudentInfoService.queryAll(query), response);
    }*/

    @GetMapping(value = "/getJudgeByStudent")
    @Log("获取教学评价")
    @ApiOperation("获取教学评价")
    public ResponseEntity getJudgeByStudent(@RequestParam("cid")Integer cid ){
        return new ResponseEntity<>(courseStudentInfoService.getJudgeByStudent(cid), HttpStatus.OK);
    }

    @PostMapping(value = "/insertJudgeByStudent")
    @Log("插入教学评价")
    @ApiOperation("插入教学评价")
    public ResponseEntity insertJudgeByStudent(@RequestParam("cid")Integer cid,@RequestParam("judge") Integer judge ){
        courseStudentInfoService.insertJudgeByStudent(cid,judge);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
