package codice;


import java.io.Serializable;

public class Dati implements Serializable {
    public String comune;
    public String provincia;
    public String tipologia;
    public String stelle;
    public String nome;
    public String telefono;
    public String indirizzo;
    public String cap;
    public String citta;
    public String siglaProvinciale;
    public String indirizzoEmail;
    public String indirizzoInternet;
    public String codEsercizio;
    public String id;
    public double latitudine;
    public double longitudine;
    public double row;

    public Dati() {
        this.comune = comune;
        this.provincia = provincia;
        this.tipologia = tipologia;
        this.stelle = stelle;
        this.nome = nome;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.cap = cap;
        this.citta = citta;
        this.siglaProvinciale = siglaProvinciale;
        this.indirizzoEmail = indirizzoEmail;
        this.indirizzoInternet = indirizzoInternet;
        this.codEsercizio = codEsercizio;
        this.id = id;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        this.row = row;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public void setStelle(String stelle) {
        this.stelle = stelle;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public void setSiglaProvinciale(String siglaProvinciale) {
        this.siglaProvinciale = siglaProvinciale;
    }

    public void setIndirizzoEmail(String indirizzoEmail) {
        this.indirizzoEmail = indirizzoEmail;
    }

    public void setIndirizzoInternet(String indirizzoInternet) {
        this.indirizzoInternet = indirizzoInternet;
    }

    public void setCodEsercizio(String codEsercizio) {
        this.codEsercizio = codEsercizio;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }

    public void setRow(double row) {
        this.row = row;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getTipologia() {
        return tipologia;
    }

    public String getStelle() {
        return stelle;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public String getCap() {
        return cap;
    }

    public String getCitta() {
        return citta;
    }

    public String getSiglaProvinciale() {
        return siglaProvinciale;
    }

    public String getIndirizzoEmail() {
        return indirizzoEmail;
    }

    public String getIndirizzoInternet() {
        return indirizzoInternet;
    }

    public String getCodEsercizio() {
        return codEsercizio;
    }

    public String getId() {
        return id;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public double getRow() {
        return row;
    }
}
