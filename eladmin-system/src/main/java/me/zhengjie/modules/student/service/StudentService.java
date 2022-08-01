package me.zhengjie.modules.student.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.student.domain.Student;
import me.zhengjie.modules.student.service.dto.StudentDto;
import me.zhengjie.modules.student.service.dto.StudentQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
*/
public interface StudentService extends CommonService<Student>  {

    static final String CACHE_KEY = "student";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<StudentDto>
    */
    PageInfo<StudentDto> queryAll(StudentQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<StudentDto>
    */
    List<StudentDto> queryAll(StudentQueryParam query);

    Student getById(Integer id);
    StudentDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(StudentDto resources);
    int updateById(StudentDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<StudentDto> all, HttpServletResponse response) throws IOException;
}
