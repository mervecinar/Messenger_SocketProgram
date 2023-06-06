/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerMessenger;


import ClientMessenger.Client;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import messenger.Mesaj;
import messenger.Sohbet;

/**
 *
 * @author LENOVO
 */
class ServerT extends Thread {

    public void run() {
        while (!Server.sSoket.isClosed()) {
            try {

                Socket clientSocket = Server.sSoket.accept();
                ServerofClient kullanicis = (new ServerofClient(clientSocket, Server.number));//gelen kullaniciyi kabul et

                Server.number++;// kullanici sayısını arttır.
                Server.clientler.add(kullanicis);//Arrayliste kullanici ekle
                kullanicis.Thread.start();// threadin gönderceği mesajjı vbekle ve dinle
                System.out.println(kullanicis.clientNumber + 1 + ".  client is connected!" + "    Info  of client : " + kullanicis.Soket);

            } catch (IOException ex) {
                Logger.getLogger(ServerT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Server {

    public static ServerSocket sSoket;//soket 
    public static int portNumber;//port numarası aynı port üzerinden buraya gelecekler 
    public static int number = 0;// kullanici numarası initial değer 0 atadım zaman ile artacak.
  
    public static ServerT thread;//serveri bu sayede dinleyebilcek 
    public static ArrayList<ServerofClient> clientler = new ArrayList<>();// kullaniciların olduğu havuz.

    public static ArrayList<Client> groups2 = new ArrayList();// group adları

    public static void Start(int portNumber) throws IOException {// verilen port numarasına göre serveri başlatır.

        Server.portNumber = portNumber;
        Server.sSoket = (new ServerSocket(portNumber));
        Server.thread = (new ServerT());
        Server.thread.start();

    }

    public static void send1(Mesaj gndr, int a) {

        if (a == 1) {
            DefaultListModel list = new DefaultListModel();
            Iterator<ServerofClient> i = clientler.iterator();
            while (i.hasNext()) {
                ServerofClient serverOfClient = i.next();
                Server.Send(serverOfClient, gndr);
            }
        } else if (a == 2) {
            DefaultListModel list2 = new DefaultListModel();
            Iterator<ServerofClient> i = clientler.iterator();
            while (i.hasNext()) {
                ServerofClient serverOfClient = i.next();
                list2.addElement(serverOfClient.name);
            }
            gndr.content = list2;
            i = clientler.iterator();
            while (i.hasNext()) {
                ServerofClient serverOfClient = i.next();
                Server.Send(serverOfClient, gndr);
            }
        } else if (a == 3) {
            Iterator<ServerofClient> i = clientler.iterator();
            while (i.hasNext()) {
                ServerofClient serverOfClient = i.next();
                try {
                    serverOfClient.out.writeObject(gndr);
                } catch (IOException ex) {
                    Logger.getLogger(ServerofClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (a == 4) {
            Sohbet message = (Sohbet) gndr.content;
            Iterator<ServerofClient> i = clientler.iterator();
            while (i.hasNext()) {
                ServerofClient serverOfClient = i.next();
                if (serverOfClient.name.equals(message.getTarget())) {
                    Server.Send(serverOfClient, gndr);
                    break;
                }
            }
        }

    }

    public static void Send(ServerofClient client, Mesaj gndt) {//bu sayede karşı tarafa bilgi mesajı yollanır.

        try {
            client.out.writeObject(gndt);
        } catch (IOException ex) {

            Logger.getLogger(ServerofClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
            public static void main(String[] args) throws IOException {//Server çağırılır ve başlatılır.
        System.out.println("Server has been started. The Clients are waiting...");
        Server.Start(5010);

    }


}
