package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clasa server.GameServer creaza in constructor un serverSocket care ruleaza la portul specificat in atributul PORT. Serverul
 * va primi request-uri de la clienti si va crea un nou thread pentru fiecare client.
 */
public class GameServer {
    public static final int PORT = 3000;

    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket).start();
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }
    public static void main ( String [] args ) throws IOException {
        GameServer server = new GameServer();
    }

}