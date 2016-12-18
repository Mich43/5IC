/**
 * Created by Michael on 11/12/2016.
 */
public class Protocol {

    private static final int WELCOME = 0;
    private static final int JOKE = 1;
    private static final int HINT = 2;
    private static final int SAYSOMETHING = 3;
    private static final int CLOSE = 4;

    public int currentState;
    public boolean close = false;
    public Protocol() {
        this.currentState = 0;
    }
    public String answer(String input)
    {
        String answer = "";
        if(this.currentState == WELCOME && input.equals("start"))
            answer = "Benvenuto digita \"joke\" per avere una barzelletta";
        else if(this.currentState == JOKE && input.equals("joke"))
            answer = "È falso!!!\n" +
                    "Ora digita \"hint\" per ricevere un consiglio";

        else if(this.currentState == HINT && input.equals("hint"))
            answer = "Controlla bene tutti i \";\"\n" +
                    "Ora digita quello che vuoi per riceverlo indietro come risposta";
        else if(this.currentState == SAYSOMETHING)
            answer = "Hai digitato: "+input+"\n" +
                    "Ora premi invio per fermarmi";
        else if(this.currentState == CLOSE) {
            answer = "Grazie per aver giocato con me!";
            close = true;
        }
        else
            answer = "Non ho capito, cerca di digitare quello che chiedo!!!";
        currentState++;
        return answer;
    }
}
