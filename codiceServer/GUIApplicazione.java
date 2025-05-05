package codiceServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GUIApplicazione extends JFrame {
    private JTextField inputRicerca;
    private JTextField inputIP;
    private JTextField inputPorta;
    private JTextArea risultatiArea;
    private JComboBox<String> menuRicerca;
    private JButton connettiButton;
    private JButton ricercaButton;

    public GUIApplicazione() {
        setTitle("Gestione Dati e Connessione Server");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pannello superiore per la connessione
        JPanel connessionePanel = new JPanel();
        connessionePanel.setLayout(new FlowLayout());
        connessionePanel.add(new JLabel("Indirizzo IP:"));
        inputIP = new JTextField(10);
        connessionePanel.add(inputIP);
        connessionePanel.add(new JLabel("Porta:"));
        inputPorta = new JTextField(5);
        connessionePanel.add(inputPorta);
        connettiButton = new JButton("Connetti");
        connessionePanel.add(connettiButton);

        // Pannello centrale per la ricerca
        JPanel ricercaPanel = new JPanel();
        ricercaPanel.setLayout(new FlowLayout());
        menuRicerca = new JComboBox<>(new String[]{
                "Nome", "Telefono", "Indirizzo", "CAP", "Città", "Email", "Sito Internet", "ID"
        });
        ricercaPanel.add(new JLabel("Cerca per:"));
        ricercaPanel.add(menuRicerca);
        inputRicerca = new JTextField(15);
        ricercaPanel.add(inputRicerca);
        ricercaButton = new JButton("Cerca");
        ricercaPanel.add(ricercaButton);

        // Area risultati
        risultatiArea = new JTextArea(10, 50);
        risultatiArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(risultatiArea);

        // Aggiunta dei pannelli al frame
        add(connessionePanel, BorderLayout.NORTH);
        add(ricercaPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Eventi
        connettiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = inputIP.getText();
                String porta = inputPorta.getText();
                connettiAlServer(ip, porta);
            }
        });

        ricercaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String criterio = (String) menuRicerca.getSelectedItem();
                String valore = inputRicerca.getText();
                eseguiRicerca(criterio, valore);
            }
        });
    }


    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private void connettiAlServer(String ip, String porta) {
        try {
            clientSocket = new Socket(ip, Integer.parseInt(porta));
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            risultatiArea.append("Connesso al server " + ip + " sulla porta " + porta + "\n");
        } catch (IOException e) {
            risultatiArea.append("Errore di connessione: " + e.getMessage() + "\n");
        }
    }

    private void eseguiRicerca(String criterio, String valore) {
        if (clientSocket == null || clientSocket.isClosed()) {
            risultatiArea.append("Errore: non sei connesso al server.\n");
            return;
        }

        try {
            String comando = "ricerca" + criterio + " " + valore;
            out.println(comando);

            String risposta;
            while ((risposta = in.readLine()) != null) {
                if (risposta.equalsIgnoreCase("END")) {
                    break; // Fine dei risultati
                }
                risultatiArea.append(formattaDati(risposta) + "\n");
            }
        } catch (IOException e) {
            risultatiArea.append("Errore durante la ricerca: " + e.getMessage() + "\n");
        }
    }

    private String formattaDati(String dati) {
        String[] campi = dati.split(";");
        return "Comune: " + campi[0] + "\n" +
                "Provincia: " + campi[1] + "\n" +
                "Tipologia: " + campi[2] + "\n" +
                "Stelle: " + campi[3] + "\n" +
                "Nome: " + campi[4] + "\n" +
                "Telefono: " + campi[5] + "\n" +
                "Indirizzo: " + campi[6] + "\n" +
                "CAP: " + campi[7] + "\n" +
                "Città: " + campi[8] + "\n" +
                "Sigla Provinciale: " + campi[9] + "\n" +
                "Email: " + campi[10] + "\n" +
                "Sito Internet: " + campi[11] + "\n" +
                "Codice Esercizio: " + campi[12] + "\n" +
                "ID: " + campi[13] + "\n" +
                "Latitudine: " + campi[14] + "\n" +
                "Longitudine: " + campi[15] + "\n" +
                "Row: " + campi[16] + "\n";
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUIApplicazione gui = new GUIApplicazione();
            gui.setVisible(true);
        });
    }
}