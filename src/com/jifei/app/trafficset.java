package com.jifei.app;

import java.text.DecimalFormat;

import com.jifei.app.MainActivity.TimeThread;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.TrafficStats;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class trafficset extends Activity{
	private EditText mtraffic;
	private Button mtrafficset;
	private Switch llyuzhikg;
	private EditText trafficcleanday;
	private Button entercleanday;
	private JifeiOpenHelper dbHelper;
	private static final int msgKey1 = 2;

	
	
	

           	   
    
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.trafficxml);
		mtraffic=(EditText)findViewById(R.id.mtraffic);
		mtrafficset=(Button)findViewById(R.id.mtrafficset);
		trafficcleanday=(EditText)findViewById(R.id.trafficcleanday);
		entercleanday=(Button)findViewById(R.id.entercleanday);
		
		
		
		
		
		
		
		
		
		
		mtrafficset.setOnClickListener(new OnClickListener() {//���������¼�  
			  
			  
			  
			 @Override  
			  
			 public void onClick(View v) {  
			  
			     // TODO Auto-generated method stub  
			  
			     new AlertDialog.Builder(trafficset.this).setTitle("ϵͳ��ʾ")//���öԻ������  
			  
			     .setMessage("���������������")//������ʾ������  
			  
			     .setPositiveButton("ȷ��",new DialogInterface.OnClickListener() {//���ȷ����ť  
			  
			          
			  
			         @Override  
			  
			         public void onClick(DialogInterface dialog, int which) {//ȷ����ť����Ӧ�¼�  
			  
			             // TODO Auto-generated method stub  
			  
			  
			         }  
			  
			     }).show();//�ڰ�����Ӧ�¼�����ʾ�˶Ի���  
			 }  
			  
			});  

	
		
		
		
	}
	
	
	
}
