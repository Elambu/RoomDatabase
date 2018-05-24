package com.example.elambarithyr.roomdb;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private UserRepositary userRepositary;
    private LiveData<List<EntityDb>> allUsers;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepositary = new UserRepositary(application);
        allUsers = userRepositary.getAllUsers();
    }

    LiveData<List<EntityDb>> getAllWords() { return allUsers; }

    public void insert(EntityDb entityDb) {
        userRepositary.insert(entityDb);
    }

    public void delete(String userName){
        userRepositary.delete(userName);
    }

    public void deleteAll(){
        userRepositary.deleteAll();
    }
}
