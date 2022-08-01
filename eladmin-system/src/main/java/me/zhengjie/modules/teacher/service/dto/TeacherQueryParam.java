package me.zhengjie.modules.teacher.service.dto;

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
public class TeacherQueryParam{

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String tname;

    /** 精确 */
    @Query
    private String sex;

    /** 模糊 */
    @Query(type = Query.Type.INNER_LIKE)
    private String address;

    /** 精确 */
    @Query
    private Integer status;

    /** BETWEEN */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Query(type = Query.Type.BETWEEN)
    private List<Integer> age;
}
