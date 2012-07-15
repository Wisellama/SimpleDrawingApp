package com.SimpleDrawingApp;

import java.nio.*;

import javax.microedition.khronos.opengles.GL10;

class Square {

    private FloatBuffer squareVB, squareVB2;

    static float squareCoords[] = { -0.1f,  0.1f, 0.0f,   // top left
                                    -0.1f, -0.1f, 0.0f,   // bottom left
                                     0.1f, -0.1f, 0.0f};  // bottom right
    
    static float square2Coords[] = { -0.1f,  0.1f, 0.0f,   // top left
    								0.1f, -0.1f, 0.0f,    // bottom right
    								0.1f,  0.1f, 0.0f };  // top right
    private float x,y;

	public Square() {
		
			x = 0;
			y = 0;

			ByteBuffer vbb = ByteBuffer.allocateDirect(squareCoords.length * 4);
			vbb.order(ByteOrder.nativeOrder());
			squareVB = vbb.asFloatBuffer();
			squareVB.put(squareCoords);
			squareVB.position(0);
				
			ByteBuffer vbb2 = ByteBuffer.allocateDirect(square2Coords.length * 4);
			vbb2.order(ByteOrder.nativeOrder());
			squareVB2 = vbb2.asFloatBuffer();
			squareVB2.put(square2Coords);
			squareVB2.position(0);
	}
	
	public Square(float x, float y) {
		this();
		this.x = x;
		this.y = y;
	}
	
	public void Draw(GL10 gl) {
		gl.glPushMatrix();
		gl.glTranslatef(this.x,this.y,0);
		gl.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, squareVB);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, squareVB2);
		gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3);
		gl.glPopMatrix();
	}
}