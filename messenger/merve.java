/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messenger;
import ClientMessenger.Client;
import static messenger.merve.kk;
import static messenger.Ana.x;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import static java.util.Collections.list;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import static messenger.NewGroup.jLabel5;


/**
 *
 * @author LENOVO
 */
public class merve extends javax.swing.JFrame {
public static Ana x = new Ana();
public static NewGroup group = new NewGroup();
public static onlineUser kk = new onlineUser();
public static String kullanici;
public DefaultListModel groupList = new DefaultListModel();  

    public static void DuyguDurumAdIcon(String Adres) throws IOException {
        //DuyguDurumunu gönderebilmek adına karşı tarafa öncelikle ilgli olan resmin dönüşümü yapılır ve karşıya iletilir daha sonra label üzerine yapıştırılır.
        Mesaj gndr = new Mesaj(Mesaj.gndr_t.duygu_isim);
        gndr.content = jLabel4.getText();;
        Client.Send(gndr);

        Icon icon = new ImageIcon(merve.class.getResource(Adres));
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        ObjectOutputStream o = null;
        try {
            o = new ObjectOutputStream(byt);
        } catch (IOException ex) {
            Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            o.writeObject(icon);
        } catch (IOException ex) {
            Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] icn_byte = byt.toByteArray();

        Mesaj gndr2 = new Mesaj(Mesaj.gndr_t.DuyguDurum);
        gndr2.content = icn_byte;
        Client.Send(gndr2);

    }
    public static void text(String txt) throws IOException{
        Mesaj gndr = new Mesaj(Mesaj.gndr_t.text);
        gndr.content = txt;
        Client.Send(gndr);
  
    }
      public merve() {
        initComponents();
        kullanici=jLabel4.getText();
        jList1.setModel(groupList);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton3 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(185, 227, 227));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/send.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 633, 57, -1));
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 630, 375, 41));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 286, 514, 326));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/people.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, 54));

        jScrollPane2.setViewportView(jList1);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 282, 155, 326));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/create.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 160, 48, 40));
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 160, 155, 40));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/join.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 336, 48, 54));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/refresh.png"))); // NOI18N
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(705, 282, 48, -1));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cool.png"))); // NOI18N
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 684, 54, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/mutlu.png"))); // NOI18N
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 684, 54, -1));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/aglama.png"))); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 684, 59, -1));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/kalp.png"))); // NOI18N
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 684, 44, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 180, 145, 83));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sinirli.png"))); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(244, 684, 54, -1));

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ıgrenmis.png"))); // NOI18N
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(316, 684, 49, -1));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 128, 317, 135));

        jButton12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/folder.png"))); // NOI18N
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 633, 52, -1));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 180, 84));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/background.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-19, -17, 790, 780));

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 210, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//mesajın karşı tarfa iletilmesi için
    try {
        text("from  " + jLabel4.getText() + "  to everyone -> " + jTextField1.getText());
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
        jTextField1.setText(" ");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
     // Online user sayfasına gidiyor
            kullanici=jLabel4.getText();
            kk.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// grup create ediyor       

       x.group.jLabel5.setText(jLabel4.getText());
        Mesaj gndr = new Mesaj(Mesaj.gndr_t.GroupAd);
        gndr.content = jTextField2.getText();
        jTextField2.setText(null);
    try {
        Client.Send(gndr);
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
          Mesaj gndr2 = new Mesaj(Mesaj.gndr_t.Grouplar);
    try {
        Client.Send(gndr2);
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
  
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //istediğin grubu seçerek o gruba katılabilirisn.
        Mesaj gndr = new Mesaj(Mesaj.gndr_t.GroupAd);
        gndr.content = (String) jList1.getSelectedValue();

       Mesaj gndr2 = new Mesaj(Mesaj.gndr_t.GroupKatılanlar);
        
        gndr2.content = (String) jList1.getSelectedValue();
      
        
             ArrayList<String> a=new ArrayList<>();
             
             a.add(jList1.getSelectedValue());
            
             a.add(jLabel4.toString());  
             
             x.group.jLabel1.setText(jList1.getSelectedValue());
         
             gndr2.content=a;
    try {
        Client.Send(gndr2);
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
             x.group.setVisible(true);
       
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
//bazen gruplar gelmiyor yenilenmesi   gerekiyor  
           Mesaj gndr = new Mesaj(Mesaj.gndr_t.Grouplar);
    try {
        Client.Send(gndr);
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    try {
        DuyguDurumAdIcon("/icon/cool.png");
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
    try {
        DuyguDurumAdIcon("/icon/mutlu.png");
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    try {
        DuyguDurumAdIcon("/icon/aglama.png");
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    try {
        DuyguDurumAdIcon("/icon/kalp.png");
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    try {
        DuyguDurumAdIcon("/icon/sinirli.png");
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
    try {
        DuyguDurumAdIcon("/icon/ıgrenmis.png");
    } catch (IOException ex) {
        Logger.getLogger(merve.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
     //dosya gönderme işlemi
        JFileChooser secilenDosya = new JFileChooser();
        System.out.println("Dosya"+secilenDosya);
int dosya = secilenDosya.showOpenDialog(this);
dosyaGon( secilenDosya,dosya);
    }//GEN-LAST:event_jButton12ActionPerformed
    public static void dosyaGon(JFileChooser secilen, int b) {

        if (b == JFileChooser.APPROVE_OPTION) {
            File dosya = secilen.getSelectedFile();

            try {
                byte[] k = Files.readAllBytes(dosya.toPath());
                Mesaj a = new Mesaj(Mesaj.gndr_t.DosyaGonder);
                a.dosya = k;
                a.content = dosya.getName();
                Client.Send(a);

                String gndr = jLabel4.getText();
            } catch (IOException ex) {
                System.out.println("hata sebebi: " + ex);
            }
        } else {
            System.out.println("dosya secilmedi");
        }

    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(merve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(merve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(merve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(merve.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new merve().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    public static javax.swing.JLabel jLabel4;
    public static javax.swing.JLabel jLabel5;
    public static javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
