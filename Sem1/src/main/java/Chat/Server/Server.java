package Chat.Server;

import Chat.Client.ChatWindow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server implements ServerListener {
    static boolean isServerRun;
    private Listener listener;

    public Server(Listener listener) {
        this.isServerRun = false;
        this.listener = listener;
    }

    private String dateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    private void start() {
        isServerRun = true;
        String dt = dateTime();
        //onMessageReceive("Server STARTED!");
        ChatWindow.safeToFile("./server.log", dt + " --> Server STARTED!");
        System.out.println(dt + " --> Server STARTED!");
    }

    private void stop() {
        isServerRun = false;
        String dt = dateTime();
        ChatWindow.safeToFile("./server.log", dt + " --> Server STOPPED!");
        System.out.println(dt + " --> Server STOPPED!");
    }

    @Override
    public void action(boolean start) {
        if (start) start();
        else stop();
    }
}
