package uz.pdp.websocketdemo.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.websocketdemo.service.AuthService;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthService authService;

    public SecurityConfig(@Lazy AuthService authService) {
        this.authService = authService;
    }
//    JwtFilter jwtFilter;
//
//    public SecurityConfig(@Lazy AuthService authService, @Lazy JwtFilter jwtFilter) {
//        this.authService = authService;
//        this.jwtFilter = jwtFilter;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
//                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/login", "/register").permitAll();

        http
                .formLogin()
//                .loginPage("/login.html")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/homepage.html", true)
                .failureUrl("/login.html?error=true")
                .and()
                .logout()
                .deleteCookies("JSESSIONID");

        http.authorizeRequests()
                .anyRequest()
//                .permitAll();
                .authenticated();

//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




}
