package com.mlegeb.customview;

import com.mlegeb.sudoku.R;
import com.mlegeb.tools.GameInit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.view.MotionEvent;
import android.view.View;

public class SudokuView extends View{

	private float width;
	private float height;
	private GameInit gameInit = new GameInit();

	private int touchX;
	private int touchY;


	public SudokuView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		this.width = w / 9f;
		this.height = h / 9f;
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint backgroundPaint = new Paint();
		backgroundPaint.setColor(getResources().
				getColor(R.color.sudoku_background));

		canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);

		Paint darkPaint = new Paint();
		darkPaint.setColor(getResources().
				getColor(R.color.sudoku_dark));

		Paint hilitePaint = new Paint();
		hilitePaint.setColor(getResources().
				getColor(R.color.sudoku_light));
		hilitePaint.setStrokeWidth(3);

		Paint lightPaint = new Paint();
		lightPaint.setColor(getResources().
				getColor(R.color.sudoku_light));

		for(int i=0; i<9; i++){
			canvas.drawLine(0, i*height, getWidth(), i*height, lightPaint);
			//			canvas.drawLine(0, i*height+1, getWidth(), i*height+1, hilitePaint);
			canvas.drawLine(i*width, 0, i*width, getHeight(), lightPaint);
			//			canvas.drawLine(i*width+1, 0, i*width+1, getHeight(), hilitePaint);


			if(i%3 == 0){
				canvas.drawLine(0, i*height, getWidth(), i*height, hilitePaint);
				canvas.drawLine(i*width, 0, i*width, getHeight(), hilitePaint);
			}

		}


		Paint numberPaint = new Paint();
		
		numberPaint.setStyle(Paint.Style.STROKE);
		numberPaint.setTextSize(height*0.75f);
		numberPaint.setTextAlign(Paint.Align.CENTER);

		FontMetrics fm = numberPaint.getFontMetrics();
		float x = width/2;
		float y = height/2 - (fm.ascent +fm.descent)/2;

		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(this.gameInit.getPint()[i][j].isAdd){
					numberPaint.setColor(Color.BLUE);
					canvas.drawText(this.gameInit.getPint()[i][j].getValue(), i*width+x, j*height+y, numberPaint);
				}
				else{
					numberPaint.setColor(Color.BLACK);
					canvas.drawText(this.gameInit.getPint()[i][j].getValue(), i*width+x, j*height+y, numberPaint);
				}
			}
		}
		super.onDraw(canvas);
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		if(event.getAction() != MotionEvent.ACTION_DOWN){
			return super.onTouchEvent(event);
		}
		touchX = (int)(event.getX()/width);
		touchY = (int)(event.getY()/height);

		for(int i=0; i<gameInit.getPint()[touchX][touchY].totalUnuse.size(); i++){
			String str =gameInit.getPint()[touchX][touchY].totalUnuse.get(i);
			System.out.println("-->" + str);
		}
		KeyDialog keyDialog = new KeyDialog(getContext(), 
				gameInit.getPint()[touchX][touchY].totalUnuse, this);
		keyDialog.show();
		return true;
	}


	public void selectKey(int key){
		this.gameInit.setPintValue(touchX, touchY, String.valueOf(key));
		invalidate();
	}
}
