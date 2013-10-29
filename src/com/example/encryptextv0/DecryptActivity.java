package com.example.encryptextv0;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class DecryptActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_decrypt);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.decrypt, menu);
		return true;
	}

	public void decrypt(View view) {
		//do decryption
		
		Intent intent = new Intent(this, DecryptedActivity.class);
		startActivity(intent);
	}
	
}
