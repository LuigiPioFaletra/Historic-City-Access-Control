/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * @authors elvira and luigi
 */

package centroStorico;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Questa classe permette l'inserimento dei dati del veicolo e del suo proprietario.
 */

public class Client{
    
    /**
     * Questa variabile permette di capire se il client vuole proseguire nell'inserimento dei veicoli.
     */
    
    public final static int CONTINUAZIONE = 0;
    
    /**
     * Questa variabile permette di capire se il client non vuole proseguire nell'inserimento dei veicoli.
     */
    
    public final static int CONCLUSIONE = 1;
    
    /**
     * Il metodo main permette al proprietario di un veicolo di inserire i dati del proprio veicolo
     * 
     * @param args String[]
     * 
     * @throws Exception causata dai metodi controlloByte() e controlloShort() della classe Controlli
     */
    
    public static void main(String[] args) throws Exception{
        boolean controllore = false;
        byte marce,piani,porte,posti,richiesta,ruote,velocita;
        int continuazione,indiceVeicoli = 0,indiceVeicoliEcologici = 0,indiceVeicoliMultati = 0;
        short anno,cilindrata,peso;
        String cambio,carburante,catalizzatore,categoria,centro,citta,cognome,marca,nome,patente,scelta,targa = null,verifica;
        VeicoloEcologico v;
        try{
            Socket client = new Socket("localhost",5200);
            OutputStream outputStream = client.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            System.out.println("Connessione al server riuscita");
            richiesta = 1;
            centro = Controlli.controlloStringa(indiceVeicoli,richiesta,null);
            if(centro == null){
                objectOutputStream.writeObject(centro);
                client.close(); // Il client non vuole inserire più i dati
            }else{
                objectOutputStream.writeObject(centro);
                JOptionPane.showMessageDialog(null,"Benvenuto a "+centro+"!\nPrema OK per procedere con il controllo del suo veicolo",
                        "Centro storico di "+centro,JOptionPane.INFORMATION_MESSAGE);
                ArrayList <Veicolo> veicoli = new ArrayList();
                ArrayList <Veicolo> veicoliEcologici = new ArrayList();
                while(!controllore){
                    richiesta = 2;
                    nome = Controlli.controlloStringa(indiceVeicoli,richiesta,centro);
                    if(nome == null){
                        break; // Il client non vuole inserire più i dati
                    }
                    richiesta = 3;
                    cognome = Controlli.controlloStringa(indiceVeicoli,richiesta,centro);
                    if(cognome == null){
                        break; //Il client non vuole inserire più i dati
                    }
                    richiesta = 4;
                    citta = Controlli.controlloStringa(indiceVeicoli,richiesta,centro);
                    if(citta == null){
                        break; // Il client non vuole inserire più i dati
                    }
                    richiesta = 5;
                    marca = Controlli.controlloStringa(indiceVeicoli,richiesta,centro);
                    if(marca == null){
                        break; // Il client non vuole inserire più i dati
                    }
                    richiesta = 6;
                    carburante = Controlli.controlloStringa(indiceVeicoli,richiesta,centro);
                    if(carburante == null){
                        break; // Il client non vuole inserire più i dati
                    }
                    cilindrata = Controlli.controlloShort(indiceVeicoli,richiesta,centro);
                    if(cilindrata == 0){
                        break; // Il client non vuole inserire più i dati
                    }
                    scelta = Controlli.controlloCilindrata(indiceVeicoli,cilindrata,centro);
                    if(scelta == null){
                        break; // Il client non vuole inserire più i dati
                    }
                    anno = Controlli.controlloAnno(indiceVeicoli,centro);
                    if(anno == 0){
                        break; // Il client non vuole inserire più i dati
                    }
                    categoria = Controlli.controlloCategoria(anno,scelta);
                    catalizzatore = Controlli.controlloCatalizzatore(anno,cilindrata,carburante);
                    switch(scelta){
                        case "Autobus":
                            String[] patentiAutobus = {"D1","D1E","D","DE"};
                            patente = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare l'autobus",
                                    "Veicolo "+(indiceVeicoli+1)+" - "+centro,JOptionPane.INFORMATION_MESSAGE, null, patentiAutobus, patentiAutobus[0]);
                            if(patente == null){
                                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                controllore = true; // Il client non vuole inserire più i dati
                            }else{
                                if(patente.equals("D1") || patente.equals("D1E")){
                                    do{
                                        posti = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                        if(posti == 0){
                                            controllore = true;
                                            break; // Il client non vuole inserire più i dati
                                        }else if(posti < 10 || posti > 16){
                                            JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di posti non compreso tra 10 e 16",
                                                    "Attenzione",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }while(posti < 10 || posti > 16);
                                }else{
                                    do{
                                        posti = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                        if(posti == 0){
                                            controllore = true;
                                            break; // Il client non vuole inserire più i dati
                                        }else if(posti < 10 || posti > 120){
                                            JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di posti non compreso tra 10 e 120",
                                                    "Attenzione",JOptionPane.ERROR_MESSAGE);
                                        }
                                    }while(posti < 10 || posti > 120);
                                }
                                if(posti == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                do{
                                    richiesta = 1;
                                    piani = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(piani == 0){
                                        controllore = true;
                                        break; // Il client non vuole inserire più i dati
                                    }else if(piani < 1 || piani > 3){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di piani non compreso tra 1 e 3",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(piani < 1 || piani > 3);
                                if(piani == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                while(true){
                                    if(indiceVeicoli > 0){
                                        for(int indiceControllo = 0;indiceControllo < indiceVeicoli;){
                                            if((veicoli.get(indiceControllo).targaVeicolo.targaProprietario()).equals(verifica)){
                                                JOptionPane.showMessageDialog(null,"Risulta che questa targa e' gia' stata inserita.\nRiprovi",
                                                        "Errore",JOptionPane.ERROR_MESSAGE);
                                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                                indiceControllo = 0;
                                            }else{
                                                indiceControllo++;
                                            }
                                        }
                                        targa = verifica;
                                        break;
                                    }else{
                                        targa = verifica;
                                        break;
                                    }
                                }
                                if(targa == null){
                                    controllore = true; // Il client non vuole inserire più i dati
                                }else{
                                    Proprietario utenteAutobus = new Proprietario(nome,cognome,citta,patente);
                                    Targa targaAutobus = new Targa(targa,utenteAutobus);
                                    Autobus autobus = new Autobus();
                                    v = autobus;
                                    ruote = 4;
                                    autobus.assegnazioneAnnoImmatricolazione(anno);
                                    autobus.assegnazioneCarburante(carburante);
                                    autobus.assegnazioneCategoria(categoria);
                                    autobus.assegnazioneCilindrata(cilindrata);
                                    autobus.assegnazioneMarca(marca);
                                    autobus.assegnazionePosti(posti);
                                    autobus.assegnazioneRuote(ruote);
                                    autobus.assegnazioneTarga(targaAutobus);
                                    autobus.assegnazioneNumeroPiani(piani);
                                    if(ControlloAccessi.classificazioneVeicolo(v,targaAutobus,indiceVeicoli) == 0){
                                        veicoli.add(indiceVeicoli,autobus);
                                        veicoliEcologici.add(indiceVeicoliEcologici,autobus);
                                        indiceVeicoliEcologici++;
                                    }else{
                                        veicoli.add(indiceVeicoli,autobus);
                                        indiceVeicoliMultati++;
                                    }
                                    outputStream.write(CONTINUAZIONE); // Questo numero permette di proseguire l'esecuzione del client (nel server corrisponde alla variabile continuazione)
                                    outputStream.write(0); // Questo numero permette di selezionare il caso dell'Autobus (nel server corrisponde alla variabile scelta)
                                    objectOutputStream.writeObject(autobus);
                                }
                            }
                        break;
                        case "Automobile":
                            String[] patentiAutomobile = {"B1","B","B96","BE"};
                            patente = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare l'automobile",
                                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE,null,patentiAutomobile,patentiAutomobile[0]);
                            if(patente == null){
                                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                controllore = true; // Il client non vuole inserire più i dati
                            }else{
                                do{
                                    posti = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(posti == 0){
                                        controllore = true;
                                        break; // Il client non vuole inserire più i dati
                                    }else if(posti < 0 || posti > 9){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di posti non compreso tra 1 e 9",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(posti < 0 || posti > 9);
                                if(posti == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                do{
                                    richiesta = 2;
                                    porte = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(porte == 0){
                                        controllore = true;
                                        break; // Il client non vuole inserire più i dati
                                    }else if(porte < 2 || porte > 5){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di porte non compreso tra 2 e 5",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(porte < 2 || porte > 5);
                                if(porte == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                while(true){
                                    if(indiceVeicoli > 0){
                                        for(int indiceControllo = 0;indiceControllo < indiceVeicoli;){
                                            if((veicoli.get(indiceControllo).targaVeicolo.targaProprietario()).equals(verifica)){
                                                JOptionPane.showMessageDialog(null,"Risulta che questa targa e' gia' stata inserita.\nRiprovi",
                                                        "Errore",JOptionPane.ERROR_MESSAGE);
                                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                                indiceControllo = 0;
                                            }else{
                                                indiceControllo++;
                                            }
                                        }
                                        targa = verifica;
                                        break;
                                    }else{
                                        targa = verifica;
                                        break;
                                    }
                                }
                                if(targa == null){
                                    controllore = true; // Il client non vuole inserire più i dati
                                }else{
                                    Proprietario utenteAutomobile = new Proprietario(nome,cognome,citta,patente);
                                    Targa targaAutomobile = new Targa(targa,utenteAutomobile);
                                    ruote = 4;
                                    Automobile automobile = new Automobile(marca,carburante,cilindrata,anno,targaAutomobile,categoria,posti,ruote,porte);
                                    v = automobile;
                                    if(ControlloAccessi.classificazioneVeicolo(v,targaAutomobile,indiceVeicoli) == 0){
                                        veicoli.add(indiceVeicoli,automobile);
                                        veicoliEcologici.add(indiceVeicoliEcologici,automobile);
                                        indiceVeicoliEcologici++;
                                    }else{
                                        veicoli.add(indiceVeicoli,automobile);
                                        indiceVeicoliMultati++;
                                    }
                                    outputStream.write(CONTINUAZIONE); // Questo numero permette di proseguire l'esecuzione del client (nel server corrisponde alla variabile continuazione)
                                    outputStream.write(1); // Questo numero permette di selezionare il caso dell'Automobile (nel server corrisponde alla variabile scelta)
                                    objectOutputStream.writeObject(automobile);
                                }
                            }
                        break;
                        case "Camion":
                            String[] patentiCamion = {"C1","C1E","C","CE"};
                            patente = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare il camion",
                                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE,null,patentiCamion,patentiCamion[0]);
                            if(patente == null){
                                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                controllore = true;
                            }else{
                                do{
                                    ruote = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(ruote == 0){
                                        controllore = true; // Il client non vuole inserire più i dati
                                        break;
                                    }else if(ruote%2 != 0){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di ruote dispari",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }else if(ruote < 4 || ruote > 10){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di ruote pari ma non compreso tra 4 e 10",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(ruote < 4 || ruote > 10 || ruote%2 != 0);
                                if(ruote == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                peso = Controlli.controlloPeso(indiceVeicoli,richiesta,centro);
                                if(peso == 30001){
                                    controllore = true; // Il client non vuole inserire più i dati
                                }else{
                                    verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                    while(true){
                                        if(indiceVeicoli > 0){
                                            for(int indiceControllo = 0;indiceControllo < indiceVeicoli;){
                                                if((veicoli.get(indiceControllo).targaVeicolo.targaProprietario()).equals(verifica)){
                                                    JOptionPane.showMessageDialog(null,"Risulta che questa targa e' gia' stata inserita.\nRiprovi",
                                                            "Errore",JOptionPane.ERROR_MESSAGE);
                                                    verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                                    indiceControllo = 0;
                                                }else{
                                                    indiceControllo++;
                                                }
                                            }
                                        targa = verifica;
                                        break;
                                        }else{
                                            targa = verifica;
                                            break;
                                        }
                                    }
                                    if(targa == null){
                                        controllore = true; // Il client non vuole inserire più i dati
                                    }else{
                                        Proprietario utenteCamion = new Proprietario(nome,cognome,citta,patente);
                                        Targa targaCamion = new Targa(targa,utenteCamion);
                                        posti = 2;
                                        Camion camion = new Camion(marca,carburante,cilindrata,anno,targaCamion,categoria,posti,ruote,peso);
                                        v = camion;
                                        if(ControlloAccessi.classificazioneVeicolo(v,targaCamion,indiceVeicoli) == 0){
                                            veicoli.add(indiceVeicoli,camion);
                                            veicoliEcologici.add(indiceVeicoliEcologici,camion);
                                            indiceVeicoliEcologici++;
                                        }else{
                                            veicoli.add(indiceVeicoli,camion);
                                            indiceVeicoliMultati++;
                                        }
                                        outputStream.write(CONTINUAZIONE); // Questo numero permette di proseguire l'esecuzione del client (nel server corrisponde alla variabile continuazione)
                                        outputStream.write(2); // Questo numero permette di selezionare il caso del Camion (nel server corrisponde alla variabile scelta)
                                        objectOutputStream.writeObject(camion);
                                    }
                                }
                            }
                        break;
                        case "Ciclomotore":
                            String[] patentiCiclomotore = {"AM","A1","A2","A","B1","B","B96","BE"};
                            patente = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare il ciclomotore",
                                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE, null, patentiCiclomotore, patentiCiclomotore[0]);
                            if(patente == null){
                                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                controllore = true; // Il client non vuole inserire più i dati
                            }else{
                                do{
                                    ruote = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(ruote == 0){
                                        controllore = true; // Il client non vuole inserire più i dati
                                        break;
                                    }else if(ruote%2 != 0){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di ruote dispari",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }else if(ruote < 2 || ruote > 4){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di ruote pari ma non compreso tra 2 e 4",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(ruote < 2 || ruote > 4 || ruote%2 != 0);
                                if(ruote == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                do{
                                    richiesta = 3;
                                    velocita = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(velocita == 0){
                                        controllore = true;
                                        break; // Il client non vuole inserire più i dati
                                    }else if(velocita < 20 || velocita > 45){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un valore di velocita' massima non compreso tra 20 e 45",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(velocita < 20 || velocita > 45);
                                if(velocita == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                while(true){
                                    if(indiceVeicoli > 0){
                                        for(int indiceControllo = 0;indiceControllo < indiceVeicoli;){
                                            if((veicoli.get(indiceControllo).targaVeicolo.targaProprietario()).equals(verifica)){
                                                JOptionPane.showMessageDialog(null,"Risulta che questa targa e' gia' stata inserita.\nRiprovi",
                                                        "Errore",JOptionPane.ERROR_MESSAGE);
                                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                                indiceControllo = 0;
                                            }else{
                                                indiceControllo++;
                                            }
                                        }
                                        targa = verifica;
                                        break;
                                    }else{
                                        targa = verifica;
                                        break;
                                    }
                                }
                                if(targa == null){
                                    controllore = true; // Il client non vuole inserire più i dati
                                }else{
                                    Proprietario utenteCiclomotore = new Proprietario(nome,cognome,citta,patente);
                                    Targa targaCiclomotore = new Targa(targa,utenteCiclomotore);
                                    posti = 2;
                                    Ciclomotore ciclomotore = new Ciclomotore(marca,carburante,cilindrata,anno,targaCiclomotore,categoria,posti,ruote,velocita);
                                    v = ciclomotore;
                                    if(ControlloAccessi.classificazioneVeicolo(v,targaCiclomotore,indiceVeicoli) == 0){
                                        veicoli.add(indiceVeicoli,ciclomotore);
                                        veicoliEcologici.add(indiceVeicoliEcologici,ciclomotore);
                                        indiceVeicoliEcologici++;
                                    }else{
                                        veicoli.add(indiceVeicoli,ciclomotore);
                                        indiceVeicoliMultati++;
                                    }
                                    outputStream.write(CONTINUAZIONE); // Questo numero permette di proseguire l'esecuzione del client (nel server corrisponde alla variabile continuazione)
                                    outputStream.write(3); // Questo numero permette di selezionare il caso del Ciclomotore (nel server corrisponde alla variabile scelta)
                                    objectOutputStream.writeObject(ciclomotore);
                                }
                            }
                        break;
                        case "Motociclo":
                            String[] patentiMotociclo = {"A1","A2","A"};
                            patente = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare il motociclo",
                                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE,null,patentiMotociclo,patentiMotociclo[0]);
                            if(patente == null){
                                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                controllore = true; // Il client non vuole inserire più i dati
                            }else{
                                do{
                                    richiesta = 4;
                                    marce = Controlli.controlloByte(indiceVeicoli,richiesta,scelta,centro);
                                    if(marce == 0){
                                        controllore = true;
                                        break; // Il client non vuole inserire più i dati
                                    }else if(marce < 5 || marce > 7){
                                        JOptionPane.showMessageDialog(null,"Errore! Ha inserito un numero di marce non compreso tra 5 e 7",
                                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                                    }
                                }while(marce < 5 || marce > 7);
                                if(marce == 0){
                                    break; // Il client esce dal ciclo per poter terminare la sua esecuzione
                                }
                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                while(true){
                                    if(indiceVeicoli > 0){
                                        for(int indiceControllo = 0;indiceControllo < indiceVeicoli;){
                                            if((veicoli.get(indiceControllo).targaVeicolo.targaProprietario()).equals(verifica)){
                                                JOptionPane.showMessageDialog(null,"Risulta che questa targa e' gia' stata inserita.\nRiprovi",
                                                        "Errore",JOptionPane.ERROR_MESSAGE);
                                                verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                                indiceControllo = 0;
                                            }else{
                                                indiceControllo++;
                                            }
                                        }
                                        targa = verifica;
                                        break;
                                    }else{
                                        targa = verifica;
                                        break;
                                    }
                                }
                                if(targa == null){
                                    controllore = true; // Il client non vuole inserire più i dati
                                }else{
                                    Proprietario utenteMotociclo = new Proprietario(nome,cognome,citta,patente);
                                    Targa targaMotociclo = new Targa(targa,utenteMotociclo);
                                    posti = 2;
                                    ruote = 2;
                                    Motociclo motociclo = new Motociclo(marca,carburante,cilindrata,anno,targaMotociclo,categoria,posti,ruote,marce);
                                    v = motociclo;
                                    if(ControlloAccessi.classificazioneVeicolo(v,targaMotociclo,indiceVeicoli) == 0){
                                        veicoli.add(indiceVeicoli,motociclo);
                                        veicoliEcologici.add(indiceVeicoliEcologici,motociclo);
                                        indiceVeicoliEcologici++;
                                    }else{
                                        veicoli.add(indiceVeicoli,motociclo);
                                        indiceVeicoliMultati++;
                                    }
                                    outputStream.write(CONTINUAZIONE); // Questo numero permette di proseguire l'esecuzione del client (nel server corrisponde alla variabile continuazione)
                                    outputStream.write(4); // Questo numero permette di selezionare il caso del Motociclo (nel server corrisponde alla variabile scelta)
                                    objectOutputStream.writeObject(motociclo);
                                }
                            }
                        break;
                        case "Triciclo":
                            String[] patentiTriciclo = {"A1","A2","A"};
                            patente = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare il triciclo",
                                    "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE,null,patentiTriciclo,patentiTriciclo[0]);
                            if(patente == null){
                                JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                        "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                controllore = true;
                            }else{
                                String[] tipoCambio = {"Automatico","Semiautomatico","Manuale"};
                                cambio = (String)JOptionPane.showInputDialog(null,"Inserisca il tipo di patente del proprietario per guidare il triciclo",
                                        "Veicolo "+(indiceVeicoli+1),JOptionPane.INFORMATION_MESSAGE,null,tipoCambio,tipoCambio[0]);
                                if(cambio == null){
                                    JOptionPane.showMessageDialog(null,"E' stato notato che non vuole inserire piu' i dati e per questo motivo uscira' dal programma.\nArrivederci!",
                                            "Terminazione programma",JOptionPane.INFORMATION_MESSAGE);
                                    controllore = true; // Il client non vuole inserire più i dati
                                }else{
                                    verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                    while(true){
                                        if(indiceVeicoli > 0){
                                            for(int indiceControllo = 0;indiceControllo < indiceVeicoli;){
                                                if((veicoli.get(indiceControllo).targaVeicolo.targaProprietario()).equals(verifica)){
                                                    JOptionPane.showMessageDialog(null,"Risulta che questa targa e' gia' stata inserita.\nRiprovi",
                                                            "Errore",JOptionPane.ERROR_MESSAGE);
                                                    verifica = Controlli.controlloTarga(indiceVeicoli,anno,scelta,centro);
                                                    indiceControllo = 0;
                                                }else{
                                                    indiceControllo++;
                                                }
                                            }
                                            targa = verifica;
                                            break;
                                        }else{
                                            targa = verifica;
                                            break;
                                        }
                                    }
                                    if(targa == null){
                                        controllore = true; // Il client non vuole inserire più i dati
                                    }else{
                                        Proprietario utenteTriciclo = new Proprietario(nome,cognome,citta,patente);
                                        Targa targaTriciclo = new Targa(targa,utenteTriciclo);
                                        posti = 1;
                                        ruote = 3;
                                        Triciclo triciclo = new Triciclo(marca,carburante,cilindrata,anno,targaTriciclo,categoria,posti,ruote,cambio);
                                        v = triciclo;
                                        if(ControlloAccessi.classificazioneVeicolo(v,targaTriciclo,indiceVeicoli) == 0){
                                            veicoli.add(indiceVeicoli,triciclo);
                                            veicoliEcologici.add(indiceVeicoliEcologici,triciclo);
                                            indiceVeicoliEcologici++;
                                        }else{
                                            veicoli.add(indiceVeicoli,triciclo);
                                            indiceVeicoliMultati++;
                                        }
                                        outputStream.write(CONTINUAZIONE); // Questo numero permette di proseguire l'esecuzione del client (nel server corrisponde alla variabile continuazione)
                                        outputStream.write(5); // Questo numero permette di selezionare il caso del Triciclo (nel server corrisponde alla variabile scelta)
                                        objectOutputStream.writeObject(triciclo);
                                    }
                                }
                            }
                        break;
                        default:
                            JOptionPane.showMessageDialog(null,"Errore! Non è stato selezionato correttemente il veicolo.",
                                    "Attenzione",JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    if(controllore == true){
                        break; // Il client non vuole inserire più i dati ed esce dal ciclo while
                    }else{
                        continuazione = JOptionPane.showConfirmDialog(null,"Desidera continuare ad inserire dati?");
                        if(continuazione == 0){
                            outputStream.write(CONTINUAZIONE);
                            indiceVeicoli++;
                            controllore = false;
                            outputStream.flush();
                        }else{ // Il client non vuole inserire più i dati
                            JOptionPane.showMessageDialog(null,"Arrivederci!","Uscita",JOptionPane.INFORMATION_MESSAGE);
                            controllore = true;
                        }
                    }
                }
                outputStream.write(CONCLUSIONE);
                outputStream.flush();
                outputStream.write(indiceVeicoliMultati);
                objectOutputStream.writeObject(veicoliEcologici); // Il client non vuole inserire più i dati e termina la sua esecuzione
                client.close();
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Errore in fase di connessione del client al server",
                    "Client",JOptionPane.ERROR_MESSAGE);
        }
    }
}