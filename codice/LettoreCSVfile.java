package codice;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LettoreCSVfile {
    ArrayList<String> contenutoFile = new ArrayList<>();

    public LettoreCSVfile() {
        leggiCSVFile();
    }

    private void leggiCSVFile() {
        try(BufferedReader br = new BufferedReader(new FileReader("Progetto-SeR\\codice\\Regione-Toscana---Strutture-ricettive.csv\""))){
            String linea;
            while((linea = br.readLine()) != null ){
                String[] dataFeatures = linea.split(";");
                Dati dati = new Dati();
                dati.setComune(dataFeatures[0]);
                dati.setProvincia(dataFeatures[1]);
                dati.setTipologia(dataFeatures[2]);
                dati.setStelle(dataFeatures[3]);
                dati.setNome(dataFeatures[4]);
                dati.setTelefono(dataFeatures[5]);
                dati.setIndirizzo(dataFeatures[6]);
                dati.setCap(dataFeatures[7]);
                dati.setCitta(dataFeatures[8]);
                dati.setSiglaProvinciale(dataFeatures[9]);
                dati.setIndirizzoEmail(dataFeatures[10]);
                dati.setIndirizzoInternet(dataFeatures[11]);
                dati.setCodEsercizio(dataFeatures[12]);
                dati.setId(dataFeatures[13]);
                dati.setLatitudine(Double.parseDouble(dataFeatures[14]));
                dati.setLongitudine(Double.parseDouble(dataFeatures[15]));
                dati.setRow(Double.parseDouble(dataFeatures[16]));
            }

        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }
}
