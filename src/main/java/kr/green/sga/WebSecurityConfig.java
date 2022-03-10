package kr.green.sga;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	// 정적 자원에 대해서는 Security 설정 미적용
	@Override
	public void configure(WebSecurity web) throws Exception{
		web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
				// main 요청은 로그인 필요
				.antMatchers("/main/**").authenticated()
				// /admin 요청은 Admin 권한 필요
				.antMatchers("/admin/**").hasRole("ADMIN")
				// 나머지 요청은 로그인 미 요구 그러므로 /testapi는 그냥 작동해야됨
				.anyRequest().permitAll()
				.and()
//				.httpBasic();

				// 로그인 폼
				.formLogin()
				// 로그인 페이지 지정
				.loginPage("/login")
				// 로그인 성공시 이동할 페이지
				.defaultSuccessUrl("/main/testApi")
				// 로그인 실패시 이동할 페이지
				.failureForwardUrl("/login")
				.permitAll()
				.and()
				
				// 로그아웃 실행
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				// 로그아웃 시 실행할 작업
				.addLogoutHandler(new TaskImplementingLogoutHandler()).permitAll().logoutSuccessUrl("/");

					
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("admin").password(bCryptPasswordEncoder().encode("password")).roles("ADMIN", "SUPERADMIN")
			.and()
			.withUser("user").password(bCryptPasswordEncoder().encode("password")).roles("USER");
	}
	
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
