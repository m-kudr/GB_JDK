package Chat.Server;

import Chat.Client.ChatWindow;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Server {
    static boolean isServerRun;
//    private static final ChatServerListener listener = new ChatServerListener() {
//        @Override
//        public void onMessageReceive(String msg) {
//
//        }
//    };

    Server(ChatServerListener listener) {
        this.isServerRun = false;
        //this.listener = listener;
    }

    private static String DateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }

    public static void Start() {
        isServerRun = true;
        String dt = DateTime();
        //onMessageReceive("Server STARTED!");
        ChatWindow.safeToFile("./server.log", dt + " --> Server STARTED!");
        System.out.println(dt + " --> Server STARTED!");
    }

    public static void Stop() {
        isServerRun = false;
        String dt = DateTime();
        ChatWindow.safeToFile("./server.log", dt + " --> Server STOPPED!");
        System.out.println(dt + " --> Server STOPPED!");
    }
}
