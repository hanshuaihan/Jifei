package com.jifei.app;

public class Sms {
	//+"SMS_sendnumber text"
	//+"SMS_money text)";
	private int id;
	private String smssendnumber;
	private String smsmoney;
	private int callId;
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getSmssendnumber(){
		return smssendnumber;
	}
	public void setSmssendnumber(String smssendnumber){
		this.smssendnumber=smssendnumber;
	}
	public String getSmsmoney(){
		return smsmoney;
	}
	public void setSmsmoney(String smsmoney){
		this.smsmoney=smsmoney;
	}
	public int getCallId(){
		return callId;
	}
	public void setCallId(int callId){
		this.callId=callId;
	}
}