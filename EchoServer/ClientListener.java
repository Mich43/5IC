import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
public class ClientListener extends Thread{
	BufferedReader is;	//Gli stream non li inizializzo nel costruttore per usufruire dell'autoclosable
    PrintStream os;
    Socket s;
    EchoServer echoServer;
    String [] wordSequence;
    String exitString;
    boolean stopped;
    public ClientListener(Socket s,String exitString,EchoServer echoServer){
		this.s = s;
		this.echoServer = echoServer;
		this.exitString = exitString;
		wordSequence = exitString.split(" ");
		stopped = false;
	}
	
	public EchoServer getEchoServer(){
		return echoServer;
	}
	
	public Socket getSocket(){
		return s;
	}
	public String getExitString(){
		return exitString;
	}
	
	public void setStopped (){
		stopped = true;
	}
	
	public boolean hasStopped(){
		return stopped;
	}
	
	public String[] getWordSequence(){
		return wordSequence;
	}
	public void run(){
		try{
			is = new BufferedReader(new InputStreamReader(getSocket().getInputStream()));
			os = new PrintStream(getSocket().getOutputStream());
			os.println(exitString);
			boolean stopped = false;
			int stopCounter = 0;
			String line = null;
			while (!hasStopped()) {
					 line = is.readLine();
					 if(line.equals(getWordSequence()[stopCounter]))
						stopCounter++;
					else{
						stopCounter = 0;
					}
					 if(stopCounter== getWordSequence().length){
						 setStopped();
						 line+="\0";
					 }
					 os.println("ECHO "+line);
				}
			getEchoServer().removeClientListener(this);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
