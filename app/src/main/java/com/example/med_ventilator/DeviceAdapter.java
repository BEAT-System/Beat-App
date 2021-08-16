package com.example.med_ventilator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.med_ventilator.Model.Device_Model;

public class DeviceAdapter extends ListAdapter<Device_Model,DeviceAdapter.DeviceHolder> {


    private OnItemClickListener listener;

    public DeviceAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Device_Model> DIFF_CALLBACK = new DiffUtil.ItemCallback<Device_Model>() {
        @Override
        public boolean areItemsTheSame(@NonNull Device_Model oldItem, @NonNull Device_Model newItem) {

            return oldItem.get_deviceId() == newItem.get_deviceId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Device_Model oldItem, @NonNull Device_Model newItem) {
            //Muss noch geprüft werden
            return false;
        }
    };

    @NonNull
    @Override
    public DeviceHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.device_item,parent,false);
        return new DeviceHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DeviceHolder holder, int position) {
        Device_Model _currentdevice = getItem(position);
        holder.textViewDeviceTitle.setText("Device");
        holder.textViewStatus.setText(String.valueOf(_currentdevice.getStatus()));
        holder.textViewID.setText(String.valueOf(_currentdevice.get_deviceId()));
    }

    public Device_Model getdeviceAt(int position){
        return getItem(position);
    }

    class DeviceHolder extends RecyclerView.ViewHolder{
        private TextView textViewDeviceTitle;
        private TextView textViewStatus;
        private TextView textViewID;

        public DeviceHolder (View itemView){
            super(itemView);
            textViewDeviceTitle = itemView.findViewById(R.id.DeviceTitleTV);
            textViewStatus = itemView.findViewById(R.id.StatusEditTV);
            textViewID = itemView.findViewById(R.id.IDeditTV);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener!=null && position!=RecyclerView.NO_POSITION) {
                        listener.OnItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void OnItemClick(Device_Model _device);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

}
