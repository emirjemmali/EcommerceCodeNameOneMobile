package com.mycompany.myapp;

import static com.codename1.ui.CN.*;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import java.io.IOException;
import com.codename1.ui.Toolbar;
import com.codename1.io.NetworkEvent;
import com.mycompany.Controllers.LoginController;
import com.mycompany.Entities.Panier;
import com.mycompany.Entities.Product;
import com.mycompany.Entities.Users;
import com.mycompany.Services.UsersService;
import com.mycompany.gui.Login;
import static com.mycompany.myapp.MyApplication.Panier;
import java.util.ArrayList;
import java.util.List;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {
    public static Users user = null;
    private Form current;
    private Resources theme;
    public static   List<Panier> Panier = new ArrayList<Panier>();

    

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature
        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        
        Product p1 = new Product();
        p1.setName("Gateau au chocolat");
        p1.setPrice(30);
        Product p2= new Product();
        p2.setName("Tarte au citron");
        p2.setPrice(20);
        Panier pa1 = new Panier();
        pa1.setProduit(p2);
        pa1.setQte(5);
          Panier pa2 = new Panier();

       pa2.setProduit(p1);
        pa2.setQte(2);
        
        
         Panier pa3 = new Panier();
p1.setId(2);
p2.setId(1);
       pa3.setProduit(p1);
        pa3.setQte(2);
        Panier.add(pa2);
        Panier.add(pa1);
        Panier.add(pa3);
        
        
        
        
        
        Login h = new Login();
        h.getF().show();
      /*  ArrayList<Users> u = new ArrayList<>();
        UsersService us = new UsersService();
        Users ul= new Users();
        ul=us.getUserbyId(3);
        u=us.getAllUsers();
        System.err.println(ul.toString());
        LoginController l = new LoginController();
      String logged;
        logged=l.login("deli", "deli");
        if (logged.equals("logged")) {
            System.err.println("Logged good");
        }
        else if (logged.equals("nouser")) {
            System.err.println("nouser");
        }
        else
        {
            System.err.println("error");
        }*/
        
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}