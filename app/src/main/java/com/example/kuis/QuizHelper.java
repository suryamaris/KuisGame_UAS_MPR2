package com.example.kuis;

import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QuizHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "mathsone";
    // tasks table name
    private static final String TABLE_QUEST = "quest";
    // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c

    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
        // db.close();
    }

    private void addQuestion() {
        Question q1 = new Question("kata \"computare\" di ambil dari bahasa?", "Yunani", "Inggris", "Jerman", "Yunani");
        this.addQuestion(q1);
        Question q2 = new Question("Arti dari computare adalah?", "Mengcoding", "Menghitung", "Ngegame", "Menghitung");
        this.addQuestion(q2);
        Question q3 = new Question("Yang bukan microsoft office adalah?", "Microsoft Windows", "Microsoft Excel", "Microsoft Outlook", "Microsoft Windows");
        this.addQuestion(q3);
        Question q4 = new Question("Keyboard merupakan hardware?", "Input Device", "Output Device", "Process Device", "Input Device");
        this.addQuestion(q4);
        Question q5 = new Question("RAM memiliki kepanjangan dari?", "Random Access Memory", "Read Access Memory", "Rock Access Memory", "Random Access Memory");
        this.addQuestion(q5);
        Question q6 = new Question("Yang bukan merupakan perangkat masukan  (input device) adalah?", "Keyboard", "Mic", "Monitor", "Monitor");
        this.addQuestion(q6);
        Question q7 = new Question("Sistem operasi yang hanya dapat dijalankan pada komputer jenis Apple, yakni?", "Windows", "Linux", "Macintosh", "Macintosh");
        this.addQuestion(q7);
        Question q8 = new Question("Komponen yang berfungsi untuk membentuk fungsi-fungsi pengolahan data komputer adalah?", "Control Unit", "Arithmetic And Logic Unit", "Register", "Arithmetic And Logic Unit");
        this.addQuestion(q8);
        Question q9 = new Question("Bagian dari CPU  yang berfungsi  menghubungkan seluruh bagian dari CPU  tersebut adalah?", "Control Unit", "CPU Interconnection", "I/O", "CPU Interconnection");
        this.addQuestion(q9);
        Question q10 = new Question("Pimpinan unit yang bertanggung jawab atas keseluruhan proses berlangsungnya pekerjaan komputer, yang merupakan pejabat eselon tertinggi di bidang IT?", "Data Processing Manager", "System Analyst", "Data Entry Operator", "Data Processing Manager");
        this.addQuestion(q10);
        Question q11 = new Question("ROM adalah kepanjangan dari ?", "read only memory", "read online memory", "reading online manga", "read only memory");
        this.addQuestion(q11);
        Question q12 = new Question("sistem komputer memiliki 3 unsur yaitu ?", "Monitor,CPU,Pengguna", "software,hardware,brainware", "Brand,Spec,Harga", "software,hardware,brainware");
        this.addQuestion(q12);
        Question q13 = new Question("Alat untuk menampilkan data softcopy ke hardcopy adalah ?", "Scanner", "Speaker", "Printer", "Printer");
        this.addQuestion(q13);
        Question q14 = new Question("Kepanjangan CPU adalah ?", "Central Processing Unit", "Central Progressing Unit", "Control Processing Unit", "Central Processing Unit");
        this.addQuestion(q14);
        Question q15 = new Question("Kepanjangan USB?", "Unit System Bus", "Universal Serial Bus", "Unit Serial Bug", "Universal Serial Bus");
        this.addQuestion(q15);
        Question q16 = new Question("Kepanjangan ALU?", "Antartic and Lebanon Unit", "Ah Lupa Uda", "Arithmetic and Logic Unit", "Arithmetic and Logic Unit");
        this.addQuestion(q16);
        Question q17 = new Question("Kepanjangan GPU?", "Graphic Processing Unit", "Google Processing Unit", "Grafis Processing Unit", "Graphic Processing Unit");
        this.addQuestion(q17);
        Question q18 = new Question("GPU/kartu grafis dari Nvidia adalah?", "Nvidia Radeon", "Nvidia Geforce", "Nvidia UHD Family", "Nvidia Geforce");
        this.addQuestion(q18);
        Question q19 = new Question("Komputer generasi pertama muncul tahun?", "1999", "1940", "2020", "1940");
        this.addQuestion(q19);
        Question q20 = new Question("Sistem bilangan biner adalah?", "Bilangan terdiri dari 0 sampai 14", "Bilangan terdiri dari 0 sampai 9", "Bilangan terdiri dari 0 dan 1", "Bilangan terdiri dari 0 dan 1");
        this.addQuestion(q20);
        Question q21 = new Question("5-4 = ?", "Bilangan terdiri dari 0 sampai 14", "Bilangan terdiri dari 0 sampai 9", "Bilangan terdiri dari 0 dan 1", "Bilangan terdiri dari 0 dan 1");
        this.addQuestion(q21);
        // END
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);
    }

    // Adding new question
    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getANSWER());
        values.put(KEY_OPTA, quest.getOPTA());
        values.put(KEY_OPTB, quest.getOPTB());
        values.put(KEY_OPTC, quest.getOPTC());

        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));

                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

}

