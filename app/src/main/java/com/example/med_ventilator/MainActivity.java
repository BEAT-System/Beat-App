package com.example.med_ventilator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.net.Uri;
import android.os.Bundle;

import com.example.med_ventilator.Viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity implements Device_Fragment.OnFragmentInteractionListener {

    private String TAG = "MainActivity";
    public Device_List_Fragment _Device_List_Fragment;
    public Device_Fragment _Device_Fragment;

    ViewModel _viewModel;
    private FragmentTransaction fragmentTransaction;

    public static final String BUNDLE_DEVICE_KEY_ID = "Device ID";
    public static final String BUNDLE_DEVICE_KEY_NAME = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Device_List_Fragment()).commit();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
/*
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final DeviceAdapter adapter = new DeviceAdapter();
        recyclerView.setAdapter(adapter);

        _viewModel = new ViewModelProvider(this).get(ViewModel.class);
        final Observer<List<device>> _observer = new Observer<List<device>>() {
            @Override
            public void onChanged(@Nullable final List<device> devices) {
                Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onChanged: "+ devices.size()+" "+ devices.isEmpty());
                adapter.submitList(devices);

            }
        };
        _viewModel.getLiveData().observe(this,_observer);



        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        adapter.setOnItemClickListener(new DeviceAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(device _device) {
                Fragment _deviceFragment = Device_Fragment.newInstance(_device.get_deviceId());
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmant_container,_deviceFragment).commit();
            }
        });
*/
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
