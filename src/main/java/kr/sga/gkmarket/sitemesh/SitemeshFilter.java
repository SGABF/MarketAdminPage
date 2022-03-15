package kr.sga.gkmarket.sitemesh;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        // 첫번째 매개변수 : 주소 ( 보통 controller의 requestMapping의 value 값 )
		//			* 등을 사용하여 다양하게 URL의 패턴으로 사용 가능.
		// 			본 예시의 첫번째 매개변수의 값은 모든 경로를 의미
        // 두번째 매개변수 : decorator 파일 경로
		builder.addDecoratorPath("/MainView/*", "/WEB-INF/decorator/decoratorList.jsp");
//		builder.addDecoratorPath("/qna/*", "/WEB-INF/decorator/decoratorList.jsp");
	}
}