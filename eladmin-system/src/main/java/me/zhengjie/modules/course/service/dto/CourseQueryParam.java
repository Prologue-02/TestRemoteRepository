package me.zhengjie.modules.course.service.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Date;
import me.zhengjie.annotation.Query;
import org.springframework.format.annotation.DateTimeFormat;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Getter
@Setter
public class CourseQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String cname;

    /** 精确 */
    @Query
    private Integer tearm;

    /** 精确 */
    @Query
    private String year;

    /** 精确 */
    @Query
    private Integer status;

}
