package chat;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class socket extends Thread{
    InetAddress ER;
    DatagramSocket DS;
    byte bp[]=new byte[1024];
    TextArea ecran=new TextArea(10,30);
    socket(TextArea ta){ecran=ta;}
    @Override
    public void run(){
        try{DS=new DatagramSocket(8082);}
        catch(IOException e){}
        while(true) receiveDP();
    }
    public void receiveDP(){
        try{
            DatagramPacket DP=new DatagramPacket(bp,1024);
            DS.receive(DP);
            int port=DP.getPort();
            byte Payload[]=DP.getData();
            int len=DP.getLength();
            String res=new String(Payload,0,0,len);
            
            String url = "http://localhost:8080/RevNick/webresources/revnick?port=" + port;
            URL obj = new URL(url);
            
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            
            String nick = response.toString();
            ecran.appendText("\n"+nick+": "+res);
        }catch(IOException e){}
        
    }
    public void sendDP(String nick,String msg,String end){
        int len=msg.length();
        byte b[]=new byte[len];
        msg.getBytes(0,len,b,0);
        String url = "http://localhost:8080/BindNick/webresources/bindnick?nick=" + nick;
        URL obj;
        
        try{
            obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            int port = Integer.parseInt(response.toString());
            System.out.println(port);
            ER=InetAddress.getByName(end);
            DatagramPacket DP=new DatagramPacket(b,len,ER,port);
            DS.send(DP);
        }catch(IOException e){}
    }
}
