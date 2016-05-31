package com.br.lp3.logger;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author 41488350
 */
@Stateless
public class LoggerBean implements LoggerBeanInterface {

    @Resource(mappedName = "jms/myQueue")
    private Queue logger;
    @Inject
    @JMSConnectionFactory(value = "jms/__defaultConnectionFactory")
    private JMSContext context;

    @Override
    public void sendMessage(String msg) {
        context.createProducer().send(logger, msg);
    }
    
}
