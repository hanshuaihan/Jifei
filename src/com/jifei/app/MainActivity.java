package com.jifei.app;

import java.text.DecimalFormat;

import com.jifei.app.WaterWaveView;
import com.jifei.app.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.CallLog;
import android.provider.CallLog.Calls;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import android.content.BroadcastReceiver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity {
	//�����õ��Ŀؼ�
	private TextView liuliangshengyu;
	private Button xiaozhengliuliang;
	private TextView hurushichang;
	private TextView huchushichang;
	private TextView xiaohaohuafei1;
	private TextView shengyuhuafei;
	private Button huafeichaxun;
	private TextView messagenumber;
	private TextView messagexiaohaohuafei;
	private WaterWaveView mWaterWaveView;
	private MessageReceiver messageReceiver;
	private IntentFilter receiveFilter;
	private static final String UNICOM = "10010";
	private JifeiOpenHelper dbHelper;
	private int scount=0;
	
	   
	
	
	
	private Handler handler = new Handler(){
		  @Override
		  public void handleMessage(Message msg) {
		   // TODO Auto-generated method stub
		   super.handleMessage(msg);
		   if(msg.what == 1){
			   messagenumber.setText(scount+"");
			  
			   
		   }
		   
		  }
		   
		 };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dbHelper=new JifeiOpenHelper(this,"Jifei.db",null,1);//����JifeiOpenHelper��������ΪJifei��db
		getContentResolver().registerContentObserver(Uri.parse 
                ("content://sms"), true, new SmsObserver(new Handler()));

		xiaozhengliuliang=(Button)findViewById(R.id.xiaozhengliuliang);
		huafeichaxun=(Button)findViewById(R.id.huafeichaxun);
		hurushichang=(TextView)findViewById(R.id.hurushichang);
		huchushichang=(TextView)findViewById(R.id.huchushichang);
		xiaohaohuafei1=(TextView)findViewById(R.id.xiaohaohuafei1);
		shengyuhuafei=(TextView)findViewById(R.id.shengyuhuafei);
		messagenumber=(TextView)findViewById(R.id.messagenumber);
		messagexiaohaohuafei=(TextView)findViewById(R.id.messagexiaohaohuafei);
		mWaterWaveView = (WaterWaveView) findViewById(R.id.wave_view);
        mWaterWaveView.setmWaterLevel(0.8F);
		mWaterWaveView.startWave();
		//�����õ���UI�ؼ��Ĵ���
		receiveFilter=new IntentFilter();
        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        messageReceiver=new MessageReceiver();
        registerReceiver(messageReceiver,receiveFilter);
        
        
        
        
        //�ڴ����ʱ�������ݿ�������һ�в���ʾ
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Callmoney", null, null, null, null, null,null);
        if(cursor.moveToFirst()){
        	do{
        		String call_money=cursor.getString(cursor.getColumnIndex("call_money"));
        		shengyuhuafei.setText(call_money+"Ԫ");
        	}while(cursor.moveToNext());
        	cursor.close();
        }
        SQLiteDatabase db1=dbHelper.getWritableDatabase();
        Cursor cursor1=db1.query("Sms", null, null, null, null, null,null);
        if(cursor1.moveToFirst()){
        	do{
        		String sms_sendnumber=cursor1.getString(cursor1.getColumnIndex("sms_sendnumber"));
        		
        		messagenumber.setText(sms_sendnumber);
        		scount=cursor1.getInt(cursor1.getColumnIndex("sms_sendnumber"));
        		float smsm= (float)scount/10;   
        		DecimalFormat df = new DecimalFormat("0.0");//��ʽ��С��   
        		String smsmm = df.format(smsm);//���ص���String���� 
  			   messagexiaohaohuafei.setText(smsmm+"Ԫ");
        	}while(cursor1.moveToNext());
        	cursor1.close();
        }
        SQLiteDatabase db2=dbHelper.getWritableDatabase();
        Cursor cursor2=db2.query("Call", null, null, null, null, null,null);
        if(cursor2.moveToFirst()){
        	do{
        		String call_in_time=cursor2.getString(cursor2.getColumnIndex("call_in_time"));
        		hurushichang.setText(call_in_time);
        		String call_out_time=cursor2.getString(cursor2.getColumnIndex("call_out_time"));
        		huchushichang.setText(call_out_time);
        	}while(cursor2.moveToNext());
        	cursor2.close();
        }
        
        
        
        //���пؼ�ע��
		huafeichaxun.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		SmsManager smsManager=SmsManager.getDefault();
        		Intent sentIntent=new Intent("SENT_SMS_ACTION");
        		PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this, 0, sentIntent, 0);
        		smsManager.sendTextMessage(UNICOM,null,"102",null,null);
        		
        		
        		
        		Cursor cursor = getContentResolver().query(Calls.CONTENT_URI, 
        				new String[] { Calls.DURATION, Calls.TYPE, Calls.DATE }, null, null, 
        				Calls.DEFAULT_SORT_ORDER); 
        				MainActivity.this.startManagingCursor(cursor); 
        				boolean hasRecord = cursor.moveToFirst(); 
        				long incoming = 0L; 
        				long outgoing = 0L; 
        				while (hasRecord) { 
        				int type = cursor.getInt(cursor.getColumnIndex(Calls.TYPE)); 
        				long duration = cursor.getLong(cursor.getColumnIndex(Calls.DURATION)); 
        				switch (type) { 
        				case Calls.INCOMING_TYPE: 
        				incoming += duration; 
        				break; 
        				case Calls.OUTGOING_TYPE: 
        				outgoing += duration; 
        				default: 
        				break; 
        				} 
        				hasRecord = cursor.moveToNext(); 
        				} 
        		
        			
        	        long h = 0;  
        	        long d = 0;  
        	        long s = 0;  
        	        long temp = incoming % 3600;  
        	        if (incoming > 3600) {  
        	            h = incoming / 3600;  
        	            if (temp != 0) {  
        	                if (temp > 60) {  
        	                    d = temp / 60;  
        	                    if (temp % 60 != 0) {  
        	                        s = temp % 60;  
        	                    }  
        	                } else {  
        	                    s = temp;  
        	                }  
        	            }  
        	        } else {  
        	            d = incoming / 60;  
        	            if (incoming % 60 != 0) {  
        	                s = incoming % 60;  
        	            }  
        	        }  
        	        String hi=Long.toString(h);
        	        String di=Long.toString(d);
        	        String si=Long.toString(s);
        	        hurushichang.setText(hi+"ʱ"+di+"��"+si+"��");
        		
        	        
        	        long h1 = 0;  
        	        long d1 = 0;  
        	        long s1= 0;  
        	        long temp1 = outgoing % 3600;  
        	        if (outgoing > 3600) {  
        	            h1 = outgoing / 3600;  
        	            if (temp1 != 0) {  
        	                if (temp1 > 60) {  
        	                    d1 = temp1 / 60;  
        	                    if (temp1 % 60 != 0) {  
        	                        s1 = temp1 % 60;  
        	                    }  
        	                } else {  
        	                    s1 = temp1;  
        	                }  
        	            }  
        	        } else {  
        	            d1 = outgoing / 60;  
        	            if (outgoing % 60 != 0) {  
        	                s1 = outgoing % 60;  
        	            }  
        	        }  
        	        String h1i=Long.toString(h1);
        	        String d1i=Long.toString(d1);
        	        String s1i=Long.toString(s1);
        	        huchushichang.setText(h1i+"ʱ"+d1i+"��"+s1i+"��");
        	        SQLiteDatabase db2=dbHelper.getWritableDatabase();
        			ContentValues values = new ContentValues();
        			values.put("call_in_time", hi+"ʱ"+di+"��"+si+"��");
        			values.put("call_out_time", h1i+"ʱ"+d1i+"��"+s1i+"��");
        			db2.insert("Call", null, values);

        		
        		
        		Toast.makeText(getApplication(), "���Եȡ�����", Toast.LENGTH_LONG).show();
        	}
    });
	
	xiaozhengliuliang.setOnClickListener(new OnClickListener(){
    	@Override
    	public void onClick(View v){
    		SmsManager smsManager=SmsManager.getDefault();
    		Intent sentIntent=new Intent("SENT_SMS_ACTION");
    		PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this, 0, sentIntent, 0);
    		smsManager.sendTextMessage(UNICOM,null,"412",null,null);
    	}
});
}
	
	
	
	
	//�㲥�����ض��Ž�ȡ�ַ�����ʾ
	class MessageReceiver extends BroadcastReceiver{
		
		@Override
		public void onReceive(Context context,Intent intent){
			Bundle bundle=intent.getExtras();
			Object[]pdus=(Object[])bundle.get("pdus");
			SmsMessage[]messages=new SmsMessage[pdus.length];
			for(int i=0;i<messages.length;i++){
				messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
			}
			String ss="";
			for (SmsMessage message : messages){
				ss +=message.getMessageBody();
			}
			String str1=ss.substring(0,ss.indexOf("Ԫ"));
			String str2=ss.substring(0,ss.indexOf("���Ϊ"));
			ss=str1.replace(str2,"");
			
			shengyuhuafei.setText(ss+"Ԫ");
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("call_money", ss);
			db.insert("Callmoney", null, values);
		
	}	
}
	class SmsObserver extends ContentObserver{ 
		   
        public SmsObserver(Handler handler) { 
            super(handler); 
            // TODO Auto-generated constructor stub 
        } 
        @Override 
        public void onChange(boolean selfChange) { 
            // TODO Auto-generated method stub 
            //��ѯ���������еĶ��� 
        	
            
            Cursor cursor=getContentResolver().query(Uri.parse( 
                    "content://sms/outbox"), null, null, null, null); 
            //������ѯ�����ȡ�û����ڷ��͵Ķ��� 
            Message msg = handler.obtainMessage();
			
			
            		if(cursor.moveToNext()==true){
            		    scount ++;
            		    msg.what = 1;
            		    handler.sendMessage(msg);
            		    float smsm= (float)scount/10;   
                		DecimalFormat df = new DecimalFormat("0.0");//��ʽ��С��   
                		String smsmm = df.format(smsm);//���ص���String���� 
          			   messagexiaohaohuafei.setText(smsmm+"Ԫ");
          			   
            		    SQLiteDatabase db1=dbHelper.getWritableDatabase();
            			ContentValues values = new ContentValues();
            			values.put("sms_sendnumber", scount);
            			db1.insert("Sms", null, values);
            		   }
            	
            	}
                
            	
        } 
           
    } 




