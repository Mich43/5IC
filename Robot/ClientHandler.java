import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 * Created by Michael on 11/12/2016.
 */
public class ClientHandler implements Runnable,Serializable{

    Socket s;
    ObjectInputStream input;
    ObjectOutputStream output;
    Protocol prot;

    public ClientHandler(Socket s) throws IOException {
        this.s = s;
        this.output = new ObjectOutputStream(this.s.getOutputStream());
        this.input = new ObjectInputStream(this.s.getInputStream());
        this.prot = new Protocol();
    }

    @Override
    public void run() {
        try {
            output.writeObject("Connected");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true && !prot.close)
            try {
                String in = (String) input.readObject();
                output.writeObject(prot.answer(in));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
}
