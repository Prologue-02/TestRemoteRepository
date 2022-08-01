package me.zhengjie.modules.mark.service.dto;

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
public class MarkDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "学生编号")
    private Integer id;

    @ApiModelProperty(value = "课程编号")
    private Integer cid;

    @ApiModelProperty(value = "成绩")
    private Integer grade;

    @ApiModelProperty(value = "编号")
    private Integer mid;

    private String sname;

    private String cname;

    private String tname;

    private String year;

    private Integer tearm;
}
