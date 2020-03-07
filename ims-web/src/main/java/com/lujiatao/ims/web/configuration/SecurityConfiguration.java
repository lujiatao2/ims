package com.lujiatao.ims.web.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lujiatao.ims.common.entity.User;
import com.lujiatao.ims.common.model.BaseVO;
import com.lujiatao.ims.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)//启用方法级别的授权
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    //引用Dubbo接口
    @Reference(version = "1.0.0")
    private UserService userService;

    //认证
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            User user = userService.getByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("帐户不存在！");
            } else {
                if (!user.getIsActive()) {
                    throw new DisabledException("帐户已禁用！");
                } else {
                    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                    grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));
                    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
                }
            }
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    //授权
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/js/*", "/css/**", "/img/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").successHandler(new SuccessHandler()).usernameParameter("username").passwordParameter("password")
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        //禁用CSRF防护机制
        http.csrf().disable();
    }

    //自定义AuthenticationSuccessHandler，让其返回JSON数据，方便前端处理。
    private static class SuccessHandler implements AuthenticationSuccessHandler {

        private ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new BaseVO(0, "成功！")));
        }

    }

}
