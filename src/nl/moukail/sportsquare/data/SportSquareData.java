package nl.moukail.sportsquare.data;

import static android.provider.BaseColumns._ID;
import static nl.moukail.sportsquare.data.Constants.TABLE_NAME;
import static nl.moukail.sportsquare.data.Constants.TABLE_NAME_DAG;
import static nl.moukail.sportsquare.data.Constants.TABLE_NAME_TRAINING;
import static nl.moukail.sportsquare.data.Constants.TABLE_NAME_OPDRACHT;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SportSquareData extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "sportsquare.db";
	private static final int DATABASE_VERSION = 10;
	public SportSquareData(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE "+TABLE_NAME+" (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT NOT NULL, VISITORS INTEGER, ADRESS TEXT NOT NULL, CITY TEXT NOT NULL, LATITUDE TEXT NOT NULL, LONGITUDE TEXT NOT NULL);");
		db.execSQL("CREATE TABLE "+TABLE_NAME_DAG+" (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ID INTEGER NOT NULL, DAGNAAM STRING NOT NULL);");
		db.execSQL("CREATE TABLE "+TABLE_NAME_TRAINING+" (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ID INTEGER NOT NULL, DAGNUMMER INTEGER NOT NULL);");
		db.execSQL("CREATE TABLE "+TABLE_NAME_OPDRACHT+" (" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, ID INTEGER NOT NULL, NAME TEXT NOT NULL, SERIE INTEGER NOT NULL, HERHAAL INTEGER NOT NULL, TRAINING_ID INTEGER NOT NULL);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DAG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_TRAINING);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_OPDRACHT);
	      onCreate(db);
		
	}

}
