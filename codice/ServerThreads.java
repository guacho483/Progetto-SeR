package codice;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThreads  extends Thread {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket clientSocket = null;
    private LettoreCSVfile csvr = new LettoreCSVfile();

    public ServerThreads(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }


    @Override
    public void run() {
        try {
            // bloccante finchè non avviene una connessione

            System.out.println("Connection accepted: " + clientSocket);
            // creazione stream di input da clientSocket
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());
            in = new BufferedReader(isr);
            // creazione stream di output su clientSocket
            OutputStreamWriter osw = new OutputStreamWriter(clientSocket.getOutputStream());
            BufferedWriter bw = new BufferedWriter(osw);
            out = new PrintWriter(bw, true);
            // ciclo di ricezione dal client e invio di risposta
            out.print("Scrivi HELP per aiuto (END to close connection): ");
            out.println("END_OF_MESSAGE");
            out.flush();
            while (true) {
                char h;
                int somma = 0;
                String str = in.readLine();
                for(int i = 0; i < str.length(); i++){
                    h = str.charAt(i);
                    somma += h;
                }
                if (str.equals("END"))
                    break;

                if(str.equalsIgnoreCase("HELP")){
                    out.println("Comandi: " +
                            "\n\rricercaComune -> ricerca strutture per comune" +
                            "\n\rricercaProvincia -> ricerca strutture in una provincia" +
                            "\n\rricercaTipologia -> ricerca strutture in base alla tipologia" +
                            "\n\rricercaStelle -> ricerca strutture in base al numero di stelle" +
                            "\n\rricercaNome -> ricerca strutture per nome" +
                            "\n\rricercaTelefono -> ricerca strutture per numero di telefono" +
                            "\n\rricercaIndirizzo -> ricerca strutture per via/indirizzo" +
                            "\n\rricercaCap -> ricerca strutture per CAP" +
                            "\n\rricercaCitta -> ricerca strutture per città" +
                            "\n\rricercaSiglaProvinciale -> ricerca strutture per sigla della provincia" +
                            "\n\rricercaEmail -> ricerca struttura per email" +
                            "\n\rricercaSitoInternet -> ricerca struttura per sito internet" +
                            "\n\rricercaCodEsercizio -> ricerca struttura per codice esercizio" +
                            "\n\rricercaId -> ricerca struttura per ID univoco" +
                            "\n\rricercaGeografia -> ricerca struttura con coordinate geografiche (latitudine e longitudine)");
                    out.flush();
                    out.println("END_OF_MESSAGE");
                    out.flush();
                    continue;
                }
                String[] words = str.split(" ", 2);
                switch(words[0]){
                    case "GET_ROW":
                        if ((Integer.parseInt(words[1]) - 1) < 1
                            && (Integer.parseInt(words[1]) - 1) > csvr.contenutoFile.size()) {
                        out.println("Errore: il numero è fuori dai limiti! ");
                        out.flush();
                        break;
                    }

                        out.print(csvr.ricercaRow(Integer.parseInt(words[1]) - 1).toString());
                        out.flush();
                        break;
                }

            }
            // chiusura di stream e socket
            System.out.println("EchoServer: closing...");
            out.close();
            in.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("Accept failed");
            System.exit(1);
        }



    }
}
