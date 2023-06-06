/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Mesaj implements Serializable {

    //Bu sınıfta sayesinde karşı tarafa gönderilen hamleler tutulur ve aracılık eder. Aşşağıda yapmak istediğim koşulların isimleri bulunmakta bu case ler ile birlikte yolluyorum..
    public enum gndr_t {
        Basla, Bitir, GroupKatılanlar, onlineUsers, ozelMesaj, GroupAd, Grouplar, DosyaGonder, GroupMsj, DuyguDurum, duygu_isim, Kapat, text, groupchat, duygu_isim2, DuyguDurum2, GroupDuyguAd
    }

    public gndr_t type;// gönderilen mesajın türü
    public Object content;
    public byte[] dosya;

    public Mesaj(gndr_t m) {//consturactor
        this.type = m;
    }

}
