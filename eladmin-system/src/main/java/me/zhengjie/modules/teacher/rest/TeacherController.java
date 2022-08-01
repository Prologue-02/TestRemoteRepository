package me.zhengjie.modules.teacher.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.TeacherService;
import me.zhengjie.modules.teacher.service.dto.TeacherDto;
import me.zhengjie.modules.teacher.service.dto.TeacherQueryParam;
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
@Api(tags = "教师管理管理")
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    @Log("查询教师管理")
    @ApiOperation("查询教师管理")
    @PreAuthorize("@el.check('teacher:list')")
    public ResponseEntity query(TeacherQueryParam query, Pageable pageable){
        return new ResponseEntity<>(teacherService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增教师管理")
    @ApiOperation("新增教师管理")
    @PreAuthorize("@el.check('teacher:add')")
    public ResponseEntity create(@Validated @RequestBody TeacherDto resources){
        return new ResponseEntity<>(teacherService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改教师管理")
    @ApiOperation("修改教师管理")
    @PreAuthorize("@el.check('teacher:edit')")
    public ResponseEntity update(@Validated @RequestBody TeacherDto resources){
        teacherService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除教师管理")
    @ApiOperation("删除教师管理")
    @PreAuthorize("@el.check('teacher:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        teacherService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出教师管理")
    @ApiOperation("导出教师管理")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('teacher:list')")
    public void download(HttpServletResponse response, TeacherQueryParam query) throws IOException {
        teacherService.download(teacherService.queryAll(query), response);
    }*/

    @GetMapping(value = "/getTeacherInfo")
    @Log("获取教学评价")
    @ApiOperation("获取教学评价")
    public ResponseEntity getTeacherInfo(){
        return new ResponseEntity<>(teacherService.getTeacherInfo(), HttpStatus.OK);
    }
}
