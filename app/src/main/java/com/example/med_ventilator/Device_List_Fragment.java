package com.example.med_ventilator;

import androidx.lifecycle.Observer;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.med_ventilator.Model.Device_Model;
import com.example.med_ventilator.Viewmodel.ViewModel;

import java.util.List;

public class Device_List_Fragment extends Fragment {

    private static final String TAG = "Device_List_Fragment";
    private ViewModel _viewModel;
    private DeviceAdapter _adapter;

    public static Device_List_Fragment newInstance() {
        return new Device_List_Fragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final NavController _navController = Navigation.findNavController(view);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        _adapter = new DeviceAdapter();
        recyclerView.setAdapter(_adapter);
        _viewModel = new ViewModelProvider(this).get(ViewModel.class);

        final Observer<List<Device_Model>> _observer = new Observer<List<Device_Model>>() {
            @Override
            public void onChanged(@Nullable final List<Device_Model> devices) {
                Toast.makeText(getContext(), "updated", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onChanged: "+ "Devicelist Size: " + devices.size()+" Devicelist isEmpty  "+ devices.isEmpty());
                _adapter.submitList(devices);
            }
        };
        _viewModel.getDeviceListLiveData().observe(getViewLifecycleOwner(),_observer);

        _adapter.setOnItemClickListener(new DeviceAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Device_Model _device) {
                Bundle _bundle = new Bundle();
                _bundle.putInt(MainActivity.BUNDLE_DEVICE_KEY_ID,_device.get_deviceId());
                _navController.navigate(R.id.action_device_List_Fragment_to_device_Fragment,_bundle);
            }
        });

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.device__list__fragment,container,false);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
