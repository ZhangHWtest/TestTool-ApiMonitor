package com.apimonitor.common.mapper;


import com.apimonitor.common.entity.HttpRequestLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface HttpRequestLogMapper  extends BaseMapper<HttpRequestLog> {

	@Insert("INSERT INTO http_request_log(pid,ppguid,pguid,`status`,`costTime`,`statusCode`,log"
			+ ")  VALUES("
			+"#{httpRequestLog.pid},#{httpRequestLog.ppguid},#{httpRequestLog.pguid},#{httpRequestLog.status},#{httpRequestLog.costTime},"
			+ "#{httpRequestLog.statusCode},#{httpRequestLog.log})")
	void insertMyHttpRequestLog(@Param("httpRequestLog") HttpRequestLog httpRequestLog);

	@Select("SELECT * FROM http_request_log")
	List<HttpRequestLog> selectAll();

	@Select("select t.* from http_request_log t where t.pguid=#{pguid}")
	HttpRequestLog getByGuid(@Param("pguid") String pguid);

	@Delete("delete from http_request_log where ppguid=#{ppguid}")
	void delete(@Param("ppguid") String ppguid);

	@Select("select r.httpMethod,r.url,t.`status`,t.statusCode,t.costTime,t.log from http_request_log t,http_request r where t.pguid=r.guid and t.pid=#{id}")
	List<Map<String,Object>> getByPid(@Param("id") int id);


	@Delete("delete from http_request_log where createTime < date_sub(curdate(),interval #{day} day)")
	void cleanLogByDay(@Param("day") int day);
}
