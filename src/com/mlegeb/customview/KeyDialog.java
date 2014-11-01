package com.mlegeb.customview;

import java.util.ArrayList;
import java.util.List;

import com.mlegeb.sudoku.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

public class KeyDialog extends Dialog {

	private final View keys[] = new View[9];
	private List<String> list = new ArrayList<String>();
	private SudokuView mainView;
	
	public KeyDialog(Context context, List<String> list, SudokuView mainView) {
		super(context);
		this.list = list;
		this.mainView = mainView;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setTitle("");
		setContentView(R.layout.keypadt);
		findViews();
		for(int i=0; i<this.list.size(); i++){
			int index = Integer.parseInt(list.get(i));
			keys[index-1].setVisibility(View.INVISIBLE);
		}
		addListener();
	}

	private void findViews(){
		keys[0] = findViewById(R.id.keypad_1);
		keys[1] = findViewById(R.id.keypad_2);
		keys[2] = findViewById(R.id.keypad_3);
		keys[3] = findViewById(R.id.keypad_4);
		keys[4] = findViewById(R.id.keypad_5);
		keys[5] = findViewById(R.id.keypad_6);
		keys[6] = findViewById(R.id.keypad_7);
		keys[7] = findViewById(R.id.keypad_8);
		keys[8] = findViewById(R.id.keypad_9);
	}
	
	private void returnResult(int key){
		mainView.selectKey(key);
		dismiss();
	}
	
	private void addListener(){
		for(int i=0; i<keys.length; i++){
			final int t = i+1;
			keys[i].setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					returnResult(t);
				}
			});
		}
	}
	
}
