package com.aaa.ygy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.aaa.ygy.staticstatus.RequestURLProperties.PACKAGE_CONTROLLER_URL;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 18:49
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()// 选择swagger具体生效的接口是什么？(service, controller, mapper)
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_CONTROLLER_URL))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测绘管理系统")
                .description("某市的测绘管理系统")
                .contact(new Contact("Seven Lee", "http://www.seven.com", "sevenLee@Gmail.com"))
                .version("1.0")
                .build();
    }

}
