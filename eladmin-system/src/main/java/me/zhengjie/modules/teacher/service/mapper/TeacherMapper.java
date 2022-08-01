package me.zhengjie.modules.teacher.service.mapper;

import me.zhengjie.base.CommonMapper;
import me.zhengjie.modules.teacher.domain.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Repository
public interface TeacherMapper extends CommonMapper<Teacher> {
    List<Map<String, Object>> getTeacherInfo();
}
