package org.friendscentral.steamnet;

import org.friendscentral.steamnet.Activities.MainActivity;
import org.friendscentral.steamnet.BaseClasses.Spark;
import org.friendscentral.steamnet.SparkWizardFragments.ContentEntry;
import org.friendscentral.steamnet.SparkWizardFragments.ContentTypeChooser;
import org.friendscentral.steamnet.SparkWizardFragments.SparkTypeChooser;

import APIHandlers.PostSpark;
import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
@SuppressWarnings("unused")
public class SparkWizard {
	MainActivity mainActivity;
	LinearLayout mainLayout;
	FragmentManager fm;
	ContentEntry ce;
	
	public SparkWizard(LinearLayout ml, FragmentManager f, MainActivity m) {
		mainLayout = ml;
		fm = f;
		mainActivity = m;
	}
	
	//Methods for the Wizard Fragments:
			//Very similar, could probably be consolidated
	public void openContentTypeChooser(View v, char type) {
		updateWeights(5, 3, 1);
		
		ContentTypeChooser ctc = new ContentTypeChooser(type);
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.WizardSection, ctc);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void revertWizard(View v) {
		updateWeights(2, 4, 2); 
		
		SparkTypeChooser stc = new SparkTypeChooser();
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.WizardSection, stc);
		ft.addToBackStack(null);
		ft.commit();
		
		//setAllInvisible();
	}
	
	public void openContentEntry(View v, char type) {
		updateWeights(5, 3, 1);
		
		ce = new ContentEntry(type, mainActivity);
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.WizardSection, ce);
		ft.addToBackStack(null);
		ft.commit();
	}
	
	public void submitSpark(View v, Spark s, GridView g, IndexGrid i) {
		// TODO "" as a replacement for Tags
		if (s != null) {
			PostSpark task = new PostSpark(s.getSparkType(), s.getContentType(), s.getContent(), "", g, i);
			revertWizard(v);
		}
	}
	
	public void updateWeights(float sp, float fs, float ib) {
    	mainLayout.findViewById(R.id.WizardSection).setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, sp));
    	mainLayout.findViewById(R.id.FilterSettings).setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, fs));
    	mainLayout.findViewById(R.id.IdeaBucket).setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0, ib));
    }
	
	public Fragment getFragment(int id) {
		return fm.findFragmentById(id);
	}
	
	public void setAllInvisible() {
		mainLayout.findViewById(R.id.text_form).setVisibility(View.GONE);
		mainLayout.findViewById(R.id.audio_form).setVisibility(View.GONE);
		mainLayout.findViewById(R.id.picture_form).setVisibility(View.GONE);
		mainLayout.findViewById(R.id.video_form).setVisibility(View.GONE);
		mainLayout.findViewById(R.id.code_form).setVisibility(View.GONE);
		mainLayout.findViewById(R.id.link_form).setVisibility(View.GONE);
	}
	
	public ContentEntry getContentEntry() {
		return ce;
	}
	
	//Set up the auto-weighting for each Sidebar componant:
		//Needs updating, to include descendants...
	/*findViewById(R.id.WizardSection).setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			updateWeights(5, 3, 1);
			//Set things invisible
		}
	});
	
	findViewById(R.id.IdeaBucket).setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			updateWeights(1, 3, 5);
			//Set things invisible
		}
	});
	
	findViewById(R.id.FilterSettings).setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
			updateWeights(1, 3, 1);
			//Set things invisible
		}
	});*/

}