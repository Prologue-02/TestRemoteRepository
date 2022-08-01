package me.zhengjie.modules.courseTimeInfo.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.courseTimeInfo.domain.CourseTimeInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Repository
public interface CourseTimeInfoMapper extends CommonMapper<CourseTimeInfo> {
    String findCourseNameById(Integer id);

    String findTeacherNameById(Integer id);

    String findOldWeekById(Integer id);

    String findOldDayById(Integer id);

    String findOldTrapById(Integer id);

    String findOldAddressById(Integer id);

    Integer findCourseIdByTimeId(Integer id);

    List<Map<String, Object>> getCourseInfo();

    List<Map<String, Object>> getStudentCourseInfo(String id);

    List<Map<String, Object>> getStudentCourseWeek(String id);

    List<Map<String, Object>> getStudentCourseInfoByWeek(String id,String week);

    List<Map<String, Object>> getTeacherCourseInfo(String id);

    List<Map<String, Object>> getTeacherCourseWeek(String id);

    List<Map<String, Object>> getTeacherCourseInfoByWeek(String id,String week);

    void updateCourseInfo(Integer timeid,String week,String day,String trap,String address);

    void insertMessageInfo(String title,String content,String createtime,String author);
}
