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
 * Un oggetto di questa classe rappresenta un'Automobile.
 */

public class Automobile extends Veicolo implements VeicoloEcologico{
    
    /**
     * Indica il numero delle porte dell'automobile.
     */
    
    private byte numeroPorte;
    
    /**
     * Indica il tipo di veicolo, ovvero Automobile, richiamato all'interno di tipologiaVeicolo().
     */
    
    public final static String VEICOLO = "Automobile";
    
    Automobile(String marcaV,String carburante,short cilindrataV,short anno,Targa targaV,String categoriaV,byte posti,byte ruote,byte porte){
        super(marcaV,carburante,cilindrataV,anno,targaV,categoriaV,posti,ruote);
        numeroPorte = porte;
    }
    
    /**
     * Questo metodo assegna il numeroPorte all'oggetto della classe Automobile.
     * 
     * @param porte è il numero delle porte da assegnare ad Automobile.
     */
    
    public void assegnazioneNumeroPorte(byte porte){
        numeroPorte = porte;
    }
    
    /**
     * Questo metodo permette di classificare l'automobile come ecologico o non ecologico.
     */   
    
    public void classificazione(){
        if(categoriaVeicolo.equals("Euro4") || categoriaVeicolo.equals("Euro5") || categoriaVeicolo.equals("Euro6")
        || categoriaVeicolo.equals("Ibrido") || categoriaVeicolo.equalsIgnoreCase("GPL") || categoriaVeicolo.equals("Metano") 
        || categoriaVeicolo.equals("Elettrico") || categoriaVeicolo.equals("Bioetanolo") || categoriaVeicolo.equals("Biodiesel")){
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
                if(categoriaVeicolo.equals("Ibrido") || categoriaVeicolo.equalsIgnoreCase("GPL") || categoriaVeicolo.equals("Metano") 
                || categoriaVeicolo.equals("Elettrico") || categoriaVeicolo.equals("Bioetanolo") || categoriaVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((597-535)+1)+535;
                }else{
                    multa = rand.nextInt((658-597)+1)+597;
                }   
            break;
            case "Euro1":
                if(categoriaVeicolo.equals("Ibrido") || categoriaVeicolo.equalsIgnoreCase("GPL") || categoriaVeicolo.equals("Metano") 
                || categoriaVeicolo.equals("Elettrico") || categoriaVeicolo.equals("Bioetanolo") || categoriaVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((473-411)+1)+411;
                }else{
                    multa = rand.nextInt((535-473)+1)+473;
                }   
            break;
            case "Euro2":
                if(categoriaVeicolo.equals("Ibrido") || categoriaVeicolo.equalsIgnoreCase("GPL") || categoriaVeicolo.equals("Metano") 
                || categoriaVeicolo.equals("Elettrico") || categoriaVeicolo.equals("Bioetanolo") || categoriaVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((349-287)+1)+287;
                }else{
                    multa = rand.nextInt((411-349)+1)+349;
                }   
            break;
            case "Euro3":
                if(categoriaVeicolo.equals("Ibrido") || categoriaVeicolo.equalsIgnoreCase("GPL") || categoriaVeicolo.equals("Metano") 
                || categoriaVeicolo.equals("Elettrico") || categoriaVeicolo.equals("Bioetanolo") || categoriaVeicolo.equals("Biodiesel")){
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
     * Questo metodo ritorna il numero delle porte dell'automobile.
     * 
     * @return il numero delle porte dell'automobile.
     */    
    
    public byte numeroPorteAutomobile(){
        return numeroPorte;
    }
   
    /**
     * Questo metodo ritorna la stringa "Automobile".
     * 
     * @return VEICOLO.
     */
    
    public String tipologiaVeicolo(){
        return VEICOLO;
    }
}