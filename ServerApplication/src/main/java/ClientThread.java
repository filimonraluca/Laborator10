import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Clasa ClientThred reprezinta threadul care se va ocupa de requesturile unui anumit client.
 */
class ClientThread extends Thread {
    private Socket socket = null ;

    /**
     * Constructorul primeste ca paramentru socketul la care se realizeaza comunicarea
     * @param socket
     */
    public ClientThread (Socket socket) { this.socket = socket ; }

    /**
     * In metoda run in interiorul unei bucle infimite se preia requestul din input stream si se genereaza raspunsul.
     * In cazul in care requestul este exit raspunsul va fi "server stopped" si se va iesi din bucla. Atfel, raspunsul va fi
     * concatenarea dinte "Server received the request" si requestul clientului.
     * */
    public void run () {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            String raspuns = "";
            while(true) {
                String request = in.readLine();
                System.out.println("Client request: " +request );
                if (request.equals("exit")) {
                    raspuns = "Server stopped";
                    out.println(raspuns);
                    out.flush();
                    break;
                }
                raspuns = String.format("Server received the request '%s'" ,request) ;
                out.println(raspuns);
                out.flush();
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) { System.err.println (e); }
        }
    }
}