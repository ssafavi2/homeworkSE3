package myweb

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.annotation.EnableRetry
import org.springframework.web.client.RestTemplate

@Configuration
@EnableRetry
class MyWebConfiguration {
    @Bean
    fun getRestTemplate() = RestTemplate()
}