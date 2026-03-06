package org.example.br.com.ocooldev.dao;

import org.example.br.com.ocooldev.exception.UserNotFundException;
import org.example.br.com.ocooldev.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private long id = 1L;
    private final List<UserModel> models = new ArrayList<>();

    //utilizado o final para garantir que o método não seja sobrescrito por subclasses
    public UserModel save(final UserModel model) {
        model.setId(id++);
        models.add(model);
        return model;
    }
    public UserModel update(final UserModel model){
    var toUpdate = findById(model.getId());
    models.remove(toUpdate);
    models.add(model);
    return model;
    }
    public void delete(final long id){
        var toDelete = findById(id);
        models.remove(toDelete);
    }

    public UserModel findById(final long id) {

        var message = String.format("Nao existe um usuario com o id %s cadastrado ", id);
        return models.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFundException(message));

    }
    public List<UserModel> findAll(){
        return models;
    }

}
