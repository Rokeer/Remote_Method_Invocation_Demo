package client;
import java.rmi.*;
import java.io.*;
import java.rmi.*;

import server.ServerInterface;
public class Client
{
    public static void main(String args[])
    {
        try{
            int RMIPort;
            String hostName;
            String subject,code,credit1,grade1,name;
            Double grade=0.0;
            String checksubject;
            int credit;
            InputStreamReader is = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(is);
            System.out.println("Please Enter the RMIregistry host name:");
            hostName = br.readLine();
            System.out.println("Please Enter the RMIregistry port number:");
            String portNum = br.readLine();
            RMIPort = Integer.parseInt(portNum);
            String registryURL =
                    "rmi://"+hostName+":" + portNum + "/DCS";
            ServerInterface h =
                    (ServerInterface) Naming.lookup(registryURL);
            System.out.println("Lookup completed");
            ClientImpl callbackObj = new ClientImpl();
            h.registerForCallback(callbackObj);
            System.out.println("Registered for callback.");
            
            while(true)
            {
                System.out.println("Input 1 to insert subject records.");
                System.out.println("Input 0 to view subject records.");
                String choice3 = br.readLine();
                int choice4 = Integer.parseInt(choice3);
                if (choice4 == 1) 
                {
                    System.out.println("Please enter Your Name, Subject Title, Subject Code, Credit and Grade.");
                    name = br.readLine();
                    subject = br.readLine();
                    code = br.readLine();
                    credit1 = br.readLine();
                    credit = Integer.parseInt(credit1);
                    grade1 = br.readLine();

                    if(grade1.equals("A")){grade=4.0;}
                    else if(grade1.equals("A-")){grade=3.7;}
                    else if(grade1.equals("B+")){grade=3.3;}
                    else if(grade1.equals("B")){grade=3.0;}
                    else if(grade1.equals("B-")){grade=2.7;}
                    else if(grade1.equals("C+")){grade=2.3;}
                    else if(grade1.equals("C")){grade=2.0;}
                    else if(grade1.equals("C-")){grade=1.7;}
                    else if(grade1.equals("D")){grade=1.0;}
                    else if(grade1.equals("E")){grade=0.0;}
                    else if(grade1.equals("F")){grade=0.0;}

                    h.enter(subject, code, credit, grade, name);
                }
                if(choice4==0)break;
            }
            
                System.out.println("Input 1 to view subject records.");
                System.out.println("Input 0 to unregister.");
            String choice1 = br.readLine();
            int choice2 = Integer.parseInt(choice1);
            if (choice2 == 1)
             {
            	System.out.println("Input you name.");
            	name = br.readLine();
                while(true)
                {
                    System.out.println("Input 1 to view subject records with the subject title or code.");
                    System.out.println("Input 2 to view your GPA.");
                    System.out.println("Input 0 to unregister.");
                    String choice5=br.readLine();
                    int choice6=Integer.parseInt(choice5);
                 if(choice6==0)break;
                 else if(choice6==1){
                    System.out.println("Please input the subeject title£»");
                    checksubject=br.readLine();         
                    h.inquire(name, checksubject,choice6,callbackObj);
                                    }
                    else if(choice6==2){
                    checksubject="null";     
                    h.inquire(name, checksubject,choice6,callbackObj);
                                       }
            }
        }
         h.unregisterForCallback(callbackObj);
         System.out.println("Unregistered Callback!");
    }
        catch(Exception e){
            System.out.println(
                    "Exception in Client:"+e);
             }
    }
}

