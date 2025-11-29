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
 * Un oggetto di questa classe rappresenta un proprietario di un veicolo.
 */

public class Proprietario implements Serializable{
    
    /**
     * Indica il cognome del proprietario.
     */
    
    public String cognome;
    
    /**
     * Indica la città del proprietario.
     */
    
    public String localita;
        
    /**
     * Indica l'eventuale multa assegnata al proprietario.
     */
    
    public int multaAccesso;
    
    /**
     * Indica il nome del proprietario.
     */
    
    public String nome;
    
    /**
     * Indica il tipo di patente posseduta dal proprietario.
     */
    
    public String tipoDiPatente;
    
    /**
     * Questo metodo costruttore inizializza i dati del proprietario del veicolo.
     * 
     * @param nomeP indica il nome.
     * @param cognomeP indica il cognome.
     * @param citta indica la località.
     * @param patente indica il tipo di patente.
     */
    
    public Proprietario(String nomeP,String cognomeP,String citta,String patente){
        multaAccesso = 0;
        cognome = cognomeP;
        localita = citta;
        nome = nomeP;
        tipoDiPatente = patente;
    }
    
    /**
     * Questo metodo assegna il valore della multa al proprietario.
     * 
     * @param multa indica la multa ricevuta.
     */
    
    public void assegnazioneMulta(int multa){
        multaAccesso = multa;
    }
}