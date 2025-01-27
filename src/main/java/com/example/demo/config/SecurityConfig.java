package com.example.demo.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

/**
 * Spring Security Config
 * 
 * @author sakaba
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    
    /** ユーザー情報取得Service */
    private final UserDetailsService userDetailsService;
    
    /** メッセージ取得 */
    private final MessageSource messageSource;

    //TODO MySQLと連携してemailとpasswordの照合（認証）を実装する
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(
                authoraize -> authoraize.requestMatchers("/login").permitAll() //「/login」は認証関係なく表示可能
                    .anyRequest().authenticated()) // それ以外のパスは認証が必要
            .formLogin(
                login -> login.loginPage("/login") // 自作ログイン画面(Controller)を使う
                    .usernameParameter("email") // ユーザー名パラメータのname属性
                    .defaultSuccessUrl("/home")) // ログイン成功後のリダイレクトURL(HomeController)
            .logout(logout -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }
    
    
    /**
     * パスワードハッシュ化
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
    }
    
    /**
     * Provider定義
     * 
     * @return カスタマイズProvaider情報
     */
    @Bean
    AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        provider.setMessageSource(messageSource);
        
        return provider;
    }
}