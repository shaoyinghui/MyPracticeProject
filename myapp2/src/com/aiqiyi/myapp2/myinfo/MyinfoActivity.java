package com.aiqiyi.myapp2.myinfo;

import com.aiqiyi.myapp2.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MyinfoActivity extends Activity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	this.requestWindowFeature(Window.FEATURE_NO_TITLE);	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.myinfoactivity);
}
}
