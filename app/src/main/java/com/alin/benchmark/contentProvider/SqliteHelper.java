package com.alin.benchmark.contentProvider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper{

	protected final static String dropTable = "drop table if exists ";
	
	public SqliteHelper(Context context) {
		super(context, "provider.db", null, 5);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		System.out.println("db onCreate()");
//		db.execSQL("PRAGMA foreign_keys = ON");
		String sql = "create table users (id integer primary key autoincrement,name varchar(32),phone varchar(32))";
		db.execSQL(sql);
		System.out.println("create tables ok");
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		System.out.println(  " onUpgrade() oldVersion:" + oldVersion
		        + " newVersion:" + newVersion);
		db.execSQL(dropTable + "users");
		onCreate(db);
	}

}
