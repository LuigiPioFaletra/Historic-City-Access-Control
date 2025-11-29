/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @authors elvira and luigi
 */

package centroStorico;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Un oggetto di questa classe rappresenta un motociclo.
 */

public class Motociclo extends Veicolo implements VeicoloEcologico{
    
    /**
     * Indica il numero delle marce del veicolo.
     */
    
    private byte numeroMarce;
    
    /**
     * Indica il tipo di veicolo, ovvero Motociclo, richiamato all'interno di tipologiaVeicolo().
     */
    
    public final static String VEICOLO = "Motociclo";
    
    Motociclo(String marcaV,String carburante,short cilindrataV,short anno,Targa targaV,String categoriaV,byte posti,byte ruote,byte marce){
        super(marcaV,carburante,cilindrataV,anno,targaV,categoriaV,posti,ruote);
        numeroMarce = marce;
    }
    
    /**
     * Questo metodo assegna il numeroMarce all'oggetto della classe Motociclo.
     * 
     * @param marce è il numero di marce da assegnare a Motociclo.
     */
    
    public void assegnazioneMarce(byte marce){
        numeroMarce = marce;
    }
    
    /**
     * Questo metodo permette di classificare il motociclo come ecologico o non ecologico.
     */  
    
    public void classificazione(){
        if(categoriaVeicolo.equals("Euro4") || categoriaVeicolo.equals("Euro5") || carburanteVeicolo.equals("Metano")
        || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equals("Idrometano") 
        || carburanteVeicolo.equals("Bioetanolo") || carburanteVeicolo.equals("Biodiesel")){
            targaVeicolo.assegnazioneEcologia("Ecologico");
        }else{
            targaVeicolo.assegnazioneEcologia("Non ecologico");
        }
    }
    
    /**
     * Questo metodo genera una multa il cui valore varia tra 163 e 658.
     * 
     * @return il valore della multa.
     */  
    
    public int multaAccesso(){
        int multa = 0;
        Random rand = new Random();
        switch(categoriaVeicolo){
            case "Euro0":
                if(carburanteVeicolo.equals("Metano") || carburanteVeicolo.equals("Elettrico")
                || carburanteVeicolo.equals("Idrometano") || carburanteVeicolo.equals("Bioetanolo")
                || carburanteVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((597-535)+1)+535;
                }else{
                    multa = rand.nextInt((658-597)+1)+597;
                }   
            break;
            case "Euro1":
                if(carburanteVeicolo.equals("Metano") || carburanteVeicolo.equals("Elettrico")
                || carburanteVeicolo.equals("Idrometano") || carburanteVeicolo.equals("Bioetanolo")
                || carburanteVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((473-411)+1)+411;
                }else{
                    multa = rand.nextInt((535-473)+1)+473;
                }   
            break;
            case "Euro2":
                if(carburanteVeicolo.equals("Metano") || carburanteVeicolo.equals("Elettrico")
                || carburanteVeicolo.equals("Idrometano") || carburanteVeicolo.equals("Bioetanolo")
                || carburanteVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((349-287)+1)+287;
                }else{
                    multa = rand.nextInt((411-349)+1)+349;
                }   
            break;
            case "Euro3":
                if(carburanteVeicolo.equals("Metano") || carburanteVeicolo.equals("Elettrico")
                || carburanteVeicolo.equals("Idrometano") || carburanteVeicolo.equals("Bioetanolo")
                || carburanteVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((225-163)+1)+163;
                }else{
                    multa = rand.nextInt((287-225)+1)+225;
                }   
            break;
            default:
                JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile selezionare"
                        + " correttamente il tipo di carburante per assegnarli la multa.",
                                "Attenzione",JOptionPane.ERROR_MESSAGE);
            break;
        }
	targaVeicolo.intestatario.assegnazioneMulta(multa);
        return multa;
    }
    
    /**
     * Questo metodo ritorna il numero di marce presente nel motociclo.
     * 
     * @return il numero di marce.
     */  
    
    public byte numeroMarceMotociclo(){
        return numeroMarce;
    }
    
    /**
     * Questo metodo ritorna la stringa "Motociclo".
     * 
     * @return VEICOLO.
     */
    
    public String tipologiaVeicolo(){
        return VEICOLO;
    }
}