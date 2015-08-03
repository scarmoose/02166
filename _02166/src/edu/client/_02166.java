package edu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import edu.client.ui.MainView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class _02166 implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	
	public void onModuleLoad() {
		MainView main;
		try {
			main = new MainView();
			RootPanel.get().add(main);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
