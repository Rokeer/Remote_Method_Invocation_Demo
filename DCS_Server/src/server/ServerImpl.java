package server;

import java.rmi.*;
import java.rmi.*;
import java.rmi.server.*;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Vector;
import java.io.*;

import client.ClientInterface;
import dao.InformationDAO;
import dao.UserDAO;
import entity.Information;
import entity.User;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	private Vector clientList;
	private Vector informationlist;
	private int ID;
	Information student = new Information();
	int creditgained = 0;
	Double total = 0.0;
	Double cgrade = 0.0;

	public ServerImpl() throws RemoteException {
		super();
		clientList = new Vector();
		informationlist = new Vector();
	}

	public void enter(String subject, String code, int credit1, Double grade,
			String name) throws RemoteException {
		User student = new User();
		UserDAO ud = new UserDAO();
		if (!ud.checkHave("name", name))
		{

			student.setName(name);
			ud.regUser(student);
		}
		student = ud.getUserByKey("name", name);
		Information info = new Information(subject, code, credit1, grade);
		InformationDAO id = new InformationDAO();
		info.setCode(code);
		info.setSubject(subject);
		info.setCredit(credit1);
		info.setGrade(grade);
		info.setUser(student);
		id.regInfo(info);
	}

	public void inquire(String name, String a, int n, ClientInterface callbackClientObj)
			throws RemoteException {
		this.ID = clientList.indexOf(callbackClientObj);
		int i;
		Double gpa;
		
		User student = new User();
		UserDAO ud = new UserDAO();
		if (!ud.checkHave("name", name))
		{
			docallbacks("This user is not exist.");
		}
		else
		{
			
			student = ud.getUserByKey("name", name);
			Iterator<Information> itr = student.getInformation().iterator();
			if (n==1)
			{
				while (itr.hasNext()) {
					Information info = (Information) itr.next();
					if(info.getSubject().equals(a)||info.getCode().equals(a))
					{
						docallbacks("your Score is£º\n" + info.getGrade());
						if (info.getGrade() < 2.0)
							docallbacks("You failed in this subject, you must retake this subject!");
						break;
					}
				}
			} else if (n==2)
			{
				Double cgpa = 0.0;
				Double total = 0.0;
				Double credit = 0.0;
				while (itr.hasNext()) {
					Information info = (Information) itr.next();
					credit = credit + info.getCredit();
					total = total + (info.getGrade()*info.getCredit());
					
				}
				cgpa = total/credit;
				DecimalFormat df = new DecimalFormat("0.00");
				String ncgpa = df.format(cgpa);
				docallbacks("Your cGPA is " + ncgpa);
			}
		}

		
	}

	public synchronized void registerForCallback(
			ClientInterface callbackClientObject) throws RemoteException {
		if (!(clientList.contains(callbackClientObject))) {
			clientList.addElement(callbackClientObject);
			System.out.println("Registered new client");
		}
	}

	public synchronized void unregisterForCallback(
			ClientInterface callbackClientObject) throws RemoteException {
		if (clientList.removeElement(callbackClientObject)) {
			System.out.println("Unregistered client");
		} else {
			System.out.println("unregister:client was't registered.");
		}
	}

	private synchronized void docallbacks(String message)
			throws RemoteException {
		ClientInterface callbackClientObj = (ClientInterface) clientList
				.elementAt(ID);
		callbackClientObj.notifyMe(message);
	}
}
