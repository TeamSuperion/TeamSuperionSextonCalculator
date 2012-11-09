package com.example.sextoncalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PayMethodActivity extends Activity implements OnClickListener {

	protected Button punchButton, flexButton, cashButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay_method);

		punchButton = (Button) findViewById(R.id.start_button);
		punchButton.setOnClickListener(this);
		flexButton = (Button) findViewById(R.id.start_button);
		flexButton.setOnClickListener(this);
		cashButton = (Button) findViewById(R.id.start_button);
		cashButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_pay_method, menu);
		return true;
	}

	public void onClick(View v) {
		if (v == punchButton) {

		} else if (v == flexButton) {

		} else if (v == cashButton) {

		}
	}
}

/*Tsuehue Punch Metho
 * 		final TextView newTotal = (TextView) findViewById(R.id.amountRemaining_textView);
		totalPrice = totalPrice - entrePrice - sidePrice - drinkPrice;
		if (totalPrice > 0.00) {
			DecimalFormat df = new DecimalFormat("#.##");
			totalPrice = Double.parseDouble(df.format(totalPrice));
			newTotal.setText("$" + String.valueOf(totalPrice));
			if (totalPrice == 0.00) {
				newTotal.setText("$0.00");
			}
			EditText edit = (EditText) findViewById(R.id.cashPay_editText);
			punchCounter = edit.getText().toString();
			int aInt = Integer.parseInt(punchCounter);
			aInt = aInt + 1;
			punchCounter = Integer.toString(aInt);
			edit.setText(punchCounter);
		}
 */
