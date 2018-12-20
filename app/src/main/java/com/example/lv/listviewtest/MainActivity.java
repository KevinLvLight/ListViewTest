package com.example.lv.listviewtest;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/** 题目：编写一个安卓应用，要求：界面包含一个listview，其显示内容为本学期本人选修的课程名
 * 点击每一提条目则以用户自定义对话框的形式显示科目相关内容（课程编号、学分数、授课老师、上课时间、教师）
 *
 * 处理方法：利用SQLite数据库存储课程信息，在点击后通过数据查询，再利用对话框显示
 */
public class MainActivity extends AppCompatActivity {

    private MyDataBaseHelper dataBaseHelper;

    //自定义函数，来完成将课程内容插入到数据库表内
    private void initLesson(MyDataBaseHelper dataBaseHelper) {

        SQLiteDatabase db = dataBaseHelper.getWritableDatabase();//写
        ContentValues values = new ContentValues();

        values.put("lessonnum", "B0200013S");
        values.put("lessonname", "通信原理C");
        values.put("score", 3.0);
        values.put("teacher", "何雪云");
        values.put("time", "周三第3、4节（2-18双周）；周五第6、7节（1-18周）");
        values.put("classroom", "4-308；4-413");
        db.insert("Lesson", null, values);//插入信息
        values.clear();//清空values方便下次插入，下同

        values.put("lessonnum", "B1801531C");
        values.put("lessonname", "Windows编程");
        values.put("score", 2);
        values.put("teacher", "陈松乐");
        values.put("time", "周二第3、4节（1-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1801581C");
        values.put("lessonname", "移动终端应用开发技术");
        values.put("score", 2.0);
        values.put("teacher", "王亚石");
        values.put("time", "周四第6、7节（1-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B22000K3S");
        values.put("lessonname", "统计学B");
        values.put("score", 3.0);
        values.put("teacher", "徐斌");
        values.put("time", "周二第1、2节（2-18双周）；周五第1、2节（1-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "00wk00010");
        values.put("lessonname", "大学生创业概论与实践（在线）");
        values.put("score", 2.0);
        values.put("teacher", "刘树森");
        values.put("time", "无");
        values.put("classroom", "无");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1801241S");
        values.put("lessonname", "软件工程（双语）");
        values.put("score", 3.0);
        values.put("teacher", "赵莎莎");
        values.put("time", "周一第3、4节（2-18双周）；周四第3、4节（1-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1801291S");
        values.put("lessonname", "操作系统原理");
        values.put("score", 3.5);
        values.put("teacher", "王晓军");
        values.put("time", "周一第6、7节（1-18双周）；周三第6、7节（1-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1801381S");
        values.put("lessonname", "计算机网络");
        values.put("score", 3.5);
        values.put("teacher", "康彬");
        values.put("time", "周一第1、2节（1-18双周）；周四第1、2节（1-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1801511S");
        values.put("lessonname", "汇编语言程序设计");
        values.put("score", 2);
        values.put("teacher", "闵丽娟");
        values.put("time", "周二第6、7节（1-8双周）；周五第3、4节（1-8周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1801521S");
        values.put("lessonname", "微型计算机接口技术");
        values.put("score", 2);
        values.put("teacher", "闵丽娟");
        values.put("time", "周二第6、7节（9-18双周）；周五第3、4节（9-18周）");
        values.put("classroom", "4-308");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B1861011C");
        values.put("lessonname", "软件开发实践");
        values.put("score", 2);
        values.put("teacher", "许斌");
        values.put("time", "无");
        values.put("classroom", "无");
        db.insert("Lesson", null, values);
        values.clear();

        values.put("lessonnum", "B2110023S");
        values.put("lessonname", "职业发展与就业指导");
        values.put("score", 1);
        values.put("teacher", "周健");
        values.put("time", "周三第8、9节（9-18双周）");
        values.put("classroom", "4-110");
        db.insert("Lesson", null, values);
        values.clear();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //建立数据库，名字为ShowLesson，版本号为1
        dataBaseHelper = new MyDataBaseHelper(this, "ShowLesson.db", null, 1);
        initLesson(dataBaseHelper);//调用自定义函数来完成信息插入

        SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();
        //listview要求每项显示的为课程名称，则从数据库中查询出课程名称并通过适配器将其展示在listview内
        String sql = "select _id,lessonname from Lesson";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                MainActivity.this,
                R.layout.item_lesson,
                cursor,
                new String[]{"lessonname"},
                new int[]{R.id.lesson_name},
                0);//适配器
        final ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(simpleCursorAdapter);

        //listview每项注册监听器，点击弹出对话框
        listView.setOnItemClickListener(new ItemClickListener());
    }

    private final class ItemClickListener implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ListView listView = (ListView) parent;
            Cursor cursor = (Cursor) listView.getItemAtPosition(position);
            String personid = String.valueOf(cursor.getInt(cursor.getColumnIndex("_id")));
            SQLiteDatabase sqLiteDatabase = dataBaseHelper.getReadableDatabase();

            Cursor qCursor = sqLiteDatabase.query("Lesson", null, "_id = ?", new String[]{personid}, null, null, null, null);

            while (qCursor.moveToNext()) {
                String lessonname = qCursor.getString(qCursor.getColumnIndex("lessonname"));
                String lessonnum = qCursor.getString(qCursor.getColumnIndex("lessonnum"));
                String score = qCursor.getString(qCursor.getColumnIndex("score"));
                String teacher = qCursor.getString(qCursor.getColumnIndex("teacher"));
                String classroom = qCursor.getString(qCursor.getColumnIndex("classroom"));
                String time = qCursor.getString(qCursor.getColumnIndex("time"));

                //自定义对话框显示课程信息
                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                View viewDialog = LayoutInflater.from(MainActivity.this).inflate(R.layout.dialog_my, null);
                builder.setView(viewDialog);
                final AlertDialog dialog = builder.show();
                final TextView dialog_lessonname = viewDialog.findViewById(R.id.dialog_my_lessonname);
                final TextView dialog_lessonnum = viewDialog.findViewById(R.id.dialog_my_lessonnum);
                final TextView dialog_score = viewDialog.findViewById(R.id.dialog_my_score);
                final TextView dialog_teacher = viewDialog.findViewById(R.id.dialog_my_teacher);
                final TextView dialog_classroom = viewDialog.findViewById(R.id.dialog_my_classroom);
                final TextView dialog_time = viewDialog.findViewById(R.id.dialog_my_time);
                final Button button = viewDialog.findViewById(R.id.dialog_my_button);
                dialog_lessonname.setText(lessonname);
                dialog_lessonnum.setText("课程编号：" + lessonnum);
                dialog_score.setText("课程学分：" + score);
                dialog_teacher.setText("教师姓名：" + teacher);
                dialog_classroom.setText("上课教室：" + classroom);
                dialog_time.setText("上课时间：" + time);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                qCursor.close();
            }
        }
    }
}




