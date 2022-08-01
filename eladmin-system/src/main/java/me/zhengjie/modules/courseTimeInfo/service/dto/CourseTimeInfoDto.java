package me.zhengjie.modules.courseTimeInfo.service.dto;

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
public class CourseTimeInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Integer timeid;

    @ApiModelProperty(value = "课程编号")
    private Integer cid;

    @ApiModelProperty(value = "周")
    private String week;

    @ApiModelProperty(value = "星期")
    private String day;

    @ApiModelProperty(value = "时段")
    private String trap;

    @ApiModelProperty(value = "上课地址")
    private String adress;

    private String cname;

    private String tname;
}
