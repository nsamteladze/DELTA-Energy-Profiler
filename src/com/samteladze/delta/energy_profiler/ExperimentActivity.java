package com.samteladze.delta.energy_profiler;

import java.util.ArrayList;
import java.util.List;

import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.utils.ExperimentTypeNameHelper;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ExperimentActivity extends Activity {

	// TEMP
	public static Context appContext;
	
	private Spinner spinnerChooseExperiment;
	private Button buttonStartExperiment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// TEMP
		appContext = getApplicationContext();
		
		initializeUI();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ac, menu);
		return true;
	}
	
	private void initializeUI() {
		addItemsToChooseExperimentSpinner();
		addOnClickListenerToStartExperimentButton();
	}
	
	// Dynamically add available energy experiment types to spinnerChooseExperiment 
	private void addItemsToChooseExperimentSpinner() {
		spinnerChooseExperiment = (Spinner) findViewById(R.id.spinnerChooseExperiment);
		List<String> spinnerElements = new ArrayList<String>();
		
		// Populate array with all available energy experiment types
		for (ExperimentType type : ExperimentType.values()) {
			spinnerElements.add(ExperimentTypeNameHelper.getExperimentName(type));
		}

		ArrayAdapter<String> dataAdapter = 
				new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerElements);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerChooseExperiment.setAdapter(dataAdapter);
	}
	
	private void addOnClickListenerToStartExperimentButton() {
		
		spinnerChooseExperiment = (Spinner) findViewById(R.id.spinnerChooseExperiment);
		buttonStartExperiment = (Button) findViewById(R.id.buttonStartExperiment);
	 
		buttonStartExperiment.setOnClickListener(new OnClickListener() {
	 
		  @Override
		  public void onClick(View v) {
			  
			  Toast.makeText(getApplicationContext(), Environment.getExternalStorageDirectory().toString(), Toast.LENGTH_SHORT).show();
			  Experimenter.startExperiment(ExperimentType.values()[(int) spinnerChooseExperiment.getSelectedItemId()],
					  							 getApplicationContext()); 
			  
			  
		  }
	 
		});
	}

}
