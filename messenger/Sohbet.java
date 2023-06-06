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
public class Sohbet implements Serializable {
      boolean b=false;
    private String t;
    private String c;
    private String s;
 


    public Sohbet(String s,String c, String t, boolean k) {
        this.s = s;
        this.b=k;  
        this.t = t;
        this.c = c;
    }

    public String getContent() {
        return c;
    }
     public String getTarget() {
        return t;
    }

    public String getSender() {
        return s;
    }

   

}
