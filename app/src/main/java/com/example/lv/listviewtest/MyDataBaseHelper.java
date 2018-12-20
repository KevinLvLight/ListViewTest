package com.example.lv.listviewtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/***
 * 数据库实现的类
 */
public class MyDataBaseHelper extends SQLiteOpenHelper{

    // 建表语句定义为String
    public static final String CREATE_LESSON = "create table Lesson("
            + "_id integer primary key autoincrement, "
            + "lessonnum text,"
            + "lessonname text, "
            + "score text, "
            + "teacher text, "
            + "time text, "
            + "classroom text)";

    private Context mContext;

    public MyDataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LESSON);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
