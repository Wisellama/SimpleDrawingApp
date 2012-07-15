package com.SimpleDrawingApp;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import com.SimpleDrawingApp.list.*;

public class SimpleDrawingAppRenderer implements GLSurfaceView.Renderer {
	public DList squareList = new DList();
	public float xPosition, dx;
	public float yPosition, dy;

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glClearColor(0f, 0f, 0f, 1.0f);
	}

	public void onDrawFrame(GL10 gl) {
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();
		GLU.gluLookAt(gl, 0, 0, -5, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
		
		try {
			ListNode currentNode = squareList.front();
			while(currentNode.isValidNode()) {
				Square s = (Square)currentNode.item();
				s.Draw(gl);
				
				currentNode = currentNode.next();
			}
		}
		catch (InvalidNodeException e) {
			System.err.println(e + "in onDrawFrame");
		}

	}

	public void onSurfaceChanged(GL10 gl, int width, int height) {
		gl.glViewport(0, 0, width, height);

		float ratio = (float) width / height;
		gl.glMatrixMode(GL10.GL_PROJECTION); // set matrix to projection mode
		gl.glLoadIdentity(); // reset the matrix to its default state
		gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7); // apply the projection
												  // matrix

		initShapes();
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	}

	private void initShapes() {
	}
}