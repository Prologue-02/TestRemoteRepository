package me.zhengjie.modules.course.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Transient;

/**
* @author mazhijian
* @date 2022-05-16
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程编号")
    private Integer cid;

    @ApiModelProperty(value = "课程名")
    private String cname;

    @ApiModelProperty(value = "课时")
    private Integer classtime;

    @ApiModelProperty(value = "周数")
    private Integer classweek;

    @ApiModelProperty(value = "教师编号")
    private Integer tid;

    @ApiModelProperty(value = "学期")
    private Integer tearm;

    @ApiModelProperty(value = "学年")
    private String year;

    @ApiModelProperty(value = "状态")
    private Integer status;

    private String tname;
}
