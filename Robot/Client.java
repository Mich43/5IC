import java.io.*;
import java.net.Socket;

/**
 * Created by Michael on 11/12/2016.
 */
public class Client implements Serializable{

    private String ip;
    private int port;
    private Socket socket;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    static BufferedReader keyboard;

    public Client(String ip, int port) throws IOException {
        this.ip = ip;
        this.port = port;
        this.socket = new Socket(this.ip, this.port);
        this.output = new ObjectOutputStream(this.socket.getOutputStream());
        this.input = new ObjectInputStream(this.socket.getInputStream());
    }

    public void connect() throws IOException, ClassNotFoundException {
        System.out.println(input.readObject());
        output.writeObject("start");
        while(true)
        {
            System.out.println(input.readObject());
            output.writeObject(keyboard.readLine());
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        keyboard = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci IP robot");
        String ip = keyboard.readLine();
        System.out.println("Inserisci porta server");
        int port = Integer.parseInt(keyboard.readLine());
        System.out.println("Mi connetto");
        Client user = new Client(ip,port);
        try {
            user.connect();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
