package com.example.encryptextv0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class KeyApp extends Activity {
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
	
	public void deleteKey(View v)
	{
		Intent intent = new Intent(this, Delete_Keys.class);
    	startActivity(intent);	
   	}
	
	public void viewKey(View v)
	{
		Intent intent = new Intent(this, View_List.class);
    	startActivity(intent);	

	}
}
