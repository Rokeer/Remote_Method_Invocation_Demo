package entity;

import java.rmi.RemoteException;

public class Information {
	private int informationid;
	private String subject;
    private String code;
    private int credit;
    private Double grade;
    private User user;
    public Information() throws RemoteException{
        this.subject = "null";
        this.code ="null";
        this.credit = 0;
        this.grade = 0.0;

    }
    public Information(String subject,String code,int credit1,Double grade)
        {
         this.subject = subject;
         this.code = code;
         this.credit = credit1;
         this.grade = grade;
    }
	public int getInformationid() {
		return informationid;
	}
	public void setInformationid(int informationid) {
		this.informationid = informationid;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public Double getGrade() {
		return grade;
	}
	public void setGrade(Double grade) {
		this.grade = grade;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
