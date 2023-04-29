package com.example.lineage5.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.lineage5.Database.RelationDao;
import com.example.lineage5.Database.UserDao;
import com.example.lineage5.ProjectModel;
import com.example.lineage5.Repository.AppRepo;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class UserViewModel extends AndroidViewModel {

    private AppRepo appRepo;

    public UserViewModel(@NonNull Application application) {
        super(application);

        appRepo= new AppRepo(application) {
            @Override
            public UserDao userDao() {
                return null;
            }

            @Override
            public RelationDao relationDao() {
                return null;
            }
        };
    }

    public void insertUser(ProjectModel projectModel){
        appRepo.insertUser(projectModel);
    }

    public void updateUser(ProjectModel projectModel){
        appRepo.updateUser(projectModel);
    }

    public void deleteUser(ProjectModel projectModel){
        appRepo.deleteUser(projectModel);
    }

    public LiveData<List<ProjectModel>> getAllUserFuture() throws ExecutionException,InterruptedException{
        return appRepo.getAllUserLive();
    }

    public LiveData<List<ProjectModel>> getAllUserLive(){
        return appRepo.getAllUserLive();
    }
}