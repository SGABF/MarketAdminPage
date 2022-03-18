package kr.sga.gkmarket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private String os = System.getProperty("os.name").toLowerCase();
	private String connectPath = "/imagePath/**";
	
	private String linuxResourcePath = "file:///resources/Back/";
	private String winResourcePath = "file:///D:/image/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if(os.contains("win")) {
	        registry.addResourceHandler(connectPath)
	                .addResourceLocations(winResourcePath);
		} else {
			registry.addResourceHandler(connectPath)
            .addResourceLocations(linuxResourcePath);
		}
    }
}
