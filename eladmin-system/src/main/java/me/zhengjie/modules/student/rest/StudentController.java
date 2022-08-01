package me.zhengjie.modules.student.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.StudentService;
import me.zhengjie.modules.student.service.dto.StudentDto;
import me.zhengjie.modules.student.service.dto.StudentQueryParam;
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
@Api(tags = "学生信息管理")
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @Log("查询学生信息")
    @ApiOperation("查询学生信息")
    @PreAuthorize("@el.check('student:list')")
    public ResponseEntity query(StudentQueryParam query, Pageable pageable){
        return new ResponseEntity<>(studentService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增学生信息")
    @ApiOperation("新增学生信息")
    @PreAuthorize("@el.check('student:add')")
    public ResponseEntity create(@Validated @RequestBody StudentDto resources){
        return new ResponseEntity<>(studentService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改学生信息")
    @ApiOperation("修改学生信息")
    @PreAuthorize("@el.check('student:edit')")
    public ResponseEntity update(@Validated @RequestBody StudentDto resources){
        studentService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除学生信息")
    @ApiOperation("删除学生信息")
    @PreAuthorize("@el.check('student:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        studentService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出学生信息")
    @ApiOperation("导出学生信息")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('student:list')")
    public void download(HttpServletResponse response, StudentQueryParam query) throws IOException {
        studentService.download(studentService.queryAll(query), response);
    }*/

}
