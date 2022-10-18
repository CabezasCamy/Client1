package itis.meucci;

import java.io.*;
import java.net.*;

public class ClientStr {
    String nomeServer = "localhost";
    int portaServer = 2018;
    Socket miosocket;
    BufferedReader tastiera;
    String stringaUtente;
    String stringaRicevutaDalServer;
    BufferedReader inDalServer;
    DataOutputStream outVersoServer;

    public Socket connetti() 
    {
        System.out.println("2: il cliente e' partito in esecuzione..." + '\n');
        try
        {
            //legge quello che l'utente scrive
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //creo un socket
            miosocket = new Socket(InetAddress.getLocalHost(), 2018);
            //stream di strittura e lettura 
            outVersoServer = new DataOutputStream(miosocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
        }
        catch(UnknownHostException e)
        {
            System.out.println("Host sconosciuto");
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione");
            System.exit(1);
        }
        //
        return miosocket;
    }

    public void comunica()
    {
        try
        {
            System.out.println("4: inserire la stringa da trasmettere al server: ");
            stringaUtente = tastiera.readLine(); //lettura da tastiera
            //spedisco la stringa al server
            System.out.println("5: invio la stringa al server e attendo...");
            outVersoServer.writeBytes(stringaUtente + '\n');
            //leggo la risposta del server
            stringaRicevutaDalServer = inDalServer.readLine();
            System.out.println("8: risposta del server: " + stringaRicevutaDalServer + '\n');
            //chiudo la connessione
            System.out.println("9: termine elaborazione e chiusura connessione");
            miosocket.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il server");
            System.exit(1);
        }
    }
}

