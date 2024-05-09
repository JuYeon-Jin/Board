package com.board.FeatureHub.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.board.FeatureHub.dao")
public class MybatisConfig {
    // 여러가지 빈 설정 등을 추가
}
