package ifpb.edu.br.pj.ifpbichos.model.config;

import ifpb.edu.br.pj.ifpbichos.handler.MyWebSocketHandler;
import ifpb.edu.br.pj.ifpbichos.handler.WebSocketSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketSessionManager sessionManager;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(sessionManager), "/ws/notifications")
                .setAllowedOrigins("*");
    }
}
