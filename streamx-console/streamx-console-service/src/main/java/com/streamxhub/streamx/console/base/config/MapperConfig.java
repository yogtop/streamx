/*
 * Copyright 2019 The StreamX Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.streamxhub.streamx.console.base.config;

import com.streamxhub.streamx.console.base.mybatis.interceptor.PostgreSQLPrepareInterceptor;
import com.streamxhub.streamx.console.base.mybatis.interceptor.PostgreSQLQueryInterceptor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * for MyBatis Configure management.
 */
@Configuration
public class MapperConfig {

    /**
     * Add the plugin to the MyBatis plugin interceptor chain.
     *
     * @return {@linkplain PostgreSQLQueryInterceptor}
     */
    @Bean
    @ConditionalOnProperty(name = "streamx.database.dialect", havingValue = "postgresql")
    public PostgreSQLQueryInterceptor postgreSqlQueryInterceptor() {
        return new PostgreSQLQueryInterceptor();
    }

    /**
     * Add the plugin to the MyBatis plugin interceptor chain.
     *
     * @return {@linkplain PostgreSQLPrepareInterceptor}
     */
    @Bean
    @ConditionalOnProperty(name = "streamx.database.dialect", havingValue = "postgresql")
    public PostgreSQLPrepareInterceptor postgreSqlPrepareInterceptor() {
        return new PostgreSQLPrepareInterceptor();
    }

}
