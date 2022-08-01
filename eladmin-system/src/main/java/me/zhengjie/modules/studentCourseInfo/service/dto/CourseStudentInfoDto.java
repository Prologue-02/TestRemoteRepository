package me.zhengjie.modules.studentCourseInfo.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
* @author mazhijian
* @date 2022-05-26
*/
@Data
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CourseStudentInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Integer rid;

    @ApiModelProperty(value = "学生编号")
    private Integer sid;

    @ApiModelProperty(value = "课程编号")
    private Integer cid;

    @ApiModelProperty(value = "教学评价")
    private Integer judge;
}
