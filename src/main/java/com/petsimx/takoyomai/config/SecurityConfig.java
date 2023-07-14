package com.petsimx.takoyomai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.petsimx.takoyomai.service.impl.UserInformationDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	public UserDetailsService userDetailsService() {
		return new UserInformationDetailsServiceImpl();
	}
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(t -> t.disable())
        		.authorizeHttpRequests(r -> r
				.requestMatchers("/user/create").permitAll()
        		.requestMatchers("/static/**", "/assets/**").permitAll()
				.requestMatchers("/**").authenticated())
        		.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .formLogin(t -> t.loginPage("/").permitAll())
                .build();
        		
//        		http.cors(t -> t.disable())
//        		.authorizeHttpRequests(t -> t
//        				.requestMatchers("/**").authenticated()
//        				.requestMatchers("/user/create").permitAll()
//        				.requestMatchers("/static/**").permitAll()
//        				.requestMatchers("/assets/**").permitAll())
//        		.formLogin(t -> t.loginPage("/").permitAll())
//        		.build();
        		
        		//List<RequestMatcher> matchers = RequestMatchers.not(null)
        		
//        		http.securityMatchers((matchers) -> matchers
// 				.requestMatchers("/static/**")
// 				.requestMatchers("/assets/**"))
//        		.authorizeHttpRequests(t -> t
//        				.requestMatchers("/**").authenticated()
//        				.requestMatchers("/user/create").permitAll())
//        		.formLogin(t -> t.loginPage("/").permitAll())
//        		.build();

        		
//        		http.csrf(t -> t.disable())
//        		.authorizeHttpRequests(t -> t
//        				.requestMatchers("/**").authenticated()
//        				.requestMatchers("/user/create").permitAll())
//        		.formLogin(t -> t.loginPage("/").permitAll())
//        		.build();
        		
//        		http.csrf().disable()
//        		.authorizeHttpRequests()
//        		.anyRequest().authenticated()
//        		.and()
//        		.authorizeHttpRequests()
//        		.requestMatchers("/user/create").permitAll()
//        		.and()
//        		.formLogin()
//        		.loginPage("/")
//        		.permitAll()
//        		.and()
//        		.build();
        		
//        		csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/products/welcome","/products/new").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/products/**")
//                .authenticated().and().formLogin().and().build();
        
//        		http.csrf().disable()
//        		.authorizeRequests()
//        		.anyRequest()
//        		.authenticated()
//        		.and()
//        		.formLogin()
//        		.loginPage("/")
//        		.permitAll();
        		
//        		http.csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/", "/user/create").permitAll()
//                .and().build();
    }
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
