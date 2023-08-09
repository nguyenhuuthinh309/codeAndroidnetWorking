package thinhnh.fpoly.demo8firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Demo8MainActivity extends AppCompatActivity {
    //B1-Cau hinh DB
    //b2- Ket noi voi Firebase và tao  ten cho collection
    TextView tvKQ;
    Context context=this;
    FirebaseFirestore database;

    String id="";
    thinhnh.fpoly.demo8firebase.ToDo toDo=null;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvKQ=findViewById(R.id.demo81TVKQ);
        database=FirebaseFirestore.getInstance();//khoi tao database
        insertFirebase();
      // updateFirebase();
      //  deleteFirebase();
        SelectDataFromFirebase();
    }
    public void insertFirebase()
    {
        id= UUID.randomUUID().toString();//lay 1 ma bat ky
        toDo=new thinhnh.fpoly.demo8firebase.ToDo(id,"title 3333333333333333333","content 2");

        HashMap<String,Object> mapToDo=toDo.convertHashMap();//goi ham convert
        database.collection("TODO").document(id)//dat ten cho document
                .set(mapToDo)//insert
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Demo8MainActivity.this, "thêm thành cong", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Demo8MainActivity.this, "them that bai",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void updateFirebase()
    {
        //dua du lieu can update
        id="83c50e85-5636-4270-9e9c-deec46108f6d";
        toDo=new thinhnh.fpoly.demo8firebase.ToDo(id,"sua title 1","sua content 1");
        database.collection("TODO")//ten bang du lieu
                .document(toDo.getId())//lay dong du lieu can update
                .update(toDo.convertHashMap())//update
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "update thanh cong",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "update that bat",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void deleteFirebase()
    {
        id="83c50e85-5636-4270-9e9c-deec46108f6d";
        database.collection("TODO")
                .document(id)
                .delete()//thuc hien xoa
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Delete thanh cong",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "delete that bai",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
    String strKQ="";
    public ArrayList<thinhnh.fpoly.demo8firebase.ToDo> SelectDataFromFirebase()
    {
        ArrayList<thinhnh.fpoly.demo8firebase.ToDo> list=new ArrayList<>();
        database.collection("TODO")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful())//neu lay du lieu thanh cong thi moi cho vao vong lap
                        {
                            strKQ="";
                            for (QueryDocumentSnapshot document: task.getResult())
                            {
                                thinhnh.fpoly.demo8firebase.ToDo toDo1=document.toObject(thinhnh.fpoly.demo8firebase.ToDo.class);
                                strKQ += "id: "+ toDo1.getId()+"\n" +
                                        ""+"id: "+ toDo1.getTitle()+"\n" +
                                        "";
                                list.add(toDo1);
                                Log.e("zzzzzz", toDo1.getId() + toDo1.getTitle());
                            }
                            Toast.makeText(context, strKQ, Toast.LENGTH_SHORT).show();
                            tvKQ.setText(strKQ);
                        }
                        else {
                            Toast.makeText(context, "Doc du lieu that bai",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
          return list;
    }

}