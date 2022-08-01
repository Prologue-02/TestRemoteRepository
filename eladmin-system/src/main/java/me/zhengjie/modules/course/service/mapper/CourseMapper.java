package me.zhengjie.modules.course.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.course.domain.Course;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Repository
public interface CourseMapper extends CommonMapper<Course> {
    String findTeacherNameById(Integer id);
    List<Map<String, Object>> queryCourseInfoByStudent(String id);
    List<Map<String, Object>> selectCourse(String id);
    void InsertStudentCourse(Integer sid,Integer cid);
    List<Map<String, Object>> queryCourseInfoByTeacher(String id);
}
