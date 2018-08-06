package com.glowbyte;

import com.ibm.mq.jms.MQConnectionFactory;
import com.sas.esp.clients.camel.EspComponent;
import org.apache.camel.component.jms.JmsComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@ImportResource("classpath:beans.xml")
public class CamelKafkaApplication {

    @Value("${mq.broker.hostname}")
    private String hostName;

    @Value("${mq.broker.port}")
    private Integer port;

    @Value("${mq.broker.queuemanager}")
    private String queueManager;

    @Value("${mq.broker.channel}")
    private String channel;

    @Value("${mq.broker.transporttype}")
    private Integer transportType;

    private ConnectionFactory getMQConnFactory () {
        MQConnectionFactory cf = new MQConnectionFactory();
        try {
            cf.setHostName(hostName);
            cf.setPort(port);
            cf.setQueueManager(queueManager);
            cf.setChannel(channel);
            cf.setTransportType(transportType);
        }
        catch (Exception e) {}
        return cf;
    }

    @Bean(name = "jms")
    public JmsComponent mqJmsComponent() {
        JmsComponent jmsc = new JmsComponent();
        jmsc.setConnectionFactory(getMQConnFactory());
        return jmsc;
    }

    @Bean(name="esp")
    public EspComponent espComponent() {
        return new EspComponent();
    }

    public static void main(String[] args) {
        SpringApplication.run(CamelKafkaApplication.class, args);
    }
}
