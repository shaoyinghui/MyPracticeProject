package com.example.myapp1;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class edit extends Activity{
	private EditText edit1;
	private EditText edit2;
	private Button queding;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_activity);

		edit1 = (EditText)findViewById(R.id.zhuti_edittext);
		edit2 = (EditText)findViewById(R.id.neirong_edittext);
		queding = (Button)findViewById(R.id.queding_button);
		queding.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String mytitle = edit1.getText().toString();		
				String mycontent = edit2.getText().toString();
				MySQLiteHelper mySQLiteHelper;
				mySQLiteHelper = new MySQLiteHelper(edit.this,"notepad.db",null,1);
				SQLiteDatabase db = mySQLiteHelper.getReadableDatabase();
				ContentValues cv = new ContentValues();
				cv.put("title", mytitle);
				cv.put("content", mycontent);
				db.insert("notepadtable", null, cv);
				
			}
		});
		
		
	}
	
}
