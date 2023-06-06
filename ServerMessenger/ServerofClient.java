/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerMessenger;

import ClientMessenger.Client;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import messenger.Mesaj;

/**
 *
 * @author LENOVO
 */
public class ServerofClient {

    int clientNumber;// kisinin id numarası tutulur.
    public String name = "-";// baslaması için defaault string atıyorum
    public String s = "-";// baslaması için defaault string atıyorum
    Socket Soket;
    ObjectOutputStream out;
    ObjectInputStream inp;
    Listen Thread;//eşinden gelenleri dinler.

    public static int k = 1;

    public ServerofClient(Socket soket, int number) throws IOException { //soket içerisinde ip ve port var
        this.Soket = soket;
        this.clientNumber = number;
        this.out = new ObjectOutputStream(this.Soket.getOutputStream());
        this.inp = new ObjectInputStream(this.Soket.getInputStream());
        this.Thread = new Listen(this);

    }

    public void Send(Mesaj gndr) throws IOException {

        this.out.writeObject(gndr);
    }

    class Listen extends Thread {

        ServerofClient kullanici;
        ArrayList<String> groupAd = new ArrayList<String>();

        Listen(ServerofClient TheClient) {
            this.kullanici = TheClient;
        }

        public void run() {
            while (kullanici.Soket.isConnected()) {

                Mesaj sc = null;
                try {
                    sc = (Mesaj) (kullanici.inp.readObject());
                } catch (IOException ex) {
                    Logger.getLogger(ServerofClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ServerofClient.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (sc.type == Mesaj.gndr_t.Basla) {
                    //basla isteği gelir ve server gelen clientin adını alır
                    System.out.println("-");
                    String kulName = sc.content.toString();//kullanici ismi
                    kullanici.name = kulName;
                    System.out.println("Bağlanan kişi " + kulName);
                    Mesaj gndr = new Mesaj(Mesaj.gndr_t.onlineUsers);
                    Server.send1(gndr, 2);// baglanan kullanici
                } else if (sc.type == Mesaj.gndr_t.Bitir) {//kulnniciyi sil ve çık
                    Server.clientler.remove(kullanici);
                    Mesaj msg2 = new Mesaj(Mesaj.gndr_t.onlineUsers);
                    Server.send1(msg2, 2);
                    try {
                        kullanici.Soket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ServerofClient.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (sc.type == Mesaj.gndr_t.DuyguDurum2) {
                    Mesaj b = new Mesaj(Mesaj.gndr_t.DuyguDurum2);
                    b.content = sc.content;
                    Server.send1(b, 3);
                } else if (sc.type == Mesaj.gndr_t.GroupDuyguAd) {
                    Mesaj d = new Mesaj(Mesaj.gndr_t.GroupDuyguAd);
                    d.content = sc.content;
                    Server.send1(d, 3);
                } else if (sc.type == Mesaj.gndr_t.text) {// ana penceredeki textare1 olan yerin mesajları
                    Server.send1(sc, 1);
                } else if (sc.type == Mesaj.gndr_t.GroupKatılanlar) {

                    Iterator<Client> i = Server.groups2.iterator();
                    while (i.hasNext()) {
                        Client item = i.next();
                        if (item.x.equals(sc.content)) {
                            s = item.x;
                            item.groupList_.add(this.kullanici.name);
                            break;
                        }
                    }
                    Mesaj grupkisiler = new Mesaj(Mesaj.gndr_t.GroupKatılanlar);
                    grupkisiler.content = s;
                    Server.Send(this.kullanici, grupkisiler);
                } else if (sc.type == Mesaj.gndr_t.ozelMesaj) {// 2 kisi mesajlaşma
                    Server.send1(sc, 3);
                } else if (sc.type == Mesaj.gndr_t.GroupAd) {// kurulan group  adı
                    Client groupY = new Client(sc.content.toString());
                    Server.groups2.add(groupY);
                    Mesaj groupSohbet = new Mesaj(Mesaj.gndr_t.GroupAd);
                    groupSohbet.content = groupY.x;
                    Server.Send(this.kullanici, groupSohbet);//grop mesajları
                } else if (sc.type == Mesaj.gndr_t.duygu_isim) {
                    Mesaj c = new Mesaj(Mesaj.gndr_t.duygu_isim);
                    c.content = sc.content;
                    Server.send1(c, 3);
                } else if (sc.type == Mesaj.gndr_t.groupchat) {
                    Server.send1(sc, 1);
                } else if (sc.type == Mesaj.gndr_t.DuyguDurum) {
                    Mesaj a = new Mesaj(Mesaj.gndr_t.DuyguDurum);
                    a.content = sc.content;
                    Server.send1(a, 3);
                } else if (sc.type == Mesaj.gndr_t.Grouplar) {// group listesi

                    Iterator<Client> i = Server.groups2.iterator();// tek tek gezerek listeye ekler
                    while (i.hasNext()) {
                        Client item = i.next();
                        groupAd.add(item.x);
                    }
                    Mesaj gruplar = new Mesaj(Mesaj.gndr_t.Grouplar);
                    gruplar.content = groupAd;
                    Server.Send(this.kullanici, gruplar);
                } else if (sc.type == Mesaj.gndr_t.GroupMsj) {
                    Mesaj e = new Mesaj(Mesaj.gndr_t.GroupMsj);
                    e.content = ("-" + this.kullanici.name + "->" + sc.content);
                    Server.send1(e, 3);
                } else if (sc.type == Mesaj.gndr_t.DosyaGonder) {
                    Server.send1(sc, 3);
                }

            }
        }
    }
}
