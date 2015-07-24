package edu.client.ui;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

import edu.client.service.IASEService;
import edu.client.service.IASEServiceAsync;
import edu.client.service.IBatchService;
import edu.client.service.IBatchServiceAsync;
import edu.client.service.ICoinService;
import edu.client.service.ICoinServiceAsync;
import edu.client.service.ICondimentsService;
import edu.client.service.ICondimentsServiceAsync;
import edu.client.service.IFruitService;
import edu.client.service.IFruitServiceAsync;
import edu.client.service.IMetaService;
import edu.client.service.IMetaServiceAsync;
import edu.client.service.IOperatoerService;
import edu.client.service.IOperatoerServiceAsync;
import edu.shared.OperatoerDTO;

public class MainView extends Composite implements Login.Callback, WeightView.Callback,
UnitWeightView.Callback, DeltaWeightView.Callback, ListView.Callback, EditView.Callback {

	private OperatoerDTO activeUser;
	private VerticalPanel content = new VerticalPanel();
	private Login login;
	private MenuView menu;
	private AddView add;
	private final IOperatoerServiceAsync service = GWT.create(IOperatoerService.class);
	private final IASEServiceAsync ASEservice = GWT.create(IASEService.class);
	private final IMetaServiceAsync Metaservice = GWT.create(IMetaService.class);
	private final ICoinServiceAsync CoinService = GWT.create(ICoinService.class);
	private final IFruitServiceAsync FruitService = GWT.create(IFruitService.class);
	private final ICondimentsServiceAsync CondimentsService = GWT.create(ICondimentsService.class);
	private final IBatchServiceAsync BatchService = GWT.create(IBatchService.class);

	private AbsolutePanel aPanel = new AbsolutePanel();

	public MainView() throws Exception {
		initWidget(aPanel);
		ASEservice.connect(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der kunne ikke skabes forbindelse til vægten.");	
				System.out.println("No connection established");
			}
			@Override
			public void onSuccess(Void result) {
				Window.alert("Forbindelse til vægten er oprettet");			
			}			
		});
		aPanel.setSize(Integer.toString(Window.getClientWidth())+"px", Integer.toString(Window.getClientHeight())+"px");

		login = new Login(this);
		openLoginView();
	}

	public Login getLogin() {
		return login;
	}
	
	@Override
	public void loginFailiure() {
		// TODO Auto-generated method stub
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	//-------------------------------------------------------------------------
	//method for opening EditView when 
	//EDIT-button (on-screen when ListView is open) is pressed
	//-------------------------------------------------------------------------

	public void openEditView(int oprId) throws Exception {
		content.clear();
		content.add(new EditView(this, oprId));

	}

	//-------------------------------------------------------------------------
	//method for opening AddView when ADD-button is pressed
	//-------------------------------------------------------------------------

	public void openAddView() throws Exception{
		content.clear();
		add = new AddView(this);
		content.add(add);

		aPanel.add(content);
		aPanel.setWidgetPosition(content,Window.getClientWidth()/8,Window.getClientHeight()/8);
	}

	//-------------------------------------------------------------------------
	//method for opening UnitView when UNIT-button is pressed
	//-------------------------------------------------------------------------

	public void openStykWeight() throws Exception{
		content.clear();
		UnitWeightView coin = new UnitWeightView(this);
		content.add(coin);
		aPanel.add(content);
		aPanel.setWidgetPosition(content,Window.getClientWidth()/8,Window.getClientHeight()/8);
	}

	//-------------------------------------------------------------------------
	//method for opening LoginView when LOGOUT-button is pressed
	//-------------------------------------------------------------------------
	
	public void openLoginView() {
		aPanel.clear();
		content.clear();
		activeUser = null;
		login.clear();
		content.add(login);
		aPanel.add(content);
		aPanel.setWidgetPosition(content,Window.getClientWidth()/2-115,Window.getClientHeight()/4);

	}

	//-------------------------------------------------------------------------
	//method for opening ListView when LIST-button is pressed
	//-------------------------------------------------------------------------
	
	public void openListView() throws Exception {
		content.clear();
		content.add(new ListView(this));
		aPanel.add(content);
		aPanel.setWidgetPosition(content,Window.getClientWidth()/8,Window.getClientHeight()/8);
	}


	//-------------------------------------------------------------------------
	//method for opening MenuView if login is successful
	//-------------------------------------------------------------------------

	@Override
	public void loginSucces(OperatoerDTO activeUser) {
		aPanel.clear();
		this.activeUser = activeUser;
		menu = new MenuView(this);
		aPanel.add(menu);
		aPanel.setWidgetPosition(menu, 10, 10);
	}

	
	//-------------------------------------------------------------------------
	//method for opening WeightView when WEIGHT-button is pressed
	//-------------------------------------------------------------------------

	public void openWeightView() throws Exception{
		content.clear();
		WeightView weight = new WeightView(this);
		content.add(weight);
		aPanel.add(content);
		aPanel.setWidgetPosition(content,Window.getClientWidth()/8,Window.getClientHeight()/8);

	}


	//---------------------------------------------------------------------------
	//method for opening DeltaWeightView when ADVANCED-button is pressed
	//---------------------------------------------------------------------------

	public void openDeltaWeightView() throws Exception {
		content.clear();
		DeltaWeightView dWView = new DeltaWeightView(this);
		content.add(dWView);
		aPanel.add(content);
		aPanel.setWidgetPosition(content,Window.getClientWidth()/8,Window.getClientHeight()/8);

	}

	//---------------------------------------------------------------------------
	//method for getting the dataBase service
	//---------------------------------------------------------------------------
	
	@Override
	public IOperatoerServiceAsync getService() {
		return service;
	}


	@Override
	public IMetaServiceAsync getMetaService() {
		// TODO Auto-generated method stub
		return Metaservice;
	}

	public ICoinServiceAsync getCoinService() {
		return CoinService;
	}

	public IASEServiceAsync getASEService() {
		return ASEservice;
	}


	public IBatchServiceAsync getBatchService() {
		return BatchService;
	}

	@Override
	public IFruitServiceAsync getFruitService() {
		return FruitService;
	}

	@Override
	public ICondimentsServiceAsync getCondimentsService(){
		return CondimentsService;
	}
}
