package me.zhengjie.modules.course.domain;

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
import javax.persistence.*;

import javax.validation.constraints.*;
import java.io.Serializable;

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
@TableName("course")
public class Course extends CommonModel<Course> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程编号")
    @TableId(type= IdType.AUTO)
    private Integer cid;

    @ApiModelProperty(value = "课程名")
    @NotBlank
    private String cname;

    @ApiModelProperty(value = "课时")
    @NotNull
    private Integer classtime;

    @ApiModelProperty(value = "周数")
    @NotNull
    private Integer classweek;

    @ApiModelProperty(value = "教师编号")
    @NotNull
    private Integer tid;

    @ApiModelProperty(value = "学期")
    @NotNull
    private Integer tearm;

    @ApiModelProperty(value = "学年")
    @NotBlank
    private String year;

    @ApiModelProperty(value = "状态")
    @NotNull
    private Integer status;

    public void copyFrom(Course source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
