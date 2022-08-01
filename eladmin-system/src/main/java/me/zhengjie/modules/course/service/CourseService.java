package me.zhengjie.modules.course.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.course.domain.Course;
import me.zhengjie.modules.course.service.dto.CourseDto;
import me.zhengjie.modules.course.service.dto.CourseQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
*/
public interface CourseService extends CommonService<Course>  {

    static final String CACHE_KEY = "course";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<CourseDto>
    */
    PageInfo<CourseDto> queryAll(CourseQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<CourseDto>
    */
    List<CourseDto> queryAll(CourseQueryParam query);

    Course getById(Integer id);
    CourseDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(CourseDto resources);
    int updateById(CourseDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<CourseDto> all, HttpServletResponse response) throws IOException;
    /***/
    Object getCourseInfoByStudent();

    Object selectCourse();

    void InsertStudentCourse(Integer cid);

    Object getCourseInfoByTeacher();

}
