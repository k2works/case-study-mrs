package mrs;

import mrs.application.service.auth.UserAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Configuration
    @Profile("!test")
    @Order(1)
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
        private final UserAuthService userDetailsService;

        public WebAppSecurityConfig(UserAuthService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    // 認可
                    .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                    .antMatchers("/", "/content", "/contacts/gust/**").permitAll()
                    .antMatchers("/**").authenticated()
                    .and()
                    // 認証
                    .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/rooms", true)
                    .failureUrl("/loginForm?error=true").permitAll().and()
                    .exceptionHandling().accessDeniedPage("/accessDenied");
            http.logout().logoutUrl("/logout").logoutSuccessUrl("/loginForm");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

    @Configuration
    @Order(2)
    public static class ApiWebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/**");
        }
    }

    @Configuration
    @Profile("test")
    @Order(3)
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class WebAppTestSecurityConfig extends WebSecurityConfigurerAdapter {
        private final UserAuthService userDetailsService;

        public WebAppTestSecurityConfig(UserAuthService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Bean
        PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    // 認可
                    .antMatchers("/js/**", "/css/**", "/img/**").permitAll()
                    .antMatchers("/", "/content", "/contacts/gust/**").permitAll()
                    .antMatchers("/h2-console/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    // 認証
                    .formLogin()
                    .loginPage("/loginForm")
                    .loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/rooms", true)
                    .failureUrl("/loginForm?error=true").permitAll().and()
                    .exceptionHandling().accessDeniedPage("/accessDenied")
                    // For H2 Console
                    .and()
                    .headers().frameOptions().disable()
                    .and()
                    .csrf().disable();
            http.logout().logoutUrl("/logout").logoutSuccessUrl("/loginForm");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }

}
