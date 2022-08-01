package me.zhengjie.modules.project.rest;

import me.zhengjie.annotation.Log;
import me.zhengjie.modules.project.domain.Project;
import me.zhengjie.modules.project.service.ProjectService;
import me.zhengjie.modules.project.service.dto.ProjectDto;
import me.zhengjie.modules.project.service.dto.ProjectQueryParam;
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
* @date 2022-06-01
**/
@RestController
@RequiredArgsConstructor
@Api(tags = "教改项目管理")
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    @Log("查询教改项目")
    @ApiOperation("查询教改项目")
    @PreAuthorize("@el.check('project:list')")
    public ResponseEntity query(ProjectQueryParam query, Pageable pageable){
        return new ResponseEntity<>(projectService.queryAll(query,pageable),HttpStatus.OK);
    }

    @PostMapping
    @Log("新增教改项目")
    @ApiOperation("新增教改项目")
    @PreAuthorize("@el.check('project:add')")
    public ResponseEntity create(@Validated @RequestBody ProjectDto resources){
        return new ResponseEntity<>(projectService.insert(resources),HttpStatus.CREATED);
    }

    @PutMapping
    @Log("修改教改项目")
    @ApiOperation("修改教改项目")
    @PreAuthorize("@el.check('project:edit')")
    public ResponseEntity update(@Validated @RequestBody ProjectDto resources){
        projectService.updateById(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    @Log("删除教改项目")
    @ApiOperation("删除教改项目")
    @PreAuthorize("@el.check('project:del')")
    public ResponseEntity delete(@RequestBody Set<Integer> ids) {
        projectService.removeByIds(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
    @Log("导出教改项目")
    @ApiOperation("导出教改项目")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('project:list')")
    public void download(HttpServletResponse response, ProjectQueryParam query) throws IOException {
        projectService.download(projectService.queryAll(query), response);
    }*/

    @GetMapping(value = "/getProjectInfo")
    @Log("获取教改项目")
    @ApiOperation("获取教改项目")
    public ResponseEntity getProjectInfo(){
        return new ResponseEntity<>(projectService.getProjectInfoById(), HttpStatus.OK);
    }

    @GetMapping(value = "/getProjectStatusInfo")
    @Log("获取教改项目")
    @ApiOperation("获取教改项目")
    public ResponseEntity getProjectStatusInfo(){
        return new ResponseEntity<>(projectService.getProjectStatusInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/getProjectInfoByStatus")
    @Log("获取教改项目")
    @ApiOperation("获取教改项目")
    public ResponseEntity getProjectInfoByStatus(@RequestParam("status")String status){
        return new ResponseEntity<>(projectService.getProjectInfoByStatus(status), HttpStatus.OK);
    }

    @PostMapping(value = "/insertProjectInfo")
    @Log("新增教改项目")
    @ApiOperation("新增教改项目")
    public ResponseEntity<Object> insertProjectInfo(
            @RequestParam("title") String title,
            @RequestParam("content") String content
    ) {
        projectService.insertProjectInfo(title,content);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
