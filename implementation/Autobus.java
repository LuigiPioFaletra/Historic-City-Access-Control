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
 * Un oggetto di questa classe rappresenta un'autobus.
 */

public class Autobus extends Veicolo implements VeicoloEcologico{
    
    /**
     * Indica il numero dei piani dell'autobus.
     */
    
    private byte numeroPiani;
    
    /**
     * Indica il tipo di veicolo, ovvero Autobus, richiamato all'interno di tipologiaVeicolo().
     */
    
    public final static String VEICOLO = "Autobus";
    
    Autobus(String marcaV,String carburante,short cilindrataV,short anno,Targa targaV,String categoriaV,byte posti,byte ruote,byte piani){
        super(marcaV,carburante,cilindrataV,anno,targaV,categoriaV,posti,ruote);
        numeroPiani = piani;
    }
    
    Autobus(){
        super();
        numeroPiani = 0;
    }
    
    /**
     * Questo metodo assegna il numeroPiani all'oggetto della classe Autobus.
     * 
     * @param piani è il numero di piani da assegnare ad Autobus.
     */
    
    public void assegnazioneNumeroPiani(byte piani){
        numeroPiani = piani;
    }
    
    /**
     * Questo metodo permette di classificare l'autobus come ecologico o non ecologico.
     */
    
    public void classificazione(){
        if(categoriaVeicolo.equals("Euro4") || categoriaVeicolo.equals("Euro5") || categoriaVeicolo.equals("Euro6")
        || categoriaVeicolo.equalsIgnoreCase("CNG") || categoriaVeicolo.equalsIgnoreCase("GPL")
        || categoriaVeicolo.equals("Biogas") || categoriaVeicolo.equals("Biodiesel")){
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
                if(categoriaVeicolo.equalsIgnoreCase("CNG") || categoriaVeicolo.equalsIgnoreCase("GPL")
                || categoriaVeicolo.equals("Biogas") || categoriaVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((597-535)+1)+535;
                }else{
                    multa = rand.nextInt((658-597)+1)+597;
                }   
            break;
            case "Euro1":
                if(categoriaVeicolo.equalsIgnoreCase("CNG") || categoriaVeicolo.equalsIgnoreCase("GPL")
                || categoriaVeicolo.equals("Biogas") || categoriaVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((473-411)+1)+411;
                }else{
                    multa = rand.nextInt((535-473)+1)+473;
                }
            break;
            case "Euro2":
                if(categoriaVeicolo.equalsIgnoreCase("CNG") || categoriaVeicolo.equalsIgnoreCase("GPL")
                || categoriaVeicolo.equals("Biogas") || categoriaVeicolo.equals("Biodiesel")){
                    multa = rand.nextInt((349-287)+1)+287;
                }else{
                    multa = rand.nextInt((411-349)+1)+349;
                }   
            break;
            case "Euro3":
                if(categoriaVeicolo.equalsIgnoreCase("CNG") || categoriaVeicolo.equalsIgnoreCase("GPL")
                || categoriaVeicolo.equals("Biogas") || categoriaVeicolo.equals("Biodiesel")){
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
     * Questo metodo ritorna il numero dei piani dell'autobus.
     * 
     * @return il numero dei piani dell'autobus.
     */
    
    public byte numeroPianiAutobus(){
        return numeroPiani;
    }
    
    /**
     * Questo metodo ritorna la stringa "Autobus".
     * 
     * @return VEICOLO.
     */
    
    public String tipologiaVeicolo(){
        return VEICOLO;
    }
}