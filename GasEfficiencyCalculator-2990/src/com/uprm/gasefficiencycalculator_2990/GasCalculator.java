/*
 * Carlos J. Gratacos Rasuk
 * 802-10-2990
 * University Of Puerto Rico Mayaguez
 * COMP4998- Android Porgramming
 * Proffesor: D. Hajek
 * Project Name: Gas Efficiency Calculator-2990 
 * */

package com.uprm.gasefficiencycalculator_2990;

import java.text.DecimalFormat;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("CutPasteId")
public class GasCalculator extends Activity {
	// Spinners are used for drop-down-list, where the user can choose the unit
	// he will input
	private Spinner distanceSpinner;
	private Spinner gasSpinner;

	// Standard textviews of the Layout
	private TextView kmTotalDistanceTextView;
	private TextView milesTotalDistanceTextView;
	private TextView gallonsTotalGasTextView;
	private TextView litersTotalGasTextView;
	private TextView mpgTextView;
	private TextView kmpLTextView;

	// TextViews used for the user numeric input
	private TextView distanceTextView;
	private TextView gasTextView;

	// Default doubles used for saving values
	private double kmDistante = 0.0;
	private double milesDistance = 0.0;
	private double gallonsGas = 0.0;
	private double litersGas = 0.0;

	private final double OnekmToMiles = 0.621371;
	private final double OneMileTokm = 1.60934;
	private final double OneGallonToLiter = 3.78541;
	private final double OneLiterToGalon = 0.26417;

	private boolean kmNow = true;
	private boolean gallonsNow = true;

	@SuppressLint("CutPasteId")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gas_calculator);

		distanceSpinner = (Spinner) findViewById(R.id.DistanceUnitSpinner);
		gasSpinner = (Spinner) findViewById(R.id.GasUnitSpinner);

		kmTotalDistanceTextView = (TextView) findViewById(R.id.kmTotalTextView);
		milesTotalDistanceTextView = (TextView) findViewById(R.id.milesTotalTextView);
		gallonsTotalGasTextView = (TextView) findViewById(R.id.gallonTotalTextView);
		litersTotalGasTextView = (TextView) findViewById(R.id.litersTotalTextView);
		mpgTextView = (TextView) findViewById(R.id.MilesPerGallonTotalTextView);
		kmpLTextView = (TextView) findViewById(R.id.KmPerLiterTotalTextView);

		distanceTextView = (TextView) findViewById(R.id.KmOrMilesEditText);
		gasTextView = (TextView) findViewById(R.id.GallOrLitEditText);

		// It is putted as a textview and EditText so the text cand be extracted
		// inside the overrided implemented methods
		EditText distanceEditText = (EditText) findViewById(R.id.KmOrMilesEditText);
		EditText gasEditText = (EditText) findViewById(R.id.GallOrLitEditText);

		distanceEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (!s.toString().contentEquals(".") && !s.toString().isEmpty()) {
					if (distanceSpinner.getSelectedItemPosition() == 0) {
						kmDistante = Double.parseDouble(s.toString());
						milesDistance = kmDistante * OnekmToMiles;
					} else if (distanceSpinner.getSelectedItemPosition() == 1) {
						milesDistance = Double.parseDouble(s.toString());
						kmDistante = milesDistance * OneMileTokm;
					}
				} else if (s.toString().contentEquals(".")) {
					distanceTextView.setText("");
					kmDistante = 0.0;
					milesDistance = 0.0;
				} else {
					kmDistante = 0.0;
					milesDistance = 0.0;
				}
				UpdateParameters();
			}
		});

		gasEditText.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

				if (!s.toString().contentEquals(".") && !s.toString().isEmpty()) {
					if (gasSpinner.getSelectedItemPosition() == 0) {
						gallonsGas = Double.parseDouble(s.toString());
						litersGas = gallonsGas * OneGallonToLiter;
					} else if (gasSpinner.getSelectedItemPosition() == 1) {
						litersGas = Double.parseDouble(s.toString());
						gallonsGas = litersGas * OneLiterToGalon;
					}
				} else if (s.toString().contentEquals(".")) {
					gasTextView.setText("");
					litersGas = 0.0;
					gallonsGas = 0.0;
				} else {
					litersGas = 0.0;
					gallonsGas = 0.0;
				}
				UpdateParameters();

			}
		});
		
		distanceSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {

				if (position == 0 && !distanceTextView.getText().equals("")
						&& !kmNow) {
					kmDistante = milesDistance;
					milesDistance = kmDistante * OnekmToMiles;
					kmNow = true;
				} else if (position == 1
						&& !distanceTextView.getText().equals("") && kmNow) {
					milesDistance = kmDistante;
					kmDistante = milesDistance * OneMileTokm;
					kmNow = false;
				} else {
					milesDistance = 0.0;
					kmDistante = 0.0;
				}
				UpdateParameters();
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});

		gasSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				if (position == 0 && !gasTextView.getText().equals("")
						&& !gallonsNow) {
					gallonsGas = litersGas;
					litersGas = gallonsGas * OneGallonToLiter;
					gallonsNow = true;
				} else if (position == 1 && !gasTextView.getText().equals("")
						&& gallonsNow) {
					litersGas = gallonsGas;
					gallonsGas = litersGas * OneLiterToGalon;
					gallonsNow = false;
				} else {
					litersGas = 0.0;
					gallonsGas = 0.0;
				}
				UpdateParameters();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void UpdateParameters() {
		String km = new DecimalFormat("0.0000").format(kmDistante);
		String miles = new DecimalFormat("0.0000").format(milesDistance);
		String liters = new DecimalFormat("0.0000").format(litersGas);
		String gallons = new DecimalFormat("0.0000").format(gallonsGas);
		kmTotalDistanceTextView.setText(km);
		milesTotalDistanceTextView.setText(miles);
		litersTotalGasTextView.setText(liters);
		gallonsTotalGasTextView.setText(gallons);

		if (gallonsGas > 0 && litersGas > 0) {
			String mpg = new DecimalFormat("0.0000").format(milesDistance
					/ gallonsGas);
			String kmpl = new DecimalFormat("0.0000").format(kmDistante
					/ litersGas);
			mpgTextView.setText(mpg);
			kmpLTextView.setText(kmpl);
		} else {
			mpgTextView.setText("0.0000");
			kmpLTextView.setText("0.0000");
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

	}

}
