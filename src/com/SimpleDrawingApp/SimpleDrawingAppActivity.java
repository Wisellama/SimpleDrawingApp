package com.SimpleDrawingApp;

import android.app.Activity;
//import android.content.Intent;
import android.content.Context;
import android.os.Bundle;
//import android.view.View;
import android.view.MotionEvent;
import android.opengl.GLSurfaceView;
//import android.widget.EditText;

public class SimpleDrawingAppActivity extends Activity {
	/** Called when the activity is first created. */
	public final static String EXTRA_MESSAGE = "firstApp.main.MESSAGE";
	private GLSurfaceView mGLView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		mGLView = new FirstAppSurfaceView(this);
		setContentView(mGLView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLView.onResume();
	}

	/*public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);
		EditText editText = (EditText) findViewById(R.id.edit_message);
		String message = editText.getText().toString();
		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}*/
}

class FirstAppSurfaceView extends GLSurfaceView {
	private final float X_SCALE_FACTOR = 1f / (240); // determined through trial
													// and error
	private final float Y_SCALE_FACTOR = 1f / (240);
	private SimpleDrawingAppRenderer mRenderer;
	private float mPreviousX;
	private float mPreviousY;

	public FirstAppSurfaceView(Context context) {
		super(context);

		mRenderer = new SimpleDrawingAppRenderer();
		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float x = e.getX();
		float y = e.getY();

		float dx = 0;
		float dy = 0;

		switch (e.getAction()) {
		case MotionEvent.ACTION_MOVE:
			dx = x - mPreviousX;
			dx *= -1;
			mRenderer.xPosition = x*X_SCALE_FACTOR*-1 + 1;
			mRenderer.dx = dx*X_SCALE_FACTOR;

			dy = y - mPreviousY;
			dy *= -1;
			mRenderer.yPosition = y*Y_SCALE_FACTOR*-1 + 1.6f;
			mRenderer.dy = dy * Y_SCALE_FACTOR;
			
			Square s = new Square(x*X_SCALE_FACTOR*-1 + .975f, y*Y_SCALE_FACTOR*-1 + 1.5f);
			mRenderer.squareList.insertBack(s);
			
			requestRender();
			break;

		}

		mPreviousX = x;
		mPreviousY = y;
		return true;
	}
}