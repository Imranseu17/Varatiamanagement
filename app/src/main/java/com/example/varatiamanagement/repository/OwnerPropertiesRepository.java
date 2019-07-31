package com.example.varatiamanagement.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;


import com.example.varatiamanagement.dao.OwnerPropertyDao;
import com.example.varatiamanagement.database.OwnerPropertyDatabase;
import com.example.varatiamanagement.model.OwnerProperty;

import java.util.List;

public class OwnerPropertiesRepository {

    private OwnerPropertyDao ownerPropertyDao;
    private LiveData<List<OwnerProperty>> allproperties;

    public OwnerPropertiesRepository(Application application){

        OwnerPropertyDatabase ownerPropertyDatabase =
                OwnerPropertyDatabase.getInstance(application);
        ownerPropertyDao = ownerPropertyDatabase.getOwnerPropertyDao();
        allproperties = ownerPropertyDao.getAllOwnerProperties();
    }

    public void insert(OwnerProperty ownerProperty){
          new InsertNoteAsyncTask(ownerPropertyDao).execute(ownerProperty);
    }

    public void update(OwnerProperty ownerProperty){
            new UpdateNoteAsyncTask(ownerPropertyDao).execute(ownerProperty);
    }

    public void delete(OwnerProperty ownerProperty){
        new DeleteNoteAsyncTask(ownerPropertyDao).execute(ownerProperty);
    }



    public LiveData<List<OwnerProperty>> getAllproperties(){
        return allproperties;
    }


    private static class InsertNoteAsyncTask extends AsyncTask<OwnerProperty,Void,Void>{

        private OwnerPropertyDao dao;

        public InsertNoteAsyncTask(OwnerPropertyDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(OwnerProperty... notes) {
            dao.insert(notes[0]);
            return null;
        }
    }


    private static class UpdateNoteAsyncTask extends AsyncTask<OwnerProperty,Void,Void>{

        private OwnerPropertyDao propertyDao;

        public UpdateNoteAsyncTask(OwnerPropertyDao propertyDao) {
            this.propertyDao = propertyDao;
        }

        @Override
        protected Void doInBackground(OwnerProperty... ownerProperties) {
            propertyDao.update(ownerProperties[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<OwnerProperty, Void, Void> {
        private OwnerPropertyDao propertyDao;

        private DeleteNoteAsyncTask(OwnerPropertyDao propertyDao) {
            this.propertyDao = propertyDao;
        }

        @Override
        protected Void doInBackground(OwnerProperty... properties) {
            propertyDao.delete(properties[0]);
            return null;
        }
    }





}
