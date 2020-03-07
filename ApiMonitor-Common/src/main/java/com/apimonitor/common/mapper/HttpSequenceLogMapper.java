package com.apimonitor.common.mapper;


import com.apimonitor.common.entity.HttpSequenceLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface HttpSequenceLogMapper extends BaseMapper<HttpSequenceLog> {

//	@Insert("INSERT INTO http_sequence_log(pguid,`status`,`costTime`,log"
//			+ ")  VALUES("
//			+"#{httpSequenceLog.pguid},#{httpSequenceLog.status},#{httpSequenceLog.costTime},#{httpSequenceLog.log})")
//	@Options(useGeneratedKeys = true, keyProperty = "httpSequenceLog.id", keyColumn = "id")
//	void insert(@Param("httpSequenceLog") HttpSequenceLog httpSequenceLog);

	@Select("SELECT * FROM http_sequence_log")
	List<HttpSequenceLog> selectAll();

	@Select("select t.* from http_sequence_log t where t.pguid=#{pguid}")
	HttpSequenceLog getByGuid(@Param("pguid") String pguid);

	@Delete("delete from http_sequence_log where pguid=#{pguid}")
	void delete(@Param("pguid") String pguid);


	@Select("select t.`status` from http_sequence_log t where t.pguid = #{pguid} order by t.createTime desc limit 1")
	Object selectRecentStatusByPguid(@Param("pguid") String pguid);

	@Select("select t.`status`,count(t.`status`) as count from http_sequence_log t where t.pguid = #{pguid} group by t.`status`")
	List<Map<String,Object>> selectUsabilityByPguid(@Param("pguid") String pguid);

	@Select("select ROUND(AVG(t.costTime)) as costTime from http_sequence_log t where t.pguid = #{pguid}")
	Object selectAvgCostTimeByPguid(@Param("pguid") String pguid);


	@Select("select t.id,t.pguid,t.createTime,t.`status`,t.costTime,t.log from http_sequence_log t where t.pguid = #{pguid}")
	List<Map<String,Object>> selectLogByPguid(@Param("pguid") String pguid);

	@Delete("delete from http_sequence_log where createTime < date_sub(curdate(),interval #{day} day)")
	void cleanLogByDay(@Param("day") int day);
}
