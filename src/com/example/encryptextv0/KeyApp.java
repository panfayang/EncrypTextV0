/**
 * @author Kevin Davison, Sam Evans-Golden, Fayang Pan
 * 
 * This is the manager keys main class.
 */
package com.example.encryptextv0;

import com.example.encryptextv0.Key_Contract.KeyEntry;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class KeyApp extends Activity {
	private Key_Manager keyManager = new Key_Manager(this);
	private SQLiteDatabase db;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_key_app);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//		 Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.key_app,menu);
		return true;
	}

	public void addKey(View v)
	{
		Intent intent = new Intent(this, Enter_Key.class);
		startActivity(intent);	

	}

	public void viewKey(View v)
	{
		if(checkDb())
		{
			Intent intent = new Intent(this, View_List.class);
			startActivity(intent);
		}	
		else 
			Toast.makeText(KeyApp.this, "Please add a key", Toast.LENGTH_SHORT).show();

	}

	public boolean checkDb()
	{
		try{
			db = keyManager.getReadableDatabase();
			db.rawQuery("SELECT * FROM " + KeyEntry.TABLE_NAME, null);
			String[] keys = {
					KeyEntry.COLUMN_NAME_NAME,
					KeyEntry.COLUMN_NAME_KEY
			};
			Cursor cursor = db.query(KeyEntry.TABLE_NAME, keys, null, null, null, null, null);
			if(cursor != null)
				cursor.moveToFirst();
			cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_NAME));
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
}
