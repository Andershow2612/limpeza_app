package com.example.testefaculdade.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.testefaculdade.model.Pedido;

import java.util.ArrayList;
import java.util.List;

public class PedidoRepository {
    private final DbHelper dbHelper;

    public PedidoRepository(Context context) {
        dbHelper = new DbHelper(context);
    }

    public long create(Pedido pedido) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("address", pedido.getAddress());
        values.put("city", pedido.getCity());
        values.put("neighborhood", pedido.getNeighborhood());
        values.put("reference", pedido.getReference());
        values.put("description", pedido.getDescription());
        values.put("user_id", pedido.getUserId());
        values.put("created_at", pedido.getCreatedAt());

        long id = db.insert("pedidos", null, values);
        db.close();
        return id;
    }

    public List<Pedido> buscarPedidosPorUserId(long userId) {
        List<Pedido> pedidos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM pedidos WHERE user_id = ? ORDER BY created_at DESC",
                new String[]{String.valueOf(userId)}
        );

        if (cursor.moveToFirst()) {
            do {
                Pedido pedido = new Pedido();
                pedido.setId(cursor.getLong(cursor.getColumnIndexOrThrow("id")));
                pedido.setAddress(cursor.getString(cursor.getColumnIndexOrThrow("address")));
                pedido.setCity(cursor.getString(cursor.getColumnIndexOrThrow("city")));
                pedido.setNeighborhood(cursor.getString(cursor.getColumnIndexOrThrow("neighborhood")));
                pedido.setReference(cursor.getString(cursor.getColumnIndexOrThrow("reference")));
                pedido.setDescription(cursor.getString(cursor.getColumnIndexOrThrow("description")));
                pedido.setUserId(cursor.getLong(cursor.getColumnIndexOrThrow("user_id")));
                pedido.setCreatedAt(cursor.getString(cursor.getColumnIndexOrThrow("created_at")));

                pedidos.add(pedido);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return pedidos;
    }
}
