package com.example.varatiamanagement.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.varatiamanagement.model.Properties;
import com.example.varatiamanagement.repository.PropertiesRepository;

import java.util.List;

public class PropertiesViewModel extends AndroidViewModel {

    private PropertiesRepository propertiesRepository;
    private LiveData<List<Properties>> listLiveData;


    public PropertiesViewModel(@NonNull Application application) {
        super(application);
        propertiesRepository = new PropertiesRepository(application);
        listLiveData = propertiesRepository.getAllproperties();
    }

    public void insert(Properties properties){
        propertiesRepository.insert(properties);
    }

    public void update(Properties properties){
        propertiesRepository.update(properties);
    }

    public void delete(Properties properties){
        propertiesRepository.delete(properties);
    }



    public LiveData<List<Properties>> getListLiveData(){
        return listLiveData;
    }
}
