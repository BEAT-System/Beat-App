package com.example.med_ventilator;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.med_ventilator.Model.Device_Model;
import com.example.med_ventilator.Viewmodel.ViewModel;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;

public class Device_Fragment extends Fragment {

    public static int DEVICE_ID;
    public static final String TAG = "Device_Fragment";
    public static final String EXTRA_ID = "com.example.med_ventilator.EXTRA_ID";

    private OnFragmentInteractionListener mListener;
    private ViewModel _viewModel;

    public Device_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            DEVICE_ID = getArguments().getInt(MainActivity.BUNDLE_DEVICE_KEY_ID);
            Log.e(TAG, "onCreate: "+ DEVICE_ID);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.device_fragment,container,false);
        return view;
     }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final  TextView _deviceID_TV = view.findViewById(R.id.TV_DeviceIDEdit);
        _deviceID_TV.setText(Integer.toString(DEVICE_ID));

        final GraphView _flowgraph = (GraphView) view.findViewById(R.id.graphFlow);
        final GraphView _pressuregraph = (GraphView) view.findViewById(R.id.graphPressure);
        final GraphView _O2graph = (GraphView) view.findViewById(R.id.graphO2);

        final TextView _deviceState_TV = view.findViewById(R.id.TV_StatusEditValue);
        final TextView _setValueFlow_TV = view.findViewById(R.id.TV_setValueFlowEdit);
        final TextView _actValueFlow_TV = view.findViewById(R.id.TV_actualValueFlowEdit);
        final TextView _setValuePressure_TV = view.findViewById(R.id.TV_setValuePressureEdit);
        final TextView _actValuePressure_TV = view.findViewById(R.id.TV_actualValuePressureEdit);
        final TextView _setValueO2_TV = view.findViewById(R.id.TV_setValueO2Edit);
        final TextView _actValueO2_TV = view.findViewById(R.id.TV_actualValueO2Edit);

        _flowgraph.setBackgroundColor(getResources().getColor(R.color.graphcolor_Flow));
        _flowgraph.getViewport().setMinX(0);
        _flowgraph.getViewport().setMaxX(60);
        _flowgraph.getViewport().setXAxisBoundsManual(true);
        _flowgraph.getGridLabelRenderer().setHorizontalAxisTitle("Time [s]");
        _flowgraph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(35.0f);
        _flowgraph.getGridLabelRenderer().setVerticalAxisTitle("Flow [l]");
        _flowgraph.getGridLabelRenderer().setVerticalAxisTitleTextSize(35.0f);
        _flowgraph.getGridLabelRenderer().setPadding(40);
        _flowgraph.getGridLabelRenderer().setTextSize(30.0f);
        _flowgraph.getGridLabelRenderer().setLabelsSpace(5);
        _flowgraph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        _flowgraph.getViewport().setScalable(true);


        _pressuregraph.setBackgroundColor(getResources().getColor(R.color.graphcolor_Pressure));
        _pressuregraph.getViewport().setMinX(0);
        _pressuregraph.getViewport().setMaxX(60);
        _pressuregraph.getViewport().setXAxisBoundsManual(true);
        _pressuregraph.getGridLabelRenderer().setHorizontalAxisTitle("Time [s]");
        _pressuregraph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(35.0f);
        _pressuregraph.getGridLabelRenderer().setVerticalAxisTitle("Pressure [mbar]");
        _pressuregraph.getGridLabelRenderer().setVerticalAxisTitleTextSize(35.0f);
        _pressuregraph.getGridLabelRenderer().setPadding(40);
        _pressuregraph.getGridLabelRenderer().setTextSize(30.0f);
        _pressuregraph.getGridLabelRenderer().setLabelsSpace(5);
        _pressuregraph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        _pressuregraph.getViewport().setScalable(true);

        _O2graph.setBackgroundColor(getResources().getColor(R.color.graphcolor_O2));
        _O2graph.getViewport().setMinX(0);
        _O2graph.getViewport().setMaxX(60);
        _O2graph.getViewport().setMinY(0);
        _O2graph.getViewport().setMaxY(100);
        _O2graph.getViewport().setXAxisBoundsManual(true);
        _O2graph.getGridLabelRenderer().setHorizontalAxisTitle("Time [s]");
        _O2graph.getGridLabelRenderer().setHorizontalAxisTitleTextSize(35.0f);
        _O2graph.getGridLabelRenderer().setVerticalAxisTitle("O2 [%]");
        _O2graph.getGridLabelRenderer().setVerticalAxisTitleTextSize(35.0f);
        _O2graph.getGridLabelRenderer().setPadding(40);
        _O2graph.getGridLabelRenderer().setTextSize(30.0f);
        _O2graph.getGridLabelRenderer().setLabelsSpace(5);
        _O2graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.BOTH);
        _O2graph.getViewport().setScalable(true);

        _viewModel = new ViewModelProvider(this).get(ViewModel.class);
        final  Observer<Device_Model> _Observer = new Observer<Device_Model>() {
            @Override
            public void onChanged(Device_Model device) {
                Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();

                Log.e(TAG, "onChanged: "+ device.get_deviceId());

                _deviceState_TV.setText(Integer.toString(device.getStatus()));

                _flowgraph.addSeries(device.getFlowserie());
                _pressuregraph.addSeries(device.getPressureserie());
                _O2graph.addSeries(device.getO2concentrationserie());

                _actValueFlow_TV.setText(Float.toString(device.get_actualValueFlow()));
                _actValuePressure_TV.setText(Float.toString(device.get_actualValuePressure()));
                _actValueO2_TV.setText(Float.toString(device.get_actualValueO2concentration()));
                _setValueFlow_TV.setText(Float.toString(device.get_setValueFlow()));
                _setValuePressure_TV.setText(Float.toString(device.get_setValuePressure()));
                _setValueO2_TV.setText(Float.toString(device.get_actualValueO2concentration()));
            }
        };
        //_viewModel.getDeviceLiveData(DEVICE_ID).observe(getViewLifecycleOwner(),_Observer);
        _viewModel.getDeviceLiveData(DEVICE_ID).observe(getViewLifecycleOwner(),_Observer);

    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
