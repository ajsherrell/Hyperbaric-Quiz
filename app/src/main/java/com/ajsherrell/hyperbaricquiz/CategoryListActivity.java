package com.ajsherrell.hyperbaricquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class CategoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_list);
    }

    // menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //TODO: create switch case for menu items.
        switch (id) {
            case R.id.action_about:
                //TODO: link to about activity
                break;
            case R.id.action_high_score:
                //TODO: link to high score activity
                break;
            case R.id.action_exam_ready:
                //TODO: link to exam ready activity
                break;
            default:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
