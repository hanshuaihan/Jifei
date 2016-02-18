package com.jifei.app;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class JifeiOpenHelper extends SQLiteOpenHelper{
	
	//traffic表创建
	public static final String CREATE_Traffic="create table Traffic("
			+"id integer primary key autoincrement,"
			+"traffic_number text,";
	//call表创建
	public static final String CREATE_Call="create table Call("
			+"id integer primary key autoincrement,"
			+"call_in_time text,"
			+"call_out_time text,"
			+"call_money_spend text,"
			+"call_money text,)";
	//SMS表创建
	public static final String CREATE_Sms="create table SMS("
			+"id integer primary key autoincrement,"
			+"Sms_sendnumber text"
			+"Sms_money text)";
	public JifeiOpenHelper(Context context,String name,CursorFactory factory,int version){
		super(context,name,factory,version);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_Traffic);
		db.execSQL(CREATE_Call);
		db.execSQL(CREATE_Sms);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
	{
		
	}
			

}
