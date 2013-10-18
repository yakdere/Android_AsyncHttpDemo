package com.example.networkingdemo;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.BinaryHttpResponseHandler;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String image_url = "http://semoetabang1.files.wordpress.com/2012/07/normal_kiss-baby.jpg";
	ImageView iv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		iv = (ImageView) findViewById(R.id.ivImage);
		downloadImageAsync(image_url);
	}
	
	private void downloadImageAsync(String imageUrl) {
		
	AsyncHttpClient client = new AsyncHttpClient();
	String[] imageTypes = new String[] { "image/jpeg" };
	//It is a binary file 
	client.get(imageUrl, new BinaryHttpResponseHandler(imageTypes) {
		@Override
		public void onSuccess(byte[] imageBytes) {
			InputStream in = new ByteArrayInputStream(imageBytes);
			Bitmap bm = BitmapFactory.decodeStream(in);
			iv.setImageBitmap(bm);
		}
		@Override
		public void onFailure(Throwable t) {
			Toast.makeText(MainActivity.this, "Image could not be found!", Toast.LENGTH_SHORT).show();
			Log.d("Debug", "Image opening failed!");
		}
	});
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
