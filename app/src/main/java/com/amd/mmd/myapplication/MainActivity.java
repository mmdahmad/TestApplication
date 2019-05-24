package com.amd.mmd.myapplication;

import android.app.VoiceInteractor;
import android.content.Context;
import android.os.AsyncTask;
import android.preference.PreferenceActivity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.amd.mmd.myapplication.model.User;
import com.amd.mmd.myapplication.model.UserResponse;
import com.amd.mmd.myapplication.webService.APIClient;
import com.amd.mmd.myapplication.webService.APIInterface;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView Txt;
    private String url_Soap = "http://172.17.100.2/WebService1/WebService1.asmx/HelloWorld";
//    private String url_Volley = "http://date.jsontest.com";
private String url_Volley = "https://jsonplaceholder.typicode.com/posts";
    private Context context;
    private String res = "";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // this is first Commit to file in project in github
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Txt = (TextView) findViewById(R.id.txt);

        Integer[] arrayOfInteger = new Integer[2];
        arrayOfInteger[0] = 2;
        arrayOfInteger[1] = 5;

//        new TestWSrvcWith_Soap().execute();
//        new TestWSrvcWith_SoapInteger(arrayOfInteger).execute();
//        new TestWSrvcWith_Volley().execute();
        TestWSrvcWith_AsyncHttpClient();
//        TestWSrvcWith_Retrofit();
//        TestWSrvcWith_Retrofit_2();

    }

    private class TestWSrvcWith_Soap extends AsyncTask<String, String, Object> {
        private final String METHOD_NAME = "HelloWorld";
        private final String NAMESPACE = "http://10.0.2.2:8080/";
        private final String SOAP_ACTION = "http://10.0.2.2:8080/HelloWorld";
        private String URL = url_Soap;

        private TestWSrvcWith_Soap() {
        }

        protected Object doInBackground(String[] paramArrayOfString) {
            SoapObject localSoapObject2;
            try {
                SoapObject localSoapObject1 = new SoapObject("http://172.17.100.2/WebService1/WebService1.asmx/", "HelloWorld");
                publishProgress(new String[]{"0"});
                PropertyInfo localPropertyInfo = new PropertyInfo();
                localPropertyInfo.setName("prmStringData");
//                localPropertyInfo.setValue(paramArrayOfString[0]);
                localPropertyInfo.setValue("");
                localPropertyInfo.setType(String.class);
                localSoapObject1.addProperty(localPropertyInfo);
                publishProgress(new String[]{"1"});
                SoapSerializationEnvelope localSoapSerializationEnvelope = new SoapSerializationEnvelope(110);
                localSoapSerializationEnvelope.dotNet = true;
                localSoapSerializationEnvelope.encodingStyle = "http://schemas.xmlsoap.org/soap/encoding/";
                localSoapSerializationEnvelope.implicitTypes = false;
                publishProgress(new String[]{"2"});
                localSoapSerializationEnvelope.setOutputSoapObject(localSoapObject1);
                HttpTransportSE localHttpTransportSE = new HttpTransportSE(this.URL);
                publishProgress(new String[]{"3"});
                localHttpTransportSE.call("http://172.17.100.2/WebService1/WebService1.asmx/HelloWorld", localSoapSerializationEnvelope);
                publishProgress(new String[]{"4"});
                localSoapObject2 = (SoapObject) localSoapSerializationEnvelope.bodyIn;
            } catch (Exception localException) {
                localSoapObject2 = null;
            }

            return localSoapObject2;
        }

        protected void onPostExecute(Object paramObject) {
            SoapObject localSoapObject = (SoapObject) paramObject;

            Txt.setText(localSoapObject.getProperty(0).toString().trim());
        }

        protected void onProgressUpdate(String[] paramArrayOfString) {

        }
    }

    private class TestWSrvcWith_SoapInteger extends AsyncTask<Integer, Integer, Object> {
        private final String METHOD_NAME = "HelloWorld";
        private final String NAMESPACE = "http://10.0.2.2:8080/";
        private final String SOAP_ACTION = "http://10.0.2.2:8080/Pow";
        private String URL = url_Soap;
        private int X = 0;
        private int Y = 0;

        public TestWSrvcWith_SoapInteger(Integer[] arrayOfInteger) {
            X = arrayOfInteger[0];
            Y = arrayOfInteger[1];
        }

        @Override
        protected Object doInBackground(Integer[] arrayOfInteger) {
            SoapObject localSoapObject2;
            try {
                SoapObject localSoapObject1 = new SoapObject("http://172.17.100.2/WebServiceStore/WebServiceStore1.asmx", "Pow");
//                publishProgress(new String[]{"0"});
                PropertyInfo localPropertyInfo = new PropertyInfo();
                localPropertyInfo.setName("X");
//                localPropertyInfo.setValue(paramArrayOfString[0]);
                localPropertyInfo.setValue(X);
                localPropertyInfo.setType(Integer.class);
                localSoapObject1.addProperty(localPropertyInfo);
//                publishProgress(new String[]{"1"});

// ................................new ...............................
                PropertyInfo localPropertyInfo2 = new PropertyInfo();
                localPropertyInfo2.setName("Y");
                localPropertyInfo2.setValue(Y);
                localPropertyInfo2.setType(Integer.class);
                localSoapObject1.addProperty(localPropertyInfo2);
// ..................................................................

                SoapSerializationEnvelope localSoapSerializationEnvelope = new SoapSerializationEnvelope(110);
                localSoapSerializationEnvelope.dotNet = true;
                localSoapSerializationEnvelope.encodingStyle = "http://schemas.xmlsoap.org/soap/encoding/";
                localSoapSerializationEnvelope.implicitTypes = false;
//                publishProgress(new String[]{"2"});
                localSoapSerializationEnvelope.setOutputSoapObject(localSoapObject1);
                HttpTransportSE localHttpTransportSE = new HttpTransportSE(this.URL);
//                publishProgress(new String[]{"3"});
                localHttpTransportSE.call("http://172.17.100.2/WebServiceStore/WebServiceStore1.asmx/Pow", localSoapSerializationEnvelope);
//                publishProgress(new String[]{"4"});
                localSoapObject2 = (SoapObject) localSoapSerializationEnvelope.bodyIn;
            } catch (Exception localException) {
                localSoapObject2 = null;
            }

            return localSoapObject2;
        }


        protected void onPostExecute(Object paramObject) {
            SoapObject localSoapObject = (SoapObject) paramObject;

//            Txt.setText(localSoapObject.getProperty(0).toString().trim());
        }

        protected void onProgressUpdate(String[] paramArrayOfString) {

        }
    }

    private class TestWSrvcWith_Volley extends AsyncTask<String, Object, String> {
        @Override
        protected String doInBackground(String... params) {
//            return getAccessCode(params[0], params[1], params[2]);
            testWbsrvs("http://172.17.100.2/Webservice1/WebService1.asmx/HelloWorld");
            return "";
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

           Txt.setText(result);
        }
    }

    private void testWbsrvs(String checkUrl) {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, checkUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.has("error")){
                    Toast.makeText(getApplicationContext(), "error1", Toast.LENGTH_LONG).show();
                } else if(response.has("id") ) {
                    try {
                       Txt.setText(response.getString("time"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "error2", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "not result", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "error3"+error, Toast.LENGTH_LONG).show();

            }
        }
        );
        queue.add(request);

    }

    void TestWSrvcWith_AsyncHttpClient(){
//        AsyncHttpClient async_client = new AsyncHttpClient();
//        async_client.get("http://127.17.100.2/Webservice1/WebService1.asmx/Pow", new TextHttpResponseHandler(){
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//
//                Toast.makeText(getApplicationContext(), "result : " + responseString, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
//            }
//
////            @Override
////            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
////                super.onSuccess(statusCode, headers, response);
//////                if(response.get)
////                try {
//////                    Txt.setText(response.getString("id"));
////                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
////                } catch (Exception e) {
////                    e.printStackTrace();
////                }
////            }
////
////            @Override
////            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
////                super.onFailure(statusCode, headers, responseString, throwable);
////                Toast.makeText(getApplicationContext(), "error"+responseString, Toast.LENGTH_LONG).show();
////            }
//
//        });
        try {
            AsyncHttpClient httpClient = new AsyncHttpClient();
            httpClient.addHeader("Accept", "application/json");
//        JSONObject json_req = new JSONObject();
//        try {
//            json_req.put("x", 2);
//            json_req.put("y", 5);
//            StringEntity entity = new StringEntity(json_req.toString());
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
            RequestParams json_req = new RequestParams();
            json_req.put("x", 2);
            json_req.put("y", 5);

            httpClient.post("http://172.17.100.2/Webservice1/WebService1.asmx/Pow", json_req, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {

                    Toast.makeText(getApplicationContext(), "result : " + responseString, Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public class TestWSrvcWith_AsyncTask extends AsyncTask<String, Boolean, String>{
//        private final String Url = "http://127.17.100.2/Webservice1/WebService1.asmx/Pow";
//        String X;
//        String Y;
//        public TestWSrvcWith_AsyncTask(String x, String y){
//            X = x;
//            Y = y;
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            return "";
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            Toast.makeText(getApplicationContext(), "result :" + s, Toast.LENGTH_LONG).show();
//        }
//    }

    private void TestWSrvcWith_Retrofit(){
        MyApp.getMyApi().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call,
                                   @NonNull retrofit2.Response<List<Post>> response) {
                List<Post> posts = response.body();
                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, Throwable t) {
               t.printStackTrace();
                Toast.makeText(getApplicationContext(), "error : "+t, Toast.LENGTH_LONG).show();
            }
        });
// .......................................................................
//        MyApp.getMyApi().getPosts().enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(@NonNull Call<Post> call,
//                                   @NonNull retrofit2.Response<Post> response) {
////                List<Post> posts = response.body();
//                Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onFailure(@NonNull Call<Post> call, Throwable t) {
//                t.printStackTrace();
//                Toast.makeText(getApplicationContext(), "error : "+t, Toast.LENGTH_LONG).show();
//            }
//        });


    }

    private void TestWSrvcWith_Retrofit_2(){
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<List<User>> call = apiInterface.getUsers();

//        call.enqueue(new Callback<UserResponse>() {
//            @Override
//            public void onResponse(Call<UserResponse> call, retrofit2.Response<UserResponse> response) {
//                if(response.isSuccessful()){
////                    response.body().getUsers();
//                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<UserResponse> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "error : "+t, Toast.LENGTH_LONG).show();
//            }
//        });
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                if(response.isSuccessful()){
//                    response.body().getUsers();
                    Toast.makeText(getApplicationContext(), "ok", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error : "+t, Toast.LENGTH_LONG).show();
            }
        });
    }


}
