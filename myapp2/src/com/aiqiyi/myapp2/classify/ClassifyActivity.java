package com.aiqiyi.myapp2.classify;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.aiqiyi.myapp2.R;

public class ClassifyActivity  extends Activity {
	
	private Integer[] mThumbIds = {  
            R.drawable.channel_type_cartoon_normal, R.drawable.channel_type_fun_normal,  
            R.drawable.channel_type_movie_normal, R.drawable.channel_type_music_normal,  
            R.drawable.channel_type_publicity_normal, R.drawable.channel_type_qiyi_works_normal,  
            R.drawable.channel_type_record_normal, R.drawable.channel_type_tourism_normal,  
            R.drawable.channel_type_tv_normal, R.drawable.channel_type_video_clips_normal,  

    }; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE);	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classifyactivity);
		Log.e("classifyactivity","classifyactivity");
		 GridView gridView=(GridView)findViewById(R.id.gridview);  
        gridView.setAdapter(new ImageAdapter(this));  
	}
	
	private class ImageAdapter extends BaseAdapter{  
        private Context mContext;  
  
        public ImageAdapter(Context context) {  
            this.mContext=context;  
        }  
  
        @Override  
        public int getCount() {  
            return mThumbIds.length;  
        }  
  
        @Override  
        public Object getItem(int position) {  
            return mThumbIds[position];  
        }  
  
        @Override  
        public long getItemId(int position) {  
            // TODO Auto-generated method stub  
            return 0;  
        }  
  
        @Override  
        public View getView(int position, View convertView, ViewGroup parent) {  
            //定义一个ImageView,显示在GridView里  
            ImageView imageView;  
            if(convertView==null){  
                imageView=new ImageView(mContext);  
                imageView.setLayoutParams(new GridView.LayoutParams(180, 180));  
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);  
                imageView.setPadding(8, 8, 8, 8);  
            }else{  
                imageView = (ImageView) convertView;  
            }  
            imageView.setImageResource(mThumbIds[position]);  
            return imageView;  
        }  
          
  
          
    }  
    //展示图片  
     
}  

