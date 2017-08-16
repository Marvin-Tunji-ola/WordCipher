    package com.softified.wordcipher;

    import android.content.Intent;
    import android.os.Handler;
    import android.os.Message;
    import android.support.constraint.ConstraintLayout;
    import android.support.v7.app.AppCompatActivity;
    import android.os.Bundle;
    import android.text.Layout;
    import android.view.MotionEvent;
    import android.view.View;
    import android.widget.ProgressBar;
    import android.widget.TextView;

    public class MainActivity extends AppCompatActivity {
       ///////START BOOTLOADER VARIABLE////////////
        Handler h = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                TextView proceed = (TextView) findViewById(R.id.proceed);
                ProgressBar loader = (ProgressBar) findViewById(R.id.loader);
                ConstraintLayout wellcome_layout = (ConstraintLayout) findViewById(R.id.wellcome_layout);

                proceed.setText("Touch to Proceed");
                wellcome_layout.removeView(loader);
                wellcome_layout.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        setContentView(R.layout.activity_main_menu);
                        return true;
                    }


                });
            }
        };
        ///////END BOOTLOADER VARIABLE////////////


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            //////START BOOTLOADER////////////
            bootloader();
            //////END BOOTLOADER////////////

        }


        private void bootloader(){
            Runnable r = new Runnable(){
                @Override
                public void run() {
                    long load = System.currentTimeMillis()+5000;
                    while(System.currentTimeMillis() < load) {
                        synchronized (this) {
                            try {
                                wait(load - System.currentTimeMillis());
                            } catch (Exception e) { }
                        }
                    }
                    h.sendEmptyMessage(0);
                }
            };

            Thread thread = new Thread(r);
            thread.start();

        }

        public void toNormalGame(View view){
            Intent i = new Intent(getApplicationContext(), NormalGameActivity.class);
            startActivity(i);
        }
    }

