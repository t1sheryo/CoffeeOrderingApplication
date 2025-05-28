package com.coffeeorder.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// Класс конфигурации, который включает механизм
// автоматического аудита в Spring Data JPA.
// Позволяет автоматически устанавливать значения
// для специальных полей в сущностях, таких как:
// - @CreatedDate
// - @LastModifiedDate
// - @CreatedBy
// - @LastModifiedBy

@Configuration
@EnableJpaAuditing
public class JpaAuditingConfig {
}
