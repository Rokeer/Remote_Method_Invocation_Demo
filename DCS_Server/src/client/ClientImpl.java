package client;
import java.rmi.*;
import java.rmi.server.*;
public class ClientImpl extends UnicastRemoteObject
        implements ClientInterface{
    public ClientImpl()throws RemoteException
    {
        super();
    }

    public String notifyMe(String message)
    {
        System.out.println(message);
        return message;
    }
}
