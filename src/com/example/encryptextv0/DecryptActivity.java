package com.example.encryptextv0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;

public class DecryptActivity extends FragmentActivity {
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
		
		DialogFragment dialog = new SenderNameFragment();
		dialog.show(getSupportFragmentManager(), "senderDialog");
		
		//Intent intent = new Intent(this, DecryptedActivity.class);
		//startActivity(intent);
	}
	
}
