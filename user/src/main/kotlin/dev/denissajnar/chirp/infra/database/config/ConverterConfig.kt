package dev.denissajnar.chirp.infra.database.config

import dev.denissajnar.chirp.infra.database.converters.UserIdReadConverter
import dev.denissajnar.chirp.infra.database.converters.UserIdWriteConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ConverterConfig {

    @Bean
    fun userIdWriteConverter() = UserIdWriteConverter()

    @Bean
    fun userIdReadConverter() = UserIdReadConverter()
}
