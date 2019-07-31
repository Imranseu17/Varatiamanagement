package com.example.varatiamanagement.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;


import com.example.varatiamanagement.model.OwnerProperty;
import com.example.varatiamanagement.repository.OwnerPropertiesRepository;

import java.util.List;

public class OwnerPropertyViewModel extends AndroidViewModel {

    private OwnerPropertiesRepository ownerPropertiesRepository;
    private LiveData<List<OwnerProperty>> listLiveData;


    public OwnerPropertyViewModel(@NonNull Application application) {
        super(application);
        ownerPropertiesRepository = new OwnerPropertiesRepository(application);
        listLiveData = ownerPropertiesRepository.getAllproperties();
    }

    public void insert(OwnerProperty ownerProperty){
        ownerPropertiesRepository.insert(ownerProperty);
    }

    public void update(OwnerProperty ownerProperty){
        ownerPropertiesRepository.update(ownerProperty);
    }

    public void delete(OwnerProperty ownerProperty){
        ownerPropertiesRepository.delete(ownerProperty);
    }



    public LiveData<List<OwnerProperty>> getListLiveData(){
        return listLiveData;
    }
}
