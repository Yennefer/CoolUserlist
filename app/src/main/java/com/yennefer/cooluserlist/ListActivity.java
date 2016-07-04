package com.yennefer.cooluserlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.yennefer.cooluserlist.model.IRestAPI;
import com.yennefer.cooluserlist.model.User;
import com.yennefer.cooluserlist.model.UsersData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yennefer on 01.07.2016.
 * Окно, отображающее список пользователей
 */
public class ListActivity extends AppCompatActivity {

    // Количество пользователей для вывода
    private final int USERS_COUNT = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Загружаю и отображаю список пользователей
        showUserList();
    }

    private void showUserList() {

        // Создаю подключение для получения списка пользователей
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        IRestAPI restAPI = retrofit.create(IRestAPI.class);

        // Создаю и выполняю запрос
        Call<UsersData> call = restAPI.getRandomUsers(USERS_COUNT);
        call.enqueue(new Callback<UsersData>() {
            @Override
            public void onResponse(Call<UsersData> call, Response<UsersData> response) {
                UsersData usersData = response.body();
                if (usersData != null) {
                    if (usersData.getResults().size() > 0) {

                        // Заполняю список полученными данными
                        FillUserList(usersData.getResults());
                    } else {
                        TextView tvEmpty = (TextView) findViewById(R.id.tvEmptyList);
                        if (tvEmpty != null) {
                            tvEmpty.setText(getResources().getString(R.string.api_error));
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<UsersData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void FillUserList(final List<User> users) {

        // Ключи для заполнения списка
        final String KEY_USERNAME = "username";
        final String KEY_PHOTO = "photo";

        // Параметры для создания адаптера
        String[] from = { KEY_USERNAME, KEY_PHOTO };
        int[] to = { R.id.tvUsername, R.id.ivPhoto };

        // Данные для заполнения списка
        ArrayList<Map<String, String>> data = new ArrayList<>(users.size());
        Map<String, String> mapElement;
        for (User user : users) {
            mapElement = new HashMap<>();
            mapElement.put(KEY_USERNAME,
                    String.format(
                            getResources().getString(R.string.name_format),
                            user.getFirstName(),
                            user.getLastName()
                    )
            );
            mapElement.put( KEY_PHOTO, user.getMediumPhoto() );
            data.add(mapElement);
        }

        // Создаю адаптер
        SimpleAdapter usersAdapter = new SimpleAdapter(getApplicationContext(), data,
                R.layout.item_user, from, to);

        // Задаю адаптеру обработчик вывода изображения по url
        usersAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Object data, String textRepresentation) {
                if (view.getId() == R.id.ivPhoto) {

                    // Асинхронно загружаю и кэширую изображение
                    Ion.with((ImageView) view)
                            .placeholder(R.drawable.photo_placeholder)
                            .load(textRepresentation);
                    return true;
                }
                return false;
            }
        });

        // Настраиваю список
        ListView lvUsers = (ListView) findViewById(R.id.lvUsers);
        if (lvUsers != null) {

            // Назначаю списку адаптер
            lvUsers.setAdapter(usersAdapter);

            // Назначаю списку view для отображения при пустом адаптере
            lvUsers.setEmptyView(findViewById(R.id.tvEmptyList));

            // Создаю обработчик нажатия элемента списка
            lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    // Открываю окно с подробной информацией о пользователе
                    Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);
                    intent.putExtra(
                            getResources().getString(R.string.intent_user_key),
                            users.get(position));
                    startActivity(intent);
                }
            });
        }
    }
}
