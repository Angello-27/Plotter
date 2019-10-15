package com.topicos.development.plotter.communication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.util.Set;

import com.topicos.development.plotter.constanst.Connection;

public class Bluetooth {

    private BluetoothSocket socket;
    private BluetoothDevice device;
    private BluetoothAdapter adapter;

    public Bluetooth() {
        this.adapter = BluetoothAdapter.getDefaultAdapter();
    }

    public void buscarVinculados() {
        Set<BluetoothDevice> devices = this.adapter.getBondedDevices();
        if (devices.size() > 0) {
            for (BluetoothDevice element : devices) {
                if (element.getName().equals("Plotter"))
                    this.device = element;
            }
        }
    }

    public void conectar() throws IOException {
        if (this.device != null &&
                (this.socket == null || !this.socket.isConnected())) {
            this.socket = this.device.createInsecureRfcommSocketToServiceRecord(Connection.BASE_UUID);
            this.socket.connect();
        }
    }

}
