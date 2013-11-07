package com.example.encryptextv0;


import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.algo.KeyParser;
public class ManageKeysActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manage_keys);
//		onClick(null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manage_keys, menu);
		return true;
	}
	
	public String newKey(){
		KeyParser kp = new KeyParser();
		return kp.parseToString(kp.randomKeyGenerator());
	}
	
	public void onClick (View view){
		((TextView) findViewById(R.id.viewKeyTV)).setText(newKey());
	}
	

}
