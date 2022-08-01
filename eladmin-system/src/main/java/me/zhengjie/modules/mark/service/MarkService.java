package me.zhengjie.modules.mark.service;

import me.zhengjie.base.PageInfo;
import me.zhengjie.base.CommonService;
import me.zhengjie.modules.mark.domain.Mark;
import me.zhengjie.modules.mark.service.dto.MarkDto;
import me.zhengjie.modules.mark.service.dto.MarkQueryParam;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Set;

/**
* @author mazhijian
* @date 2022-05-16
*/
public interface MarkService extends CommonService<Mark>  {

    static final String CACHE_KEY = "mark";

    /**
    * 查询数据分页
    * @param query 条件
    * @param pageable 分页参数
    * @return PageInfo<MarkDto>
    */
    PageInfo<MarkDto> queryAll(MarkQueryParam query, Pageable pageable);

    /**
    * 查询所有数据不分页
    * @param query 条件参数
    * @return List<MarkDto>
    */
    List<MarkDto> queryAll(MarkQueryParam query);

    Mark getById(Integer id);
    MarkDto findById(Integer id);

    /**
     * 插入一条新数据。
     */
    int insert(MarkDto resources);
    int updateById(MarkDto resources);
    int removeById(Integer id);
    int removeByIds(Set<Integer> ids);

    /**
    * 导出数据
    * @param all 待导出的数据
    * @param response /
    * @throws IOException /
    */
    // void download(List<MarkDto> all, HttpServletResponse response) throws IOException;
    /**
     * */
    Object getCourseGradeByStudent();
    Object getAllUnMarkedStudent();
    Object getlUnMarkedStudentByCid(Integer cid);
    void insertStudentMark(Integer cid,Integer sid,Integer mark);
}
