package me.zhengjie.modules.mark.domain;

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
@TableName("mark")
public class Mark extends CommonModel<Mark> implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生编号")
    @NotNull
    private Integer id;

    @ApiModelProperty(value = "课程编号")
    @NotNull
    private Integer cid;

    @ApiModelProperty(value = "成绩")
    @NotNull
    private Integer grade;

    @ApiModelProperty(value = "编号")
    @TableId(type= IdType.AUTO)
    private Integer mid;

    public void copyFrom(Mark source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
