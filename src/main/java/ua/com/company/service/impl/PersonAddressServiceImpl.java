package ua.com.company.service.impl;

import ua.com.company.entity.PersonAddress;
import ua.com.company.service.PersonAddressService;

import java.util.List;

public class PersonAddressServiceImpl implements PersonAddressService {
    private static  PersonAddressServiceImpl instance;

   public static  synchronized PersonAddressServiceImpl getInstance(){
        if(instance==null){
            instance = new PersonAddressServiceImpl();
        }
        return instance;
    }

    private PersonAddressServiceImpl() {
    }

    @Override
    public void create(PersonAddress personAddress) {

    }

    @Override
    public void update(PersonAddress personAddress) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public PersonAddress findById(int id) {
        return null;
    }

    @Override
    public List<PersonAddress> findAll() {
        return null;
    }
}
