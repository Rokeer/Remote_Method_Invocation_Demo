package server;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.net.*;
import java.io.*;
public class Server{
    public static void main(String args[])
    {
        InputStreamReader is=
                new InputStreamReader(System.in);
        BufferedReader br=new BufferedReader(is);
        String portNum,registryURL;
        try
        {
            System.out.println(
                    "Enter the RMIregistry port number:");
            portNum=(br.readLine()).trim();
            int RMIPortNum=Integer.parseInt(portNum);
            startRegistry(RMIPortNum);
            ServerImpl exportedObj=
                    new ServerImpl();
            registryURL="rmi://localhost:"+portNum+"/DCS";
            Naming.rebind(registryURL,exportedObj);
            System.out.println("Callback Server ready.");
        }
        catch (Exception re)
        {
            System.out.println(
                    "Exception in Server.main:"+re);
        }
    }

    private static void startRegistry(int RMIPortNum)
            throws RemoteException{
        try
        {
            Registry registry=
                    LocateRegistry.getRegistry(RMIPortNum);
            registry.list();
        }
        catch(RemoteException e)
        {
            Registry registry=
                    LocateRegistry.createRegistry(RMIPortNum);
        }
    }
}
