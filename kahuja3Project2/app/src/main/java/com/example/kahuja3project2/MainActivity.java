package com.example.kahuja3project2;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.content.Intent;
import android.net.Uri;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {
    public static String EXTRA_RES_ID = "EXTRA_RES_ID";
    private ImageAdapter imageAdapter;
    private ListView listView;

    ArrayList<String> movieNames = new ArrayList<String>(Arrays.asList("The Accountant","Logan","A Star Is Born","Bohemian Rhapsody","Black Panther","John Wick: Chapter 2","Venom"));
    ArrayList<Integer> movieImages = new ArrayList<Integer>(Arrays.asList(R.drawable.theaccountant,R.drawable.logan,R.drawable.astarisborn,R.drawable.bohemianrhapsody,R.drawable.blackpanther,R.drawable.johnwick,R.drawable.venom));
    ArrayList<String> movieTrailer = new ArrayList<String>(Arrays.asList("https://www.youtube.com/watch?v=DBfsgcswlYQ","https://www.youtube.com/watch?v=Div0iP65aZo","https://www.youtube.com/watch?v=nSbzyEJ8X9E","https://www.youtube.com/watch?v=mP0VHJYFOAU","https://www.youtube.com/watch?v=xjDjIWPwcPU","https://www.youtube.com/watch?v=XGk2EfbD_Ps","https://www.youtube.com/watch?v=u9Mv98Gr5pY"));
    ArrayList<String> movieWiki = new ArrayList<>(Arrays.asList("https://en.wikipedia.org/wiki/Gavin_O'Connor_(filmmaker)","https://en.wikipedia.org/wiki/James_Mangold","https://en.wikipedia.org/wiki/Bradley_Cooper","https://en.wikipedia.org/wiki/Bryan_Singer","https://en.wikipedia.org/wiki/Ryan_Coogler","https://en.wikipedia.org/wiki/Chad_Stahelski","https://en.wikipedia.org/wiki/Ruben_Fleischer"));
    ArrayList<String> movieIMDB = new ArrayList<>(Arrays.asList("https://www.imdb.com/title/tt2140479/?ref_=ttls_li_tt","https://www.imdb.com/title/tt3315342/?ref_=ttls_li_tt","https://www.imdb.com/title/tt1517451/?ref_=ttls_li_tt","https://www.imdb.com/title/tt1727824/?ref_=ttls_li_tt","https://www.imdb.com/title/tt1825683/?ref_=ttls_li_tt","https://www.imdb.com/title/tt4425200/?ref_=ttls_li_tt","https://www.imdb.com/title/tt1270797/?ref_=ttls_li_tt"));
    ArrayList<String> movieYear = new ArrayList<>(Arrays.asList("2016","2017","2018","2018","2018","2017","2018"));
    ArrayList<String> movieDuration = new ArrayList<>(Arrays.asList("2h 8min","2h 17min","2h 16min","2h 14min","2h 14min","2h 2min","1h 52min"));
    ArrayList<String> movieDirectors = new ArrayList<>(Arrays.asList("Gavin O'Connor","James Mangold","Bradley Cooper","Bryan Singer","Ryan Coogler","Chad Stahelski","Ruben Fleischer"));
    ArrayList<String> movieStars = new ArrayList<>(Arrays.asList("Ben Affleck, Anna Kendrick, J.K. Simmons","Hugh Jackman, Patrick Stewart, Dafne Keen","Lady Gaga, Bradley Cooper, Sam Elliott","Rami Malek, Lucy Boynton, Gwilym Lee","Chadwick Boseman, Michael B. Jordan, Lupita Nyong'o","Keanu Reeves, Riccardo Scamarcio, Ian McShane","Tom Hardy, Michelle Williams, Riz Ahmed"));
    ArrayList<String> movieRatings = new ArrayList<>(Arrays.asList("IMDB: 7.3/10 , Rotten Tomatoes: 52%","IMDB: 8.1/10 , Rotten Tomatoes: 93%","IMDB: 7.7/10 , Rotten Tomatoes: 90%","IMDB: 8.0/10 , Rotten Tomatoes: 60%","IMDB: 7.3/10 , Rotten Tomatoes: 97%","IMDB: 7.5/10 , Rotten Tomatoes: 89%","IMDB: 6.7/10 , Rotten Tomatoes: 29%"));


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        listView = (ListView) findViewById(R.id.movielist);
        imageAdapter = new ImageAdapter(this,movieNames,movieImages,movieYear);
        listView.setAdapter(imageAdapter);

        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,ViewImageActivity.class);
                intent.putExtra("movieName",movieNames.get(position));
                intent.putExtra("movieImage",movieImages.get(position));
                intent.putExtra("movieIMDB",movieIMDB.get(position));
                MainActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle("Your choices are:");
        menu.add(0,v.getId(),0,"Movie Details");
        menu.add(0,v.getId(),0,"Movie Trailer");
        menu.add(0,v.getId(),0,"Director Wiki");
        menu.add(0,v.getId(),0,"Movie IMDB");

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        if(item.getTitle().equals("Movie Details")){
            showDetails(info.position);
        } else if(item.getTitle().equals("Movie Trailer")){
            showTrailer(info.position);
        } else if(item.getTitle().equals("Director Wiki")){
            showDirector(info.position);
        } else if(item.getTitle().equals("Movie IMDB")) {
            showIMDB(info.position);
        }
        return true;
    }

    public void showDetails(int position){
        String release = movieYear.get(position);
        String duration = movieDuration.get(position);
        String director = movieDirectors.get(position);
        String stars = movieStars.get(position);
        String ratings = movieRatings.get(position);
        Intent intent = new Intent(MainActivity.this,ViewDetailsActivity.class);
        intent.putExtra("release",release);
        intent.putExtra("duration",duration);
        intent.putExtra("director",director);
        intent.putExtra("stars",stars);
        intent.putExtra("ratings",ratings);
        startActivity(intent);
    }


    public void showTrailer(int position){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(movieTrailer.get(position)));
        startActivity(intent);
    }

    public void showDirector(int position){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(movieWiki.get(position)));
        startActivity(intent);
    }

    public void showIMDB(int position){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(movieIMDB.get(position)));
        startActivity(intent);
    }
}
