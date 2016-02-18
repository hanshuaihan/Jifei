package com.jifei.app;

public class Call {
	//call_in_time text,"
			//+"call_out_time text,"
			//+"call_money_spend text,"
			//+"call_money text,)"
	private int id;
	private String callin;
	private String callout;
	private String callmoneyspend;
	private String callmoney;
	private int trafficId;
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getCallin(){
		return callin;
	}
	public void setCallin(String callin){
		this.callin=callin;
	}
	public String getCallout(){
		return callout;
	}
	public void setCallout(String callout){
		this.callout=callout;
	}
	public String getCallmoneyspend(){
		return callmoneyspend;
	}
	public void setCallmoneyspend(String callmoneyspend){
		this.callmoneyspend=callmoneyspend;
	}
	public String getCallmoney(){
		return callmoney;
	}
	public void setCallmoney(String callmoney){
		this.callmoney=callmoney;
	}
	public int getTrafficId(){
		return trafficId;
	}
	public void setTrafficId(int trafficId){
		this.trafficId=trafficId;
	}

}
