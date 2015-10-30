package com.aiqiyi.myapp2.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;

import com.aiqiyi.myapp2.R;



public class MainActivityHome extends Activity {

	private ViewPager viewPager;
	private ImageView[] tips;
	private int[] images;
	private int currentPage=0;
	private ExpandableListView mExpandableListView;
	String[] groups = new String[] { "同步剧场", "奇艺出品", "热播电影", "3月片花速递", "动漫乐园" };
	String[][] childs = new String[5][10];
	int[] tags = new int[] { 0, 0, 0, 0, 0 };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.mainactivity);
		 MyListAdapter adapter = new MyListAdapter();
		 mExpandableListView = (ExpandableListView) findViewById(R.id.expandableListView1);
	        viewPager=(ViewPager)findViewById(R.id.viewpager1);

	        View header = LayoutInflater.from(this).inflate(R.layout.headerview,
					null);
	        mExpandableListView.addHeaderView(header);
	        
	        
	        mExpandableListView.setAdapter(adapter);
    		mExpandableListView
    				.setOnGroupExpandListener(new OnGroupExpandListener() {

    					@Override
    					public void onGroupExpand(int arg0) {
    						// TODO Auto-generated method stub
    						tags[arg0] = 1;
    					}
    				});
    		mExpandableListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

    					@Override
    					public void onGroupCollapse(int arg0) {
    						// TODO Auto-generated method stub
    						tags[arg0] = 0;
    					}
    				});

	        LinearLayout tipsBox=(LinearLayout)findViewById(R.id.tipsBox);

	     

	        images=new int[]{R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.logo};

	       



	        tips=new ImageView[4];

	        for(int i=0;i<tips.length;i++){

	            ImageView img=new ImageView(this);

	            img.setLayoutParams(new LayoutParams(10,10));

	            tips[i]=img;

	            if(i==0)

	            {

	                img.setBackgroundResource(R.drawable.page_now);

	            }

	            else{

	                img.setBackgroundResource(R.drawable.page);

	            }

	           

	            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

	            params.leftMargin=5;

	            params.rightMargin=5;

	            tipsBox.addView(img, params);

	        }

	       

	       

	        PagerAdapter adapter1=new PagerAdapter(){

	 

	            @Override

	            public int getCount() {

	                // TODO Auto-generated method stub

	                return images.length;

	            }

	 

	            @Override

	            public boolean isViewFromObject(View arg0, Object arg1) {

	                // TODO Auto-generated method stub

	                return arg0==arg1;

	            }

	           

	            @Override

	            public void destroyItem(ViewGroup container,int position,Object o){

	                //container.removeViewAt(position);

	            }

	           

	            @Override

	            public Object instantiateItem(ViewGroup container,int position){

	                ImageView im=new ImageView(MainActivityHome.this);

	               

	                im.setImageResource(images[position]);

	                container.addView(im);

	                return im;

	            }

	        };

	        viewPager.setAdapter(adapter1);

	       

	        //更改当前tip

	        viewPager.setOnPageChangeListener(new OnPageChangeListener(){

	 

	            @Override

	            public void onPageScrollStateChanged(int arg0) {

	                // TODO Auto-generated method stub

	               

	            }

	 

	            @Override

	            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

	                // TODO Auto-generated method stub

	               

	            }

	 

	            @Override

	            public void onPageSelected(int position) {

	                // TODO Auto-generated method stub

	                Log.e("rf",String.valueOf(position));

	                tips[currentPage].setBackgroundResource(R.drawable.page);

	               

	                currentPage=position;

	                tips[position].setBackgroundResource(R.drawable.page_now);

	               

	            }

	       
	           
	    		
	       

	        });
	        
	        
	        
	        

	    }

	
	
	class MyListAdapter extends BaseExpandableListAdapter {

		public MyListAdapter() {
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 10; j++) {
					childs[i][j] = "child" + i + "_" + j;
				}
			}
		}

		@Override
		public String getChild(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return childs[arg0][arg1];
		}

		@Override
		public long getChildId(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getChildView(int arg0, int arg1, boolean arg2, View arg3,
				ViewGroup arg4) {
			// TODO Auto-generated method stub
			if (arg3 == null) {
				arg3 = LayoutInflater.from(MainActivityHome.this).inflate(
						R.layout.list_child_item, null);
			}
			return arg3;
		}

		@Override
		public int getChildrenCount(int arg0) {
			// TODO Auto-generated method stub
			return 10;
		}

		@Override
		public Object getGroup(int arg0) {
			// TODO Auto-generated method stub
			return groups[arg0];
		}

		@Override
		public int getGroupCount() {
			// TODO Auto-generated method stub
			return groups.length;
		}

		@Override
		public long getGroupId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		class GroupHolder {
			ImageView img;
			TextView title;
		}

		@Override
		public View getGroupView(int arg0, boolean arg1, View arg2,
				ViewGroup arg3) {
			// TODO Auto-generated method stub
			GroupHolder groupHolder;
			if (arg2 == null) {
				arg2 = LayoutInflater.from(MainActivityHome.this).inflate(
						R.layout.list_group_item, null);
				groupHolder = new GroupHolder();
				groupHolder.img = (ImageView) arg2.findViewById(R.id.tag_img);
				groupHolder.title = (TextView) arg2
						.findViewById(R.id.title_view);
				arg2.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) arg2.getTag();
			}
			if (tags[arg0] == 0) {
				groupHolder.img
						.setImageResource(R.drawable.list_indecator_button);
			} else {
				groupHolder.img
						.setImageResource(R.drawable.list_indecator_button_down);
			}
			groupHolder.title.setText(groups[arg0]);

			return arg2;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public boolean isChildSelectable(int arg0, int arg1) {
			// TODO Auto-generated method stub
			return true;
		}

	}
	}
