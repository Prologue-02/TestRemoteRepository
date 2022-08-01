package me.zhengjie.modules.courseTimeInfo.domain;

import me.zhengjie.base.CommonModel;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
import java.util.Date;
import java.sql.Timestamp;

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
@TableName("course_time_info")
public class CourseTimeInfo extends CommonModel<CourseTimeInfo> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(type= IdType.AUTO)
    private Integer timeid;

    @ApiModelProperty(value = "课程编号")
    @NotNull
    private Integer cid;

    @ApiModelProperty(value = "周")
    private String week;

    @ApiModelProperty(value = "星期")
    private String day;

    @ApiModelProperty(value = "时段")
    private String trap;

    @ApiModelProperty(value = "上课地址")
    private String adress;

    public void copyFrom(CourseTimeInfo source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
