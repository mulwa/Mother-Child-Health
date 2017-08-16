package com.example.mulwa.mc_care;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.mulwa.mc_care.Adapters.AppController;
import com.example.mulwa.mc_care.Adapters.DividerItemDecoration;
import com.example.mulwa.mc_care.Adapters.ExperAdapter;
import com.example.mulwa.mc_care.Pojo.Expert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search_Specialist extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView mRecyclerview;
    private EditText search_name;
    private String tag_json_arry = "json_array_req";
    private ImageButton search_btn;
    private String BASE_URL = "http://kfarmer.co.nf/search_specialist.php?name=";
    private String url;
    public ProgressDialog mProgressDialog;
    private List<Expert> experts =  new ArrayList<>();
    private ExperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__specialist);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        mRecyclerview = (RecyclerView) findViewById(R.id.specific_specialist);
        search_name = (EditText) findViewById(R.id.ed_searchName);
        search_btn = (ImageButton) findViewById(R.id.im_search);

        mRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerview.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL ));

        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Verify Specialist");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideKeyboard(view);
                if(TextUtils.isEmpty(search_name.getText().toString().trim())){
                    Toast.makeText(getApplicationContext(),"Please Enter Specialist Name",Toast.LENGTH_LONG).show();
                }else {
                    getData();

                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    private void getData(){
        url = BASE_URL+search_name.getText().toString().trim();
        showProgressDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response == null){
                    hideProgressDialog();
                    Toast.makeText(getApplicationContext(),"No Specialist found",Toast.LENGTH_LONG).show();

                }

                Log.d("me",response.toString());
                hideProgressDialog();
                try {
                    JSONArray array = response.getJSONArray("specialist");
                    for(int i = 0; i<array.length(); i++){
                        JSONObject col  =  array.getJSONObject(i);
                        int id = col.getInt("id");
                        String  name =  col.getString("name");
                        String specialist = col.getString("specialist");
                        String phoneNo = col.getString("phone_number");
                        String email =  col.getString("email");
                        String date_registered = col.getString("date_registered");

                        Expert expert = new Expert(id,name,specialist,phoneNo,email,date_registered);
                        experts.add(expert);
                        addToAdapter();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("me","No errro"+error.getMessage());
                hideProgressDialog();
                if(error  instanceof TimeoutError || error instanceof NoConnectionError){
                    showToast(getApplicationContext(),"Time out error due  to Connection error");
                }else if( error  instanceof ServerError){
                    showToast(getApplicationContext(),"Server Not Responding");
                }else if(error instanceof NetworkError){
                    showToast(getApplicationContext(),"No Network Connection");
                }else if(error instanceof ParseError){
                    showToast(getApplicationContext(),"Parse Error");
                }


            }
        }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();
                return params;

            }
        };



        AppController.getInstance().addToRequestQueue(jsonObjectRequest,tag_json_arry);


    }
    private void addToAdapter(){
        adapter = new ExperAdapter(experts,getApplicationContext());
        mRecyclerview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Please Wait Getting Specialist");
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }
    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
    private void showToast(Context context,String msg){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }
}
