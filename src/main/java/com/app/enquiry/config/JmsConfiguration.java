package com.app.enquiry.config;

import com.app.enquiry.utils.CustomJmsListener;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
//import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.transaction.PlatformTransactionManager;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;

@Slf4j
@PropertySource("classpath:application.properties")
class JmsConfiguration {
    // private static final Logger logger = LogManager.getLogger(JmsConfiguration.class);

    @Value("${ibm.mq.queue}")
    private String queue;

    @Value("${ibm.mq.port}")
    private Integer port;

    @Value("${ibm.mq.host}")
    private String host;

    @Value("${ibm.mq.queueManager}")
    private String queueManager;

    @Value("${ibm.mq.channel}")
    private String channel;

    @Value("${ibm.mq.receiveTimeout}")
    private long receiveTimeout;

    @Resource(name = "internalJmsConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource
    private CustomJmsListener customJmsListener;

    private int maxConsumers = 3;

  /*
  @Bean
  public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                          DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    return factory;
  }

  @Bean 
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    return converter;
  }
  
  */

    @Bean
    public MQQueueConnectionFactory mqQueueConnectionFactory() {
        MQQueueConnectionFactory mqQueueConnectionFactory = new MQQueueConnectionFactory();
        mqQueueConnectionFactory.setHostName(host);
        try {
            mqQueueConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqQueueConnectionFactory.setCCSID(1208);
            mqQueueConnectionFactory.setChannel(channel);
            mqQueueConnectionFactory.setPort(port);
            mqQueueConnectionFactory.setQueueManager(queueManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mqQueueConnectionFactory;
    }

    @Bean
    public PlatformTransactionManager jmsTransactionManager(MQQueueConnectionFactory mqConnectionFactory) {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(mqConnectionFactory);
        return jmsTransactionManager;
    }

    @Bean
    public JmsTemplate jmsOperations(MQQueueConnectionFactory mqConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(mqConnectionFactory);
        jmsTemplate.setReceiveTimeout(receiveTimeout);
        return jmsTemplate;
    }

    /*@Bean
    public SimpleMessageListenerContainer queueContainer(MQQueueConnectionFactory mqQueueConnectionFactory) {

        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(mqQueueConnectionFactory);
        container.setDestinationName(queue);
        container.setMessageListener(getListenerWrapper());
        container.start();
        return container;
    }

    @Bean
    public MQListener getListenerWrapper() {
        return new MQListener();
    }*/

}
