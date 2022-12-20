package ua.com.company.service;

import ua.com.company.entity.PersonAddress;

public interface PersonAddressService extends BaseService<PersonAddress> {
    void updateByPersonId(int personId);
}
