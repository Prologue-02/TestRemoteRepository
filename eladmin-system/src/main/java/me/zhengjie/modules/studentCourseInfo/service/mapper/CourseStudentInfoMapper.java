package me.zhengjie.modules.studentCourseInfo.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.studentCourseInfo.domain.CourseStudentInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-05-26
*/
@Repository
public interface CourseStudentInfoMapper extends CommonMapper<CourseStudentInfo> {
    List<Map<String, Object>> getJudgeByStudent(Integer sid,Integer cid);
    void insertJudgeByStudent(Integer sid,Integer cid,Integer judge);
}
