package com.samteladze.delta.energy_profiler;

import java.util.ArrayList;
import java.util.List;

import com.samteladze.delta.energy_profiler.model.ExperimentType;
import com.samteladze.delta.energy_profiler.utils.ExperimentTypeNameHelper;
import com.samteladze.delta.energy_profiler.utils.FileManager;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class ExperimentActivity extends Activity {

	private static Window _currentWindow = null;
	
	private Spinner spinnerChooseExperiment;
	private Button buttonStartExperiment;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		_currentWindow = this.getWindow();
		FileManager.initialize();
		
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
			  Experimenter.startExperiment(ExperimentType.values()[(int) spinnerChooseExperiment.getSelectedItemId()],
					  							 getApplicationContext(), _currentWindow); 
			  
			  
		  }
	 
		});
	}

}
