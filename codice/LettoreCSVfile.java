package codice;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class LettoreCSVfile {
    ArrayList<Dati> contenutoFile = new ArrayList<>();

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

                contenutoFile.add(dati);
            }

        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    public List<Dati> ricercaComune(String comune) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getComune().equalsIgnoreCase(comune)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaProvincia(String provincia) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getProvincia().equalsIgnoreCase(provincia)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaTipologia(String tipologia) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getTipologia().equalsIgnoreCase(tipologia)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaStelle(String stelle) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getStelle().equalsIgnoreCase(stelle)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaNome(String nome) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getNome().equalsIgnoreCase(nome)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaTelefono(String telefono) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getTelefono().equalsIgnoreCase(telefono)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaIndirizzo(String indirizzo) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getIndirizzo().equalsIgnoreCase(indirizzo)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaCap(String cap) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getCap().equalsIgnoreCase(cap)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaCitta(String citta) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getCitta().equalsIgnoreCase(citta)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaSiglaProvinciale(String siglaProvinciale) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getSiglaProvinciale().equalsIgnoreCase(siglaProvinciale)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaEmail(String email) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getIndirizzoEmail().equalsIgnoreCase(email)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaSitoInternet(String sito) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getIndirizzoInternet().equalsIgnoreCase(sito)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaCodEsercizio(String codEsercizio) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getCodEsercizio().equalsIgnoreCase(codEsercizio)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaId(String id) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getId().equalsIgnoreCase(id)) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaLatitudine(double latitudine) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getLatitudine() == latitudine) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaLongitudine(double longitudine) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getLongitudine() == longitudine) {
                risultati.add(dati);
            }
        }
        return risultati;
    }

    public List<Dati> ricercaRow(double row) {
        List<Dati> risultati = new ArrayList<>();
        for (Dati dati : contenutoFile) {
            if (dati.getRow() == row) {
                risultati.add(dati);
            }
        }
        return risultati;
    }



}