package com.test.springioc.service.impl;

import com.test.springioc.dao.UserDAO;
import com.test.springioc.ioc.AutoResource;
import com.test.springioc.dao.AddressDAO;
import com.test.springioc.dao.BankDAO;
import com.test.springioc.service.UserService;

/**
 * Created by liuzz on 2018/02/05
 */
public class UserServiceImpl implements UserService {

    private BankDAO bankDAO;

    @AutoResource
    private UserDAO userDAO;

    private AddressDAO addressDAO;

    @AutoResource
    public void setBankDAO(BankDAO bankDAO) {
        this.bankDAO = bankDAO;
    }

    @AutoResource(name = "addressDAO")
    public void setAddressDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    public void show() {
        System.out.println("UserService start........");
        bankDAO.show();
        userDAO.show();
        addressDAO.show();
        System.out.println("UserService end........");
    }

    public BankDAO getBankDAO() {
        return bankDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }
}
