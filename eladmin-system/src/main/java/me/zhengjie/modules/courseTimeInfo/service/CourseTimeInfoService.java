package me.zhengjie.modules.courseTimeInfo.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.courseTimeInfo.domain.CourseTimeInfo;
import me.zhengjie.modules.courseTimeInfo.service.dto.CourseTimeInfoDto;
import me.zhengjie.modules.courseTimeInfo.service.dto.CourseTimeInfoQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
*/
public interface CourseTimeInfoService extends CommonService<CourseTimeInfo>  {

    static final String CACHE_KEY = "courseTimeInfo";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<CourseTimeInfoDto>
    */
    PageInfo<CourseTimeInfoDto> queryAll(CourseTimeInfoQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<CourseTimeInfoDto>
    */
    List<CourseTimeInfoDto> queryAll(CourseTimeInfoQueryParam query);

    CourseTimeInfo getById(Integer id);
    CourseTimeInfoDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(CourseTimeInfoDto resources);
    int updateById(CourseTimeInfoDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<CourseTimeInfoDto> all, HttpServletResponse response) throws IOException;
    Integer getCourseId(Integer id);

    String getCourseName(Integer id);

    String getTeacherName(Integer id);

    String getOldWeek(Integer id);

    String getOldDay(Integer id);

    String getOldTrap(Integer id);

    String getOldAddress(Integer id);

    Object getCourseInfo();

    Object getStudentCourseInfo();

    Object getStudentCourseWeek();

    Object getStudentCourseInfoByWeek(String week);

    Object getTeacherCourseInfo();

    Object getTeacherCourseWeek();

    Object getTeacherCourseInfoByWeek(String week);

    void updateCourseInfo(Integer timeid,String week,String day,String trap,String address);

    void insertMessageInfo(String title,String content,String createtime,String author);
}
