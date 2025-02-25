import java.io.BufferedReader;      // Per leggere dati dal socket in modo efficiente
import java.io.IOException;         // Per la gestione delle eccezioni di I/O
import java.net.Socket;             // Classe per la comunicazione tramite socket
import java.io.InputStreamReader;   // Converte i dati in input in un formato leggibile

public class ThreadRicevi implements Runnable { // Implementa Runnable per essere eseguito in un thread

    private Socket socket;   // Socket per la comunicazione con il server
    BufferedReader in;       // Buffer per leggere i dati in ingresso dal server

    // Costruttore: inizializza il socket e il BufferedReader per ricevere dati
    public ThreadRicevi(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
        // Legge i dati inviati dal server e li converte in stringhe
    }

    public void run() {
        String messaggio;  // Variabile per memorizzare i messaggi ricevuti

        try {
            messaggio = in.readLine(); // Legge la prima riga di testo inviata dal server

            // Continua a leggere finché il server non chiude la connessione
            while (messaggio != null) {  
                System.out.println(messaggio);  // Stampa il messaggio ricevuto
                messaggio = in.readLine();  // Legge il messaggio successivo
            }

            // Quando il server chiude la connessione, esce dal ciclo
            System.out.println("Server Chiuso");

            // Chiude il socket dopo la fine della comunicazione
            socket.close();

        } catch (IOException e) {  // Gestione degli errori di connessione
            System.out.println("Errore di connessione");
        }
    }
}

