package me.zhengjie.modules.mark.rest;

import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.MarkService;
import me.zhengjie.modules.mark.service.dto.MarkDto;
import me.zhengjie.modules.mark.service.dto.MarkQueryParam;
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
@Slf4j
@RestController
@RequiredArgsConstructor
@Api(tags = "mark管理")
@RequestMapping("/api/mark")
public class MarkController {

    private final MarkService markService;

    @GetMapping
    @Log("查询mark")
    @ApiOperation("查询mark")
    @PreAuthorize("@el.check('mark:list')")
    public ResponseEntity query(MarkQueryParam query, Pageable pageable){
        return new ResponseEntity<>(markService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增mark")
    @ApiOperation("新增mark")
    @PreAuthorize("@el.check('mark:add')")
    public ResponseEntity create(@Validated @RequestBody MarkDto resources){
        return new ResponseEntity<>(markService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改mark")
    @ApiOperation("修改mark")
    @PreAuthorize("@el.check('mark:edit')")
    public ResponseEntity update(@Validated @RequestBody MarkDto resources){
        markService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除mark")
    @ApiOperation("删除mark")
    @PreAuthorize("@el.check('mark:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        markService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出mark")
    @ApiOperation("导出mark")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('mark:list')")
    public void download(HttpServletResponse response, MarkQueryParam query) throws IOException {
        markService.download(markService.queryAll(query), response);
    }*/

    @GetMapping(value = "/getCourseGradeByStudent")
    @Log("学生查询成绩")
    @ApiOperation("学生查询成绩")
    public ResponseEntity<Object> getCourseGradeByStudent(
    ) {
        return new ResponseEntity<>(markService.getCourseGradeByStudent(), HttpStatus.OK);
    }

    @GetMapping(value = "/getAllUnMarkedStudent")
    @Log("查询未给成绩的学生")
    @ApiOperation("查询未给成绩的学生")
    public ResponseEntity<Object> getAllUnMarkedStudent(
    ) {
        return new ResponseEntity<>(markService.getAllUnMarkedStudent(), HttpStatus.OK);
    }

    @GetMapping(value = "/getlUnMarkedStudentByCid")
    @Log("查询未给成绩的学生")
    @ApiOperation("查询未给成绩的学生")
    public ResponseEntity<Object> getlUnMarkedStudentByCid(@RequestParam("cid")Integer cid) {
        return new ResponseEntity<>(markService.getlUnMarkedStudentByCid(cid), HttpStatus.OK);
    }

    @PostMapping(value = "/insertStudentMark")
    @Log("查询未给成绩的学生")
    @ApiOperation("查询未给成绩的学生")
    public ResponseEntity<Object> insertStudentMark(@RequestParam("cid")Integer cid,@RequestParam("sid") Integer sid,@RequestParam("mark")Integer mark) {
        markService.insertStudentMark(cid,sid,mark);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
