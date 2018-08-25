package com.example.springaopandaspectjlearn.cglib.sample;

import java.beans.PropertyChangeListener;
import java.io.Serializable;

/**
 * https://github.com/cglib/cglib/tree/master/cglib-sample
 *
 * Created by liuzz on 2018/06/11
 */
public abstract class Bean implements Serializable {

    private String sampleProperty;

    abstract public void addPropertyChangeListener(PropertyChangeListener listener);

    abstract public void removePropertyChangeListener(PropertyChangeListener listener);

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        this.sampleProperty = value;
    }

    @Override
    public String toString() {
        return "sampleProperty is " + sampleProperty;
    }
}
