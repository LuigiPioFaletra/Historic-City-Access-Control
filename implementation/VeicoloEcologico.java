/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @authors elvira and luigi
 */

package centroStorico;

/**
 * Questa è un'interfaccia utile per la classe ControlloAccessi.
 */

public interface VeicoloEcologico{
    
    /**
     * Questo metodo, presente in ogni sottoclasse di Veicolo, classifica il veicolo come 
     * ecologico o meno e permette di selezionare il metodo corretto per il veicolo scelto.
     */
    
    public void classificazione();
    
    /**
     * Questo metodo, presente in ogni sottoclasse di Veicolo, fornisce una multa
     * e permette di selezionare il metodo corretto per il veicolo scelto.
     *
     * @return multa 
     */
    
    public int multaAccesso();
}