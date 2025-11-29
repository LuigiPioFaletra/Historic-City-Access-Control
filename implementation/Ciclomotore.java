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
 * Un oggetto di questa classe rappresenta un ciclomotore.
 */

public class Ciclomotore extends Veicolo implements VeicoloEcologico{
    
    /**
     * Indica il tipo di veicolo, ovvero Ciclomotore, richiamato all'interno di tipologiaVeicolo().
     */
    
    public final static String VEICOLO = "Ciclomotore";
    
    /**
     * Indica la velocità massima del ciclomotore.
     */
    
    private byte velocitaMassima;
    
    Ciclomotore(String marcaV,String carburante,short cilindrataV,short anno,Targa targaV,String categoriaV,byte posti,byte ruote,byte velocita){
        super(marcaV,carburante,cilindrataV,anno,targaV,categoriaV,posti,ruote);
        velocitaMassima = velocita;
    }
    
    /**
     * Questo metodo assegna la velocitaMassima all'oggetto della classe Ciclomotore.
     * 
     * @param velocita è la velocità massima da assegnare a Ciclomotore.
     */
    
    public void assegnazioneVelocita(byte velocita){
        velocitaMassima = velocita;
    }
    
    /**
     * Questo metodo permette di classificare il ciclomotore come ecologico o non ecologico.
     */  
    
    public void classificazione(){
        if(categoriaVeicolo.equals("Euro4") || categoriaVeicolo.equals("Euro5")
        || carburanteVeicolo.equalsIgnoreCase("GPL") || carburanteVeicolo.equals("Ibrido")
        || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equals("Idrogeno")){
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
                if(carburanteVeicolo.equalsIgnoreCase("GPL") || carburanteVeicolo.equals("Ibrido")
                || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equals("Idrogeno")){
                    multa = rand.nextInt((597-535)+1)+535;
                }else{
                    multa = rand.nextInt((658-597)+1)+597;
                }   
            break;
            case "Euro1":
                if(carburanteVeicolo.equalsIgnoreCase("GPL") || carburanteVeicolo.equals("Ibrido")
                || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equals("Idrogeno")){
                    multa = rand.nextInt((473-411)+1)+411;
                }else{
                    multa = rand.nextInt((535-473)+1)+473;
                }   
            break;
            case "Euro2":
                if(carburanteVeicolo.equalsIgnoreCase("GPL") || carburanteVeicolo.equals("Ibrido")
                || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equals("Idrogeno")){
                    multa = rand.nextInt((349-287)+1)+287;
                }else{
                    multa = rand.nextInt((411-349)+1)+349;
                }   
            break;
            case "Euro3":
                if(carburanteVeicolo.equalsIgnoreCase("GPL") || carburanteVeicolo.equals("Ibrido")
                || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equals("Idrogeno")){
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
     * Questo metodo ritorna la stringa "Ciclomotore".
     * 
     * @return VEICOLO.
     */
    
    public String tipologiaVeicolo(){
        return VEICOLO;
    }
    
    /**
     * Questo metodo ritorna la velocità massima del ciclomotore.
     * 
     * @return la velocità massima.
     */  
    
    public byte velocitaMassimaCiclomotore(){
        return velocitaMassima;
    }
}