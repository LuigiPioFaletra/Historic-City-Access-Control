/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @authors elvira and luigi
 */

package centroStorico;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

/**
 * Questa classe ha lo scopo di controllare se un veicolo è ecologico o meno e
 * di permettere l'accesso al centro storico o impedirlo ed assegnare una multa
 */

public class ControlloAccessi{
    
    /**
     * Questo metodo determina quali veicolo possono entrare o meno nel centro storico.
     * 
     * @param veicolo è un'interfaccia che permette di selezionare il metodo opportuno del veicolo.
     * @param targa è l'oggetto targa che ha un metodo che permette di leggere se un veicolo è ecologico o non lo è.
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * 
     * @return 1 se è stato multato un veicolo, altrimenti ritorna 0.
     */
    
    public static int classificazioneVeicolo(VeicoloEcologico veicolo,Targa targa,int indiceVeicoli){
        int veicoliMultati,multa;
        DecimalFormat df = new DecimalFormat("0.00");
        veicolo.classificazione();
        if(targa.ecologia().equals("Non ecologico")){
            JOptionPane.showMessageDialog(null,"La sua vettura non rispetta i criteri ecologici della citta'.\nPer questo motivo verra' sanzionato",
                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE);
            multa = veicolo.multaAccesso();
            JOptionPane.showMessageDialog(null,"Il valore della multa e' pari a €" 
                    + df.format(multa),"Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE);               
            veicoliMultati = 1;
        }else{ 
            JOptionPane.showMessageDialog(null,"La sua vettura rispetta i criteri ecologici della citta'.\nPuo' proseguire all'interno del centro storico",
                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE);
            veicoliMultati = 0;
        }
        return veicoliMultati;
    }
}