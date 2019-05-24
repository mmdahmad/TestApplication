package com.amd.mmd.myapplication.RX;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amd.mmd.myapplication.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RXActivity extends AppCompatActivity {

    private final String Tag = "myApp";
    private String greeting = "Hello From RxJava";
    private Observable<String> myObservable;
    private Observer<String> myObserver;
    private TextView TvObservable;
    private Button Btn_Observsr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);
        TvObservable = (TextView) findViewById(R.id.tvObservable);
        Btn_Observsr = (Button) findViewById(R.id.btnObservsr);

        myObservable = Observable.just(greeting);

        myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.i(Tag, "onSubscribe invoked");
            }

            @Override
            public void onNext(String s) {
                Log.i(Tag, "onNext invoked");
                TvObservable.setText(s);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(Tag, "onError invoked");
            }

            @Override
            public void onComplete() {
                Log.i(Tag, "onComplete invoked");
            }
        };


        Btn_Observsr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myObservable.subscribe(myObserver);
            }
        });

    }
}
