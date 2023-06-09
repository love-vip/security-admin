package com.vip.admin.commons.core.mybatis;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Transient;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author echo
 * @title: BaseEntity
 * @date 2023/3/15 16:30
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 2393269568666085258L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 创建人
     */
    @TableField(value = "creator", fill = FieldFill.INSERT)
    private String creator;

    /**
     * 创建人id
     */
    @TableField(value = "creator_id", fill = FieldFill.INSERT)
    private Long creatorId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 最近操作人
     */
    @TableField(value = "last_operator", fill = FieldFill.UPDATE)
    private String lastOperator;

    /**
     * 最近操作人id
     */
    @TableField(value = "last_operator_id", fill = FieldFill.UPDATE)
    private Long lastOperatorId;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updateTime;

    /**
     * Is new boolean.
     *
     * @return the boolean
     */
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

}
