/*
package com.kangladevelopers.filmfinder.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.kangladevelopers.filmfinder.Adapter.SimpleBaseAdapter;
import com.kangladevelopers.filmfinder.DataModel.Book;
import com.kangladevelopers.filmfinder.R;
import com.kangladevelopers.filmfinder.Utility.BooksAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class RetrofitDemoActvity extends AppCompatActivity implements ListView.OnItemClickListener {

    //Root URL of our web service
    public static final String ROOT_URL = "http://beta.json-generator.com/api/json/get/";

    //Strings to bind with intent will be used to send data to other activity
    public static final String KEY_BOOK_ID = "key_book_id";
    public static final String KEY_BOOK_NAME = "key_book_name";
    public static final String KEY_BOOK_PRICE = "key_book_price";
    public static final String KEY_BOOK_STOCK = "key_book_stock";

    private ListView listView;

    //List of type books this list will store type Book which is our data model
    private List<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_demo_actvity);
        listView = (ListView) findViewById(R.id.listViewBooks);

        getBooks();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }


    private void getBooks(){
        final ProgressDialog loading = ProgressDialog.show(this,"Fetching Data","Please wait...",false,false);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(ROOT_URL).build();
        BooksAPI booksAPI = restAdapter.create(BooksAPI.class);
        booksAPI.getBooks(new Callback<List<Book>>() {
            @Override
            public void success(List<Book> list, Response response) {
                books =list;
                showList();
                loading.dismiss();


            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    private void showList(){
        //String array to store all the book names
        ArrayList<String> items = new ArrayList<>();

        //Traversing through the whole list to get all the names
        for(int i=0; i<books.size(); i++){
            //Storing names to string array
            items.add(books.get(i).getName());
        }

        //Creating an array adapter for list view
        SimpleBaseAdapter adapter = new SimpleBaseAdapter(getApplicationContext(),items);

        //Setting adapter to listview
        listView.setAdapter(adapter);
    }
}
*/
