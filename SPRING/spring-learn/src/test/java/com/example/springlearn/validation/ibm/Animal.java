package com.example.springlearn.validation.ibm;

public interface Animal {

    @NotEmpty
    String getName();

    @NotEmpty
    String getOwnerName();
}
