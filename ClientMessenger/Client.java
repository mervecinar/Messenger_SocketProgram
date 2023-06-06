/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClientMessenger;

import messenger.Sohbet;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import static java.util.Spliterators.iterator;
import static java.util.Spliterators.iterator;
import static java.util.Spliterators.iterator;
import static java.util.Spliterators.iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import messenger.Ana;
import messenger.Mesaj;

import messenger.Mesaj;
import static messenger.Mesaj.gndr_t.Bitir;
import static messenger.Mesaj.gndr_t.Basla;
import static messenger.Mesaj.gndr_t.DosyaGonder;
import static messenger.Mesaj.gndr_t.DuyguDurum;
import static messenger.Mesaj.gndr_t.DuyguDurum2;
import static messenger.Mesaj.gndr_t.GroupAd;
import static messenger.Mesaj.gndr_t.GroupDuyguAd;
import static messenger.Mesaj.gndr_t.GroupKatılanlar;
import static messenger.Mesaj.gndr_t.GroupMsj;
import static messenger.Mesaj.gndr_t.Grouplar;
import static messenger.Mesaj.gndr_t.Kapat;
import static messenger.Mesaj.gndr_t.duygu_isim;
import static messenger.Mesaj.gndr_t.groupchat;
import static messenger.Mesaj.gndr_t.onlineUsers;
import static messenger.Mesaj.gndr_t.ozelMesaj;
import static messenger.Mesaj.gndr_t.text;
import messenger.merve;
import messenger.onlineUser;
import static ClientMessenger.Client.sInp;

/**
 *
 * @author LENOVO
 */
public class Client {
 public String x;
 public ArrayList<String> groupList_ = new ArrayList();
    public static Socket socket;// port ve ip taşır

    public static ObjectInputStream sInp;

    public static ObjectOutputStream sOut;

    public static Listen listen;// servera dinler

    public static void Start(String ipAdress, int portNumber) throws IOException {
// Başlama methodu bu sayede client başlar ve basla ile ilk mesajı server a gönderir.

        Client.socket = new Socket(ipAdress, portNumber);
        Client.sInp = new ObjectInputStream(Client.socket.getInputStream());
        Client.sOut = new ObjectOutputStream(Client.socket.getOutputStream());
        Client.listen = new Listen();
        Client.listen.start();
        Mesaj gndr = new Mesaj(Mesaj.gndr_t.Basla);
        gndr.content = Ana.jTextField1.getText();
        Client.Send(gndr);

    }

    public static void Send(Mesaj gndr) throws IOException {

        sOut.writeObject(gndr);

    }

    public static void Stop() throws IOException {
//kapatma soketi
        if (Client.socket != null) {
            Client.socket.close();
            Client.sOut.close();
            Client.sInp.close();
            Client.sOut.flush();

        }

    }
       public Client(String groupAdi) {
        this.x = groupAdi;

    }

}

class Listen extends Thread {

    ArrayList<String> groups1 = new ArrayList();
    merve mer1 = Ana.x;// obje ana penceredeki verilerin gözükmesini sağlar
    onlineUser cnr = Ana.x.kk;// obje online user sayfasındaki verilerin gözükmesini sağlar

    public void run() {

        while (Client.socket.isConnected()) {
            try {
                Mesaj clientM = (Mesaj) (sInp.readObject());
                if (clientM.type == Basla) {
                    //bu Sayade başlar ve bağlanan kullanıcının adı alınır.
                    System.out.println("bağlanan  adı" + Basla);
                } else if (clientM.type == text) {
                    // Yazılan mesajlar alınır. ana penceredeki
                    System.out.println("mesaj kısmında");
                    mer1.jTextArea1.setText((mer1.jTextArea1.getText() + "\n\n" + "- " + clientM.content.toString()));
                } else if (clientM.type == groupchat) {
                    // oluşturulan gruplardaki mesajlar
                    System.out.println("burdaaa");
                    Ana.group.jTextArea1.setText(Ana.group.jTextArea1.getText() + "\n\n" + "- " + clientM.content.toString());
                } else if (clientM.type == onlineUsers) {
                    // servera bağlı olan clientler.
                    cnr.online_Kullanicilar(((DefaultListModel) clientM.content));

                } else if (clientM.type == GroupAd) {
                    // oluştutulan grupların adları
                    Ana.group.jLabel1.setText(clientM.content.toString());
                    System.out.println("Hngi Gruba bağlı" + clientM.content.toString());
                } else if (clientM.type == Grouplar) {
                    //Grupların listesi burda tutukuyor array biçiminde
                    mer1.groupList.removeAllElements();
                    groups1 = ((ArrayList<String>) clientM.content);
                    Iterator<String> i = groups1.iterator();
                    while (i.hasNext()) {

                        String a = i.next();
                        System.out.println("isim" + a);
                        mer1.groupList.addElement(a);
                    }

                } else if (clientM.type == DuyguDurum) {
                    //emojilerin gösterilmesi
                    byte[] byt = (byte[]) clientM.content;
                    ByteArrayInputStream b = new ByteArrayInputStream(byt);
                    ObjectInputStream o = new ObjectInputStream(b);
                    Icon receivedIcon = (Icon) o.readObject();

                    mer1.jLabel2.setIcon(receivedIcon);

                } else if (clientM.type == duygu_isim) {
                    // emojinin kime ait olduğu ismini gösterir.
                    mer1.jLabel3.setText(clientM.content.toString() + " feels like");

                } else if (clientM.type == GroupKatılanlar) {

                } else if (clientM.type == GroupDuyguAd) {
                    Ana.group.jLabel6.setText(clientM.content.toString() + " feels like");

                } else if (clientM.type == DuyguDurum2) {
                    byte[] byt = (byte[]) clientM.content;
                    ByteArrayInputStream b = new ByteArrayInputStream(byt);
                    ObjectInputStream o = new ObjectInputStream(b);
                    Icon bb = (Icon) o.readObject();

                    Ana.group.jLabel3.setIcon(bb);

                } else if (clientM.type == ozelMesaj) {
                    //2 kişi arasındaki Online user sayfasında kullanılır mesaj gösterimi yapar.
                    cnr.sohbet((Sohbet) clientM.content, mer1.jLabel3.getText());
                } else if (clientM.type == DosyaGonder) {
                    dosyaG(clientM);

                } else if (clientM.type == GroupMsj) {
                    Ana.group.jTextArea1.append("- " + clientM.content.toString() + "\n\n");

                } else if (clientM.type == Bitir) {
                    System.out.println("Kapatıldı");

                }
            } catch (IOException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void dosyaG(Mesaj msj) {

        String yol = ("C:/Users/LENOVO/Documents/" + msj.content);

        try (OutputStream o = new FileOutputStream(yol)) {
            System.out.println("kayıt yeri: " + yol);

            byte[] c = (byte[]) msj.dosya;
            o.write(c);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
