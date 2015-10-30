package com.aiqiyi.myapp2;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.aiqiyi.myapp2.classify.ClassifyActivity;
import com.aiqiyi.myapp2.main.MainActivityHome;
import com.aiqiyi.myapp2.more.MoreActivity;
import com.aiqiyi.myapp2.myinfo.MyinfoActivity;
import com.aiqiyi.myapp2.search.SearchActivity;
import com.aiqiyi.myapp2.R;



public class MainActivity extends TabActivity implements OnClickListener{

	private TabHost tabHost;
	ImageView mBut1, mBut2, mBut3, mBut4, mBut5;
	TextView mCateText1, mCateText2, mCateText3, mCateText4, mCateText5;
	int mCurTabId = R.id.tab1;
	String aaa = "aaa";
	String bbb = "bbb";
	String ccc = "ccc";
	String ddd = "ddd";
	String eee = "eee";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);	
		super.onCreate(savedInstanceState);
	


	         //LayoutInflater.from(this).inflate(R.layout.activity_main, tabHost.getTabContentView(),true);
         	setContentView(R.layout.activity_main);
	         tabHost = getTabHost();
		
		
		Intent  intent1 = new Intent(this,MainActivityHome.class);  

	  
	        tabHost.addTab(tabHost.newTabSpec(aaa)  
	                .setIndicator("首页",  
	                        getResources().getDrawable(R.drawable.icon_1_n))  
	                .setContent(intent1)); 
			Intent  intent2 = new Intent(this, ClassifyActivity.class);  
		  
		        tabHost.addTab(tabHost.newTabSpec(bbb)  
		                .setIndicator("分类",  
		                        getResources().getDrawable(R.drawable.icon_2_n))  
		                .setContent(intent2)); 
			Intent  intent3 = new Intent(this, SearchActivity.class);  
 
			  
			        tabHost.addTab(tabHost.newTabSpec(ccc)  
			                .setIndicator("搜索",  
			                        getResources().getDrawable(R.drawable.icon_3_n))  
			                .setContent(intent3)); 
			Intent  intent4 = new Intent(this, MyinfoActivity.class);  

				        tabHost.addTab(tabHost.newTabSpec(ddd)  
				                .setIndicator("我的",  
				                        getResources().getDrawable(R.drawable.icon_4_n))  
				                .setContent(intent4)); 
			Intent  intent5 = new Intent(this, MoreActivity.class);  
					  
					        tabHost.addTab(tabHost.newTabSpec(eee)  
					                .setIndicator("更多",  
					                        getResources().getDrawable(R.drawable.icon_5_n))  
					                .setContent(intent5)); 
					        
		
					        mBut1 = (ImageView) findViewById(R.id.image1);
							mBut2 = (ImageView) findViewById(R.id.image2);
							mBut3 = (ImageView) findViewById(R.id.image3);
							mBut4 = (ImageView) findViewById(R.id.image4);
							mBut5 = (ImageView) findViewById(R.id.image5);
							findViewById(R.id.tab1).setOnClickListener(this);
							findViewById(R.id.tab2).setOnClickListener(this);
							findViewById(R.id.tab3).setOnClickListener(this);
							findViewById(R.id.tab4).setOnClickListener(this);
							findViewById(R.id.tab5).setOnClickListener(this);
							mCateText1 = (TextView) findViewById(R.id.textView1);
							mCateText2 = (TextView) findViewById(R.id.textView2);
							mCateText3 = (TextView) findViewById(R.id.textView3);
							mCateText4 = (TextView) findViewById(R.id.textView4);
							mCateText5 = (TextView) findViewById(R.id.textView5);
	        
	        
	        
	        
	        
	        

	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		if (mCurTabId == v.getId()) {
			return;
		}
		int COLOR2 = Color.parseColor("#ffffff");
		mBut1.setImageResource(R.drawable.icon_1_n);
		mBut2.setImageResource(R.drawable.icon_2_n);
		mBut3.setImageResource(R.drawable.icon_3_n);
		mBut4.setImageResource(R.drawable.icon_4_n);
		mBut5.setImageResource(R.drawable.icon_5_n);
		mCateText1.setTextColor(COLOR2);
		mCateText2.setTextColor(COLOR2);
		mCateText3.setTextColor(COLOR2);
		mCateText4.setTextColor(COLOR2);
		mCateText5.setTextColor(COLOR2);
		switch (v.getId()) {
		case R.id.tab1:
			tabHost.setCurrentTabByTag(aaa);
			mBut1.setImageResource(R.drawable.icon_1_c);
			mCateText1.setTextColor(getResources().getColor(R.color.color1));
			break;
		case R.id.tab2:
			tabHost.setCurrentTabByTag(bbb);
			mBut2.setImageResource(R.drawable.icon_2_c);
			mCateText2.setTextColor(getResources().getColor(R.color.color1));
			break;
		case R.id.tab3:
			tabHost.setCurrentTabByTag(ccc);
			mBut3.setImageResource(R.drawable.icon_3_c);
			mCateText3.setTextColor(getResources().getColor(R.color.color1));
			break;
		case R.id.tab4:
			tabHost.setCurrentTabByTag(ddd);
			mBut4.setImageResource(R.drawable.icon_4_c);
			mCateText4.setTextColor(getResources().getColor(R.color.color1));
			break;
		case R.id.tab5:
			tabHost.setCurrentTabByTag(eee);
			mBut5.setImageResource(R.drawable.icon_5_c);
			mCateText5.setTextColor(getResources().getColor(R.color.color1));
			break;
	}
	
	}


}
