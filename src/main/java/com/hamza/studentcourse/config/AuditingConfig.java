package com.hamza.studentcourse.config;

import org.springframework.context.annotation.Configuration;
//This annotation tells Spring that this class
//contains bean definitions or configuration settings.
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
//This is the most important annotation for enabling
// auditing features (like @CreatedDate...
@Configuration
@EnableJpaAuditing
public class AuditingConfig {
}