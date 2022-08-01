package me.zhengjie.modules.course.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.CourseService;
import me.zhengjie.modules.course.service.dto.CourseDto;
import me.zhengjie.modules.course.service.dto.CourseQueryParam;
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
* @date 2022-05-16
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "课程管理管理")
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    @Log("查询课程管理")
    @ApiOperation("查询课程管理")
    @PreAuthorize("@el.check('course:list')")
    public ResponseEntity query(CourseQueryParam query, Pageable pageable){
        return new ResponseEntity<>(courseService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增课程管理")
    @ApiOperation("新增课程管理")
    @PreAuthorize("@el.check('course:add')")
    public ResponseEntity create(@Validated @RequestBody CourseDto resources){
        return new ResponseEntity<>(courseService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改课程管理")
    @ApiOperation("修改课程管理")
    @PreAuthorize("@el.check('course:edit')")
    public ResponseEntity update(@Validated @RequestBody CourseDto resources){
        courseService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除课程管理")
    @ApiOperation("删除课程管理")
    @PreAuthorize("@el.check('course:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        courseService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出课程管理")
    @ApiOperation("导出课程管理")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('course:list')")
    public void download(HttpServletResponse response, CourseQueryParam query) throws IOException {
        courseService.download(courseService.queryAll(query), response);
    }*/
    @GetMapping(value = "/getCourseInfoByStudent")
    @Log("学生课程信息")
    @ApiOperation("学生课程信息")
    public ResponseEntity<Object> getCourseInfoByStudent(
    ) {
        return new ResponseEntity<>(courseService.getCourseInfoByStudent(), HttpStatus.OK);
    }

    @GetMapping(value = "/selectCourse")
    @Log("学生课程信息")
    @ApiOperation("学生课程信息")
    public ResponseEntity<Object> selectCourse(
    ) {
        return new ResponseEntity<>(courseService.selectCourse(), HttpStatus.OK);
    }

    @PostMapping(value = "/InsertStudentCourse")
    @Log("插入至学生课程表")
    @ApiOperation("插入至学生课程表")
    public ResponseEntity<Object> InsertStudentCourse(
            @RequestParam("cid") Integer cid
    ) {
        courseService.InsertStudentCourse(cid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getCourseInfoByTeacher")
    @Log("教师课程信息")
    @ApiOperation("教师课程信息")
    public ResponseEntity<Object> getCourseInfoByTeacher(
    ) {
        return new ResponseEntity<>(courseService.getCourseInfoByTeacher(), HttpStatus.OK);
    }
}
