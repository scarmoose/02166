package edu.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

import edu.client.service.IOperatoerServiceAsync;
import edu.shared.OperatoerDTO;

public class EditView extends Composite {
	private FlexTable ft = new FlexTable();
	private Label lblID = new Label("ID");
	private TextBox txtBoxID = new TextBox();
	private Label lblCPR = new Label ("CPR");
	private TextBox txtBoxCPR = new TextBox();
	private Label lblNavn = new Label ("Navn");
	private TextBox txtBoxNavn = new TextBox();
	private Label lblPassword = new Label ("Password");
	private TextBox txtBoxPassword = new TextBox();
	private Label lblIn = new Label ("Initialer");
	private Label lblAct = new Label ("Active status");
	private TextBox txtBoxActive = new TextBox();
	private TextBox txtBoxIn = new TextBox();
	private OperatoerDTO opr;


	public interface Callback{
		public IOperatoerServiceAsync getService();
		public void openListView() throws Exception;
	}
	
	public EditView(final Callback c, final int oprID) throws Exception {
		this.initWidget(ft);

		c.getService().getOperatoer(oprID, new AsyncCallback<OperatoerDTO>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Fail");
				
			}

			@Override
			public void onSuccess(OperatoerDTO result) {
				opr = result;
				txtBoxCPR.setText(opr.getCpr());
				txtBoxNavn.setText(opr.getOprNavn());
				txtBoxPassword.setText(opr.getPassword());
				txtBoxIn.setText(opr.getIni());
				txtBoxActive.setText(Integer.toString(opr.getActive()));
				
			}
			
		});
		ft.setWidget(0, 0, lblID);
		ft.setWidget(0, 1, lblCPR);
		ft.setWidget(0, 2, lblNavn);
		ft.setWidget(0, 3, lblPassword);
		ft.setWidget(0, 4, lblIn);
		ft.setWidget(0, 5, lblAct);

		txtBoxID.setText(""+oprID);

		txtBoxID.setEnabled(false);
		txtBoxCPR.setEnabled(true);
		txtBoxNavn.setEnabled(true);
		txtBoxPassword.setEnabled(true);
		txtBoxIn.setEnabled(true);
		
		ft.setWidget(1, 0, txtBoxID);
		ft.setWidget(1, 1, txtBoxCPR);
		ft.setWidget(1, 2, txtBoxNavn);
		ft.setWidget(1, 3, txtBoxPassword);
		ft.setWidget(1, 4, txtBoxIn);
		ft.setWidget(1, 5, txtBoxActive);
		
		Button btnEdit = new Button("Edit", new ClickHandler(){
			@Override
			public void onClick(ClickEvent event) {
				
				//ikke testet
				opr = new OperatoerDTO(oprID, txtBoxNavn.getText(), txtBoxIn.getText(),
						txtBoxCPR.getText(), txtBoxPassword.getText(),Integer.parseInt(txtBoxActive.getText()));
				//ikke testet
				try {
					c.getService().updateOperatoer(opr, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Error: "+caught.getMessage());

						}
						
						@Override
						public void onSuccess(Void result) {
							Window.alert("Successfully updated operator");
							try {
								c.openListView();
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							//txtBoxCPR.setText(txtBoxCPR.getText());
							//txtBoxNavn.setText(txtBoxNavn.getText());
							//txtBoxPassword.setText(txtBoxPassword.getText());
						}
					});
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try{
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		
		ft.setWidget(2, 0, btnEdit);
		btnEdit.setPixelSize(174, 30);
	}


}
