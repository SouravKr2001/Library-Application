package com.example.sourav8;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class
Utils {

    private static final String ALL_BOOKS_KEY="all_books";
    private static final String AlREADY_READ_BOOKS="already_read_books";
    private static final String WANT_TO_READ_BOOKS="want_to_read_books";
    private static final String CURRENTLY_READING_BOOKS="currently_reading_books";
    private static final String FAVOURITE_BOOKS="favourite_books";


    private static  Utils instance;
    private SharedPreferences sharedPreferences;


//    private  static ArrayList<Book> allBooks;
//    private static ArrayList<Book> alreadyReadBooks;
//    private static ArrayList<Book> wantToReadBooks;
//    private static ArrayList<Book> currentlyReadingBooks;
//    private static ArrayList<Book> favouriteBooks;

   private Utils(Context context) {
       sharedPreferences=context.getSharedPreferences("alternate_db",Context.MODE_PRIVATE);


       if(null == getAllBooks())
       {

           initData();
       }
       SharedPreferences.Editor editor=sharedPreferences.edit();
       Gson gson=new Gson();

              if(null == getAlreadyReadBooks())
       {
           editor.putString(AlREADY_READ_BOOKS,gson.toJson(new ArrayList<Book>()));

           editor.commit();

       }
       if(null == getWantToReadBooks())
       {  editor.putString(WANT_TO_READ_BOOKS,gson.toJson(new ArrayList<Book>()));

           editor.commit();
       }
       if(null == getCurrentlyReadingBooks())
       {  editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(new ArrayList<Book>()));

           editor.commit();
       }
       if(null == getFavouriteBooks())
       {  editor.putString(FAVOURITE_BOOKS,gson.toJson(new ArrayList<Book>()));

           editor.commit();
       }

   }

    private void initData() {
       //TODO: add some initial data

        ArrayList<Book> books=new ArrayList<>();

        books.add(new Book(1,"1Q84","Haruki Murkani",1350,"A work of maddening brillance","Long Descrip..","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRgt81TYLBcLJ_rKf4SH4B7gO_gXt7XZMET7g&usqp=CAU.png"));

        books.add(new Book(2,"2nd Book","2nd Author",2350,"Second Short Description","Second Long Descrip..","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtdq2gkoR7OtnlnudkvD-5pcvsyTkN-FljPA&usqp=CAU.png"));

        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        editor.putString(ALL_BOOKS_KEY,gson.toJson(books));
        editor.commit();

   }


    public static  Utils getInstance(Context context) {
       if(null != instance)
       {
           return instance;
       }
        else
       {
           instance=new Utils(context);
           return instance;
       }
    }

    public  ArrayList<Book> getAllBooks() {

       Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(ALL_BOOKS_KEY,null),type);
        return books;
    }

    public  ArrayList<Book> getAlreadyReadBooks() {


        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(AlREADY_READ_BOOKS,null),type);
        return books;

    }

    public ArrayList<Book> getWantToReadBooks() {

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(WANT_TO_READ_BOOKS,null),type);
        return books;
    }

    public ArrayList<Book> getCurrentlyReadingBooks() {

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(CURRENTLY_READING_BOOKS,null),type);
        return books;
    }

    public  ArrayList<Book> getFavouriteBooks() {

        Gson gson=new Gson();
        Type type=new TypeToken<ArrayList<Book>>() {}.getType();
        ArrayList<Book> books=gson.fromJson(sharedPreferences.getString(FAVOURITE_BOOKS,null),type);
        return books;
    }

    public Book getBookById(int id){
       ArrayList<Book> books=getAllBooks();
        if (null != books) {


            for (Book b : books) {
                if (b.getId() == id) {
                    return b;
                }
            }
        }
       return null;
    }

    public boolean addToAlreadyRead(Book book)
    {
        ArrayList<Book> books=getAlreadyReadBooks();
        if (null != books) {


            if(books.add(book)) {
             Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(AlREADY_READ_BOOKS);
                editor.putString(AlREADY_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }
    public boolean addToWantToRead(Book book)
    {
        ArrayList<Book> books=getWantToReadBooks();
        if (null != books) {


            if(books.add(book)) {
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(WANT_TO_READ_BOOKS);
                editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }
    public boolean addToCurrentlyReading(Book book)
    {
        ArrayList<Book> books=getCurrentlyReadingBooks();
        if (null != books) {


            if(books.add(book)) {
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(CURRENTLY_READING_BOOKS);
                editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }
    public boolean addToFavourite(Book book)
    {
        ArrayList<Book> books=getFavouriteBooks();
        if (null != books) {


            if(books.add(book)) {
                Gson gson=new Gson();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.remove(FAVOURITE_BOOKS);
                editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                editor.commit();
                return true;

            }
        }
        return false;
    }

    public boolean removeFromAlraedyRead(Book book)
    {
        ArrayList<Book> books=getAlreadyReadBooks();
        if(null != books){
            for (Book b:books){
                if (b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(AlREADY_READ_BOOKS);
                        editor.putString(AlREADY_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromWantToRead(Book book)
    {
        ArrayList<Book> books=getWantToReadBooks();
        if(null != books){
            for (Book b:books){
                if (b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(WANT_TO_READ_BOOKS);
                        editor.putString(WANT_TO_READ_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;

    }
    public boolean removeFromCurrentlyReading(Book book)
    {
        ArrayList<Book> books=getCurrentlyReadingBooks();
        if(null != books){
            for (Book b:books){
                if (b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(CURRENTLY_READING_BOOKS);
                        editor.putString(CURRENTLY_READING_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public boolean removeFromFavourite(Book book)
    {
        ArrayList<Book> books=getFavouriteBooks();
        if(null != books){
            for (Book b:books){
                if (b.getId() == book.getId())
                {
                    if(books.remove(b)){
                        Gson gson=new Gson();
                        SharedPreferences.Editor editor=sharedPreferences.edit();
                        editor.remove(FAVOURITE_BOOKS);
                        editor.putString(FAVOURITE_BOOKS,gson.toJson(books));
                        editor.commit();
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
