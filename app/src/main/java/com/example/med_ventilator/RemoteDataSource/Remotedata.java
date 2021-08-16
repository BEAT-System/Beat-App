package com.example.med_ventilator.RemoteDataSource;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.med_ventilator.Model.VitalData_Model;
import com.example.med_ventilator.Model.Device_Model;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Remotedata {

    final private List<Device_Model> _deviceList = new ArrayList();

    public void data() {

        Device_Model _newdevice = new Device_Model(100);
        VitalData_Model _newVitalData = new VitalData_Model(1, 1, 1, 1, 1, 1, 1, 100);
        _newdevice.addData(_newVitalData);
        _deviceList.add(_newdevice);
    }

    public void data2() {

        for(int i = 0; i <5; i++) {
            int deviceID = 100+i;
            Device_Model _newdevice = selectDevice(deviceID);

            if(_newdevice ==null){

                _newdevice = new Device_Model(100 +i);
                Log.e("Remotedata", "data2: Device dont exist, create a new device, with ID"+ _newdevice.get_deviceId());

                for(int ii=0; ii<=10; ii++) {

                    VitalData_Model _newVitalData = new VitalData_Model(1+ii, 1+ii, 1+ii, 1+i, 1+i, 1+i, 1+i, 100 + i);
                    _newdevice.addData(_newVitalData);
                }
                _deviceList.add(_newdevice);

            }else{
                Log.e("Remotedata", "data2: Device exist, with ID"+ _newdevice.get_deviceId());
                for(int ii=0; ii<=10; ii++) {

                    VitalData_Model _newVitalData = new VitalData_Model(1+ii, 1+ii, 1+ii, 1+i, 1+i, 1+i, 1+i, 100 + i);
                    _newdevice.addData(_newVitalData);
                }
            }
        }
    }

    public LiveData<List<Device_Model>> liveDataListDevices() {
        final MutableLiveData<List<Device_Model>> _mutableLiveData = new MutableLiveData<>();
        Log.e("Remotedata", "liveDataListDevices: " + _deviceList.size());
         _mutableLiveData.setValue(_deviceList);
        return _mutableLiveData;
    }

    public LiveData<Device_Model> liveDataDevice(int deviceID) {
        Log.e("RemoteData", "liveDataDevice: Listdevice size "+_deviceList.size() );
        final MutableLiveData<Device_Model> _mutableLiveData = new MutableLiveData<>();
                Device_Model device = selectDevice(deviceID);
                _mutableLiveData.setValue(device);
        return _mutableLiveData;
    }

    @Nullable
    private Device_Model selectDevice(int deviceID) {
        Log.e("RemoteData", "selectDevice: "+ deviceID+ " length of deviceList "+_deviceList.size());
        for (Device_Model _device : _deviceList) {
            if(_device.get_deviceId() == deviceID){
                return _device;
            }
        }
        return null;
    }

}
