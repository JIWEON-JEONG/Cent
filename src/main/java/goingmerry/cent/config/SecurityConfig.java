package goingmerry.cent.config;

import goingmerry.cent.oauth.OAuth2AccessTokenAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final OAuth2AccessTokenAuthenticationFilter oAuth2AccessTokenAuthenticationFilter;
    private final CorsFilter corsFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        그리고 각 유저의 role 에 따른 웹 리소스 제한 하는 방법은
//        configure(HttpSecurity http) 메소드에 다음의 문법을 적용하면 된다
//                .antMatchers("url").hasRole("roleName")

        //== 접근 제한 ==//
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션을 사용하지 않겠다!
                .and()
                .addFilter(corsFilter)
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/login/oauth2/*").permitAll() //로그인 화면 접근 가능
                .antMatchers("/").permitAll() //메인 화면 접근 가능
                .antMatchers("/api/team/*").permitAll();
        // 아직 적용은 안 했지만, api 구성을 마치게 되면 role 별 api 접근을 제한하도록 할 것.
//              .anyRequest().hasRole("USER");

        http.addFilterBefore(oAuth2AccessTokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }



}
