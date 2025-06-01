package com.example.testefaculdade.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "cleanapp.db";
    private static final int DB_VERSION = 4;

    private static final String CREATE_USERS =
            "CREATE TABLE users (" +
                    "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "  email TEXT UNIQUE NOT NULL," +
                    "  password TEXT NOT NULL," +
                    "  address TEXT," +
                    "  neighborhood TEXT," +
                    "  city TEXT," +
                    "  state TEXT," +
                    "  created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    "  updated_at DATETIME DEFAULT CURRENT_TIMESTAMP" +
                    ");";

    private static final String CREATE_PEDIDOS =
            "CREATE TABLE pedidos (" +
                    "  id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "  address TEXT NOT NULL," +
                    "  city TEXT," +
                    "  neighborhood TEXT," +
                    "  reference TEXT," +
                    "  description TEXT," +
                    "  user_id INTEGER NOT NULL," +
                    "  created_at DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    "  FOREIGN KEY(user_id) REFERENCES users(id) ON DELETE CASCADE" +
                    ");";

    public DbHelper(Context ctx) {
        super(ctx, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERS);
        db.execSQL(CREATE_PEDIDOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS pedidos");
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean apagarPedido(long idPedido) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("pedidos", "id = ?", new String[]{ String.valueOf(idPedido) });
        db.close();
        return rowsAffected > 0;
    }
}
