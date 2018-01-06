package my.edu.tarc.staff;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import my.edu.tarc.staff.Model.Subject;

public class Add_SubjectActivity extends AppCompatActivity {

    private EditText text_subject;
    private EditText text_code;
    private EditText text_desc;
    private Button btn_file;
    private Button btn_exercise;
    private Button btn_upload;
    private TextView messageText;
    private int serverResponseCode = 0;
    private ProgressDialog dialog = null;

    private String upLoadServerUri = null;
    private String imagepath = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__subject);

        btn_upload = (Button) findViewById(R.id.btn_upload);
        btn_file = (Button) findViewById(R.id.btn_file);
        text_subject = (EditText) findViewById(R.id.text_subject);
        text_code = (EditText) findViewById(R.id.text_code);
        text_desc = (EditText) findViewById(R.id.text_desc);
        messageText = (TextView) findViewById(R.id.messageText);

        upLoadServerUri = "";

       // btn_file.setOnClickListener(this);
        //btn_upload.setOnClickListener(this);
    }

    public void saveRecord(View v) {
        Subject subject = new Subject();
        String code, title, description;

        code = text_code.getText().toString();
        if(code.isEmpty()){
            text_code.setError("Please enter subject code");
            return;
        }

        title = text_subject.getText().toString();
        if(title.isEmpty()){
            text_subject.setError("Please enter subject title");
            return;
        }

        description = text_desc.getText().toString();
        if(description.isEmpty()){
            text_desc.setError("Please enter subject description");
            return;
        }

        subject.setCode(code);
        subject.setTitle(title);
        subject.setDescription(description);

        try {
            makeServiceCall(this, getString(R.string.insert_subject_url), subject);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void reset(View v){
        text_subject.setText("");
        text_code.setText("");
        text_desc.setText("");
        text_subject.requestFocus();
    }

    public void makeServiceCall(Context context, String url, final Subject subject) {
        //mPostCommentResponse.requestStarted();
        RequestQueue queue = Volley.newRequestQueue(context);

        //Send data
        try {
            StringRequest postRequest = new StringRequest(
                    Request.Method.POST,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            JSONObject jsonObject;
                            try {
                                jsonObject = new JSONObject(response);
                                int success = jsonObject.getInt("success");
                                String message = jsonObject.getString("message");
                                if (success==0) {
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), "Error. " + error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("code", subject.getCode());
                    params.put("title", subject.getTitle());
                    params.put("description", subject.getDescription());
                    return params;
                }

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Content-Type", "application/x-www-form-urlencoded");
                    return params;
                }
            };
            queue.add(postRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


