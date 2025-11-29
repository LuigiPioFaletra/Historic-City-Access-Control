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
 * Un oggetto di questa classe rappresenta un Camion.
 */

public class Camion extends Veicolo implements VeicoloEcologico{
    
    /**
     * Indica il peso trasportato dal camion.
     */
    
    private short pesoTrasportato;
    
    /**
     * Indica il tipo di veicolo, ovvero Camion, richiamato all'interno di tipologiaVeicolo().
     */
    
    public final static String VEICOLO = "Camion";
    
    Camion(String marcaV,String carburante,short cilindrataV,short anno,Targa targaV,String categoriaV,byte posti,byte ruote,short peso){
        super(marcaV,carburante,cilindrataV,anno,targaV,categoriaV,posti,ruote);
        pesoTrasportato = peso;
    }
    
    /**
     * Questo metodo assegna il pesoTrasportato all'oggetto della classe Camion.
     * 
     * @param peso è il peso trasportato da assegnare a Camion.
     */
    
    public void assegnazionePeso(short peso){
        pesoTrasportato = peso;
    }
    
    /**
     * Questo metodo permette di classificare il camion come ecologico o non ecologico.
     */   
    
    public void classificazione(){
        if(categoriaVeicolo.equals("Euro4") || categoriaVeicolo.equals("Euro5") || categoriaVeicolo.equals("Euro6")
        || carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equalsIgnoreCase("GPL")|| categoriaVeicolo.equalsIgnoreCase("LNG")
        || carburanteVeicolo.equals("Biometano") || carburanteVeicolo.equalsIgnoreCase("CNG")){
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
                if(carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equalsIgnoreCase("GPL") 
                || carburanteVeicolo.equalsIgnoreCase("LNG") || carburanteVeicolo.equals("Biometano") 
                || carburanteVeicolo.equalsIgnoreCase("CNG")){
                multa = rand.nextInt((597-535)+1)+535;
                }else{
                    multa = rand.nextInt((658-597)+1)+597;
                }   
            break;
            case "Euro1":
                if(carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equalsIgnoreCase("GPL") 
                || carburanteVeicolo.equalsIgnoreCase("LNG") || carburanteVeicolo.equals("Biometano") 
                || carburanteVeicolo.equalsIgnoreCase("CNG")){
                    multa = rand.nextInt((473-411)+1)+411;
                }else{
                    multa = rand.nextInt((535-473)+1)+473;
                }   
            break;
            case "Euro2":
                if(carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equalsIgnoreCase("GPL") 
                || carburanteVeicolo.equalsIgnoreCase("LNG") || carburanteVeicolo.equals("Biometano") 
                || carburanteVeicolo.equalsIgnoreCase("CNG")){
                    multa = rand.nextInt((349-287)+1)+287;
                }else{
                    multa = rand.nextInt((411-349)+1)+349;
                }   
            break;
            case "Euro3":
                if(carburanteVeicolo.equals("Elettrico") || carburanteVeicolo.equalsIgnoreCase("GPL") 
                || carburanteVeicolo.equalsIgnoreCase("LNG") || carburanteVeicolo.equals("Biometano") 
                || carburanteVeicolo.equalsIgnoreCase("CNG")){
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
     * Questo metodo ritorna il peso di ciò che è trasportato dal camion.
     * 
     * @return il peso di ciò che trasportato dal camion.
     */    
    
    public short pesoTrasportatoCamion(){
        return pesoTrasportato;
    }
    
    /**
     * Questo metodo ritorna la stringa "Camion".
     * 
     * @return VEICOLO.
     */ 
    
    public String tipologiaVeicolo(){
        return VEICOLO;
    }
}