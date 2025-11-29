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
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Questa è la classe che gestisce coloro che vogliono accedere a centri storici siciliani contemporaneamente.
 */

public class Server extends Thread{
    private ServerSocket server;
    
    /**
     * Nel main viene creato un server.
     *
     * @param argv String[] 
     */
    
    public static void main(String[] argv){
        Server accensioneServer = new Server();
    }
    
    /**
     * Crea un nuovo server che cancella i vecchi file già precedentemente scritti,
     * si mette in attesa nella porta 5200 e avvia un thread.
     */
    
    public Server(){
        String[] cittaSicilia = {"Agrigento","Caltanissetta","Catania","Enna","Messina","Palermo",
            "Ragusa","Siracusa","Trapani"};
        try{
            File file1 = new File("listaVeicoli.txt");
            file1.delete();
            for(String citta : cittaSicilia){
                File file2 = new File(citta+".txt"); 
                file2.delete();
            }
            server = new ServerSocket(5200);
            System.out.println("Il server e' in attesa sulla porta 5200");
            this.start();
        }catch(IOException e){
            System.out.println("Errore! La porta e' gia' utilizzata");
        }
    }
    
    /**
     * Qui vengono gestite le connessioni con gli eventuali client. Ogni volta che un
     * client si collega al server viene creato un thread che gestirà la comunicazione.
     */
    
    public void run(){
        int numeroClient = 1;
        Date ora;
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while(true){
            try{
                ora = new Date();
                System.out.println("In cerca di connessioni...");
                Socket client = server.accept();
                System.out.println("Trovato client!");
                System.out.println("Connessione accettata alle ore "+sdf.format(ora));
                Connect c = new Connect(client,numeroClient);
                numeroClient++;
            }catch(IOException e){
                System.out.println("Errore! Non e' stata effettuata una connessione con il client");
            }
        }
    }
}

/**
 * Questa classe permette di gestire più client, attraverso l'uso dei thread.
 * 
 * @author elvira and luigi
 */

class Connect extends Thread{
    private int indiceClient;
    private Socket client = null;
    BufferedReader in = null;
    InputStream inputStream = null;
    ObjectInputStream objectInputStream = null;
    public Connect(Socket clientSocket,int numeroClient){
        indiceClient = numeroClient;
        client = clientSocket;
        try{
            inputStream = client.getInputStream();
            objectInputStream = new ObjectInputStream(inputStream);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }catch(IOException e){
            System.out.println("Errore durante la creazione dei canali di comunicazione con il client");
        }
        this.start();
    }
    
    public void run(){
        int continuazione = 0,indiceVeicoli = 0,indiceVeicoliMultati,scelta;
        Map <String,ArrayList> centroStorico = new TreeMap <>();
        try{
            String centro = (String) objectInputStream.readObject();
            if(centro == null){
                System.out.println("Il client " + indiceClient + " ha terminato in anticipo la sua esecuzione");
                continuazione = 1;
            }
            while(continuazione == 0){
                continuazione = in.read();
                if(continuazione != 0){
                    System.out.println("Il client " + indiceClient + " ha terminato in anticipo la sua esecuzione");
                    break;
                }
                scelta = in.read(); // Seleziona il veicolo scelto dal client
                indiceVeicoli++;
                switch(scelta){
                    case 0:
                        Autobus autobus = (Autobus) objectInputStream.readObject();
                        stampaAutobus(autobus,indiceVeicoli,indiceClient,centro);
                    break;
                    case 1:
                        Automobile automobile = (Automobile) objectInputStream.readObject();
                        stampaAutomobile(automobile,indiceVeicoli,indiceClient,centro); 
                    break;
                    case 2:
                        Camion camion = (Camion) objectInputStream.readObject();
                        stampaCamion(camion,indiceVeicoli,indiceClient,centro);
                    break;
                    case 3:
                        Ciclomotore ciclomotore = (Ciclomotore) objectInputStream.readObject();
                        stampaCiclomotore(ciclomotore,indiceVeicoli,indiceClient,centro);
                    break;
                    case 4:
                        Motociclo motociclo = (Motociclo) objectInputStream.readObject();
                        stampaMotociclo(motociclo,indiceVeicoli,indiceClient,centro);
                    break;
                    case 5:
                        Triciclo triciclo = (Triciclo) objectInputStream.readObject();
                        stampaTriciclo(triciclo,indiceVeicoli,indiceClient,centro);
                    break;
                    default:
                        JOptionPane.showMessageDialog(null,"Errore! Non è stato possibile selezionare correttamente il veicolo",
                                "Attenzione",JOptionPane.ERROR_MESSAGE);
                    break;
                }
                continuazione = in.read();
            }
            if(indiceVeicoli != 0){
                indiceVeicoliMultati = in.read();
                informazioni(indiceVeicoli,indiceVeicoliMultati,centro);
                ArrayList<Veicolo> listaVeicoli =(ArrayList<Veicolo>) objectInputStream.readObject();
                centroStorico.put(centro,listaVeicoli);
                try{
                    for(Veicolo veicolo : listaVeicoli){
                    File file = new File(centro+".txt");
                        try{
                            FileOutputStream outFileStream = new FileOutputStream(file,true);
                            PrintWriter outStream = new PrintWriter(outFileStream,true);
                            outStream.println(centro+": TARGA VEICOLO CONTROLLATO: "+veicolo.targa().targaProprietario());
                        }catch(FileNotFoundException e){
                            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato",
                                    "Server",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch(NullPointerException e){
                    JOptionPane.showMessageDialog(null,"Errore! Non sono stati inseriti corretamente i veicoli all'interno del centro",
                            "Server",JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("Il client " + indiceClient + " ha inserito correttamente uno o più veicoli.");
                client.close();
            }
        }catch(ClassNotFoundException e){
            System.out.println("Errore non e' stato inserito correttamente l'oggetto");
        }catch(IOException e){
            System.out.println("Errore!");
            e.printStackTrace();
        }
    }
    
    /**
     * Questo metodo stampa nel file listaVeicoli.txt il numero dei veicoli controllati e quelli multati.
     * 
     * @param indiceVeicoli indica il numero di veicoli controllati.
     * @param indiceVeicoliMultati indica il numero di veicolo multati.
     * @param centro inidica la città dove sono entrati questi veicoli.
     */
    
    
    public static void informazioni(int indiceVeicoli,int indiceVeicoliMultati,String centro){
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println(centro+": NUMERO VEICOLI CONTROLLATI: "+indiceVeicoli);
            outStream.println(centro+": NUMERO VEICOLI MULTATI: "+indiceVeicoliMultati+"\n\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Questo metodo stampa in listaVeicolo.txt le informazioni dell'autobus.
     * 
     * @param autobus l'oggeto Autobus.
     * @param indiceVeicoli indica il numero del veicolo da stampare.
     * @param client indica il numero del client che vuole stampare.
     * @param centro indica il centro storico alla quale il veicolo vuole accedere.
     */
    
    
    public static void stampaAutobus(Autobus autobus,int indiceVeicoli,int client,String centro){
        DecimalFormat df = new DecimalFormat("0.00");
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println("Client "+client+":");
            outStream.println(centro+":\nInformazioni sul veicolo numero "+indiceVeicoli+":");
            outStream.println("TIPO DI VEICOLO: "+autobus.tipologiaVeicolo());
            outStream.println("MARCA: "+autobus.marca());
            outStream.println("CARBURANTE: "+autobus.carburante());
            outStream.println("CILINDRATA: "+autobus.cilindrata());
            outStream.println("NUMERO PIANI: "+autobus.numeroPianiAutobus());
            outStream.println("ANNO DI IMMATRICOLAZIONE: "+autobus.immatricolazione());
            outStream.println("CATEGORIA DI APPARTENENZA: "+autobus.categoria());
            outStream.println("TARGA: "+autobus.targa().targaProprietario());
            outStream.println("POSTI COMPLESSIVI: "+autobus.posti());
            outStream.println("RUOTE: "+autobus.ruote());
            outStream.println("NATURA DEL VEICOLO: "+autobus.targa().ecologia());
            outStream.println("NOME PROPRIETARIO: "+autobus.targa().nomeProprietario());
            outStream.println("COGNOME PROPRIETARIO: "+autobus.targa().cognomeProrietario());
            outStream.println("CITTA' PROPRIETARIO: "+autobus.targa().cittaProprietario());
            outStream.println("PATENTE PROPRIETARIO: "+autobus.targa().patenteProprietario());
            outStream.println("MULTA PROPRIETARIO: €"+df.format(autobus.targa().multaProprietario())+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Questo metodo stampa in listaVeicoli.txt le informazioni dell'automobile.
     * 
     * @param automobile l'oggetto Automobile.
     * @param indiceVeicoli indica il numero del veicolo da stampare.
     * @param client indica il numero del client che vuole stampare.
     * @param centro indica la città alla quale vuole accedere il veicolo.
     */
    
    
    public static void stampaAutomobile(Automobile automobile,int indiceVeicoli,int client,String centro){
        DecimalFormat df = new DecimalFormat("0.00");
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println("Client "+client+":");
            outStream.println(centro+":\nInformazioni sul veicolo numero "+indiceVeicoli+":");
            outStream.println("TIPO DI VEICOLO: "+automobile.tipologiaVeicolo());
            outStream.println("MARCA: "+automobile.marca());
            outStream.println("CARBURANTE: "+automobile.carburante());
            outStream.println("CILINDRATA: "+automobile.cilindrata());
            outStream.println("NUMERO PORTE: "+automobile.numeroPorteAutomobile());
            outStream.println("ANNO DI IMMATRICOLAZIONE: "+automobile.immatricolazione());
            outStream.println("CATEGORIA DI APPARTENENZA: "+automobile.categoria());
            outStream.println("TARGA: "+automobile.targa().targaProprietario());
            outStream.println("POSTI COMPLESSIVI: "+automobile.posti());
            outStream.println("RUOTE: "+automobile.ruote());
            outStream.println("NATURA DEL VEICOLO: "+automobile.targa().ecologia());
            outStream.println("NOME PROPRIETARIO: "+automobile.targa().nomeProprietario());
            outStream.println("COGNOME PROPRIETARIO: "+automobile.targa().cognomeProrietario());
            outStream.println("CITTA' PROPRIETARIO: "+automobile.targa().cittaProprietario());
            outStream.println("PATENTE PROPRIETARIO: "+automobile.targa().patenteProprietario());
            outStream.println("MULTA PROPRIETARIO: €"+df.format(automobile.targa().multaProprietario())+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Questo metodo stampa in listaVeicoli.txt le informazioni del camion.
     * 
     * @param camion l'oggeto Camion.
     * @param indiceVeicoli indica il numero del veicolo da stampare.
     * @param client indica il numero del client che vuole stampare.
     * @param centro indica la città alla quale vuole accedere il veicolo.
     */
    
    
    public static void stampaCamion(Camion camion,int indiceVeicoli,int client,String centro){
        DecimalFormat df = new DecimalFormat("0.00");
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println("Client "+client+":");
            outStream.println(centro+":\nInformazioni sul veicolo numero "+indiceVeicoli+":");
            outStream.println("TIPO DI VEICOLO: "+camion.tipologiaVeicolo());
            outStream.println("MARCA: "+camion.marca());
            outStream.println("CARBURANTE: "+camion.carburante());
            outStream.println("CILINDRATA: "+camion.cilindrata());
            outStream.println("PESO TRASPORTATO: "+camion.pesoTrasportatoCamion()+" kg");
            outStream.println("ANNO DI IMMATRICOLAZIONE: "+camion.immatricolazione());
            outStream.println("CATEGORIA DI APPARTENENZA: "+camion.categoria());
            outStream.println("TARGA: "+camion.targa().targaProprietario());
            outStream.println("POSTI COMPLESSIVI: "+camion.posti());
            outStream.println("RUOTE: "+camion.ruote());
            outStream.println("NATURA DEL VEICOLO: "+camion.targa().ecologia());
            outStream.println("NOME PROPRIETARIO: "+camion.targa().nomeProprietario());
            outStream.println("COGNOME PROPRIETARIO: "+camion.targa().cognomeProrietario());
            outStream.println("CITTA' PROPRIETARIO: "+camion.targa().cittaProprietario());
            outStream.println("PATENTE PROPRIETARIO: "+camion.targa().patenteProprietario());
            outStream.println("MULTA PROPRIETARIO: €"+df.format(camion.targa().multaProprietario())+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Questo metodo stampa in listaVeicoli.txt le informazioni del ciclomotore.
     * 
     * @param ciclomotore l'oggeto Ciclomotore.
     * @param indiceVeicoli indica il numero del veicolo da stampare.
     * @param client indica il numero del client che vuole stampare.
     * @param centro indica la città alla quale vuole accedere il veicolo.
     */
    
    public static void stampaCiclomotore(Ciclomotore ciclomotore,int indiceVeicoli,int client,String centro){
        DecimalFormat df = new DecimalFormat("0.00");
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println("Client "+client+":");
            outStream.println(centro+":\nInformazioni sul veicolo numero "+indiceVeicoli+":");
            outStream.println("TIPO DI VEICOLO: "+ciclomotore.tipologiaVeicolo());
            outStream.println("MARCA: "+ciclomotore.marca());
            outStream.println("CARBURANTE: "+ciclomotore.carburante());
            outStream.println("CILINDRATA: "+ciclomotore.cilindrata());
            outStream.println("VELOCITA' MASSIMA: "+ciclomotore.velocitaMassimaCiclomotore());
            outStream.println("ANNO DI IMMATRICOLAZIONE: "+ciclomotore.immatricolazione());
            outStream.println("CATEGORIA DI APPARTENENZA: "+ciclomotore.categoria());
            outStream.println("TARGA: "+ciclomotore.targa().targaProprietario());
            outStream.println("POSTI COMPLESSIVI: "+ciclomotore.posti());
            outStream.println("RUOTE: "+ciclomotore.ruote());
            outStream.println("NATURA DEL VEICOLO: "+ciclomotore.targa().ecologia());
            outStream.println("NOME PROPRIETARIO: "+ciclomotore.targa().nomeProprietario());
            outStream.println("COGNOME PROPRIETARIO: "+ciclomotore.targa().cognomeProrietario());
            outStream.println("CITTA' PROPRIETARIO: "+ciclomotore.targa().cittaProprietario());
            outStream.println("PATENTE PROPRIETARIO: "+ciclomotore.targa().patenteProprietario());
            outStream.println("MULTA PROPRIETARIO: €"+df.format(ciclomotore.targa().multaProprietario())+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Questo metodo stampa in listaVeicoli.txt le informazioni del motociclo.
     * 
     * @param motociclo l'oggetto Motociclo.
     * @param indiceVeicoli indica il numero del veicolo da stampare.
     * @param client indica il numero del client che vuole stampare.
     * @param centro indica la città alla quale vuole accedere il veiolo.
     */
    
    public static void stampaMotociclo(Motociclo motociclo,int indiceVeicoli,int client,String centro){
        DecimalFormat df = new DecimalFormat("0.00");
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println("Client "+client+":");
            outStream.println(centro+":\nInformazioni sul veicolo numero "+indiceVeicoli+":");
            outStream.println("TIPO DI VEICOLO: "+motociclo.tipologiaVeicolo());
            outStream.println("MARCA: "+motociclo.marca());
            outStream.println("CARBURANTE: "+motociclo.carburante());
            outStream.println("CILINDRATA: "+motociclo.cilindrata());
            outStream.println("NUMERO MARCE: "+motociclo.numeroMarceMotociclo());
            outStream.println("ANNO DI IMMATRICOLAZIONE: "+motociclo.immatricolazione());
            outStream.println("CATEGORIA DI APPARTENENZA: "+motociclo.categoria());
            outStream.println("TARGA: "+motociclo.targa().targaProprietario());
            outStream.println("POSTI COMPLESSIVI: "+motociclo.posti());
            outStream.println("RUOTE: "+motociclo.ruote());
            outStream.println("NATURA DEL VEICOLO: "+motociclo.targa().ecologia());
            outStream.println("NOME PROPRIETARIO: "+motociclo.targa().nomeProprietario());
            outStream.println("COGNOME PROPRIETARIO: "+motociclo.targa().cognomeProrietario());
            outStream.println("CITTA' PROPRIETARIO: "+motociclo.targa().cittaProprietario());
            outStream.println("PATENTE PROPRIETARIO: "+motociclo.targa().patenteProprietario());
            outStream.println("MULTA PROPRIETARIO: €"+df.format(motociclo.targa().multaProprietario())+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Questo metodo stampa in listaVeicoli.txt le informazioni del triciclo.
     * 
     * @param triciclo l'oggetto Triciclo.
     * @param indiceVeicoli indica il numero del veicolo da stampare.
     * @param client indica il numero del client che vuole stampare.
     * @param centro indica la città alla quale vuole accedere il veicolo.
     */
    
    public static void stampaTriciclo(Triciclo triciclo,int indiceVeicoli,int client,String centro){
        DecimalFormat df = new DecimalFormat("0.00");
        File outFile = new File("listaVeicoli.txt");
        try{
            FileOutputStream outFileStream = new FileOutputStream(outFile,true);
            PrintWriter outStream = new PrintWriter(outFileStream,true);
            outStream.println("Client "+client+":");
            outStream.println(centro+":\nInformazioni sul veicolo numero "+indiceVeicoli+":");
            outStream.println("TIPO DI VEICOLO: "+triciclo.tipologiaVeicolo());
            outStream.println("MARCA: "+triciclo.marca());
            outStream.println("CARBURANTE: "+triciclo.carburante());
            outStream.println("CILINDRATA: "+triciclo.cilindrata());
            outStream.println("TIPO CAMBIO: "+triciclo.tipoCambioTriciclo());
            outStream.println("ANNO DI IMMATRICOLAZIONE: "+triciclo.immatricolazione());
            outStream.println("CATEGORIA DI APPARTENENZA: "+triciclo.categoria());
            outStream.println("TARGA: "+triciclo.targa().targaProprietario());
            outStream.println("POSTI COMPLESSIVI: "+triciclo.posti());
            outStream.println("RUOTE: "+triciclo.ruote());
            outStream.println("NATURA DEL VEICOLO: "+triciclo.targa().ecologia());
            outStream.println("NOME PROPRIETARIO: "+triciclo.targa().nomeProprietario());
            outStream.println("COGNOME PROPRIETARIO: "+triciclo.targa().cognomeProrietario());
            outStream.println("CITTA' PROPRIETARIO: "+triciclo.targa().cittaProprietario());
            outStream.println("PATENTE PROPRIETARIO: "+triciclo.targa().patenteProprietario());
            outStream.println("MULTA PROPRIETARIO: €"+df.format(triciclo.targa().multaProprietario())+"\n");
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Il file non e' stato trovato","Server",JOptionPane.ERROR_MESSAGE);
        }
    }
}