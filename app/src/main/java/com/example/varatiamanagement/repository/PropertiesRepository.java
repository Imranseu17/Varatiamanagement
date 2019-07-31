package com.example.varatiamanagement.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.varatiamanagement.dao.PropertiesDao;
import com.example.varatiamanagement.database.PropertiesDatabase;
import com.example.varatiamanagement.model.Properties;

import java.util.List;

public class PropertiesRepository {

    private PropertiesDao propertiesDao;
    private LiveData<List<Properties>> allproperties;

    public PropertiesRepository(Application application){

        PropertiesDatabase propertyDatabase =
                PropertiesDatabase.getInstance(application);
        propertiesDao = propertyDatabase.getPropertiesDao();
        allproperties = propertiesDao.getAllProperties();
    }

    public void insert(Properties properties){
        new PropertiesRepository.InsertNoteAsyncTask(propertiesDao).
                execute(properties);
    }

    public void update(Properties properties){
        new PropertiesRepository.UpdateNoteAsyncTask
                (propertiesDao).execute(properties);
    }

    public void delete(Properties properties){
        new PropertiesRepository.DeleteNoteAsyncTask
                (propertiesDao).execute(properties);
    }



    public LiveData<List<Properties>> getAllproperties(){
        return allproperties;
    }


    private static class InsertNoteAsyncTask extends
            AsyncTask<Properties,Void,Void> {

        private PropertiesDao dao;

        public InsertNoteAsyncTask(PropertiesDao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Properties... properties) {
            dao.insert(properties[0]);
            return null;
        }
    }


    private static class UpdateNoteAsyncTask extends
            AsyncTask<Properties,Void,Void>{

        private PropertiesDao propertyDao;

        public UpdateNoteAsyncTask(PropertiesDao propertyDao) {
            this.propertyDao = propertyDao;
        }

        @Override
        protected Void doInBackground(Properties... properties) {
            propertyDao.update(properties[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends
            AsyncTask<Properties, Void, Void> {
        private PropertiesDao propertyDao;

        private DeleteNoteAsyncTask(PropertiesDao propertyDao) {
            this.propertyDao = propertyDao;
        }

        @Override
        protected Void doInBackground(Properties... properties) {
            propertyDao.delete(properties[0]);
            return null;
        }
    }
}
