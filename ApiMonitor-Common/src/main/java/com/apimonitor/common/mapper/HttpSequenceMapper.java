package com.apimonitor.common.mapper;

import com.apimonitor.common.entity.HttpSequence;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

@Mapper
public interface HttpSequenceMapper extends BaseMapper<HttpSequence> {


	@Select("select t.* from http_sequence t where t.guid=#{guid}")
	HttpSequence getByGuid(@Param("guid") String guid);

	
	
}
