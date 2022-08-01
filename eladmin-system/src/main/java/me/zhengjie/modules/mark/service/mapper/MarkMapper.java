package me.zhengjie.modules.mark.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.mark.domain.Mark;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Repository
public interface MarkMapper extends CommonMapper<Mark> {
    String findStudentNameById(Integer id);
    String findCourseNameById(Integer id);
    String findCourseYearById(Integer id);
    Integer findCourseTearmById(Integer id);
    String findTeacherNameById(Integer id);
    List<Map<String, Object>> queryMarkBySid(String id);
    List<Map<String, Object>> getAllUnMarkedStudent(String id);
    List<Map<String, Object>> getlUnMarkedStudentByCid(Integer tid,Integer cid);
    void insertStudentMark(Integer cid,Integer sid,Integer mark);
}
