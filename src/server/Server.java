package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

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
        //avatars.put("a","a");

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
