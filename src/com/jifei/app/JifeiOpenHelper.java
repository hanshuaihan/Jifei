package com.jifei.app;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class JifeiOpenHelper extends SQLiteOpenHelper{
	
	//traffic表创建
	public static final String CREATE_TRAFFIC="create table Traffic("
			+"id integer primary key autoincrement,"
			+"used_tfcnum text,"
			+"traffic_number integer)";
	public static final String CREATE_CALLMONEY="create table Callmoney("
			+"id integer primary key autoincrement,"
			+"call_money text)";
	//call表创建
	public static final String CREATE_CALL="create table Call("
			+"id integer primary key autoincrement,"
			+"call_in_time text,"
			+"call_out_time text)";
			
	//SMS表创建
	public static final String CREATE_SMS="create table Sms("
			+"id integer primary key autoincrement,"
			+"sms_sendnumber text"
			+"sms_money text)";
	
	public JifeiOpenHelper(Context context,String name,CursorFactory factory,int version){
		super(context,name,factory,version);
	}
	@Override
	public void onCreate(SQLiteDatabase db){
		
		db.execSQL(CREATE_TRAFFIC);
		db.execSQL(CREATE_CALLMONEY);
		db.execSQL(CREATE_CALL);
		db.execSQL(CREATE_SMS);

	}
	@Override
	public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
	{
		
	}
			

}
