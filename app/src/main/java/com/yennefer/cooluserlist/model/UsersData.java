package com.yennefer.cooluserlist.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Yennefer on 01.07.2016.
 * Класс для хранения результата запроса к API в виде списка пользователей
 */
public class UsersData {

    private List<User> results = new LinkedList<User>();

    public List<User> getResults() {
        return results;
    }
}
