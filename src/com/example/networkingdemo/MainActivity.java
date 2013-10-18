package com.example.networkingdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;

public class MainActivity extends Activity {
    private static final String image_url = "http://semoetabang1.files.wordpress.com/2012/07/normal_kiss-baby.jpg";
	ImageView iv;
    		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		disableStrickPolicy();
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.ivImage);
		Bitmap b = downloadImage(image_url);
		iv.setImageBitmap(b);
	}
    /*
     * Android has a way of catching potentially locking operations, 
     * like disk access or internet, on the app’s main thread. 
     * StrictMode will throw a NetworkOnMainThreadException error when this happens.
     */
  	//for test disable strickpolicy
	private void disableStrickPolicy() {
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
	}

	private Bitmap downloadImage(String imageUrl) {
		//Convert string to URL
		URL url = getUrlFromSring(image_url);
		//Get input stream
		InputStream in = getInputStream(url);
	    //decode bitmap
		Bitmap bmp = decodeBitmap(in);
		//return bitmap result
		return bmp;
	}

	private Bitmap decodeBitmap(InputStream in) {
		Bitmap bitmap;
		try {
			//turn response into bitmap
			bitmap = BitmapFactory.decodeStream(in);
			//close stream
			in.close();
		} catch (IOException e) {
			bitmap = null;
			in = null;
			Log.d("Debug", "Decode failed");
		}
		return bitmap;
	}
	
	private InputStream getInputStream(URL url) {
		InputStream in;
		URLConnection conn;
		try {
			//open connnection
			conn = url.openConnection();
			conn.connect();
			in = conn.getInputStream();
		} catch (IOException e) {
			in = null;
			Log.d("Debug", "inputstream error");
		}
		return in;
	}

	private URL getUrlFromSring(String imageUrl) {
		URL url;
		try {
			url = new URL(image_url);
		} catch (MalformedURLException e) {
			url = null;
			Log.d("Debug", "image link doesn't work..");
		}
		return url;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
