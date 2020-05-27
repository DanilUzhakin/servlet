package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class SessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent arg){
        System.out.println("Session is created!");
    }

    public void sessionDestroyed(HttpSessionEvent arg){
        System.out.println("Session is destroyed!");
    }
}
