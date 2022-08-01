package me.zhengjie.modules.project.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.project.domain.Project;
import me.zhengjie.modules.project.service.dto.ProjectDto;
import me.zhengjie.modules.project.service.dto.ProjectQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-06-01
*/
public interface ProjectService extends CommonService<Project>  {

    static final String CACHE_KEY = "project";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<ProjectDto>
    */
    PageInfo<ProjectDto> queryAll(ProjectQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<ProjectDto>
    */
    List<ProjectDto> queryAll(ProjectQueryParam query);

    Project getById(Integer id);
    ProjectDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(ProjectDto resources);
    int updateById(ProjectDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<ProjectDto> all, HttpServletResponse response) throws IOException;

    Object getProjectInfoById();
    Object getProjectStatusInfo();
    String getTeacherName(Integer id);
    Object getProjectInfoByStatus(String status);
    void insertProjectInfo(String title,String content);

}
