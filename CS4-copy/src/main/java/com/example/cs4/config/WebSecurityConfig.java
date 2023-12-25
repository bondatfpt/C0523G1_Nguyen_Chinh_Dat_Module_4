package com.example.cs4.config;


import com.example.cs4.account.service.AccountDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountDetailService accountDetailService;

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //gọi userDetailsService trong bước 5 tiếp theo
        auth.userDetailsService(accountDetailService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // Các trang không yêu cầu login
        http.authorizeRequests().antMatchers("/","/signup", "/login","/showFormActiveAccount","/activeAccount",
                             "/public/**","/new","/showFormSendEmail","/sendEmailGetAccount","/showFormGetAccount","/gmail","/getAccount").permitAll();

        // Trang /userInfo yêu cầu phải login với vai trò ROLE_USER hoặc ROLE_ADMIN.
        // Nếu chưa login, nó sẽ redirect tới trang /login.
//        http.authorizeRequests().antMatchers("/userInfo").hasAnyAuthority("admin", "user");
        http.authorizeRequests().antMatchers("/accountInfo").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_USER')");
        http.authorizeRequests().antMatchers("/logout").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_USER')");
        http.authorizeRequests().antMatchers("/bookings").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')");
        http.authorizeRequests().antMatchers("/bookings/create/**").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_USER')");
        http.authorizeRequests().antMatchers("/bookings/confirm").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE','ROLE_USER')");
        http.authorizeRequests().antMatchers("/deleteAccount").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/bookings/update/**").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')");
        http.authorizeRequests().antMatchers("/customers/**").access("hasAnyRole('ROLE_ADMIN','ROLE_EMPLOYEE')");
        http.authorizeRequests().antMatchers("/employee/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/times/**").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().antMatchers("/yards/list/**").access("hasRole('ROLE_ADMIN')")
                .and().rememberMe().userDetailsService(accountDetailService).tokenValiditySeconds(60*4);


        // Khi người dùng đã login, với vai trò XX.
        // Nhưng truy cập vào trang yêu cầu vai trò YY,
        // Ngoại lệ AccessDeniedException sẽ ném ra.
        http.authorizeRequests().anyRequest().authenticated().and().exceptionHandling().accessDeniedPage("/403");

        // Cấu hình cho Login Form.
        http.authorizeRequests().and().formLogin()//
                // Submit URL của trang login
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Cấu hình cho Logout Page.
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");


    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
        return memory;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
