package com.example.hogwartsmini.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Cros 跨域资源共享配置类
 */
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter(){
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();

        /**
         * 运行cookies跨域
         */
        config.setAllowCredentials(true);

        /**
         * 允许向该服务器提交请求的 URL , * 表示全部，在 SpringMVC 中，如果设成 * ，会自动转换成当前请求头中的 Origin
         */
        config.addAllowedOrigin("*");

        /**
         * 允许访问的请求头信息，* 表示全部
         */
        config.addAllowedHeader("*");

        /**
         * 预检请求的缓存时间(秒)，即在这个时间段里，对于相同的跨域请求不会再预检了
         */
        config.setMaxAge(18000L);

        /**
         * 允许提交的请求方法，* 表示全部，其他分别是 OPTIONS 等请求
         */
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
