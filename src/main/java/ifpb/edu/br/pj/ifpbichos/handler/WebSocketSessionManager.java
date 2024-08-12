package ifpb.edu.br.pj.ifpbichos.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Component
public class WebSocketSessionManager {

    private final Set<WebSocketSession> sessions = new HashSet<>();

    public synchronized void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public synchronized void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    public synchronized Set<WebSocketSession> getSessions() {
        return new HashSet<>(sessions);
    }
}
