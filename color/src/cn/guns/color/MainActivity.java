package cn.guns.color;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener {
	SeekBar sBarR;
	SeekBar sBarG;
	SeekBar sBarB;
	SeekBar sBarAlpha;
	TextView TextRed;
	TextView TextGreen;
	TextView TextBlue;
	TextView Textalpha;
	TextView displayhex;
	String color;
	Drawable drawable;
	int colorAlpha;
	int colorR;
	int colorG;
	int colorB;
	String Rtag;
	String Gtag;
	String Btag;
	String Atag;
	
	View colorView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(16974123);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 填充标题栏，delete the title
		setContentView(R.layout.activity_main);
		/*Obtain the Views*/
		sBarR=(SeekBar)this.findViewById(R.id.seekBarR);
		sBarG=(SeekBar)this.findViewById(R.id.seekBarG);
		sBarB=(SeekBar)this.findViewById(R.id.seekBarB);
		sBarAlpha=(SeekBar)this.findViewById(R.id.seekBarAlpha);
		TextRed = (TextView)this.findViewById(R.id.editR);
		TextGreen=(TextView)this.findViewById(R.id.editG);
		TextBlue=(TextView)this.findViewById(R.id.editB);
		Textalpha = (TextView)this.findViewById(R.id.editAlpha);
		displayhex= (TextView)this.findViewById(R.id.displayHex);
		colorView = this.findViewById(R.id.color);
		
		/*set the default color and refresh display*/
		colorAlpha = 255;
		colorR=127;
		colorG=127;
		colorB=127;
		refresh();

		/*Listen the seekBar*/
		sBarAlpha.setOnSeekBarChangeListener(this);
		sBarR.setOnSeekBarChangeListener(this);
		sBarG.setOnSeekBarChangeListener(this);
		sBarB.setOnSeekBarChangeListener(this);
	}
	/*
	protected void onStop(){
		finish();
		System.exit(0);
	}
	protected void onPause(){
		finish();
		System.exit(0);
	}
	*/
	//当用户用手移动滑动条，改变进度值时，触发该响应。progress表示当前进度值。  
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromTouch) {
		if (seekBar.equals(this.sBarR)) {
			this.colorR=progress;
		}
		if (seekBar.equals(this.sBarG)) {
			this.colorG=progress;
		}
		if (seekBar.equals(this.sBarB)) {
			this.colorB=progress;
		}
		if (seekBar.equals(this.sBarAlpha)) {
			this.colorAlpha=progress;
		}
	}

	 //用户开始对SeekBar进行触屏操作时触发响应  
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	 //用户停止对SeekBar进行触屏操作时触发响应  
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		refresh();
	}
	public void refresh() {
		Textalpha.setText(String.valueOf(colorAlpha));
		TextRed.setText(String.valueOf(colorR));
		TextGreen.setText(String.valueOf(colorG));
		TextBlue.setText(String.valueOf(colorB));
		if (colorR< 16) {
			Rtag="0";
		}
		else {
			Rtag="";
		}
		if (colorG< 16) {
			Gtag="0";
		}
		else {
			Gtag="";
		}
		if (colorB< 16) {
			Btag="0";
		}
		else {
			Btag="";
		}
		if (colorAlpha< 16) {
			Atag="0";
		}
		else {
			Atag="";
		}
		color= "#" + Atag + String.valueOf(Integer.toHexString(colorAlpha)) +Rtag + String.valueOf(Integer.toHexString(colorR)) + Gtag + String.valueOf(Integer.toHexString(colorG)) + Btag + String.valueOf(Integer.toHexString(colorB)) ;
		this.displayhex.setText(color.toUpperCase());
		colorView.setBackgroundColor(Color.parseColor(color));

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		
		return true;
	}
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {                  
		case R.id.menu_settings:
			/*send suggests mail*/
			Intent i = new Intent(Intent.ACTION_SEND);  
			//i.setType("text/plain"); //use this line for testing in the emulator  
			i.setType("message/rfc822") ; // use from live device
			String emailAddr=getString(R.string.EmailAddress);
			i.putExtra(Intent.EXTRA_EMAIL, new String[]{emailAddr});
			String subject=getString(R.string.EmailSubject);
			i.putExtra(Intent.EXTRA_SUBJECT,subject);  
			i.putExtra(Intent.EXTRA_TEXT,"");  
			startActivity(Intent.createChooser(i, ""));
			break;
		case R.id.review:
			String appIdentify = "88a51b939d9b4c12a7cf7249ebaa4b45";
			 Uri appUri = Uri.parse("mstore:http://app.meizu.com/phone/apps/" + appIdentify);
			 Intent intent = new Intent(Intent.ACTION_VIEW, appUri);
			 startActivity(intent);
			break;
		  }
		  return true;
		}
}
