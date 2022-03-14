package kr.sga.gkmarket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
	                .securityContexts(Arrays.asList(securityContext()))
	                .securitySchemes(Arrays.asList(apiKey()));
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
