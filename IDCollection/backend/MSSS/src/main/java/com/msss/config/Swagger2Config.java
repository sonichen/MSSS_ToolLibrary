package com.msss.config;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;

@Configuration
public class Swagger2Config {

    @Bean
    public Docket group1() {
        return new Docket(DocumentationType.OAS_30) // v2 不同
                .apiInfo(apiInfo())
                .groupName("接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.msss.controller")) // 设置扫描路径
                .paths(PathSelectors.any())
                .build()
.securityContexts(Arrays.asList(securityContext()))
                // ApiKey的name需与SecurityReference的reference保持一致
                .securitySchemes(Arrays.asList(new ApiKey("token", "token", SecurityScheme.In.HEADER.name())));

//                .globalRequestParameters(
//                        singletonList(new springfox.documentation.builders.RequestParameterBuilder()
//                                // 不能叫Authorization
//                                .name("token")
//                                .description("token")
//                                .in(ParameterType.HEADER)
//                                .required(true)
//                                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                                .build()));

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder().title("云笔记接口文档").description("By msss").version("1.0.0")
                .contact(new Contact("云笔记","http://localhost:8081/","msss@example.com")).build();
    }
    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                //.forPaths(PathSelectors.regex("/*.*"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return singletonList(
                new SecurityReference("token", authorizationScopes));
    }

}
