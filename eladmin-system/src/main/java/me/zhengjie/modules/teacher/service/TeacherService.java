package me.zhengjie.modules.teacher.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.teacher.domain.Teacher;
import me.zhengjie.modules.teacher.service.dto.TeacherDto;
import me.zhengjie.modules.teacher.service.dto.TeacherQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
*/
public interface TeacherService extends CommonService<Teacher>  {

    static final String CACHE_KEY = "teacher";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<TeacherDto>
    */
    PageInfo<TeacherDto> queryAll(TeacherQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<TeacherDto>
    */
    List<TeacherDto> queryAll(TeacherQueryParam query);

    Teacher getById(Integer id);
    TeacherDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(TeacherDto resources);
    int updateById(TeacherDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<TeacherDto> all, HttpServletResponse response) throws IOException;
    Object getTeacherInfo();
}
