package com.app.enquiry.config;

class JmsConfiguration {
  private static final Logger logger = LogManager.getLogger(JmsConfiguration.class);
  
  @Value("${mq.queue}")
  private String devQueue;
  
  @Value("${mq.port}")
  private Integer port;
    
  @Value("${mq.queue-manager}")
  private String queueManager;
  
  @Value("${mq.channel}")
  private String channel;
  
  @Value("${mq.receive-timeout}")
  private long receiveTimeout;
  
  @Resource (name = "internalJmsConnectionFactory")
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
  
  @Bean
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
  }


}
