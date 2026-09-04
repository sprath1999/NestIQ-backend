package com.nestiq.notification.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final WebSocketAuthInterceptor webSocketAuthInterceptor;
    private final WebSocketHandshakeHandler webSocketHandshakeHandler;

    public WebSocketConfig(WebSocketAuthInterceptor webSocketAuthInterceptor,
                           WebSocketHandshakeHandler webSocketHandshakeHandler) {
        this.webSocketAuthInterceptor = webSocketAuthInterceptor;
        this.webSocketHandshakeHandler = webSocketHandshakeHandler;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("*")
                .addInterceptors(webSocketAuthInterceptor)
                .setHandshakeHandler(webSocketHandshakeHandler)
                .withSockJS();
    }
}