package com.example.demo.configuration;


//import com.example.demo.security.JwtAuthenticationEntryPoint;
//import com.example.demo.security.JwtAuthenticationFilter;
//import com.example.demo.security.UsDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    UsDetailsService usDetailsService;
//    @Autowired
//    JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Bean
//    JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(usDetailsService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable().exceptionHandling().
//                authenticationEntryPoint(jwtAuthenticationEntryPoint).
//                and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
//                authorizeRequests().antMatchers("/").
//                permitAll().antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui",
//                "/swagger-resources", "/swagger-resources/configuration/security",
//                "/swagger-ui.html", "/webjars/**").permitAll().
//                antMatchers("api/auth").
//                permitAll().antMatchers("api/user/**").
//                permitAll().antMatchers("api/card/**").
//                permitAll().antMatchers("api/transaction/**").permitAll().anyRequest().authenticated();
//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()      .antMatchers("/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/webjars/**").permitAll()

                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/account/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/user/**").permitAll()

                //      .antMatchers(HttpMethod.POST, "/api/creditCards/**", "/api/persons/**").permitAll()
                //    .antMatchers(HttpMethod.PUT, "/api/creditCards/**", "/api/persons/**").permitAll()
                .antMatchers("/api/creditCards").permitAll()
                .antMatchers("/api/persons").permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**", "/configuration/ui");
    }
}
