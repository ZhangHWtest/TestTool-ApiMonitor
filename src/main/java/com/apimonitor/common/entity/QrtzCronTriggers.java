package com.apimonitor.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("QRTZ_CRON_TRIGGERS")
public class QrtzCronTriggers implements Serializable {

private static final long serialVersionUID=1L;

    @TableId("SCHED_NAME")
    private String schedName;

    @TableField("TRIGGER_NAME")
    private String triggerName;

    @TableField("TRIGGER_GROUP")
    private String triggerGroup;

    @TableField("CRON_EXPRESSION")
    private String cronExpression;

    @TableField("TIME_ZONE_ID")
    private String timeZoneId;


}
