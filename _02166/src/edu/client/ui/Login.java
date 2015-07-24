package edu.client.ui;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IOperatoerServiceAsync;
import edu.shared.OperatoerDTO;

public class Login extends Composite {
	private VerticalPanel vPanel = new VerticalPanel();
	Label lblOne = new Label("User ID ");
	Label lblTwo = new Label("Password ");
	TextBox userID = new TextBox();

	LayoutPanel lPanel = new LayoutPanel();
	PasswordTextBox password = new PasswordTextBox();

	public interface Callback{
		public void loginSucces(OperatoerDTO activeUser);
		public void loginFailiure();
		public IOperatoerServiceAsync getService();
	}


	public Login(final Callback c){
		this.initWidget(vPanel);
		
		vPanel.add(lblOne);
		vPanel.add(userID);
		vPanel.add(lblTwo);
		vPanel.add(password);
		
		userID.addStyleName("userID");
		userID.setPixelSize(151, 20);
		vPanel.setBorderWidth(2);
		password.addStyleName("password");
		password.setPixelSize(151, 20);
		vPanel.setBorderWidth(2);
	
		Button btnOne = new Button("Submit", new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				try {
					c.getService().loginVerify(Integer.parseInt(userID.getText()), password.getText(), new AsyncCallback<OperatoerDTO>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Server fejl! " + caught.getMessage());
							userID.setText("");
							password.setText("");
						}
						@Override
						public void onSuccess(OperatoerDTO data) {

							if(data.getOprId() >= 0){
								c.loginSucces(data);
							}
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		});

		vPanel.add(btnOne);	
		btnOne.addStyleName("btnOne");
		vPanel.setBorderWidth(2);
		btnOne.setPixelSize(160, 30);


	}
	
	public void clear() {
		userID.setText("");
		password.setText("");
	}
	
	public String getUName() {
		return userID.getText();
	}
	public String getPassW() {
		return password.getText();
	}
	public TextBox getUserID() {
		return userID;
	}

	public void setUserID(TextBox userID) {
		this.userID = userID;
	}

	public PasswordTextBox getPassword() {
		return password;
	}

	public void setPassword(PasswordTextBox password) {
		this.password = password;
	}
	
}
