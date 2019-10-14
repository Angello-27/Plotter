package com.topicos.development.plotter.communication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;

public class Bluetooth {

    private BluetoothAdapter adapter;
    private BluetoothSocket socket;

    public Bluetooth() {
        this.adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public boolean checkState() {
        if (adapter == null)
            return false;
        return adapter.isEnabled();
    }
}
