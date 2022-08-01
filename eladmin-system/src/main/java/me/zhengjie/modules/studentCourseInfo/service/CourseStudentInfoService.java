package me.zhengjie.modules.studentCourseInfo.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.studentCourseInfo.domain.CourseStudentInfo;
import me.zhengjie.modules.studentCourseInfo.service.dto.CourseStudentInfoDto;
import me.zhengjie.modules.studentCourseInfo.service.dto.CourseStudentInfoQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-26
*/
public interface CourseStudentInfoService extends CommonService<CourseStudentInfo>  {

    static final String CACHE_KEY = "courseStudentInfo";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<CourseStudentInfoDto>
    */
    PageInfo<CourseStudentInfoDto> queryAll(CourseStudentInfoQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<CourseStudentInfoDto>
    */
    List<CourseStudentInfoDto> queryAll(CourseStudentInfoQueryParam query);

    CourseStudentInfo getById(Integer id);
    CourseStudentInfoDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(CourseStudentInfoDto resources);
    int updateById(CourseStudentInfoDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<CourseStudentInfoDto> all, HttpServletResponse response) throws IOException;
    Object getJudgeByStudent(Integer cid);
    void insertJudgeByStudent(Integer cid,Integer judge);
}
