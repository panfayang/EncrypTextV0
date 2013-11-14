package com.example.encryptextv0;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Enter_Key extends Activity {

	EditText name;
	EditText key;
	private Key_Manager keyManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_enter__key);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.enter__key, menu);
		return true;
	}
	
	public void addAKey(View v)
	{
			addKeyToDb();	
			Intent intent = new Intent(this, View_List.class);
	    	startActivity(intent);	
	}
	
	public void addKeyToDb()
	{	
		
		name =((EditText)findViewById(R.id.enterName));
		key = ((EditText)findViewById(R.id.PasteKeyEdit));
		keyManager = new Key_Manager(this);
		SQLiteDatabase db = keyManager.getWritableDatabase();
		keyManager.addKey(name.getText().toString(), key.getText().toString()); 
		keyManager.close();
		db.close();
	}
	
	
	
	
}
