package com.example.encryptextv0;

import android.provider.BaseColumns;

public class Key_Contract {
	
	public Key_Contract(){}
	public static abstract class KeyEntry implements BaseColumns
	{
		public static final String DB_NAME = "KeyEntry.db";
		public static final String TABLE_NAME = "Key_Manager";
		public static final String TABLE_KEY = "keys";
		public static final String COLUMN_NAME_NAME = "name";		
		public static final String KEY_ID = "id";
		public static final String COLUMN_NAME_KEY = "key";
		
	}

}
