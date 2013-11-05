package com.example.encryptextv0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void startEncryptActivity(View view) {
    	Intent intent = new Intent(this, EncryptActivity.class);
    	startActivity(intent);
    }
    
    public void startDecryptActivity(View view) {
    	Intent intent = new Intent(this, DecryptActivity.class);
    	startActivity(intent);
    }
    
    public void startManageKeysActivity(View view) {
    	Intent intent = new Intent(this, ManageKeysActivity.class);
    	startActivity(intent);
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
		Intent intent = new Intent(this, View_Keys.class);
    	startActivity(intent);	
	}
}
