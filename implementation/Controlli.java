/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @authors elvira and luigi
 */

package centroStorico;
import java.util.regex.*;
import javax.swing.JOptionPane;

/**
 * Questa classe aiuta a gestire il controllo dei dati inseriti dal client.
 */

public class Controlli{
    
    /**
     * Questo metodo controlla che l'anno di immatricolazione sia compreso tra 1900 e 2021.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param centro indica la città alla quale si vuole accedere.
     * 
     * @return l'anno di immatricolazione.
     */
    
    public static short controlloAnno(int indiceVeicoli,String centro){
        short anno;
        String formato = "[12][09][0-9]{2}",prov;
        Matcher matcher;
        Pattern pattern = Pattern.compile(formato);
        while(true){
            try{
                prov = JOptionPane.showInputDialog(null,"Inserisca l'anno immatricolazione del veicolo.\nDeve essere compreso tra il 1900 ed il 2021",
                        "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);
                if(prov == null){
                    JOptionPane.showMessageDialog(null, "E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                    anno = 0;
                    break;
                }else{
                    matcher = pattern.matcher(prov);
                    anno = Short.parseShort(prov);
                    if(matcher.matches() && (anno >= 1900 && anno <= 2021)){
                        break;
                    }
                    else if(anno < 0){
                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un anno inesistente",
                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un anno non compreso "
                                + "tra il 1900 ed il 2021","Attenzione",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Errore! Ha inserito caratteri non accettabili",
                        "Attenzione",JOptionPane.ERROR_MESSAGE);
            }
        }
        return anno;
    }
    
    /**
     * Questo metodo controlla che il numero piani e di posti dell'autobus, il numero di porte e di posti dell'automobile, 
     * la velocità massima e il numero di ruote del ciclomotore e il numero di marce del motociclo siano interi positivi.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param richiesta permette di selezionare il dato che deve essere controllato.
     * @param scelta permette di capire quale sia il veicolo.
     * @param centro indica la città alla quale si vuole accedere.
     * 
     * @return un numero che rappresenta una di queste informazioni:
     * il numero piani o di posti dell'autobus, il numero di porte o di posti dell'automobile, 
     * la velocità massima o il numero di ruote del ciclomotore o il numero di marce del motociclo
     * 
     * @throws Exception se il numero inserito è minore o uguale a 0.
     */
    
    public static byte controlloByte(int indiceVeicoli,byte richiesta,String scelta,String centro) throws Exception{
        boolean controllore = false;
        byte intero = 0;
        String scan = "";
        while(!controllore){
            try{
                switch(scelta){
                    case "Autobus":
                        if(richiesta == 1){
                            scan = JOptionPane.showInputDialog(null,"Inserisca il numero di piani dell'autobus.\nDevono essere:\nminimo 1,\nmassimo 3",
                                    "Piani autobus - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }else{
                            scan = JOptionPane.showInputDialog(null,"Inserisca i posti disponibili dell'autobus.\nDevono essere:\nminimo 10 (Per le patenti D1, D1E, D e DE),\nmassimo 16 (Per le patenti D1 e D1E),\nmassimo 120 (Per le patenti D e DE)",
                                    "Posti autobus - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }
                    break;
                    case "Automobile":
                        if(richiesta == 2){
                            scan = JOptionPane.showInputDialog(null,"Inserisca il numero di porte dell'automobile.\nDevono essere:\nminimo 2,\nmassimo 5",
                                    "Porte automobile - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }else{
                            scan = JOptionPane.showInputDialog(null,"Inserisca i posti disponibili dell'automobile.\nDevono essere:\nminimo 1,\nmassimo 9",
                                    "Posti auto - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }
                    break;
                    case "Camion":
                        scan = JOptionPane.showInputDialog(null,"Inserisca il numero di ruote del camion.\nDevono essere 4, 6, 8 o 10",
                                "Posti furgone - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                    break;
                    case "Ciclomotore":
                        if(richiesta == 3){
                            scan = JOptionPane.showInputDialog(null,"Inserisca la velocita' massima del ciclomotore.\nDeve essere:\nminimo 20 km/h,\nmassimo 45 km/h",
                                    "Velocita' ciclomotore - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }else{
                            scan = JOptionPane.showInputDialog(null,"Inserisca il numero di ruote del ciclomotore.\nDevono essere 2 o 4",
                                    "Ruote ciclomotore - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }
                    break;
                    case "Motociclo":
                        if(richiesta == 4){
                            scan = JOptionPane.showInputDialog(null,"Inserisca il numero di marce del motociclo.\nDevono essere:\nminimo 5,\nmassimo 7",
                                    "Marce motociclo - Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.QUESTION_MESSAGE);
                        }
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile selezionare correttamente il veicolo",
                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if(scan == null){
                    JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                            "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                    controllore = true;
                }else{
                    intero = Byte.parseByte(scan);
                    if(intero <= 0){
                        throw new Exception("Ha inserito un numero negativo o nullo.\nDeve inserire un numero intero positivo");
                    }
                    controllore = true;
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Errore! Non ha inserito un numero o ha inserito un numero non intero",
                        "Attenzione",JOptionPane.ERROR_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Errore! "+e.getMessage(),"Attenzione",JOptionPane.ERROR_MESSAGE);
            }
        }
        return intero;
    }
    
    /**
     * Questo metodo controlla se il veicolo possiede il catalizzatore.
     * 
     * @param anno indica l'anno di immatricolazione del veicolo.
     * @param cilindrata indica la cilindrata del veicolo.
     * @param carburante indica il tipo di alimentazione del veicolo.
     * 
     * @return una stringa che verifica il possesso del catalizzatore.
     */
    
    public static String controlloCatalizzatore(short anno,short cilindrata,String carburante){
        String catalizzatore;
        if(anno >= 1990 && anno <= 1992 && cilindrata >= 2000 && carburante.equals("Benzina")){
            catalizzatore = "Si";
        }else if(anno >= 1993 && anno <= 1996 && carburante.equals("Benzina")){
            catalizzatore = "Si";
        }else if(anno >= 1997){
            catalizzatore = "Si";
        }else{
            catalizzatore = "No";
        }
        if(catalizzatore.equals("Si")){
            JOptionPane.showMessageDialog(null,"Il veicolo possiede il catalizzatore",
                    "Emissioni",JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(null,"Il veicolo non presenta il catalizzatore",
                    "Emissioni",JOptionPane.INFORMATION_MESSAGE);
        }
        return catalizzatore;
    }
    
    /**
     * Questo metodo controlla la categoria "Euro" a cui appartiene il veicolo.
     * 
     * @param anno indica l'anno di immatricolazione del veicolo.
     * @param scelta indica il tipo di veicolo da passare al controllo.
     * 
     * @return la categoria "Euro".
     */
    
    public static String controlloCategoria(short anno,String scelta){
        String categoria = "";
        if((anno >= 2016) && (scelta.equals("Autobus") || scelta.equals("Automobile") || scelta.equals("Camion"))){
            categoria = "Euro6";
        }else if(anno <= 1992){
            categoria = "Euro0";
        }else if(anno >= 1993 && anno <= 1996){
            categoria = "Euro1";
        }else if(anno >= 1997 && anno <= 2000){
            categoria = "Euro2";
        }else if(anno >= 2001 && anno <= 2005){
            categoria = "Euro3";
        }else if(anno >= 2006  && anno <= 2008){
            categoria = "Euro4";
        }else if(anno >= 2009){
            categoria = "Euro5";
        }
        JOptionPane.showMessageDialog(null,"Rietra nella categoria "+categoria,
                "Emissioni",JOptionPane.INFORMATION_MESSAGE);
        return categoria;
    }
    
    /**
     * Questo metodo permette di selezionare il veicolo adatto al valore di cilindrata inserito.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param cilindrata indica la cilindrata del veicolo.
     * @param centro indica il centro storico scelto dal veicolo.
     * 
     * @return il veicolo scelto.
     */
    
    public static String controlloCilindrata(int indiceVeicoli,short cilindrata,String centro){
        String scelta = "";
        while(true){
            if(cilindrata <= 50){
                String[] sceltePossibili = {"Ciclomotore","Triciclo"};
                scelta = (String)JOptionPane.showInputDialog(null,"Che tipo di veicolo possiede?",
                        "Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.INFORMATION_MESSAGE,null,sceltePossibili,sceltePossibili[0]);
            }else if(cilindrata > 50){
                String[] sceltePossibili = {"Autobus","Automobile","Camion","Motociclo","Triciclo"};
                scelta = (String)JOptionPane.showInputDialog(null,"Che tipo di veicolo possiede?",
                        "Veicolo "+(indiceVeicoli+1)+" "+centro,JOptionPane.INFORMATION_MESSAGE,null,sceltePossibili,sceltePossibili[0]);
            }
            if(scelta == null){
                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            break;
        }
        return scelta;
    }
    
    /**
     * Questo metodo controlla se il peso del carico trasportato dal camion è compreso tra 0 e 30000 kg.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param richiesta gestisce la richiesta del client.
     * @param centro indica la città alla quale si vuole accedere.
     * 
     * @return il valore del peso del carico trasportato dal camion.
     * 
     * @throws Exception ereditato dal metodo controlloShort
     */
    
    public static short controlloPeso(int indiceVeicoli,byte richiesta,String centro) throws Exception{
        int scan;
        short peso = 0;
        scan = JOptionPane.showConfirmDialog(null,"Il suo camion e' vuoto?");
        switch(scan){
            case -1:
                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                peso = 30001;
            break;
            case 0:
                peso = 0;
                JOptionPane.showMessageDialog(null,"Il peso del suo camion e' pari a "
                        + peso + " kg","Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE);
            break;
            case 1:
                do{
                    richiesta = 1;
                    peso = Controlli.controlloShort(indiceVeicoli,richiesta,centro);
                    if(peso > 30000){
                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un peso superiore a 30000 kg",
                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                    }else if(peso == 0){
                        peso = 30001; // Prevede l'uscita dal programma
                        break;
                    }
                }while(peso > 30000);
            break;
            case 2:
                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                peso = 30001; // Prevede l'uscita dal programma 
            break;
            default:
                JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile assegnare il peso trasportato dal camion",
                        "Attenzione",JOptionPane.ERROR_MESSAGE);
            break;
        }
        return peso;
    }
    
    /**
     * Questo metodo controlla che la cilindrata e il peso del carico trasportato dal camion siano interi positivi.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param richiesta gestisce la richiesta del client.
     * @param centro indica la città alla quale si vuole accedere.
     * 
     * @return un numero intero positivo che rappresenta una di queste informazioni:
     * la cilindrata o il peso del carico trasportato dal camion.
     * 
     * @throws Exception se il numero è minore o uguale a 0.
     */
    
    public static short controlloShort(int indiceVeicoli,byte richiesta,String centro) throws Exception{
        boolean controllore = false;
        short intero = 0;
        String scan;
        while(!controllore){
            try{
                if(richiesta == 1){
                    scan = JOptionPane.showInputDialog(null,"Inserisca il peso in chilogrammi del carico trasportato dal camion.\nDeve essere massimo pari a 30000 kg",
                            "Peso trasportato camion - " + centro,JOptionPane.QUESTION_MESSAGE);
                }else{
                    scan = JOptionPane.showInputDialog(null,"Inserisca la cilindrata del veicolo.\nDeve essere massimo pari a 50 per ciclomotori e tricicli.\nDeve essere superiore a 50 per autobus, automobili, camion, motocicli e tricicli",
                            "Veicolo " + (indiceVeicoli+1) + " - " + centro,JOptionPane.QUESTION_MESSAGE);
                }
                if(scan == null){
                    JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                            "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                    intero = 0;
                    controllore = true;
                }else{
                    intero = Short.parseShort(scan);
                    if(intero <= 0){
                        throw new Exception("Ha inserito un numero negativo o nullo.\nDeve inserire un numero intero positivo");
                    }
                    controllore = true;
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Errore! Deve inserire un numero intero","Attenzione",JOptionPane.ERROR_MESSAGE);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Errore! "+e.getMessage(),"Attenzione",JOptionPane.ERROR_MESSAGE);
            }
        }
        return intero;
    }
    
    /**
     * Questo metodo consente di controllare che il centro storico scelto, il nome, il cognome e la città del proprietario,
     * la marca e il tipo di carburante del veicolo siano stringhe senza alcun numero.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param richiesta indica il numero di richiesta da gestire.
     * @param centro indica la città alla quale si vuole accedere.
     * 
     * @return una stringa con l'iniziale maiuscola che rappresenta una di queste informazioni:
     * il centro storico scelto, il nome, il cognome o la città del proprietario,
     * la marca o il tipo di carburante del veicolo.
     */
    
    public static String controlloStringa(int indiceVeicoli,byte richiesta,String centro){
        boolean controllore = false;
        char carattere;
        char[] caratteriRicercati = {' ','-'};
        String centrale,fine,inizio,scan = "",stringa = "";
        String[] cittaSicilia = {"Agrigento","Caltanissetta","Catania","Enna","Messina","Palermo","Ragusa","Siracusa","Trapani"};
        do{
            try{
                switch(richiesta){
                    case 1:
                        scan = (String) JOptionPane.showInputDialog(null,"Inserisca la citta' siciliana alla quale vuole accedere",
                                "Citta",JOptionPane.QUESTION_MESSAGE,null,cittaSicilia,cittaSicilia[0]);
                    break;
                    case 2:
                        scan = JOptionPane.showInputDialog(null,"Inserisca il nome del proprietario",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);
                    break;
                    case 3:
                        scan = JOptionPane.showInputDialog(null,"Inserisca il cognome del proprietario",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);
                    break;
                    case 4:
                        scan = JOptionPane.showInputDialog(null,"Inserisca la citta' del proprietario",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);
                    break; 
                    case 5:
                        scan = JOptionPane.showInputDialog(null,"Inserisca la marca del veicolo",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);
                    break;
                    case 6:
                        scan = JOptionPane.showInputDialog(null,"Inserisca il tipo di carburante del veicolo",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile selezionare la sua richiesta.",
                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                if(scan == null){
                    JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                            "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                    stringa = null;
                }else{
                    stringa = scan;
                    if(stringa.equals("")){
                        throw new Exception("Non ha inserito alcun dato. Deve inserire una stringa");
                    }else{
                        for(int j = 0;j < stringa.length();j++){
                            carattere = stringa.charAt(j);
                            if(!Character.isLetter(carattere) && (Character.compare(carattere,' ')!=0 && Character.compare(carattere,'-')!=0 && !stringa.contains("'") || j == 0)){
                                throw new Exception("Deve inserire una stringa. Non sono concessi numeri e caratteri diversi da lettere, spazi, trattini e apostrofi");
                            }
                        }
                    }
                    inizio = stringa.substring(0,1).toUpperCase();
                    stringa = stringa.substring(1,stringa.length()).toLowerCase();
                    stringa = inizio+stringa;
                    for(int j = 0;j < stringa.length();j++){
                        for(int k = 0;k < caratteriRicercati.length;k++){
                            if(stringa.contains("'")){
                                centrale = stringa.substring(1,stringa.indexOf("'"));
                                carattere = stringa.charAt(stringa.indexOf("'")+1);
                                carattere = Character.toUpperCase(carattere);
                                fine = stringa.substring(stringa.indexOf("'")+2,stringa.length());
                                stringa = inizio+centrale+"'"+carattere+fine;
                            }
                            if(caratteriRicercati[k] == stringa.charAt(j)){
                                centrale = stringa.substring(1,j);
                                carattere = stringa.charAt(j+1);
                                carattere = Character.toUpperCase(carattere);
                                fine = stringa.substring(j+2,stringa.length());
                                stringa = inizio+centrale+caratteriRicercati[k]+carattere+fine;
                            }
                        }
                    }
                }
                controllore = true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Errore! "+e.getMessage(),
                        "Attenzione",JOptionPane.ERROR_MESSAGE);
            }
        }while(!controllore);
        return stringa;
    }
    
    /**
     * Questo metodo controlla che la targa rispetti un formato specifico.
     * 
     * @param indiceVeicoli indica il numero del veicolo passato al controllo.
     * @param anno indica l'anno di immatricolazione.
     * @param veicolo indica il tipo di veicolo.
     * @param centro indica la città alla quale si vuole accedere.
     * 
     * @return la targa del veicolo.
     */
    
    public static String controlloTarga(int indiceVeicoli,short anno,String veicolo,String centro){
        boolean controllore = false;
        String formato1 = "[A-Z]{2}[0-9]{3}[A-Z]{2}",formato2 = "[A-Z]{2}[0-9]{6}",formato3 = "[A-Z]{2}[0-9]{5}",formato4 = "[0-9][A-Z][0-9][A-Z][0-9]",targa = "";
        Pattern pattern1 = Pattern.compile(formato1),pattern2 = Pattern.compile(formato2),pattern3 = Pattern.compile(formato3),pattern4 = Pattern.compile(formato4);
        Matcher matcher = pattern1.matcher(targa);
        while(!controllore){
            switch(veicolo){
                case "Autobus":
                    if(anno >= 1995 && anno <= 2021){
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa dell'autobus.\nDeve essere del tipo: AA000AA",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern1.matcher(targa);
                        }
                    }else{
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa dell'autobus.\nDeve essere del tipo: AA000000",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern2.matcher(targa);
                        }
                    }                    
                break;
                case "Automobile":
                    if(anno >= 1995 && anno <= 2021){
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa dell'automobile.\nDeve essere del tipo: AA000AA",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern1.matcher(targa);
                        }
                    }
                    else{
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa dell'automobile.\nDeve essere del tipo: AA000000",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern2.matcher(targa);
                        }
                    }
                break;
                case "Camion":
                    if(anno >= 1995 && anno <= 2021){
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del camion.\nDeve essere del tipo: AA000AA",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern1.matcher(targa);
                        }
                    }
                    else{
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del camion.\nDeve essere del tipo: AA000000",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern2.matcher(targa);
                        }
                    }
                break;
                case "Ciclomotore":
                    if(anno >= 1993 && anno <= 2021){
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del ciclomotore.\nDeve essere del tipo: AA00000",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern3.matcher(targa);
                        }
                    }
                    else{
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del ciclomotore.\nDeve essere del tipo: 0A0A0",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern4.matcher(targa);
                        }
                    }
                break;
                case "Motociclo":
                    if(anno >= 1995 && anno <= 2021){
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del motociclo.\nDeve essere del tipo: AA00000",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern3.matcher(targa);
                        }
                    }
                    else{
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del motociclo.\nDeve essere del tipo: 0A0A0",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern4.matcher(targa);
                        }
                    }
                break;
                case "Triciclo":                
                    if(anno >= 1993 && anno <= 2021){
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del triciclo.\nDeve essere del tipo: AA00000",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern3.matcher(targa);
                        }
                    }
                    else{
                        targa = JOptionPane.showInputDialog(null,"Inserisca la targa del triciclo.\nDeve essere del tipo: 0A0A0",
                                "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.QUESTION_MESSAGE);   
                        if(targa == null){
                            JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                    "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            matcher = pattern4.matcher(targa);
                        }
                    }
                break;
                default:
                    JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile assegnare nessuna targa,"+
                            " dato che non è stato possibile selezionare il veicolo","Attenzione",JOptionPane.ERROR_MESSAGE);
                break;
            }
            if(targa == null || matcher.matches()){
                controllore = true;
            }
            else{
                JOptionPane.showMessageDialog(null,"Errore! La targa da lei fornita è scorretta. "
                        + "Si ricordi di inserire le lettere in maiuscolo","Attenzione",JOptionPane.ERROR_MESSAGE);
                controllore = false;
            }
        }
        return targa;
    }
}