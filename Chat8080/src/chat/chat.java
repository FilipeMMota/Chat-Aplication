/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.*;
import java.awt.*;

/**
 *
 * @author filip
 */
public class chat extends Frame{
    TextArea ecran=new TextArea(10,30);
    TextField addr=new TextField(30);
    TextField text=new TextField(30);
    Button Send=new Button("Send");
    socket sock=new socket(ecran);
    public chat(String str){super(str);}
    
    public static void main(String[] args)throws IOException{
        chat app=new chat("Cliente Batata");
        app.resize(320,290);
        app.GUI();
        app.show();
        app.StartSocket();
    }
    
    public void GUI(){
        setBackground(Color.lightGray);
        ecran.setEditable(false);
        GridBagLayout GBL=new GridBagLayout();
        setLayout(GBL);
        Panel P1=new Panel();
        P1.setLayout(new BorderLayout(5,5));
        P1.add("North",text);
        P1.add("West",addr);
        P1.add("East",Send);
        P1.add("South",ecran);
        GridBagConstraints P1C=new GridBagConstraints();
        P1C.gridwidth=GridBagConstraints.REMAINDER;
        GBL.setConstraints(P1,P1C);
        add(P1);
    }
    
    public void StartSocket(){sock.start();}

    @Override
    public boolean handleEvent(Event i){
        if(i.id==Event.WINDOW_DESTROY){
            dispose();
            System.exit(0);
            return true;
        }
        return super.handleEvent(i);
    }
    
    @Override
    public boolean action(Event i,Object o){
        if(i.target==Send){
            String msg=text.getText();
            String nick=addr.getText();
            sock.sendDP(nick,msg,"127.0.0.1");
            text.setText("");
            return true;
        }
        return false;
    }
}


