package com.example.encryptextv0;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.encryptextv0.Key_Contract.KeyEntry;

public class View_Keys extends Activity {

	Key_Manager keyManager;
	private SQLiteDatabase db;
	TextView showAll;
	public StringBuilder builder; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__keys);
		viewKeys(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__keys, menu);
		return true;
	}
	
	public String showKeys()
	{
		keyManager = new Key_Manager(this);
		db = keyManager.getReadableDatabase();
		
		String[] keys = {
				KeyEntry.COLUMN_NAME_NAME,
				KeyEntry.COLUMN_NAME_KEY
				};
		try
		{
			Cursor cursor = db.query(KeyEntry.TABLE_NAME, keys, null, null, null, null, null);
			StringBuilder sb = new StringBuilder();
			cursor.moveToFirst();
			for(String i: cursor.getColumnNames())
			{
				sb.append(i);
				sb.append("|");
			}
			
			sb.append("/n");
			
			while(!cursor.isAfterLast())
			{
				sb.append(cursor.getString(0));
				sb.append("|");
				sb.append(cursor.getString(1));
				sb.append("/n");
				cursor.moveToNext();
			}
			return sb.toString();
			
		}
		catch(Exception e)
		{
			return "There are no keys. Please add keys in Add Keys";
		}
		
	}
	
	public void viewKeys(View v)
	{
		((TextView) findViewById(R.id.viewAll)).setText(" "+ this.showKeys());
		
	}


	

}
