package com.example.sourav8;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonAllBooks,buttonCurrenreading,buttonAlreadyRead,buttonWishlist,buttonFav,buttonAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        buttonAllBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,AllBooksActivity.class);
                startActivity(intent);
            }
        });

        buttonAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AlreadyReadBookActivity.class);
                startActivity(intent);
            }
        });
        buttonFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FavouritrBookActivity.class);
                startActivity(intent);
            }
        });
        buttonCurrenreading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,CurrentlyReadBookAvtivity.class);
                startActivity(intent);
            }
        });
        buttonWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,FavouritrBookActivity.class);
                startActivity(intent);
            }
        });

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(getString(R.string.app_name));
                builder.setMessage("Designed and Developed by sourav kumar");
                builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Intent intent=new Intent(MainActivity.this,WebsiteActivity.class);
                        intent.putExtra("url","https://google.com/");
                        startActivity(intent);
                    }
                });

                builder.create().show();
            }
        });

        Utils.getInstance(this);



    }
    private void initViews()
    {
        buttonAllBooks=findViewById(R.id.buttonAllBooks);
        buttonCurrenreading=findViewById(R.id.buttonCurrenreading);
        buttonAlreadyRead=findViewById(R.id.buttonAlreadyRead);
        buttonWishlist=findViewById(R.id.buttonWishlist);
        buttonFav=findViewById(R.id.buttonFav);
        buttonAbout=findViewById(R.id.buttonAbout);
    }
}