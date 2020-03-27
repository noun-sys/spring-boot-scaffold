package com.noun.sys.scaffold.base.apiversion;

import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;

/**
 * @Author gaoxu
 * @Date 2019-05-28 16:57
 */
@Configuration
public class WebMvcRegistrationsAdapter implements WebMvcRegistrations{
    @Override
    public org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new RequestMappingHandlerMapping();
    }
}
