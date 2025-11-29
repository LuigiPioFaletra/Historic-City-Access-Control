/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @authors elvira and luigi
 */

package centroStorico;
import java.io.Serializable;

/**
 * Un oggetto di questa classe rappresenta la targa di un veicolo.
 */

public class Targa implements Serializable{
    
    /**
     * Indica il proprietario del veicolo con quella determinata targa.
     */
    
    public Proprietario intestatario;
    
    /**
     * Indica la targa del veicolo.
     */
    
    private String targaVeicolo;
    
    /**
     * Indica se il veicolo è ecologico o meno.
     */
    
    private String tipoVeicolo;
    
    /**
     * Questo metodo costruttore inizializza i dati della targa.
     * 
     * @param targa indica la targa del veicolo.
     * @param utente indica il proprietario del veicolo.
     */
    
    public Targa(String targa,Proprietario utente){
        intestatario = utente;
        targaVeicolo = targa;
        tipoVeicolo = "Non ecologico";
    }
    
    /**
     * Questo metodo permette di inizializzare il dato tipoVeicolo.
     * 
     * @param eco è una stringa che indica se il veicolo è ecologico o meno.
     */
    
    public void assegnazioneEcologia(String eco){
        tipoVeicolo = eco;
    }
    
    /**
     * Questo metodo permette di inizializzare il dato targaVeicolo.
     * 
     * @param targa è una stringa che indica la targa.
     */
    
    public void assegnazioneTarga(String targa){
        targaVeicolo = targa;
    }
    
    /**
     * Questo metodo restituisce la città del proprietario.
     * 
     * @return la città del proprietario.
     */
    
    public String cittaProprietario(){
        return intestatario.localita;
    }
    
    /**
     * Questo metodo restituisce il cognome del proprietario.
     * 
     * @return il cognome del proprietario.
     */
    
    public String cognomeProrietario(){
        return intestatario.cognome;
    }
    
    /**
     * Questo metodo restiuisce l'informazione tipo veicolo che indica se ecologico o meno.
     * 
     * @return una stringa.
     */
    
    public String ecologia(){
        return tipoVeicolo;
    }
    
    /**
     * Questo metodo restituisce il valore della multa del proprietario. 
     * 
     * @return il valore della multa.
     */
    
    public int multaProprietario(){
        return intestatario.multaAccesso;
    }
    
    /**
     * Questo metodo restituisce il nome del proprietario.
     * 
     * @return il nome del proprietario.
     */
    
    public String nomeProprietario(){
        return intestatario.nome;
    }
    
    /**
     * Questo metodo restituisce il tipo di patente del proprietario.
     * 
     * @return il tipo di patente del proprietario.
     */
    
    public String patenteProprietario(){
        return intestatario.tipoDiPatente;
    }
    
    /**
     * Questo metodo ritorna la targa del veicolo.
     * 
     * @return la targa del veicolo.
     */
    
    public String targaProprietario(){
        return targaVeicolo;
    }    
}