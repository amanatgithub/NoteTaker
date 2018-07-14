package com.example.aman.notetaker;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Data> arrayList;
    int count;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TaskDatabase taskDatabase = new TaskDatabase(this);
       if(taskDatabase.getAllDatas()!=null)
           arrayList=taskDatabase.getAllDatas();
       else
           arrayList=new ArrayList<>();
        count=arrayList.size();
        final MyAdapter myAdapter=new MyAdapter(arrayList);
        recyclerView=findViewById(R.id.rView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
        final View viewAlert = LayoutInflater.from(this).inflate(R.layout.alert_dialogue, null);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        final AlertDialog alertDialog=new AlertDialog.Builder(this).setView(viewAlert).
                setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        count++;
                        EditText etHead=viewAlert.findViewById(R.id.tvHeadAlert);
                        EditText etContent=viewAlert.findViewById(R.id.tvContentAlert);

                        Data data =new Data(""+count,etHead.getText().toString(),etContent.getText().toString());
                        arrayList.add(data);
                        taskDatabase.insert(data);
                        myAdapter.notifyDataSetChanged();
                        etHead.setText("");
                        etContent.setText("");
}
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                Toast.makeText(MainActivity.this, "Notes not Saved!!!", Toast.LENGTH_SHORT).show();
            }
        }).create();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {alertDialog.show(); }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
