package group5.number_hit.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Gorup5AuthConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  private MyLogoutHandler myLogoutHandler;

  /**
   * 誰がログインできるか(認証処理)
   */
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.inMemoryAuthentication().withUser("user1")
        .password("$2y$10$Pj3Nt9CXKY06BmrtG.ZFo.CxaBzHEO/.I4fnAgAPPUKxQ0Tm3Autu").roles("USER");
    auth.inMemoryAuthentication().withUser("user2")
        .password("$2y$10$Pj3Nt9CXKY06BmrtG.ZFo.CxaBzHEO/.I4fnAgAPPUKxQ0Tm3Autu").roles("USER");
    auth.inMemoryAuthentication().withUser("user3")
        .password("$2y$10$Pj3Nt9CXKY06BmrtG.ZFo.CxaBzHEO/.I4fnAgAPPUKxQ0Tm3Autu").roles("USER");
    auth.inMemoryAuthentication().withUser("user4")
        .password("$2y$10$Pj3Nt9CXKY06BmrtG.ZFo.CxaBzHEO/.I4fnAgAPPUKxQ0Tm3Autu").roles("USER");
    auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("pAssw0rd")).roles("ADMIN");
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  /**
   * 認証されたユーザがどこにアクセスできるか（認可処理）
   */
  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // Spring Securityのフォームを利用してログインを行う
    http.formLogin();

    http.authorizeRequests().antMatchers("/yubisuma/**").authenticated();

    // 完成版は以下二行をコメントアウト
    http.csrf().disable();
    http.headers().frameOptions().disable();

    // Spring Securityの機能を利用してログアウト．ログアウト時は http://localhost:8080/ に戻る
    // ログアウト時にデータベースから削除する処理をMyLogoutHandlerにて実装
    http.logout().addLogoutHandler(myLogoutHandler).logoutSuccessUrl("/");

  }

}
