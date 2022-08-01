package me.zhengjie.modules.project.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.project.domain.Project;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-06-01
*/
@Repository
public interface ProjectMapper extends CommonMapper<Project> {
    List<Map<String, Object>> getProjectInfoById(String author);
    String findTeacherNameById(Integer id);
    List<Map<String, Object>> getProjectInfoByStatus(String author,String status);
    List<Map<String, Object>> getProjectStatusInfoById(String author);
    void insertProjectInfo(String title, String content, String createtime, String author,String status);
}
