import java.io.IOException;      // Importa l'eccezione per la gestione degli errori di I/O
import java.io.PrintWriter;      // Importa PrintWriter per inviare dati al server
import java.net.Socket;          // Importa la classe Socket per la connessione TCP
import java.util.Scanner;        // Importa Scanner per leggere input da tastiera

public class ThreadInvio implements Runnable {  // Implementa Runnable per creare un thread

    private Scanner sc;    // Scanner per leggere input da tastiera
    private PrintWriter out;  // PrintWriter per inviare dati al server

    // Costruttore che riceve il socket di connessione
    public ThreadInvio(Socket socket) throws IOException {
        sc = new Scanner(System.in);  // Inizializza lo Scanner per leggere input da tastiera
        out = new PrintWriter(socket.getOutputStream());  // Ottiene il canale di output del socket
    }

    // Metodo run() eseguito quando il thread parte
    public void run() {
        String message;  // Variabile per memorizzare il messaggio da inviare
        boolean primo = true;  // Variabile per sapere se è il primo messaggio (nome utente)

        // Continua a inviare messaggi finché il thread non viene interrotto
        while (!Thread.interrupted()) {
            if (primo) {  
                System.out.println("Dammi il nome utente");  // Chiede il nome utente all'inizio
            }

            message = sc.nextLine();  // Legge l'input dell'utente da tastiera
            out.println(message);  // Scrive il messaggio nel canale di output
            out.flush();  // Forza l'invio del messaggio al server

            if (primo) {  
                System.out.println("Utente acquisito, scrivi messaggio");  // Dopo il nome, passa ai messaggi
                primo = false;  // Imposta "primo" a false per non ripetere la richiesta del nome utente
            }
        }
    }
}

