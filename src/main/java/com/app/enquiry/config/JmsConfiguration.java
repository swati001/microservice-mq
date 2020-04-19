package com.app.enquiry.config;

class JmsConfiguration {
  private static final Logger logger = LogManager.getLogger(JmsConfiguration.class);
  
  @Value("${queue}")
    private String devQueue;
 
 @Resource (name = "internalJmsConnectionFactory")
 private ConnectionFactory connectionFactory;
 
 @Resource
 private CustomJmsListener customJmsListener;
 
 private int maxConsumers = 3;

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

}
