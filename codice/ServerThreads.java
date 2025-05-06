package codice;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class ServerThreads extends Thread {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Socket clientSocket = null;
    private LettoreCSVfile csvreader = new LettoreCSVfile();

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
            out.println("END_OF_MESSAGE");
            out.flush();
            while (true) {
                char h;
                int somma = 0;
                String str = in.readLine();
                if (str == null) {
                    System.out.println("Il client ha chiuso la connessione o non ha inviato dati.");
                } else {
                    System.out.println("Comando ricevuto dal client: " + str);
                }


                if (str.equalsIgnoreCase("END"))
                    break;

                if (str.equalsIgnoreCase("HELP")) {
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
                            "\n\rGET_ROW [n]  ricerca struttura per numero di riga");
                    out.flush();
                    out.println("END_OF_MESSAGE");
                    out.flush();
                    continue;
                }
                String[] words = str.split(" ", 2);
                switch (words[0]) {
                    case "GET_ROW":
                        if ((Integer.parseInt(words[1]) - 1) < 1
                                && (Integer.parseInt(words[1]) - 1) > csvreader.contenutoFile.size()) {
                            out.println("Errore!!!");
                            out.flush();
                            break;
                        }

                        out.print(csvreader.ricercaRow(Integer.parseInt(words[1]) - 1).toString());
                        out.flush();
                        break;

                    case "ricercaComune":
                        List<Dati> risultati = csvreader.ricercaComune(words[1]);
                        if (risultati.isEmpty()) {
                            out.println("Nessun risultato trovato per il comune: " + words[1]);
                            out.flush();
                        } else {
                            out.print(csvreader.ricercaComune(words[1]).toString() + "\n\r");
                            out.flush();
                        }

                        break;

                    case "ricercaProvincia":
                        List<Dati> risultati2 = csvreader.ricercaProvincia(words[1]);
                        out.print(csvreader.ricercaProvincia(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaTipologia":
                        out.print(csvreader.ricercaTipologia(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaStelle":
                        out.print(csvreader.ricercaStelle(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaNome":
                        out.print(csvreader.ricercaNome(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaTelefono":
                        out.print(csvreader.ricercaTelefono(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaIndirizzo":
                        out.print(csvreader.ricercaIndirizzo(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaCap":
                        out.print(csvreader.ricercaCap(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaCitta":
                        out.print(csvreader.ricercaCitta(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaSiglaProvinciale":
                        out.print(csvreader.ricercaSiglaProvinciale(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaEmail":
                        out.print(csvreader.ricercaEmail(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaSitoInternet":
                        out.print(csvreader.ricercaSitoInternet(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaCodEsercizio":
                        out.print(csvreader.ricercaCodEsercizio(words[1]).toString());
                        out.flush();
                        break;

                    case "ricercaId":
                        out.print(csvreader.ricercaId(words[1]).toString());
                        out.flush();
                        break;
                    default:
                        out.println("Comando invalido!!!!!!!! Scrivi HELP per aiuto.");
                        out.flush();
                        break;

                }
                out.println("END_OF_MESSAGE");
                out.flush();


            }

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
