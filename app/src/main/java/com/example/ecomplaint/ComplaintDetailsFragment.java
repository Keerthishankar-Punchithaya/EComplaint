package com.example.ecomplaint;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComplaintDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ComplaintDetailsFragment extends BottomSheetDialogFragment {


    // variables for our buttons.
    Button generatePDFbtn;

    // declaring width and height
    // for our PDF file.
    int pageHeight = 1120;
    int pagewidth = 792;

    // creating a bitmap variable
    // for storing our images
    Bitmap bmp, scaledbmp;

    // constant code for runtime permissions
    private static final int PERMISSION_REQUEST_CODE = 200;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    com.example.ecomplaint.Complaint present;

    public ComplaintDetailsFragment() {
        // Required empty public constructor
    }

    public ComplaintDetailsFragment(com.example.ecomplaint.Complaint present){
        this.present=present;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ComplaintDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ComplaintDetailsFragment newInstance(String param1, String param2) {
        ComplaintDetailsFragment fragment = new ComplaintDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_complaint_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();

        TextView title=view.findViewById(R.id.details_title);
        title.setText(present.getTitle());

        TextView name=view.findViewById(R.id.details_name);
        name.setText(present.getName());

        TextView regno=view.findViewById(R.id.details_regno);
        regno.setText(present.getRegno());

        TextView incident=view.findViewById(R.id.details_info);
        incident.setText(present.getIncident_info());

        TextView ComplaintFrom=view.findViewById(R.id.complaintFrom);
        ComplaintFrom.setText(present.getComplaintFrom().getName());

        TextView status=view.findViewById(R.id.details_status);
        status.setText(present.getStatus().toUpperCase(Locale.ROOT));

//        TextView expert=view.findViewById(R.id.details_expert);
//        expert.setText("EComplaint");
//        Button pdf=view.findViewById(R.id.printbtn);
//        pdf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(), "PDF button clicked", Toast.LENGTH_SHORT).show();
//            }
//        });
        // initializing our variables.
        generatePDFbtn = view.findViewById(R.id.printbtn);
        bmp = BitmapFactory.decodeResource(getResources(), R.drawable.logo_pdf1);
        scaledbmp = Bitmap.createScaledBitmap(bmp, 140, 140, false);

        // below code is used for
        // checking our permissions.
        if (checkPermission()) {
            Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
        } else {
            requestPermission();
        }

        generatePDFbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling method to
                // generate our PDF file.
                Toast.makeText(getContext(), "PDF button clicked", Toast.LENGTH_SHORT).show();
                generatePDF();
//                Intent i = new Intent(getContext(),generatePdf.class);
//                startActivity(i);
            }
        });


        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.i("this",currentUser.getDisplayName());

        //HomeFragment.arrayList.clear();
        //HomeFragment.adapter.notifyDataSetChanged();





    }

    private void generatePDF() {
        // creating an object variable
        // for our PDF document.
        PdfDocument pdfDocument = new PdfDocument();

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        Paint paint = new Paint();
        Paint title = new Paint();

        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        PdfDocument.PageInfo mypageInfo = new PdfDocument.PageInfo.Builder(pagewidth, pageHeight, 1).create();

        // below line is used for setting
        // start page for our PDF file.
        PdfDocument.Page myPage = pdfDocument.startPage(mypageInfo);

        // creating a variable for canvas
        // from our page of PDF.
        Canvas canvas = myPage.getCanvas();

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas.drawBitmap(scaledbmp, 56, 40, paint);

        // below line is used for adding typeface for
        // our text which we will be adding in our PDF file.
        title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

        // below line is used for setting text size
        // which we will be displaying in our PDF file.
        title.setTextSize(15);

        // below line is sued for setting color
        // of our text inside our PDF file.
        title.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));

        // below line is used to draw text in our PDF file.
        // the first parameter is our text, second parameter
        // is position from start, third parameter is position from top
        // and then we are passing our variable of paint which is title.
        canvas.drawText("Register your complaints easily online!", 209, 100, title);
        canvas.drawText("EComplaint", 209, 80, title);

        // similarly we are creating another text and in this
        // we are aligning this text to center of our PDF file.
        title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL));
        title.setColor(ContextCompat.getColor(getContext(), R.color.purple_200));
        title.setTextSize(15);

        // below line is used for setting
        // our text to center of PDF.
        title.setTextAlign(Paint.Align.CENTER);
        canvas.drawText("This is sample document which we have created.", 396, 560, title);

        canvas.drawText("Title: "+present.getTitle(), 396, 580, title);
        canvas.drawText("Address: "+present.getName(), 396, 600, title);
        canvas.drawText("Phone Number: "+present.getRegno(), 396, 620, title);
        canvas.drawText("Complaint Details: "+present.getIncident_info(), 396, 640, title);
        canvas.drawText("Registered By: "+present.getComplaintFrom().getName(), 396, 660, title);
        canvas.drawText("Status: "+present.getStatus().toUpperCase(Locale.ROOT), 396, 680, title);






        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage);

        // below line is used to set the name of
        // our PDF file and its path.
        File file = new File(Environment.getExternalStorageDirectory(), "YourComplaint.pdf");

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(new FileOutputStream(file));

            // below line is to print toast message
            // on completion of PDF generation.
            Toast.makeText(getContext(), "PDF file generated successfully.", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            // below line is used
            // to handle error
            e.printStackTrace();
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close();
    }

    private boolean checkPermission() {
        // checking of permissions.
        int permission1 = ContextCompat.checkSelfPermission(getContext(), WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(getContext(), READ_EXTERNAL_STORAGE);
        return permission1 == PackageManager.PERMISSION_GRANTED && permission2 == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        // requesting permissions if not provided.
        ActivityCompat.requestPermissions(getActivity(), new String[]{WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0) {

                // after requesting permissions we are showing
                // users a toast message of permission granted.
                boolean writeStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (writeStorage && readStorage) {
                    Toast.makeText(getContext(), "Permission Granted..", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
//                    finish();
                }
            }
        }
    }
}