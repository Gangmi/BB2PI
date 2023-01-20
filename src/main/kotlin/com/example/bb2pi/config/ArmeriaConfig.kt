package com.example.bb2pi.config

import com.linecorp.armeria.client.ClientFactory
import com.linecorp.armeria.client.WebClientBuilder
import com.linecorp.armeria.client.circuitbreaker.CircuitBreakerClient
import com.linecorp.armeria.client.circuitbreaker.CircuitBreakerRule
import com.linecorp.armeria.server.ServerBuilder
import com.linecorp.armeria.server.docs.DocService
import com.linecorp.armeria.server.healthcheck.HealthCheckService
import com.linecorp.armeria.server.logging.AccessLogWriter
import com.linecorp.armeria.server.logging.LoggingService
import com.linecorp.armeria.spring.ArmeriaServerConfigurator
import com.linecorp.armeria.spring.web.reactive.ArmeriaClientConfigurator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * An example of a configuration which provides beans for customizing the server and client.
 */
@Configuration
class ArmeriaConfig {
    /**
     * A user can configure a [Server] by providing an [ArmeriaServerConfigurator] bean.
     */
    @Bean
    fun armeriaServerConfigurator(): ArmeriaServerConfigurator {
        // Customize the server using the given ServerBuilder. For example:
        return ArmeriaServerConfigurator { builder: ServerBuilder ->
            // Add DocService that enables you to send Thrift and gRPC requests from web browser.
            builder.serviceUnder("/docs", DocService())

            // Log every message which the server receives and responds.
            builder.decorator(LoggingService.newDecorator())
            builder.service("/health/check", HealthCheckService.of())

            // Write access log after completing a request.
            builder.accessLogWriter(AccessLogWriter.combined(), false)
        }
    }

    /**
     * Returns a custom [ClientFactory] with TLS certificate validation disabled,
     * which means any certificate received from the server will be accepted without any verification.
     * It is used for an example which makes the client send an HTTPS request to the server running
     * on localhost with a self-signed certificate. Do NOT use the [ClientFactory.insecure] or
     * [ClientFactoryBuilder.tlsNoVerify] in production.
     */
    @Bean
    fun clientFactory(): ClientFactory {
        return ClientFactory.insecure()
    }

    /**
     * A user can configure an [HttpClient] by providing an [ArmeriaClientConfigurator] bean.
     */
    @Bean
    fun armeriaClientConfigurator(clientFactory: ClientFactory?): ArmeriaClientConfigurator {
        // Customize the client using the given WebClientBuilder. For example:
        return ArmeriaClientConfigurator { builder: WebClientBuilder ->
            // Use a circuit breaker for each remote host.
            val rule = CircuitBreakerRule.builder()
                .onServerErrorStatus()
                .onException()
                .thenFailure()
            builder.decorator(
                CircuitBreakerClient.builder(rule)
                    .newDecorator()
            )

            // Set a custom client factory.
            builder.factory(clientFactory!!)
        }
    }
}