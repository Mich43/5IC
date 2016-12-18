import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Michael on 11/12/2016.
 */
public class Server implements Runnable{

    private static int port = 9999;
    public ServerSocket SocketServer;

    public Server(int port) throws IOException {
        this.SocketServer = new ServerSocket(port);
    }


    @Override
    public void run() {
        while(true)
        {
            try {
                Socket clientSocket= SocketServer.accept();
                ClientHandler clientThread = new ClientHandler(clientSocket);
                System.out.println("Client connected");
                clientThread.run();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(port);
        System.out.println("Server Started");
        server.run();
    }

}
