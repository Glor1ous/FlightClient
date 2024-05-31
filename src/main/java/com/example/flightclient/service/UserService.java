package com.example.flightclient.service;

import com.example.flightclient.entity.UserEntity;
import com.example.flightclient.response.DataResponse;
import com.example.flightclient.response.ListResponse;
import com.google.gson.reflect.TypeToken;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;

import java.lang.reflect.Type;
import java.util.Comparator;
import java.util.Optional;

public class UserService {

    @Getter
    private ObservableList<UserEntity> users = FXCollections.observableArrayList();
    private final HttpService httpService = new HttpService();
    private final JsonService jsonService = new JsonService();
    private final ClientProperties clientProperties = new ClientProperties();

    private final Type dataType = new TypeToken<DataResponse<UserEntity>>() {}.getType();
    private final Type listType = new TypeToken<ListResponse<UserEntity>>() {}.getType();

    public void getAll() {
        ListResponse<UserEntity> userList = jsonService.getObject(httpService.get(clientProperties.getUserGetAll()), listType);
        if (userList.isStatus()) {
            users.addAll(userList.getData());
            sortByLogin();
        } else {
            throw new RuntimeException(userList.getStatusText());
        }
    }

    public void update(UserEntity user) {
        String tempData = httpService.put(clientProperties.getUserUpdate(), jsonService.getJson(user));
        DataResponse<UserEntity> response = jsonService.getObject(tempData, dataType);
        if (response.isStatus()) {
            // Обновите локальный список пользователей, если нужно
            users.replaceAll(u -> u.getId().equals(user.getId()) ? user : u);
        } else {
            throw new RuntimeException(response.getStatusText());
        }
    }

    public void save(UserEntity user) {
        String tempData = httpService.post(clientProperties.getUserSave(), jsonService.getJson(user));
        DataResponse<UserEntity> response = jsonService.getObject(tempData, dataType);
        if (response.isStatus()) {
            users.add(response.getData());
            sortByLogin();
        } else {
            throw new RuntimeException(response.getStatusText());
        }
    }

    public Optional<UserEntity> findByLoginAndEmail(String login, String email) {
        String tempData = httpService.get(clientProperties.getUserFindByLoginAndEmail() + "?login=" + login + "&email=" + email);
        DataResponse<UserEntity> response = jsonService.getObject(tempData, dataType);
        if (response.isStatus()) {
            return Optional.ofNullable(response.getData());
        } else {
            throw new RuntimeException(response.getStatusText());
        }
    }

    private void sortByLogin() {
        users.sort(Comparator.comparing(UserEntity::getLogin));
    }
}