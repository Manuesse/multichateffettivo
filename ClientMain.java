import java.io.IOException;  // Importa l'eccezione per la gestione degli errori di I/O
import java.net.Socket;      // Importa la classe Socket per la comunicazione TCP

public class ClientMain {   // Classe principale del client

    public static void main(String[] args) {  // Metodo principale (entry point)
        Socket clientSocket;  // Dichiarazione del socket del client
        try {
            // Creazione del socket e connessione al server sulla porta 5500 dell'IP localhost (127.0.0.1)
            clientSocket = new Socket("127.0.0.1", 5500);

            // Creazione e avvio di un thread per inviare dati al server
            Thread invioThread = new Thread(new ThreadInvio(clientSocket));
            
            // Creazione e avvio di un thread per ricevere dati dal server
            Thread riceviThread = new Thread(new ThreadRicevi(clientSocket));

            invioThread.start();   // Avvia il thread di invio
            riceviThread.start();  // Avvia il thread di ricezione
        } catch (IOException e) {  // Gestisce eventuali errori di connessione
            System.out.println("Impossibile connettersi con il server");
        }
    }
}