package com.apimonitor.httpclient.mapper;

import com.apimonitor.httpclient.entity.HttpSequence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * http序列表 Mapper 接口
 * </p>
 *
 * @author zhwtest
 * @since 2020-03-19
 */
public interface HttpSequenceMapper extends BaseMapper<HttpSequence> {
    @Update("UPDATE http_sequence t SET t.`enabled` = #{enabled} WHERE t.`job_id`= #{jobId} ")
    void updateEnabled(@Param("jobId")String jobId, @Param("enabled")int enabled);


}
