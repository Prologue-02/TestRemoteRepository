package me.zhengjie.modules.studentCourseInfo.domain;

import me.zhengjie.base.CommonModel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import javax.validation.constraints.*;
import java.io.Serializable;

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
@TableName("course_student_info")
public class CourseStudentInfo extends CommonModel<CourseStudentInfo> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(type= IdType.AUTO)
    private Integer rid;

    @ApiModelProperty(value = "学生编号")
    @NotNull
    private Integer sid;

    @ApiModelProperty(value = "课程编号")
    @NotNull
    private Integer cid;

    @ApiModelProperty(value = "教学评价")
    private Integer judge;

    public void copyFrom(CourseStudentInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
