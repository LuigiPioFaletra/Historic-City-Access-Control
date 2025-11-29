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
 * Questa classe è la superclasse di Autobus, Automobile, Camion, Ciclomotore, Motociclo e Triciclo.
 */

public class Veicolo implements Serializable{
    
    /**
     * Indica l'anno in cui è stato immatricolato il veicolo.
     */
    
    protected short annoImmatricolazioneVeicolo;
        
    /**
     * Indica il tipo di carburante del veicolo.
     */
    
    protected String carburanteVeicolo;
    
    /**
     * Indica il tipo di classe "Euro" a cui appartiene il veicolo
     */
    
    protected String categoriaVeicolo;
    
    /**
     * Indica la cilindrata del veicolo.
     */
    
    protected short cilindrataVeicolo;
    
    /**
     * Indica la marca del veicolo.
     */
    
    protected String marcaVeicolo;
        
    /**
     * Indica i posti presenti nel veicolo.
     */
    
    protected byte postiVeicolo;
    
    /**
     * Indica le ruote presenti nel veicolo.
     */
    
    protected byte ruoteVeicolo;
    
    /**
     * Indica la targa del veicolo con tutte le sue informazioni con un oggetto di tipo Targa.
     */
    
    protected Targa targaVeicolo;
    
    /**
     * Questo metodo costruttore, richiamato dalle sottoclassi, inizializza tutti i dati comuni tra i veicoli.
     * 
     * @param marcaV indica la marca.
     * @param carburante indica il carburante.
     * @param cilindrataV indica la cilindrata.
     * @param anno indica l'anno di immatricolazione.
     * @param targaV indica l'oggetto della classe Targa.
     * @param categoriaV indica la categoria.
     * @param posti indica i posti.
     * @param ruote indica le ruote.
     */
   
    public Veicolo(String marcaV,String carburante,short cilindrataV,short anno,Targa targaV,String categoriaV,byte posti,byte ruote){
        postiVeicolo = posti;
        ruoteVeicolo = ruote;
        annoImmatricolazioneVeicolo = anno;
        cilindrataVeicolo = cilindrataV;
        carburanteVeicolo = carburante;
        categoriaVeicolo = categoriaV;
        marcaVeicolo = marcaV;
        targaVeicolo = targaV;
    }
    
    /**
     * Questo metodo costruttore, richiamato dalle sottoclassi, inizializza a null tutti i dati comuni tra i veicoli.
     */
    
    public Veicolo(){
        annoImmatricolazioneVeicolo = 0000;
        cilindrataVeicolo = 00;
        carburanteVeicolo = null;
        categoriaVeicolo = null;
        marcaVeicolo = null;
        targaVeicolo = null;
    }
    
    /**
     * Questo metodo permette di inizializzare l'anno di immatricolazione del veicolo.
     * 
     * @param anno è l'anno di immatricolazione.
     */
    
    public void assegnazioneAnnoImmatricolazione(short anno){
        annoImmatricolazioneVeicolo = anno;
    }
    
    /**
     * Questo metodo permette di inizializzare il tipo di alimentazione del veicolo. 
     * 
     * @param carburante è il tipo di alimentazione.
     */
    
    public void assegnazioneCarburante(String carburante){
        carburanteVeicolo = carburante;
    }
    
    /**
     * Questo metodo permette di inizializzare la categoria a cui appartiene il veicolo.
     * 
     * @param categoria è una stringa di tipo "Euro".
     */
    
    public void assegnazioneCategoria(String categoria){
        categoriaVeicolo = categoria;
    }
    
    /**
     * Questo metodo permette inizializzare la cilindrata del veicolo.
     * 
     * @param cilindrata è la cilindrata del veicolo.
     */
    
    public void assegnazioneCilindrata(short cilindrata){
        cilindrataVeicolo = cilindrata;
    }
    
    /**
     * Questo metodo permette di inizializzare la marca del veicolo. 
     * 
     * @param marca è la marca del veicolo.
     */
    
    public void assegnazioneMarca(String marca){
        marcaVeicolo = marca;
    }
    
    /**
     * Questo metodo permette di inizializzare il numero di posti del veicolo.
     * 
     * @param posti è il numero posti del veicolo.
     */
    
    public void assegnazionePosti(byte posti){
        postiVeicolo = posti;
    }
    
    /**
     * Questo metodo permette di inizializzare il numero di ruote del veicolo.
     * 
     * @param ruote è il numero di ruote del veicolo.
     */
    
    public void assegnazioneRuote(byte ruote){
        ruoteVeicolo = ruote;
    }
    
    /**
     * Questo metodo permette di inizializzare l'oggetto targa.
     * 
     * @param targa è un oggetto della classe Targa.
     */
    
    public void assegnazioneTarga(Targa targa){
        targaVeicolo = targa;
    }
    
    /**
     * Questo metodo ritorna il tipo di alimentazione del veicolo. 
     * 
     * @return il tipo di alimentazione del veicolo.
     */
    
    public String carburante(){
        return carburanteVeicolo;
    }
    
    /**
     * Questo metodo ritorna la categoria alla quale appartiene un veicolo.
     * 
     * @return una stringa del tipo "Euro".
     */
    
    public String categoria(){
        return categoriaVeicolo;
    }
    
    /**
     * Questo metodo ritorna la cilindrata del veicolo.
     * 
     * @return la cilindrata del veicolo.
     */
    
    public short cilindrata(){
        return cilindrataVeicolo;
    }
            
    /**
     * Questo metodo ritorna l'anno di immatricolazione del veicolo.
     * 
     * @return l'anno di immatricolazione.
     */
    
    public short immatricolazione(){
        return annoImmatricolazioneVeicolo;
    }
    
    /**
     * Questo metodo ritorna la marca del veicolo.
     * 
     * @return la marca del veicolo.
     */
    
    public String marca(){
        return marcaVeicolo;
    }
    
    /**
     * Questo metodo ritorna il numero di posti del veicolo.
     * 
     * @return il numero di posti del veicolo.
     */
    
    public byte posti(){
        return postiVeicolo;
    }
    
    /**
     * Questo metodo ritorna il numero di ruote del veicolo.
     * 
     * @return il numero di ruote del veicolo.
     */
    
    public byte ruote(){
        return ruoteVeicolo;
    }
    
    /**
     * Questo metodo ritorna l'oggetto Targa
     * 
     * @return l'oggetto Targa
     */
    
    public Targa targa(){
        return targaVeicolo;
    }
}