package com.glowbyte;

import com.ibm.mq.jms.MQConnectionFactory;
import com.sas.esp.clients.camel.EspComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.jms.JMSException;

@Configuration
public class CustomCamelConfiguration {
    @Autowired
    private Environment env;

    @Bean
    CamelContextConfiguration contextConfiguration() {
        return new CamelContextConfiguration() {
            public void beforeApplicationStart(CamelContext context) {
                context.addComponent("esp", new EspComponent());

                //System.out.println("Creating MQ connection factory...");

                MQConnectionFactory connFactory = new MQConnectionFactory();
                try {
                    connFactory.setHostName(env.getProperty("mc.broker.hostname"));
                    connFactory.setPort(env.getProperty("mc.broker.port", Integer.class));
                    connFactory.setQueueManager(env.getProperty("mc.broker.queuemanager"));
                    connFactory.setChannel(env.getProperty("mc.broker.channel"));
                    connFactory.setTransportType(env.getProperty("mc.broker.transporttype", Integer.class));
                }
                catch (JMSException e) {
                    //System.out.println("Unable to create MQ connection factory!");
                }

                JmsComponent mq = new JmsComponent();
                mq.setConnectionFactory(connFactory);
                context.addComponent("mq", mq);
            }

            public void afterApplicationStart(CamelContext camelContext) {}
        };
    }
}