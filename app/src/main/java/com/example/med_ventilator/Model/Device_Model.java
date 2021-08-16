package com.example.med_ventilator.Model;

import android.util.Log;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Calendar;

public class Device_Model {


    public int _deviceId;
    public int _status;

    private float _time;
    //Messwerte
    private float _actualValuePressure;
    private float _actualValueFlow;
    private float _actualValueO2concentration;
    //Einstellwerte
    private float _setValuePressure;
    private float _setValueFlow;
    private float _setValueO2concentration;

    public LineGraphSeries<DataPoint> flowserie;
    public LineGraphSeries<DataPoint> pressureserie;
    public LineGraphSeries<DataPoint> O2concentrationserie;

    public Device_Model(int _deviceId) {
        this._deviceId = _deviceId;
        flowserie = new LineGraphSeries<>();
        pressureserie = new LineGraphSeries<>();
        O2concentrationserie = new LineGraphSeries<>();

    }

    public int get_deviceId() {
        return _deviceId;
    }

    public int getStatus() {
        return _status;
    }

    public void setStatus(int status) {
        this._status = status;
    }

    public void addData(VitalData_Model data){
        //aktualisiere den Status
        setStatus(data.get_status());
        // read in the data
        setVitaldata(data.get_pressureIST(),data.get_flowIST(),data.get_O2concentrationIST(),data.get_pressureValue(),data.get_flowValue(),data.get_O2concentrationValue());
    }

    private void setVitaldata(float actualValuePressure, float actualValueFlow, float actualValueO2concentration, float setValuePressure, float setValueFlow, float setValueO2concentration){
        this._actualValuePressure = actualValuePressure;
        this._actualValueFlow = actualValueFlow;
        this._actualValueO2concentration = actualValueO2concentration;
        this._setValuePressure = setValuePressure;
        this._setValueFlow = setValueFlow;
        this._setValueO2concentration= setValueO2concentration;

        this._time = actualValueFlow+1;// Calendar.getInstance().getTimeInMillis();
        //Log.e("Device_Model", "setVitaldata: "+ _time);

        //add data to the LineGraphseries
        DataPoint currentFlowDP = new DataPoint(_time,_actualValueFlow);
        flowserie.appendData(currentFlowDP,true,50);
        DataPoint currentPressureDP = new DataPoint(_time,_actualValuePressure);
        pressureserie.appendData(currentPressureDP,true,50);
        DataPoint currentO2concentrationDP = new DataPoint(_time,_actualValueO2concentration);
        O2concentrationserie.appendData(currentO2concentrationDP,true,50);

    }

    public LineGraphSeries<DataPoint> getFlowserie() {
        return flowserie;
    }

    public LineGraphSeries<DataPoint> getPressureserie() {
        return pressureserie;
    }

    public LineGraphSeries<DataPoint> getO2concentrationserie() {
        return O2concentrationserie;
    }

    public float get_setValuePressure() {
        return _setValuePressure;
    }

    public float get_setValueFlow() {
        return _setValueFlow;
    }

    public float get_setValueO2concentration() {
        return _setValueO2concentration;
    }

    public float get_actualValuePressure() {
        return _actualValuePressure;
    }

    public float get_actualValueFlow() {
        return _actualValueFlow;
    }

    public float get_actualValueO2concentration() {
        return _actualValueO2concentration;
    }
}
