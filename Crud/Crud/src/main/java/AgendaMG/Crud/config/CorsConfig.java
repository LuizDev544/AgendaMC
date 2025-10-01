// Código do CorsConfig.java permanece o mesmo.
package AgendaMG.Crud.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();

    // 🔹 Permite requisições do seu front
    config.setAllowedOrigins(List.of("http://127.0.0.1:5500", "http://localhost:5500", "http://localhost:8080"));

    // 🔹 Métodos liberados
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

    // 🔹 Headers permitidos
    config.setAllowedHeaders(List.of("*"));

    // 🔹 Permite envio de cookies (JSESSIONID)
    config.setAllowCredentials(true);

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);

    return source;
  }
}