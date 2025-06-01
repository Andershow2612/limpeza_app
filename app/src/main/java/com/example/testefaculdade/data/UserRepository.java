package com.example.testefaculdade.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;

import com.example.testefaculdade.model.User;

public class UserRepository {
    private final DbHelper dbHelper;

    public UserRepository(Context ctx) {
        dbHelper = new DbHelper(ctx);
    }

    /**
     * Insere o usuário e retorna o ID gerado, ou -1 se falhar.
     */
    public long create(User u) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("email", u.getEmail());
        cv.put("password", u.getPassword());
        cv.put("address", u.getAddress());
        cv.put("neighborhood", u.getNeighborhood());
        cv.put("city", u.getCity());
        cv.put("state", u.getState());
        long newId = db.insert("users", null, cv);
        db.close();
        return newId;
    }

    public User login(String email, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(
                "users", null,
                "email=? AND password=?",
                new String[]{ email, password },
                null, null, null
        );
        if (!c.moveToFirst()) {
            c.close();
            db.close();
            return null;
        }
        User u = new User();
        u.setId(c.getLong(c.getColumnIndexOrThrow("id")));
        u.setEmail(c.getString(c.getColumnIndexOrThrow("email")));
        u.setAddress(c.getString(c.getColumnIndexOrThrow("address")));
        u.setNeighborhood(c.getString(c.getColumnIndexOrThrow("neighborhood")));
        u.setCity(c.getString(c.getColumnIndexOrThrow("city")));
        u.setState(c.getString(c.getColumnIndexOrThrow("state")));
        c.close();
        db.close();
        return u;
    }

    public User findById(long id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.query(
                "users", null,
                "id=?",
                new String[]{ String.valueOf(id) },
                null, null, null
        );
        if (!c.moveToFirst()) {
            c.close();
            db.close();
            return null;
        }
        User u = new User();
        u.setId(c.getLong(c.getColumnIndexOrThrow("id")));
        u.setEmail(c.getString(c.getColumnIndexOrThrow("email")));
        u.setPassword(c.getString(c.getColumnIndexOrThrow("password")));
        u.setAddress(c.getString(c.getColumnIndexOrThrow("address")));
        u.setNeighborhood(c.getString(c.getColumnIndexOrThrow("neighborhood")));
        u.setCity(c.getString(c.getColumnIndexOrThrow("city")));
        u.setState(c.getString(c.getColumnIndexOrThrow("state")));
        c.close();
        db.close();
        return u;
    }

}
