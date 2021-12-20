package com.example.fire_dire;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataPage extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private List<DataList> dataList;
    private RecyclerAdapter adapter;
    EditText dataSetsNumber;
    Button getData;
//    Button generatePDF;
//    Bitmap bmp, scaledbmp;
//    int pageWidth=1200;
    private int count = 0;
    int input_count =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_page);
//        int given_count;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getData = (Button) findViewById(R.id.getData);
//        generatePDF = (Button) findViewById(R.id.generatePDF);

        firebaseAuth = FirebaseAuth.getInstance();
//        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.pdf_image);
//        scaledbmp = Bitmap.createScaledBitmap(bmp, 1200, 518, false);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dataList = new ArrayList<>();
        adapter = new RecyclerAdapter(dataList);
        recyclerView.setAdapter(adapter);

        ActivityCompat.requestPermissions(DataPage.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataSetsNumber = (EditText) findViewById(R.id.dataCount);
                int given_count;
                try{
                    given_count = Integer.parseInt(dataSetsNumber.getText().toString());
                    input_count = given_count;
                    databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dataList.clear();
                            if(snapshot.exists()){
                                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                    DataList list = dataSnapshot.getValue(DataList.class);
                                    if(input_count==count && count!=0)
                                    {
                                        break;
                                    }
                                    else{
                                        dataList.add(list);
                                    }
                                    count++;
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
                catch (NumberFormatException e){
                    Toast.makeText(DataPage.this, "Provide valid number!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        generatePDF.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                createPDF();
//            }
//        });
    }

//    private void createPDF(){
//        runOnUiThread(new Runnable(){
//            public void run() {
//                int y_axis = 40;
//                int increment = 10;
//
//                PdfDocument myPdfDocument = new PdfDocument();
//                Paint paint = new Paint();
//                Paint forLinePaint = new Paint();
//                PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(250, 350, 1).create();
//                PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
//                Canvas canvas = myPage.getCanvas();
//
//                paint.setTextSize(15.5f);
//                paint.setColor(Color.rgb(0, 50, 250));
//
//                canvas.drawText("Fire Dire", 20, 20, paint);
//
//                forLinePaint.setStyle(Paint.Style.STROKE);
//                forLinePaint.setPathEffect(new DashPathEffect(new float[]{5, 5},0));
//                forLinePaint.setStrokeWidth(2);
//                canvas.drawLine(20, 40, 230, 40,forLinePaint);
//                paint.setTextSize(8.5f);
//                for(DataList i: dataList){
//                    System.out.println(i.getCo2());
//                    y_axis = y_axis+increment;
//                    canvas.drawText("CO2: "+i.getCo2(), 20, y_axis, paint);
//                    y_axis = y_axis+increment;
//                    canvas.drawText("Latitude: "+i.getLatitude(),20, y_axis, paint);
//                    y_axis = y_axis+increment;
//                    canvas.drawText("Longitude: "+i.getLongitude(), 20, y_axis, paint);
//                    y_axis = y_axis+increment;
//                    canvas.drawText("Smoke: "+i.getSmoke(), 20, y_axis, paint);
//                    y_axis = y_axis+increment;
//                    canvas.drawText("Temperature: "+i.getTemperature(), 20, y_axis, paint);
//                    y_axis = y_axis+10;
//                    canvas.drawLine(20, y_axis, 230, y_axis,forLinePaint);
//                }
//
//                myPdfDocument.finishPage(myPage);
////                File file = new File(this.getExternalFilesDir("/"), "FireDire.pdf");
//                File file = new File(Environment.getExternalStorageDirectory(),"/FireDire.pdf");
//
//                try{
//                    myPdfDocument.writeTo(new FileOutputStream(file));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                myPdfDocument.close();
//            }
//        });
//    }



//    private void createPDF() {
////        generatePDF.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//                PdfDocument document = new PdfDocument();
//                final Paint paint = new Paint();
//                Paint titlePaint = new Paint();
//
//                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
//                PdfDocument.Page page = document.startPage(pageInfo);
//
//                final Canvas canvas = page.getCanvas();
//                canvas.drawBitmap(scaledbmp,0,0, paint);
//                titlePaint.setTextAlign(Paint.Align.CENTER);
//                titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
//                titlePaint.setTextSize(70);
//                canvas.drawText("Temperature Data",pageWidth/2, 270, titlePaint);
//
//                paint.setStyle(Paint.Style.STROKE);
//                paint.setStrokeWidth(2);
//                canvas.drawRect(20,780,pageWidth-20,860,paint);
//
//                paint.setTextAlign(Paint.Align.LEFT);
//                paint.setStyle(Paint.Style.FILL);
//                canvas.drawText("Date", 40, 830, paint);
//                canvas.drawText("Time", 500, 830, paint);
//                canvas.drawText("Temperature", 800, 830, paint);
//
//                canvas.drawLine(480, 790, 480, 850, paint);
//                canvas.drawLine(780, 790, 780, 850, paint);
//
//                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        int y = 950;
//                        if(snapshot.exists()){
//                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                                y+=80;
//                                canvas.drawText((String) dataSnapshot.child("co2").getValue(), 40, y, paint);
//                                canvas.drawText((String) dataSnapshot.child("smoke").getValue(), 500, y, paint);
//                                canvas.drawText((String) dataSnapshot.child("latitude").getValue(), 800, y, paint);
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//                document.finishPage(page);
//
//                File file = new File(Environment.getExternalStorageDirectory(),"/Hello.pdf");
//                try{
//                    document.writeTo(new FileOutputStream(file));
//                }catch(IOException e){
//                    e.printStackTrace();
//                }
//                document.close();
//            }
////        });
////    }

    private void alertBox(){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);

        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        firebaseAuth.signOut();
                        Toast.makeText(DataPage.this, "You are logged out!", Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        startActivity(new Intent(DataPage.this, MainActivity.class));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void Logout(){
        alertBox();
    }

    @Override
    public void onBackPressed() {
        alertBox();
    }
}
