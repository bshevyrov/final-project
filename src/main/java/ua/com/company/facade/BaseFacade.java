package ua.com.company.facade;

import java.util.List;

public interface BaseFacade<BaseDTO> {
    void create(BaseDTO baseDTO);

    void update(BaseDTO baseDTO);

    void delete(int id);

    BaseDTO findById(int id);

    List<BaseDTO> findAll();
}
