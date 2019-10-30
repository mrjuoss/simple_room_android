package com.mrjuoss.lifecycleawaredemo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MainActivityViewModel extends ViewModel {

    private String TAG = this.getClass().getSimpleName();
    private MutableLiveData<String> randomNumber;

    public MutableLiveData<String> getNumber() {
        Log.i(TAG, "Get Random Number");
        if (randomNumber == null) {
            randomNumber = new MutableLiveData<>();
            createRandomNumber();
        }
        return randomNumber;
    }

    public void createRandomNumber() {
        Log.i(TAG, "Create Random Number");

        Random random = new Random();
        randomNumber.setValue("Number " + (random.nextInt(10 - 1) + 1));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");
    }
}
