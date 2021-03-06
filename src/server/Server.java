package server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Properties;

public class Server {
    private static HashMap<String, String> users;
    private static HashMap<String, String> avatars;

    public Server(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);

            MatchRoom matchRoom = new MatchRoom();

            while (true) {
                new Player(serverSocket.accept(), matchRoom).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        users = new HashMap<>();
        avatars = new HashMap<>();
        users.put("a","a");
        users.put("b","b");
        users.put("q","q");
        users.put("w","w");
        avatars.put("a","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");
        avatars.put("b","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");
        avatars.put("q","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");
        avatars.put("w","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");

        int port = 8900;

        new Server(port);
    }

    public static boolean checkUser(String login, String password)  {
        synchronized(users) {
            if (users.containsKey(login))
                return users.get(login).equals(password);
            else
                return false;
        }
    }

    public static boolean userExist(String login){
        synchronized(users) {
            return users.containsKey(login);
        }
    }

    public static boolean addUser(String login , String user){
        synchronized(users) {
            if(userExist(login))
                return false;

            try {
                users.put(login, user);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    public static boolean addAvatar(String login , String avatar){
        synchronized (avatars){
            if(!avatars.containsKey(login)) {
                try {
                    avatars.put(login, avatar);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }
            return true;
        }
    }
    public static String getAvatar(String login){
        synchronized (avatars){
            if(avatars.containsKey(login))
                return avatars.get(login);
            else
                return null;
        }

    }



}
