package com.yennefer.cooluserlist;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;
import com.yennefer.cooluserlist.model.User;

/**
 * Created by Yennefer on 01.07.2016.
 * Окно, оторажающее подробную информацию о пользователе
 */
public class DetailsActivity extends AppCompatActivity {

    // Данные о пользователе
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Получаю данные о пользователе
        user = getIntent().getParcelableExtra(getResources().getString(R.string.intent_user_key));

        // Заполняю view данными о пользователе
        if (user != null) {
            fillViews();
        } else {
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.acShare: {

                // Открываю приложение для отправки письма с данными о пользователе
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "", null));
                intent.putExtra(Intent.EXTRA_SUBJECT,
                        String.format(getResources().getString(R.string.email_subject_format),
                                user.getFirstName(),
                                user.getLastName())
                );
                intent.putExtra(Intent.EXTRA_TEXT,
                        String.format(getResources().getString(R.string.email_text_format),
                                user.getFirstName(),
                                user.getLastName(),
                                user.getNationality(),
                                user.getEmail(),
                                user.getPhone(),
                                user.getStreet(),
                                user.getLargePhoto())
                );
                startActivity(Intent.createChooser(intent, null));
                return true;
            }
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void fillViews() {

        // Асинхронно загружаю и кэширую фотографию пользователя
        ImageView ivPhoto = (ImageView) findViewById(R.id.ivPhoto);
        if (ivPhoto != null) {
            Ion.with(ivPhoto)
                    .placeholder(R.drawable.photo_placeholder)
                    .load(user.getLargePhoto());
        }

        // Вывожу текстовую информацию о пользователе
        findTextViewAndSetText(R.id.tvNameAndNat,
                String.format(getResources().getString(R.string.name_and_nat_format),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getNationality()
                ));
        findTextViewAndSetText(R.id.tvEmail, user.getEmail());
        findTextViewAndSetText(R.id.tvPhone, user.getPhone());
        findTextViewAndSetText(R.id.tvAddress,
                String.format(getResources().getString(R.string.address_format),
                        user.getStreet(),
                        user.getCity(),
                        user.getState(),
                        user.getPostcode()
                ));
    }

    private void findTextViewAndSetText(int id, String text) {

        // Нахожу по id и заполняю TextView
        TextView tv = (TextView) findViewById(id);
        if (tv != null) {
            tv.setText(text);
        }
    }

    public void onPhoneClick(View view) {

        // Открываю приложение для совершения вызова
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + user.getPhone()));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void onEmailClick(View view) {

        // Открываю приложение для отправки письма на адрес пользователя
        final String chooseApp = getResources().getString(R.string.send_email_text);
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", user.getEmail(), null));
        startActivity(Intent.createChooser(intent, chooseApp));
    }
}
