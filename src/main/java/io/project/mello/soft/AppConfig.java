package io.project.mello.soft;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import(JPAConfig.class)
@Configuration
@ComponentScan("io.project.mello.soft")
public class AppConfig {
}
