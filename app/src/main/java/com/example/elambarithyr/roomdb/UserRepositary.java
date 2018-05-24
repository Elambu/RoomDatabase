package com.example.elambarithyr.roomdb;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class UserRepositary {

    private UserDAO userDAO;
    private LiveData<List<EntityDb>> allUsers;

    public UserRepositary(Application application){

        UserRoomDatabase db= UserRoomDatabase.getDatabase(application);
        this.userDAO = db.userDAO();
        this.allUsers = userDAO.getAllUserName();
    }

    LiveData<List<EntityDb>> getAllUsers() {
        return allUsers;
    }

    public void insert (EntityDb entityDb) {
        new insertAsyncTask(userDAO).execute(entityDb);
    }

    public void delete (String userName) {
        new deletetAsyncTask(userDAO).execute(userName);
    }

    public void deleteAll () {
        new deleteAllAsyncTask(userDAO).execute();
    }

    private class insertAsyncTask extends AsyncTask<EntityDb, Void, Void> {

        private UserDAO userDAO;
        public insertAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(EntityDb... entityDbs) {
            userDAO.insert(entityDbs[0]);
            return null;
        }
    }

    private class deletetAsyncTask extends AsyncTask<String, Void, Void> {

        private UserDAO userDAO;
        public deletetAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(String... userName) {
            userDAO.delete(userName[0]);
            return null;
        }
    }

    private class deleteAllAsyncTask extends AsyncTask<LiveData<List<EntityDb>>, Void, Void> {

        private UserDAO userDAO;
        public deleteAllAsyncTask(UserDAO userDAO) {
            this.userDAO = userDAO;
        }

        @Override
        protected Void doInBackground(LiveData<List<EntityDb>>... userName) {
            userDAO.deleteAll();
            return null;
        }


    }
}
