import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * In clasa GameClient dupa crearea socketului, cat timp raspunsul primit de la server nu este "Server stopped"
 * se cere introducerea de la tastatura a unei comenzi care se trimite la server si se asteapta raspunsul.
 * Raspunsul primit este afisat pe ecran.
 */

public class GameClient {
    public static void main (String[] args) throws IOException {
        String serverAddress = "127.0.0.1";
        int PORT = 3000;
        try (
                Socket socket = new Socket(serverAddress, PORT);
                PrintWriter out =
                        new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader (
                        new InputStreamReader(socket.getInputStream())) ) {
                Scanner scanner = new Scanner(System.in);
            String response="";
            while(!response.equals("Server stopped")){
                System.out.println("Enter your command:");
                String request = scanner.nextLine();
                out.println(request);
                response = in.readLine();
                System.out.println("Response from server: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}