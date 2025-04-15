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
                                "\nricercaComune -> ricerca strutture per comune" +
                                "\nricercaProvincia -> ricerca strutture in una provincia" +
                                "\nricercaTipologia -> ricerca strutture in base alla tipologia" +
                                "\nricercaStelle -> ricerca strutture in base al numero di stelle" +
                                "\nricercaNome -> ricerca strutture per nome" +
                                "\nricercaTelefono -> ricerca strutture per numero di telefono" +
                                "\nricercaIndirizzo -> ricerca strutture per via/indirizzo" +
                                "\nricercaCap -> ricerca strutture per CAP" +
                                "\nricercaCitta -> ricerca strutture per città" +
                                "\nricercaSiglaProvinciale -> ricerca strutture per sigla della provincia" +
                                "\nricercaEmail -> ricerca struttura per email" +
                                "\nricercaSitoInternet -> ricerca struttura per sito internet" +
                                "\nricercaCodEsercizio -> ricerca struttura per codice esercizio" +
                                "\nricercaId -> ricerca struttura per ID univoco" +
                                "\nricercaGeografia -> ricerca struttura con coordinate geografiche (latitudine e longitudine)");
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


