package com.example.jatinderkumar.projectxretry;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.jatinderkumar.projectxretry.R.color.colorAccent;

public class MainActivity extends AppCompatActivity {
    Button btn;
    Toolbar toolbar;
    int image[] = {R.drawable.h6w29,R.drawable.js,R.drawable.img1,R.drawable.img2,R.drawable.img3};
    String carName[] = {"Merceedes","Honda","Maruti","Ford","BMW"};
    String carDescription[] = {"Merceedes","Honda","Maruti","Ford","BMW"};
    ActionBar actionBar;
    Handler progressHandler = new Handler();
    int progressBarStatus =0;
    long fileSize =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn =(Button) findViewById(R.id.btn1);
        toolbar =(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setTitle("Project ZX");
        actionBar.show();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setMessage("CARS");
                dialog.setTitle("CARS Portal");
                dialog.setCancelable(true);
                dialog.setIcon(R.drawable.ic_launcher_background);
                View view = getLayoutInflater().inflate(R.layout.lay,null);
                dialog.setView(view);
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                       builder.setMessage("File DownLoad Portal");
                       builder.setTitle("Cars Id");
                       View vie = getLayoutInflater().inflate(R.layout.lay,null);
                       builder.setView(vie);
                       builder.setCancelable(true);
                       builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, final int which) {
                              final ProgressDialog p = new ProgressDialog(MainActivity.this);
                               p.setMessage("File Downloading");
                               p.setCancelable(true);
                               p.setProgress(0);
                               p.setMax(100);
                               p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                               p.show();
                               progressBarStatus =0;
                               fileSize =0;
                               new Thread(new Runnable() {
                                   @Override
                                   public void run() {
                                       while (progressBarStatus<=100){
                                           progressBarStatus = doSome();
                                           try {
                                               Thread.sleep(100);
                                           }catch (InterruptedException e)
                                           {
                                               e.printStackTrace();
                                           }
                                           progressHandler .post(new Runnable() {
                                               @Override
                                               public void run() {
                                                   p.setProgress(progressBarStatus);

                                               }
                                           });

                                       }
                                       if(progressBarStatus >=100)
                                       {
                                           try {
                                               Thread.sleep(5000);
                                           }catch (InterruptedException e)
                                           {
                                               e.printStackTrace();
                                           }
                                           p.dismiss();
                                       }
                                   }
                               }).start();


                           }
                       });
                       builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               dialog.dismiss();

                           }
                       });
                       builder.create();
                       builder.show();


                    }
                });
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        LayoutInflater l = getLayoutInflater();
                        View la = l.inflate(R.layout.lay,(ViewGroup) findViewById(R.id.la));
                        TextView textView =(TextView) la.findViewById(R.id.tc);
                        textView.setText("This is a customized Toast");
                        Toast toast = new Toast(getApplicationContext());
                       // toast.setText((CharSequence) textView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setView(la);
                        toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
                        toast.show();
                    }
                });

                dialog.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        View view = getLayoutInflater().inflate(R.layout.list, null);
                        builder.setView(view);
                        builder.setTitle("Car Details");
                        builder.setMessage("This is The List of Cars");
                        builder.setCancelable(true);
                        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        ListView listView = (ListView) view.findViewById(R.id.listView);
                        ArrayList<Cars> cars = new ArrayList<Cars>();
                        for (int i = 0; i < 5; i++) {
                            Cars d = new Cars();
                            d.setCarName(carName[i]);
                            d.setCarDetails(carDescription[i]);
                            d.setCarImage(image[i]);
                            cars.add(d);

                        }
                        ListCar adapter = new ListCar(cars, MainActivity.this);
                        listView.setAdapter(adapter);
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        });
                        builder.create();
                        builder.show();


                    }
                });
                dialog.create();
                dialog.show();
            }
        });

    }

    private int doSome() {
        while (fileSize <= 100000)
        {
            fileSize++;
            if(fileSize == 10000)
            {
                return 10;
            }
            else if(fileSize == 20000)
            {
                return 20;
            }
            else if (fileSize == 30000)
            {
                return 30;
            }
            else if (fileSize == 40000)
            {
                return 40;
            }
            else  if(fileSize == 50000)
            {
                return 50;
            }
            else  if(fileSize == 60000)
            {
                return 60;
            }
            else  if(fileSize == 70000)
            {
                return 70;
            }
            else if(fileSize == 80000)
            {
                return 80;
            }
            else if (fileSize == 90000)
            {
                return 90;
            }

        }
        return  100;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);


        return true;
    }
}
