package ru.yusdm.shop.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static ru.yusdm.shop.admin.api.rest.AdminRestController.ADMIN_API;
import static ru.yusdm.shop.category.api.rest.CategoryRestController.CATEGORY_BASE_REST_CONTROLLER_URL;
import static ru.yusdm.shop.product.api.rest.ProductRestController.PRODUCT_BASE_REST_CONTROLLER_URL;

@Configuration
public class WebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(bCryptPasswordEncoder)
                .withUser(Users.SUPER_USER.getLogin())
                .password(bCryptPasswordEncoder.encode(Users.SUPER_USER.getPassword()))
                .roles(Role.ADMIN.getRoleName());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors()
                .and()
                .authorizeRequests()
                .antMatchers(CATEGORY_BASE_REST_CONTROLLER_URL, PRODUCT_BASE_REST_CONTROLLER_URL)
                .permitAll()

                .and()
                .authorizeRequests()
                .antMatchers(ADMIN_API + "/**").hasAnyRole(Role.ADMIN.getRoleName());
    }
}
