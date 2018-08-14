package com.glowbyte;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExceptionProcessor  implements Processor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public void process(Exchange exchange) throws Exception {
        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOO " + exchange.getIn().getBody(String.class));
        //exchange.getIn().setBody("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
        log.info("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
    }

}
