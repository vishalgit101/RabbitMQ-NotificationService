package config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMQConfig {
	// Exchange 
	public static final String APP_EXCHANGE = "app.exchange";
	
	// Routing keys
	public static final String EMAIL_NOTIFICATION_ROUTING_KEY = "notification.email";
	public static final String SMS_NOTIFICATION_ROUTING_KEY = "notification.sms";
	
	// QUEUES
	public static final String EMAIL_QUEUE = "email.queue";
	public static final String SMS_QUEUE = "sms.queue";
	
	@Bean
	public TopicExchange appExchange() {
		return new TopicExchange(APP_EXCHANGE, true, false);
	}
	
	@Bean
	public Queue emailQueue() {
		return QueueBuilder.durable(EMAIL_QUEUE).build();
	}
	
	@Bean
	public Queue smsQueue() {
		return QueueBuilder.durable(SMS_QUEUE).build();
	}
	
	@Bean
	public Binding emailBinding() { 
		return BindingBuilder.bind(emailQueue())
							 .to(appExchange())
							 .with(EMAIL_NOTIFICATION_ROUTING_KEY);
		
		// Bind Queue to -> Exchange with -> Routing Key
	}
	
	@Bean
	public Binding smsBinding() {
		return BindingBuilder.bind(smsQueue())
							 .to(appExchange())
							 .with(SMS_NOTIFICATION_ROUTING_KEY);
	}
	
	
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(messageConverter);
		return rabbitTemplate;
	}

	/*private JacksonJsonMessageConverter messageConverter() {
		JsonMapper mapper = new JsonMapper(); // spring boot 4 auto configures the JavaTimeModule with its Jackson 3
		mapper.registeredModules(new JavaTimeModule());
		return new JacksonJsonMessageConverter(mapper);
	}*/
	
}
