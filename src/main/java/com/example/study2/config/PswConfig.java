package com.example.study2.config;

import com.example.study2.filter.PswFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PswConfig {

    @Bean
    public FilterRegistrationBean<PswFilter> pswFilter(){
        FilterRegistrationBean<PswFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new PswFilter());
        registrationBean.setOrder(1);
        registrationBean.addUrlPatterns("/v1/*");

        return registrationBean;
    }
}
