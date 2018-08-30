package com.glowbyte

import com.ibm.mq.jms.MQConnectionFactory
import org.apache.camel.CamelContext
import org.apache.camel.component.jms.JmsComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class Application {

    @Autowired
    lateinit var cf: MQConnectionFactory

    @Bean(name = ["jms"])
    fun jmsComponent(): JmsComponent {
        val jmsc = JmsComponent()
        jmsc.setConnectionFactory(cf)
        return jmsc
    }
}


fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}