package com.example.sev_user.demo5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by sev_user on 6/29/2018.
 */
public class MyDB extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MY_DATABASE";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "FRIEND";
    private static final String ID = "ID";
    private static final String NAME = "NAME";
    private static final String BIRTH_DAY = "BIRTH_DAY";
    private static final String PHONE = "PHONE";
    private static final String EMAIL = "EMAIL";
    private static final String ADDRESS = "ADDRESS";
    private static final String AVATAR = "AVATAR";
    private static final String SFRIEND = "SFRIEND";

    public MyDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " ( " +
                ID + " integer primary key autoincrement, " +
                NAME + " text, " +
                BIRTH_DAY + " text, " +
                PHONE + " text, " +
                EMAIL + " text, " +
                ADDRESS + " text, " +
                AVATAR + " text, " +
                SFRIEND + " integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addFriend(Friend fr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, fr.getName());
        contentValues.put(BIRTH_DAY, fr.getBirthday());
        contentValues.put(PHONE, fr.getPhone());
        contentValues.put(EMAIL, fr.getEmail());
        contentValues.put(ADDRESS, fr.getAddress());
        contentValues.put(AVATAR, fr.getAvata());
        contentValues.put(SFRIEND, fr.getsFriend());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
    }


    public ArrayList<Friend> getFriend(int type) {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Friend> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE  " + SFRIEND  + " = " + type;
        Cursor cr = db.rawQuery(sql,null);
        if (cr.moveToFirst()) {
            do {
                Friend aFriend = new Friend();
                aFriend.setId(cr.getInt(0));
                aFriend.setName(cr.getString(1));
                aFriend.setBirthday(cr.getString(2));
                aFriend.setPhone(cr.getString(3));
                aFriend.setEmail(cr.getString(4));
                aFriend.setAddress(cr.getString(5));
                aFriend.setAvata(cr.getString(6));
                aFriend.setsFriend(cr.getInt(7));
                list.add(aFriend);
            }
            while (cr.moveToNext());
        }
        return list;
    }

    public void updateFriend(Friend fr) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, fr.getName());
        contentValues.put(BIRTH_DAY, fr.getBirthday());
        contentValues.put(PHONE, fr.getPhone());
        contentValues.put(EMAIL, fr.getEmail());
        contentValues.put(ADDRESS, fr.getAddress());
        contentValues.put(AVATAR, fr.getAvata());
        contentValues.put(SFRIEND, fr.getsFriend());
        db.update(TABLE_NAME, contentValues, ID + " = ? ",new String[]{String.valueOf(fr.getId())});
    }

    public void deleteFriend (Friend fr)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, ID + " = ? ", new String[]{String.valueOf(fr.getId())});
    }
}