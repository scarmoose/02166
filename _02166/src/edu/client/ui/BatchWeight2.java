package edu.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IASEServiceAsync;
import edu.client.service.IBatchServiceAsync;

public class BatchWeight2 extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private VerticalPanel vPanel2 = new VerticalPanel();
	private HorizontalPanel hPanel1 = new HorizontalPanel();
	private HorizontalPanel hPanel2 = new HorizontalPanel();
	private FlexTable ft = new FlexTable();
	private FlexTable ft2 = new FlexTable();

	private DeltaBar dbar = new DeltaBar();
	public interface Callback{
		public IASEServiceAsync getASEService();
		public IBatchServiceAsync getBatchService();
		public void openBatchWeightView() throws Exception;
		public void setRecursiveRunning(boolean running);
		public boolean isRecursiveRunning();
	}

	public BatchWeight2(final Callback c) throws Exception {
		initWidget(vPanel);

		vPanel.setWidth("100%");
		vPanel.setHeight("100%");
		vPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

		vPanel.add(dbar);
		dbar.addStyleName("dbar");
		dbar.setHeight("60px");
		vPanel.add(vPanel2);
		vPanel2.add(hPanel1);
		vPanel2.add(hPanel2);
		hPanel1.setHeight("52px");
		vPanel.add(ft2);
		ft2.setWidth("550");
		vPanel.setStyleName("DVP2");
		hPanel1.add(ft);
		ft.setWidth("474px");
		ft2.setStyleName("H2");



		
	}
}
