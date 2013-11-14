package com.example.encryptextv0;

import java.util.ArrayList;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.encryptextv0.Key_Contract.KeyEntry;

public class View_List extends Activity {
	
	private SQLiteDatabase db;
	private ArrayList<String> keyList = new ArrayList<String>();	
	ListView list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view__list);
		list = (ListView) findViewById(R.id.ListView1);
		openDatabase();
		list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keyList));
		
	}
	
	private void openDatabase()
	{
		try{
		Key_Manager key_Manager = new Key_Manager(this.getApplicationContext());
		db = key_Manager.getWritableDatabase();
		String[] keys = {
				KeyEntry.COLUMN_NAME_NAME,
				KeyEntry.COLUMN_NAME_KEY
				};
		Cursor cursor = db.query(KeyEntry.TABLE_NAME, keys, null, null, null, null, null);
		if(cursor != null)
			if(cursor.moveToFirst())
			{
				do
				{
				String keyName = cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_NAME));
				String keyKey = cursor.getString(cursor.getColumnIndex(KeyEntry.COLUMN_NAME_KEY));
				keyList.add(keyName + "\n" + keyKey);
				}while(cursor.moveToNext());
			}
			}
		catch (SQLiteException se)
		{
			Log.e("could not create key", null);
			
		}
		
		
		}
	
	
	
	
	//create a key

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view__list, menu);
		return true;
	}

}
