package com.koror.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.koror.app.api.IDataIO;
import com.koror.app.api.repository.IUserRepository;
import com.koror.app.entity.User;
import com.koror.app.error.WrongInputException;
import com.koror.app.repository.UserRepository;

import java.io.*;
import java.util.List;

public class UserService extends AbstractService<UserRepository, User> implements IUserRepository, IDataIO {

    private final String pathXml = "data/user/data_user.xml";

    private final String pathJson = "data/user/data_user.json";

    private final String pathBin = "data/user/data_user.tmp";

    private final String pathPackage = "data/user/";

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadUser(User user) {
        if(user == null) throw new WrongInputException("Wrong input");
        repository.loadUser(user);
    }

    @Override
    public User getByLogin(String login) {
        if(login == null || login.isEmpty()) throw new WrongInputException("Wrong input");
        return repository.getByLogin(login);
    }

    @Override
    public void saveDataSerialization() throws IOException {
        final FileOutputStream fos = new FileOutputStream(pathBin);
        final ObjectOutputStream oos = new ObjectOutputStream(fos);
        final File f = new File(pathBin);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        oos.writeObject(getList());
    }

    @Override
    public void loadDataSerialization() throws IOException, ClassNotFoundException {
        final FileInputStream fis = new FileInputStream(pathBin);
        final ObjectInputStream ois = new ObjectInputStream(fis);
        final List users = (List) ois.readObject();
        for (Object user : users)
            if (user instanceof User) loadUser((User)user);
    }

    @Override
    public void saveDataXml() throws IOException {
        final File f = new File(pathXml);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new XmlMapper();
        final User[] listUser = getList().toArray(new User[getList().size()]);
        objectMapper.writeValue(new File(pathXml), listUser);
    }

    @Override
    public void loadDataXml() throws IOException {
        final ObjectMapper objectMapper = new XmlMapper();
        final User[] listUser = objectMapper.readValue(new File(pathXml), User[].class);
        for (User user : listUser) loadUser(user);
    }

    @Override
    public void saveDataJson() throws IOException {
        final File f = new File(pathJson);
        new File(pathPackage).mkdirs();
        if (f.isFile()) f.delete();
        final ObjectMapper objectMapper = new ObjectMapper();
        final User[] listUser = getList().toArray(new User[getList().size()]);
        objectMapper.writeValue(new File(pathJson), listUser);
    }

    @Override
    public void loadDataJson() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        final User[] listUser = objectMapper.readValue(new File(pathJson), User[].class);
        for (User user : listUser) loadUser(user);
    }

}
