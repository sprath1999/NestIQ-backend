package com.nestiq.notification.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Component
public class WebSocketAuthInterceptor implements HandshakeInterceptor {

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response,
                                   WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) {

        String query = request.getURI().getQuery();
        System.out.println("WebSocket handshake query: " + query);
        
        if (query != null && query.contains("token=")) {
            String token = query.split("token=")[1];
            try {
                Claims claims = Jwts.parser()
                        .verifyWith(getSigningKey())
                        .build()
                        .parseSignedClaims(token)
                        .getPayload();

                Number userIdNum = claims.get("userId", Number.class);
                Long userId = userIdNum != null ? userIdNum.longValue() : null;
                String email = claims.getSubject();

                System.out.println("WebSocket userId mapped: " + userId);
                System.out.println("WebSocket email mapped: " + email);

                attributes.put("userId", String.valueOf(userId));
                attributes.put("email", email);
                return true;
            } catch (Exception e) {
                System.out.println("WebSocket token error: " + e.getMessage());
                return false;
            }
        }
        System.out.println("No token found in WebSocket handshake");
        return true;
    }
   
    @Override
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response,
                               WebSocketHandler wsHandler,
                               Exception exception) {
    }
}