package com.glowbyte;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.component.kafka.KafkaManualCommit;
import org.springframework.stereotype.Component;

@Component
public class ManualCommit implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        KafkaManualCommit manual =
                exchange.getIn().getHeader(KafkaConstants.MANUAL_COMMIT, KafkaManualCommit.class);
        manual.commitSync();
    }
}
