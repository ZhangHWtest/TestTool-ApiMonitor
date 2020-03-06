package com.apimonitor.system.config;


import com.apimonitor.system.entity.resultException.CommonException;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.apimonitor.system.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 */
@Component
public class JwtInterceptor  extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 进入到控制器方法之前执行的内容，
     *      返回boolean
     *          true继续执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /**
         * 1、通过拦截器request获取头信息中的token
         * 2、从token中解析claims
         * 3、将claims绑定到request中
         */
        // 获取token
        String token=request.getHeader("token");
        //判断是否为空，或者已经过期
        if(!StringUtils.isEmpty(token)) {
            try {
                //2、从token中解析claims
                Claims claims = jwtUtils.parseJwt(token);
                if (claims != null) {
                    request.setAttribute("user_claims", claims);
                    return true;
                }
            }catch (Exception e){
                throw new CommonException(ResultCode.TOKENEXPIRE);
            }

        }
        throw new CommonException(ResultCode.UNAUTHENTICATED);

    }
}
