package cn.eshop.cloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 所有的请求，都需要经过HTTP basic认证
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 明文编码器。这是一个不做任何操作的密码编码器，是Spring提供给我们做明文测试的。
        // A password encoder that does nothing. Useful for testing where working with plain text
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    private MyUserDetailService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Component
    class MyUserDetailService implements UserDetailsService {
        /**
         * 模拟两个账户：
         * ① 账号是user，密码是password1，角色是user-role
         * ② 账号是admin，密码是password2，角色是admin-role
         */
        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            if ("user".equals(username)) {
                return new MyUser("user", "password1", "user-role");
            } else if ("admin".equals(username)) {
                return new MyUser("admin", "password2", "admin-role");
            } else {
                return null;
            }
        }
    }

    static class MyUser extends User {
        public MyUser(String username, String password, String role) {
            super(username, password, Arrays.asList(new SimpleGrantedAuthority(role)));
        }
    }
}
