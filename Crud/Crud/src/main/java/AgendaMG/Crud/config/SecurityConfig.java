// Código do SecurityConfig.java permanece o mesmo.
package AgendaMG.Crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(AbstractHttpConfigurer::disable) // 1. DESATIVA CSRF
      .cors(cors -> {}) // 2. Habilita CORS (referencia CorsConfig.java)
      
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/auth/**", "/", "/index.html", "/login.html", "/scripts/**", "/styles/**").permitAll() 
        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 🚨 REGRA CRÍTICA DE AUTORIZAÇÃO
        .anyRequest().authenticated()
      )
      .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) 
        .maximumSessions(1)
      )
      .formLogin(AbstractHttpConfigurer::disable)
      .httpBasic(AbstractHttpConfigurer::disable)
      .logout(logout -> logout
        .logoutUrl("/auth/logout")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutSuccessUrl("/login.html") 
      );

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
return config.getAuthenticationManager();
}
}