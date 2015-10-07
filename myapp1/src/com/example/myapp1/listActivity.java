package com.example.myapp1;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;



public class listActivity extends Activity {
	private TextView tv;
	private ListView lv;
	private MySQLiteHelper mySQLiteHelper;
	private SQLiteDatabase db;
	EditText et01;
	EditText et02;
	private Cursor cursor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_activity);
		tv = (TextView)findViewById(R.id.nocontent);
		lv = (ListView)findViewById(R.id.listview);
		MySQLiteHelper mySQLiteHelper;
		mySQLiteHelper = new MySQLiteHelper(listActivity.this,"notepad.db",null,1);
		final SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
		final Cursor cursor = db.query("notepadtable",new String[]{"_id","title","content"},null,null,null,null,null);
		Log.v("mmmmmm",cursor.toString());
			if(cursor.getCount()>0){
				tv.setVisibility(View.GONE);
			}
		SimpleCursorAdapter sca = new SimpleCursorAdapter(listActivity.this, R.layout.listview, cursor, new String[]{"_id","title","content"}, new int[]{R.id.textview01,R.id.textview02,R.id.textview03});
		lv.setAdapter(sca);
		lv.setOnItemClickListener(new OnItemClickListener(){

			public void onItemClick(AdapterView<?> arg0, View arg1, int which,
					long arg3) {
				
		
		Builder builder = new Builder(listActivity.this);
		builder.setSingleChoiceItems(new String[]{"查看","修改","删除"},0,new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int which) {
				// TODO Auto-generated method stub
				if(which==0){
					//Cursor cursor=db.query("notepadtable", new String[]{"_id","title","content"}, null, null, null, null, null);
					
					int myidindex=cursor.getColumnIndex("_id");
					int myid=cursor.getInt(myidindex);
					int titleindex=cursor.getColumnIndex("title");
					String title=cursor.getString(titleindex);
					int contentindex=cursor.getColumnIndex("content");
					String content=cursor.getString(contentindex);
					
					Toast.makeText(listActivity.this, myid+title+content, Toast.LENGTH_LONG).show();	
				}else if(which==2){
					int myidindex=cursor.getColumnIndex("_id");
					 int myid=cursor.getInt(myidindex);

					db.delete("notepadtable", "_id="+myid, null);
					
					Cursor cursor=db.query("notepadtable", new String[]{"_id","title","content"}, null, null, null, null, null);
					SimpleCursorAdapter sca=new SimpleCursorAdapter(listActivity.this, R.layout.listview, cursor, new String[]{"_id","title","content"}, new int[]{R.id.textview01,R.id.textview02,R.id.textview03});
					lv.setAdapter(sca);
				}else if(which==1){
				
					
					
					
					Builder builder01=new Builder(listActivity.this);
					
					builder01.setTitle("编辑");
					
					LayoutInflater inflater=LayoutInflater.from(listActivity.this);
					View view=inflater.inflate(R.layout.updatedialogeview, null);
					 et01=(EditText) view.findViewById(R.id.EditText01);
				    et02=(EditText) view.findViewById(R.id.EditText02);
					
					builder01.setView(view);
					builder01.setPositiveButton("确定", new DialogInterface.OnClickListener(){

						public void onClick(DialogInterface dialog, int which) {
							//取得数据
							int idindex=cursor.getColumnIndex("_id");
							int myid=cursor.getInt(idindex);
							//int titleindex=cursor.getColumnIndex("title");
							//title=cursor.getString(titleindex);
							//int contentindex=cursor.getColumnIndex("content");
							//content=cursor.getString(contentindex);
						String newtitle=et01.getText().toString();
						String newcontent=et02.getText().toString();
						ContentValues cv=new ContentValues();
						cv.put("title", newtitle);
						cv.put("content", newcontent);
						db.update("notepadtable", cv, "_id="+myid, null);
						Cursor cursor=db.query("notepadtable", new String[]{"_id","title","content"}, null, null, null, null, null);
						SimpleCursorAdapter sca=new SimpleCursorAdapter(listActivity.this, R.layout.listview, cursor, new String[]{"_id","title","content"}, new int[]{R.id.textview01,R.id.textview02,R.id.textview03});
						lv.setAdapter(sca);
						
						}
						
					});
				
					builder01.setNegativeButton("取消", new DialogInterface.OnClickListener(){

						public void onClick(DialogInterface dialog, int which) {

							
						}
						
					});
					builder01.show();
				}
				
				
				
				}
				
			});
			builder.show();
		}
		
	});
	
	
	}
	
	
}