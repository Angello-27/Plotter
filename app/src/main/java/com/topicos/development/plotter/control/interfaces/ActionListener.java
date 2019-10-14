package com.topicos.development.plotter.control.interfaces;

public interface ActionListener {

    void onCreate();

    void onAttach();

    void onFinish(boolean option);

    void onSave();

    void onLoad();

    void onPrint();
}
