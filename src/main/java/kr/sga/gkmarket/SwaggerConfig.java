package kr.sga.gkmarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	    @Bean
	    public Docket swaggerApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select()
	                .apis(RequestHandlerSelectors.any())
	                .paths(PathSelectors.any())
	                .build()
	                .apiInfo(apiInfo())
	                .securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(apiKey()));
	    }
	    
	    private ApiInfo apiInfo() {
	        return new ApiInfoBuilder()
	                .title("GKMarket Admin API Page")
	                .description("감춰진 기능이나 UI구현이 어려운 경우, 테스트는 관리자가 여기서 직접 Control합니다. \n"
	                			+ "401에러가 나는 경우 Authorize에서 토큰이 등록되어 있는지 확인하십시오.")
	                .version("1.0")
	                .build();
	    }
	    
	    private ApiKey apiKey() {
	        return new ApiKey("JWT", "Authorization", "header");
	    }
	    
	    @SuppressWarnings("deprecation")
		private SecurityContext securityContext() {
	        return springfox
	                .documentation
	                .spi.service
	                .contexts
	                .SecurityContext
	                .builder()
	                .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	    }

	    List<SecurityReference> defaultAuth() {
	        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
	        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	        authorizationScopes[0] = authorizationScope;
	        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
	    }
}
