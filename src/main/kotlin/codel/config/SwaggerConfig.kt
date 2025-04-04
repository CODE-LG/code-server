package codel.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI =
        OpenAPI()
            .info(
                Info()
                    .title("Code:L API")
                    .description("API 문서입니다.")
                    .version("v1.0.0")
                    .contact(
                        Contact()
                            .name("code")
                            .email("codel@gmail.com"),
                    ).license(
                        License()
                            .name("Apache 2.0")
                            .url("http://www.apache.org/licenses/LICENSE-2.0"),
                    ),
            )
}
