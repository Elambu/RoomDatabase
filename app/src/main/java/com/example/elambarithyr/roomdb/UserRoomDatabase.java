package com.example.elambarithyr.roomdb;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = EntityDb.class, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {


    public abstract UserDAO userDAO();
    private static UserRoomDatabase INSTANCE;

    static UserRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class, "user_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private  UserDAO mDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mDao = db.userDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            EntityDb user = new EntityDb();
            user.setUserName("Elam");
            mDao.insert(user);
            user.setUserName("Maruthi");
            mDao.insert(user);
            user.setUserName("Saravanan");
            mDao.insert(user);
            user.setUserName("Salmon");
            mDao.insert(user);
            return null;
        }
    }
}
