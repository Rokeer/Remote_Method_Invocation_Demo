package server;

import java.rmi.*;

import client.ClientInterface;
public interface ServerInterface extends Remote{
    public void enter(String subject,String code,int credit,Double grade,String name)
            throws java.rmi.RemoteException ;
    public void inquire(String name, String a,int n,ClientInterface callbackClientObj)
            throws java.rmi.RemoteException ;
    public void registerForCallback(
            ClientInterface callbackClientObject)
            throws java.rmi.RemoteException ;
    public void unregisterForCallback(
            ClientInterface callbackClientObject)
            throws java.rmi.RemoteException ;
}
