package com.example.med_ventilator.Model;

public class VitalData_Model {

    private float _time;
    //Messwerte
    private float _pressureIST;
    private float _flowIST;
    private float _O2concentrationIST;
    //Einstellwerte
    private float _pressureValue;
    private float _flowValue;
    private float _O2concentrationValue;

    private int _status;
    private int _deviceID;

    public VitalData_Model(float _pressureIST, float _flowIST, float _O2concentrationIST, float _pressureValue, float _flowValue, float _O2concentrationValue, int _status, int _deviceID) {
        this._pressureIST = _pressureIST;
        this._flowIST = _flowIST;
        this._O2concentrationIST = _O2concentrationIST;
        this._pressureValue = _pressureValue;
        this._flowValue = _flowValue;
        this._O2concentrationValue = _O2concentrationValue;
        this._status = _status;
        this._deviceID = _deviceID;
    }


    public float get_time() {
        return _time;
    }

    public float get_pressureIST() {
        return _pressureIST;
    }

    public void set_pressureIST(float _pressureIST) {
        this._pressureIST = _pressureIST;
    }

    public float get_flowIST() {
        return _flowIST;
    }

    public void set_flowIST(float _flowIST) {
        this._flowIST = _flowIST;
    }

    public float get_O2concentrationIST() {
        return _O2concentrationIST;
    }

    public void set_O2concentrationIST(float _O2concentrationIST) {
        this._O2concentrationIST = _O2concentrationIST;
    }

    public float get_pressureValue() {
        return _pressureValue;
    }

    public void set_pressureValue(float _pressureValue) {
        this._pressureValue = _pressureValue;
    }

    public float get_flowValue() {
        return _flowValue;
    }

    public void set_flowValue(float _flowValue) {
        this._flowValue = _flowValue;
    }

    public float get_O2concentrationValue() {
        return _O2concentrationValue;
    }

    public void set_O2concentrationValue(float _O2concentrationValue) {
        this._O2concentrationValue = _O2concentrationValue;
    }

    public int get_status() {
        return _status;
    }

    public void set_status(int _status) {
        this._status = _status;
    }

    public int get_deviceID() {
        return _deviceID;
    }

    public void set_deviceID(int _deviceID) {
        this._deviceID = _deviceID;
    }
}
