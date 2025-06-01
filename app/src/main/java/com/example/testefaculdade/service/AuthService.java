package com.example.testefaculdade.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.testefaculdade.data.UserRepository;
import com.example.testefaculdade.model.User;

public class AuthService {
    private UserRepository userRepo;

    public AuthService(Context ctx) {
        userRepo = new UserRepository(ctx);
    }

    /** retorna id do novo user, ou -1 em falha */
    public long register(User u) {
        return userRepo.create(u);
    }

    public static long getCurrentUserId(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences("app_prefs", Context.MODE_PRIVATE);
        return prefs.getLong("USER_ID", -1); // Retorna -1 se não encontrar
    }
}
