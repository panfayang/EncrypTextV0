package com.example.encryptextv0;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.encryptextv0.Key_Contract.KeyEntry;

public class Delete_Keys extends Activity {
	
	private Key_Manager keyManager; 
	EditText delete; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delete__keys);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.delete__keys, menu);
		return true;
	}

	public void deleteKey(String name)
	{
		delete = (EditText)findViewById(R.id.enterDelete);
		name = delete.getText().toString();
		keyManager = new Key_Manager(this);
		SQLiteDatabase db = keyManager.getWritableDatabase();
		try{
				db.delete(KeyEntry.TABLE_KEY, "Key name = ?", new String[]{name});
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			db.close();
		}		
	}
	
	public void deleteButton(View v, String name)
	{
		keyManager = new Key_Manager(this);
		deleteKey(name);
		
	}

}
