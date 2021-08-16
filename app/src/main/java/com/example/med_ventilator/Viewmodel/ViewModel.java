package com.example.med_ventilator.Viewmodel;
import android.app.Application;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.med_ventilator.RemoteDataSource.Remotedata;
import com.example.med_ventilator.Model.Device_Model;
import java.util.List;

public class ViewModel extends AndroidViewModel {

    private Remotedata _remotedata;
    private LiveData<List<Device_Model>> _alldevicesLiveData;
    private LiveData<Device_Model> _deviceLiveData;
    public boolean activity = true;

    public ViewModel(@NonNull Application application)
    {
        super(application);
        _remotedata = new Remotedata();
        _remotedata.data();
        _remotedata.data2();
    }

    public LiveData<List<Device_Model>> getDeviceListLiveData(){
        if(_alldevicesLiveData == null) {
            _alldevicesLiveData = _remotedata.liveDataListDevices();
        }
        return _alldevicesLiveData;
    }

    public LiveData<Device_Model> getDeviceLiveData(int deviceID)
    {

        if(_deviceLiveData == null){
            Log.e("ViewModelClass", "getDeviceLiveData: Device data is null");
            _deviceLiveData = _remotedata.liveDataDevice(deviceID);
        }
        return _deviceLiveData;
        }






}
