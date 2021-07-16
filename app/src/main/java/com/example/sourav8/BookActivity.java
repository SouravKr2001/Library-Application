package com.example.sourav8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {

    public static final String BOOK_ID_KEY ="bookId";

    private TextView textViewBookNameValue,textViewAuthorValue,textViewPagesValue,textViewDescriptionValues;
    private Button btnAddToCurrentlyReadingList,btnAddToWishList,btnAddToAlraedyRead,btnAddToFavourite;
    private ImageView bookImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        initView();

//        String longDescription = "My nmae is sourav kumar."+"\n"+
//                "I am from steel city Jamshedpur"+"\n"+
//                "My hobbies are sketching basketball"+"\n"+
//                "My father name is Sumir Singh"+"\n"+
//                "My mothehr name isGayanamati mmauraya";
//
//
//        //TODO: get the datda from recycler View here
//        Book book = new Book(1,"1Q84","Haruki Murkani",1350,"A work of maddening brillance",longDescription,"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgt81TYLBcLJ_rKf4SH4B7gO_gXt7XZMET7g&usqp=CAU.png");

        Intent intent= getIntent();
        if(null!= intent) {
            int bookId=intent.getIntExtra(BOOK_ID_KEY,-1);
            if (bookId != -1)
            {
                Book incomingBook =Utils.getInstance(this).getBookById(bookId);
                if (null != incomingBook){
                    setData(incomingBook);

                    handleAlreadyRead(incomingBook);
                    handleWantToReadBooks(incomingBook);
                    handleCurrentlyReadinBooks(incomingBook);
                    handleFavouriteBooks(incomingBook);
                }
            }
        }

    }
private void handleFavouriteBooks(final Book book){
    ArrayList<Book> favouriteBooks= Utils.getInstance(this).getWantToReadBooks();

    Boolean existInFavouriteBooks = false;

    for(Book b: favouriteBooks)
    {
        if(b.getId()==book.getId())
        {
            existInFavouriteBooks=true;
        }
    }
    if(existInFavouriteBooks)
    {
        btnAddToFavourite.setEnabled(false);
    }
    else
    {
        btnAddToFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Utils.getInstance(BookActivity.this).addToFavourite(book))
                {
                    Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();


                    Intent intent=new Intent(BookActivity.this,FavouritrBookActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
    private void handleCurrentlyReadinBooks(final Book book)
    {
        ArrayList<Book> currentlyReadingBooks= Utils.getInstance(this).getWantToReadBooks();

        Boolean existInCurrentlyReadingBooks = false;

        for(Book b: currentlyReadingBooks)
        {
            if(b.getId()==book.getId())
            {
                existInCurrentlyReadingBooks=true;
            }
        }
        if(existInCurrentlyReadingBooks)
        {
            btnAddToWishList.setEnabled(false);
        }
        else
        {
            btnAddToCurrentlyReadingList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToCurrentlyReading(book))
                    {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(BookActivity.this,CurrentlyReadBookAvtivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void handleWantToReadBooks( final Book book)
    {
        ArrayList<Book> wantToReadBooks= Utils.getInstance(this).getWantToReadBooks();

        Boolean existInWantToReadBooks = false;

        for(Book b: wantToReadBooks)
        {
            if(b.getId()==book.getId())
            {
                existInWantToReadBooks=true;
            }
        }
        if(existInWantToReadBooks)
        {
           btnAddToWishList.setEnabled(false);
        }
        else
        {
            btnAddToWishList.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToWantToRead(book))
                    {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(BookActivity.this,WantToReadActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

//    Enable and disable button
//            Add the book to Already Read Book arraylist

    private  void handleAlreadyRead(Book book)
    {
        ArrayList<Book> alreadyReadBooks= Utils.getInstance(this).getAlreadyReadBooks();

        Boolean existInAlreadyReadBooks = false;

        for(Book b: alreadyReadBooks)
        {
            if(b.getId()==book.getId())
            {
                existInAlreadyReadBooks=true;
            }
        }
        if(existInAlreadyReadBooks)
        {
            btnAddToAlraedyRead.setEnabled(false);
        }
        else
        {
            btnAddToAlraedyRead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Utils.getInstance(BookActivity.this).addToAlreadyRead(book))
                    {
                        Toast.makeText(BookActivity.this, "Book added", Toast.LENGTH_SHORT).show();


                        Intent intent=new Intent(BookActivity.this,AlreadyReadBookActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(BookActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private void initView()
    {
        textViewBookNameValue=findViewById(R.id.textViewBookNameValue);
        textViewAuthorValue=findViewById(R.id.textViewAuthorValue);
        textViewPagesValue=findViewById(R.id.textViewPagesValue);
        textViewDescriptionValues=findViewById(R.id.textViewDescriptionValues);

        btnAddToCurrentlyReadingList=findViewById(R.id.btnAddToCurrentlyReadingList);
        btnAddToWishList=findViewById(R.id.btnAddToWishList);
        btnAddToAlraedyRead=findViewById(R.id.btnAddToAlraedyRead);
        btnAddToFavourite=findViewById(R.id.btnAddToFavourite);

        bookImage=findViewById(R.id.bookImage);


    }

    private  void setData(Book book)
    {
        textViewBookNameValue.setText(book.getName());
        textViewAuthorValue.setText(book.getAuthor());
        textViewPagesValue.setText(String.valueOf(book.getPages()));
        textViewDescriptionValues.setText(book.getLongDesc());

        Glide.with(this)
                .asBitmap().load(book.getImageUrl())
                .into(bookImage);

    }
}