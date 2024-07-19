package hamsung.hamsung_project.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {

        corsRegistry.addMapping("/**")
                .exposedHeaders("Set-Cookie")
                .allowedOriginPatterns("*")
//                .allowedOrigins("http://localhost:3000", "https://localhost:3000", "https://nozigak.site", "https://127.0.0.1:3000")
                .allowCredentials(true); // 쿠키를 포함한 요청을 허용
//        corsRegistry.addMapping("/**")
//                .allowedOrigins("http://localhost:3000", "https://localhost:3000", "https://nozigak.site", "https://127.0.0.1:3000")
//                .exposedHeaders("Set-Cookie")
//                .allowCredentials(true);

    }
}
