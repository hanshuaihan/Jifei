package com.jifei.app;

import com.jifei.app.WaterWaveView;
import com.jifei.app.R;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MainActivity extends Activity {
	//定义用到的控件
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
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		dbHelper=new JifeiOpenHelper(this,"Jifei.db",null,1);//创建JifeiOpenHelper对象，命名为Jifei。db
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
		//所有用到的UI控件的创建
		receiveFilter=new IntentFilter();
        receiveFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
        messageReceiver=new MessageReceiver();
        registerReceiver(messageReceiver,receiveFilter);
        //在创建活动时调用数据库搜索第一行并显示
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("Call", null, null, null, null, null,null);
        if(cursor.moveToFirst()){
        	do{
        		String call_money=cursor.getString(cursor.getColumnIndex("call_money"));
        		shengyuhuafei.setText(call_money+"元");
        	}while(cursor.moveToNext());
        	cursor.close();
        }
        //所有控件注册
		huafeichaxun.setOnClickListener(new OnClickListener(){
        	@Override
        	public void onClick(View v){
        		SmsManager smsManager=SmsManager.getDefault();
        		Intent sentIntent=new Intent("SENT_SMS_ACTION");
        		PendingIntent pi=PendingIntent.getBroadcast(MainActivity.this, 0, sentIntent, 0);
        		smsManager.sendTextMessage(UNICOM,null,"102",null,null);
        		dbHelper.getWritableDatabase();
        		Toast.makeText(getApplication(), "请稍等。。。", Toast.LENGTH_LONG).show();
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
	//广播器拦截短信截取字符并显示
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
			String str1=ss.substring(0,ss.indexOf("元"));
			String str2=ss.substring(0,ss.indexOf("余额为"));
			ss=str1.replace(str2,"");
			
			shengyuhuafei.setText(ss+"元");
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("call_money", ss);
			db.insert("Call", null, values);
		}

}
	
}


