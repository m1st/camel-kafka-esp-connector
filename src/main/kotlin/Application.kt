package com.glowbyte.kotlin.camelkafkaconnector

import com.ibm.mq.jms.MQConnectionFactory
import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.apache.camel.component.jms.JmsComponent
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import javax.jms.ConnectionFactory

@SpringBootApplication
@EnableAdminServer
class Application {
    @Value("\${mq.broker.hostname}")
    private val hostName: String? = null

    @Value("\${mq.broker.port}")
    private val port: Int? = null

    @Value("\${mq.broker.queuemanager}")
    private val queueManager: String? = null

    @Value("\${mq.broker.channel}")
    private val channel: String? = null

    @Value("\${mq.broker.transporttype}")
    private val transportType: Int? = null

    private fun getMQConnFactory(): ConnectionFactory {
        val cf = MQConnectionFactory()
        try {
            cf.hostName = hostName
            cf.port = port!!
            cf.queueManager = queueManager
            cf.channel = channel
            cf.transportType = transportType!!
        } catch (e: Exception) {}

        return cf
    }

    @Bean(name = ["jms"])
    fun mqJmsComponent() : JmsComponent {
        val jmsc = JmsComponent()
        jmsc.setConnectionFactory(getMQConnFactory())
        return jmsc
    }

    @Configuration
    class SecurityPermitAllConfig : WebSecurityConfigurerAdapter() {
        @Throws(Exception::class)
        override fun configure(http: HttpSecurity) {
            http.authorizeRequests().anyRequest().permitAll()
                    .and().csrf().disable()
        }
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
