package com.example.testefaculdade.service;

import android.content.Context;

import com.example.testefaculdade.data.UserRepository;
import com.example.testefaculdade.model.User;

public class UserService {
    private final UserRepository repo;

    public UserService(Context ctx) {
        repo = new UserRepository(ctx);
    }

    public long createAccount(User u) {
        // aqui você pode adicionar validações (e-mail válido, tamanho da senha etc.)
        return repo.create(u);
    }

    public User login(String email, String password) {
        return repo.login(email, password);
    }

    public User getById(long id) {
        return repo.findById(id);
    }
}

