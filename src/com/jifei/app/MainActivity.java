package com.jifei.app;

import com.jifei.app.WaterWaveView;
import com.jifei.app.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
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
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
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
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mWaterWaveView.stopWave();
		mWaterWaveView=null;
		super.onDestroy();
	}

}
