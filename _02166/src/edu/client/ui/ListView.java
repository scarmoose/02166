package edu.client.ui;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

import edu.client.service.IBatchServiceAsync;
import edu.client.service.ICoinServiceAsync;
import edu.client.service.ICondimentsServiceAsync;
import edu.client.service.IFruitServiceAsync;
import edu.client.service.IMetaServiceAsync;
import edu.client.service.IOperatoerServiceAsync;
import edu.shared.BatchDTO;
import edu.shared.CoinDTO;
import edu.shared.CondimentsDTO;
import edu.shared.FruitDTO;
import edu.shared.OperatoerDTO;

public class ListView extends Composite {

	private VerticalPanel vPanel = new VerticalPanel();
	private	HorizontalPanel buttonPanel = new HorizontalPanel();
	private	HorizontalPanel hPanel = new HorizontalPanel();

	private List<String> tableList;
	private List<OperatoerDTO> oprList;
	private List<CoinDTO> coinList;
	private List<FruitDTO> fruitList;
	private List<CondimentsDTO> condimentsList;
	private List<BatchDTO> batchList;

	private BatchDTO batchSelect;
	private CoinDTO coinSelect;
	private FruitDTO fruitSelect;
	private CondimentsDTO condimentsSelect;
	private OperatoerDTO oprSelect;

	private int chosen = 0;
	
	Button editBtn = new Button("Edit");
	Button removeBtn = new Button("Edit");

	private	FlexTable ft = new FlexTable();
	private FlexCellFormatter ftFormat = ft.getFlexCellFormatter();

	public interface Callback{

		public IOperatoerServiceAsync getService();
		public void openListView() throws Exception;
		public void openEditView(int i) throws Exception;
		public ICoinServiceAsync getCoinService();
		public IFruitServiceAsync getFruitService();
		public ICondimentsServiceAsync getCondimentsService();
		public IMetaServiceAsync getMetaService();
		public IBatchServiceAsync getBatchService();

	}


	public ListView(final Callback c) throws Exception {
		initWidget(vPanel);

		vPanel.add(ft);
		ft.setBorderWidth(1);

		ft.setWidget(0,0,hPanel);
		vPanel.add(buttonPanel);


		/**
		 * The list of data to display.
		 */

		c.getMetaService().getTables(new AsyncCallback<List<String>>(){
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to access database: "+caught.getMessage());

			}

			@Override
			public void onSuccess(List<String> result) {
				tableList = result;
				// Create a CellTable.
				CellTable<String> tables = new CellTable<String>();
				tables.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
				// Add a text column to show the name.
				TextColumn<String> nameColumn = new TextColumn<String>() {
					@Override
					public String getValue(String object) {
						return object;
					}
				};

				tables.addColumn(nameColumn, "Table name");
				// Set the total row count. This isn't strictly necessary, but it affects
				// paging calculations, so its good habit to keep the row count up to date.
				tables.setRowCount(tableList.size(), true);
				// Push the data into the widget.
				tables.setRowData(0, tableList);
				tables.redraw();
				//Placement of tables on the flextable nr 2
				ft.setWidget(0, 0, tables);
				ft.setStyleName("H2");
				//				tables.getRowElement(0).getCells().getItem(0).setId("H3");

				final SingleSelectionModel<String> selectionModel = new SingleSelectionModel<String>();
				tables.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						String selected = selectionModel.getSelectedObject();
						/*
						 * Here we want to display the data in the table operatoer
						 * then we want to make it able to then show both coins and operatoer
						 * 
						 */
						if(selected.equals("operatoer")){
							oprCellView(c);

						}
						if(selected.equals("coins")){
							coinCellView(c);

						}
						if(selected.equals("fruits")){
							fruitCellView(c);

						}
						if(selected.equals("condiments")){
							condimentsCellView(c);

						}
						if(selected.equals("batch")){
							batchCellView(c);

						}
					}

				});

			}
		});

		//edit is only set up to work with operatoer atm
		Button editBtn = new Button("Edit");
		editBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if(chosen == 0){
					Window.alert("Nothing is chosen, please choose something to remove - only works with operatoer");
				}else if(chosen == 1){
					try {
						c.openEditView(oprSelect.getOprId());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		

		});
		/*
		 * Removal is done by checking what has been chosen as the object currently, then contact the server to remove that
		 * from the database
		 * Only setup to "remove"
		 */
		Button removeBtn = new Button("Remove");
		removeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				try {
					if(chosen == 0){
						Window.alert("Nothing is chosen, please choose something to remove - only works with operatoer");
					}else if(chosen == 1){

						c.getService().deleteOperatoer(oprSelect, new AsyncCallback<Void>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("an error occured" +caught.getMessage());

							}

							@Override
							public void onSuccess(Void result) {

								try {
									c.openListView();
								} catch (Exception e) {
								
									e.printStackTrace();
								}

							}

						});

					}
					//if we had time to implement the system to be able to delete the other data in the database.
					//				else if(chosen == 2){
					//					
					//				}else if(chosen == 3){
					//					
					//				}else if(chosen == 4){
					//					
					//				}

				} catch (Exception e) {
				
					e.printStackTrace();


				}
			}
		});

		buttonPanel.add(editBtn);
		buttonPanel.add(removeBtn);
		vPanel.remove(buttonPanel);
	}


	private void coinCellView(Callback c) {
		vPanel.remove(buttonPanel);
		try {
			c.getCoinService().getCoinList(new AsyncCallback<List<CoinDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failed to access database: "+caught.getMessage());

				}
				@Override
				public void onSuccess(List<CoinDTO> result) {
					coinList = result;

					CellTable<CoinDTO> coinTable = new CellTable<CoinDTO>();
					coinTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					// Add a text column to show the name.
					TextColumn<CoinDTO> valueColumn = new TextColumn<CoinDTO>() {
						@Override
						public String getValue(CoinDTO object) {
							return Double.toString(object.getValue());
						}
					};

					coinTable.addColumn(valueColumn, "Coin Value");

					TextColumn<CoinDTO> toleColumn = new TextColumn<CoinDTO>() {
						@Override
						public String getValue(CoinDTO object) {
							return Double.toString(object.getTolerance());
						}
					};
					coinTable.addColumn(toleColumn, "Tolerance");

					TextColumn<CoinDTO> wPrUnitColumn = new TextColumn<CoinDTO>() {
						@Override
						public String getValue(CoinDTO object) {
							return Double.toString(object.getWeightPerUnit());
						}
					};

					coinTable.addColumn(wPrUnitColumn, "Weight Pr Unit");

					final SingleSelectionModel<CoinDTO> selectionModel = new SingleSelectionModel<CoinDTO>();
					coinTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					coinTable.setSelectionModel(selectionModel);
					selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							CoinDTO selected = selectionModel.getSelectedObject();
							/*
							 * Here we want to display the data in the table operatoer
							 * then we want to make it able to then show both coins and operatoer
							 * 
							 */
						
							coinSelect = selected;
						}
					});


					coinTable.setRowCount(coinList.size(), true);
					// Push the data into the widget.
					coinTable.setRowData(0, coinList);
					coinTable.redraw();
					ft.setWidget(0,1, coinTable);
				}

			});


		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void fruitCellView(Callback c) {
		//buttonPanel.remove(editBtn);
		//buttonPanel.remove(removeBtn);
		vPanel.remove(buttonPanel);
		try {
			c.getFruitService().getFruitList(new AsyncCallback<List<FruitDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failed to access database: "+caught.getMessage());

				}
				@Override
				public void onSuccess(List<FruitDTO> result) {
					fruitList = result;

					CellTable<FruitDTO> fruitTable = new CellTable<FruitDTO>();
					fruitTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					// Add a text column to show the name.
					TextColumn<FruitDTO> nameColumn = new TextColumn<FruitDTO>() {
						@Override
						public String getValue(FruitDTO object) {
							return object.getName();
						}
					};
					fruitTable.addColumn(nameColumn, "Fruit Name");

					TextColumn<FruitDTO> toleColumn = new TextColumn<FruitDTO>() {
						@Override
						public String getValue(FruitDTO object) {
							return Double.toString(object.getTolerance());
						}
					};
					fruitTable.addColumn(toleColumn, "Tolerance");

					TextColumn<FruitDTO> wPrUnitColumn = new TextColumn<FruitDTO>() {
						@Override
						public String getValue(FruitDTO object) {
							return Double.toString(object.getWeightPerUnit());
						}
					};

					fruitTable.addColumn(wPrUnitColumn, "Weight Pr Unit");

					final SingleSelectionModel<FruitDTO> selectionModel = new SingleSelectionModel<FruitDTO>();
					fruitTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					fruitTable.setSelectionModel(selectionModel);
					selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							FruitDTO selected = selectionModel.getSelectedObject();
							/*
							 * Here we want to display the data in the table operatoer
							 * then we want to make it able to then show both coins and operatoer
							 * 
							 */
						
							fruitSelect = selected;
						}
					});


					fruitTable.setRowCount(fruitList.size(), true);
					// Push the data into the widget.
					fruitTable.setRowData(0, fruitList);
					fruitTable.redraw();
					ft.setWidget(0,1, fruitTable);
				}

			});


		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void condimentsCellView(Callback c) {
		vPanel.remove(buttonPanel);
		try {
			c.getCondimentsService().getCondimentsList(new AsyncCallback<List<CondimentsDTO>>(){

				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failed to access database: "+caught.getMessage());

				}
				@Override
				public void onSuccess(List<CondimentsDTO> result) {
					condimentsList = result;

					CellTable<CondimentsDTO> condimentsTable = new CellTable<CondimentsDTO>();
					condimentsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					// Add a text column to show the name.
					TextColumn<CondimentsDTO> nameColumn = new TextColumn<CondimentsDTO>() {
						@Override
						public String getValue(CondimentsDTO object) {
							return object.getName();
						}
					};
					condimentsTable.addColumn(nameColumn, "Condiments Name");

					TextColumn<CondimentsDTO> toleColumn = new TextColumn<CondimentsDTO>() {
						@Override
						public String getValue(CondimentsDTO object) {
							return Double.toString(object.getTolerance());
						}
					};
					condimentsTable.addColumn(toleColumn, "Tolerance");

					TextColumn<CondimentsDTO> wPrUnitColumn = new TextColumn<CondimentsDTO>() {
						@Override
						public String getValue(CondimentsDTO object) {
							return Double.toString(object.getWeightPerUnit());
						}
					};

					condimentsTable.addColumn(wPrUnitColumn, "Weight Pr Unit");

					final SingleSelectionModel<CondimentsDTO> selectionModel = new SingleSelectionModel<CondimentsDTO>();
					condimentsTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

					condimentsTable.setSelectionModel(selectionModel);
					selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							CondimentsDTO selected = selectionModel.getSelectedObject();
							/*
							 * Here we want to display the data in the table operatoer
							 * then we want to make it able to then show both coins and operatoer
							 * 
							 */
						
							condimentsSelect = selected;
						}
					});


					condimentsTable.setRowCount(condimentsList.size(), true);
					// Push the data into the widget.
					condimentsTable.setRowData(0, condimentsList);
					condimentsTable.redraw();
					ft.setWidget(0,1, condimentsTable);
				}

			});


		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	private void oprCellView(final Callback c) {
		vPanel.remove(buttonPanel);
		vPanel.add(buttonPanel);
		try {
			c.getService().getOperatoerList(new AsyncCallback<List<OperatoerDTO>>(){
				@Override
				public void onFailure(Throwable caught) {
					Window.alert("Failed to access database: "+caught.getMessage());
				}

				@Override
				public void onSuccess(List<OperatoerDTO> result) {
					oprList = result;

					CellTable<OperatoerDTO> oprTable = new CellTable<OperatoerDTO>();
					oprTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					// Add a text column to show the name.
					TextColumn<OperatoerDTO> oprColumn = new TextColumn<OperatoerDTO>() {
						@Override
						public String getValue(OperatoerDTO object) {
							return Integer.toString(object.getOprId());
						}


					};
					oprTable.addColumn(oprColumn, "Opr ID");

					TextColumn<OperatoerDTO> nameColumn = new TextColumn<OperatoerDTO>() {
						@Override
						public String getValue(OperatoerDTO object) {
							return object.getOprNavn();
						}


					};
					oprTable.addColumn(nameColumn, "name");


					TextColumn<OperatoerDTO> iniColumn = new TextColumn<OperatoerDTO>() {
						@Override
						public String getValue(OperatoerDTO object) {
							return object.getIni();
						}


					};
					oprTable.addColumn(iniColumn, "initials");

					TextColumn<OperatoerDTO> cprColumn = new TextColumn<OperatoerDTO>() {
						@Override
						public String getValue(OperatoerDTO object) {
							return object.getCpr();
						}


					};
					oprTable.addColumn(cprColumn, "cpr");
					TextColumn<OperatoerDTO> passwColumn = new TextColumn<OperatoerDTO>() {
						@Override
						public String getValue(OperatoerDTO object) {
							return object.getPassword();
						}


					};
					oprTable.addColumn(passwColumn, "Password");

					TextColumn<OperatoerDTO> activeColumn = new TextColumn<OperatoerDTO>() {
						@Override
						public String getValue(OperatoerDTO object) {
							return Integer.toString(object.getActive());
						}


					};
					oprTable.addColumn(activeColumn, "active rights ID");

					final SingleSelectionModel<OperatoerDTO> selectionModel = new SingleSelectionModel<OperatoerDTO>();
					oprTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
					oprTable.setSelectionModel(selectionModel);
					selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
						public void onSelectionChange(SelectionChangeEvent event) {
							OperatoerDTO selected = selectionModel.getSelectedObject();
							/*
							 * Here we want to display the data in the table operatoer
							 * then we want to make it able to then show both coins and operatoer
							 * 
							 */
							
							//sets oprSelected to be the one we marked in the table
							oprSelect = selected;
							//sets the chosen to 1 so we know that if we click a button we will use the oprSelect for methods.
							chosen = 1;

						}


					});
					//	Add a selection model to handle user selection.
					oprTable.setRowCount(oprList.size(), true);
					// Push the data into the widget.
					oprTable.setRowData(0, oprList);
					oprTable.redraw();
					ft.setWidget(0,1, oprTable);


				}

			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void batchCellView(final Callback c) {
		vPanel.remove(buttonPanel);

		c.getBatchService().getBatchList(new AsyncCallback<List<BatchDTO>>(){

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Failed to access database: "+caught.getMessage());
			}
			@Override
			public void onSuccess(List<BatchDTO> result) {
				batchList = result;


				CellTable<BatchDTO> batchTable = new CellTable<BatchDTO>();
				batchTable.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

				// Add a text column to show the name.
				TextColumn<BatchDTO> IDColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Integer.toString(object.getBatch_id());
					}


				};
				batchTable.addColumn(IDColumn, "Batch ID");

				// Add a text column to show the name.
				TextColumn<BatchDTO> raaNavnColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return object.getRaavare_navn();
					}


				};
				batchTable.addColumn(raaNavnColumn, "Raavare");

				// Add a text column to show the name.
				TextColumn<BatchDTO> raavareIDColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Integer.toString(object.getRaavare_id());
					}


				};
				batchTable.addColumn(raavareIDColumn, "Raavare ID");

				// Add a text column to show the name.
				TextColumn<BatchDTO> baWghtColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Double.toString(object.getBatchweight());
					}


				};
				batchTable.addColumn(baWghtColumn, "Batch Weight");

				// Add a text column to show the name.
				TextColumn<BatchDTO> toleranceColumn = new TextColumn<BatchDTO>() {
					@Override
					public String getValue(BatchDTO object) {
						return Double.toString(object.getTolerance());
					}


				};
				batchTable.addColumn(toleranceColumn, "Tolerance");

				final SingleSelectionModel<BatchDTO> selectionModel = new SingleSelectionModel<BatchDTO>();


				batchTable.setSelectionModel(selectionModel);
				selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						BatchDTO selected = selectionModel.getSelectedObject();
						/*
						 * Here we want to display the data in the table operatoer
						 * then we want to make it able to then show both coins and operatoer
						 * 
						 */
					
						batchSelect = selected;

					}
				});

				batchTable.setRowCount(batchList.size(), true);
				// Push the data into the widget.
				batchTable.setRowData(0, batchList);
				batchTable.redraw();
				ft.setWidget(0,1, batchTable);
			}
		});
	}

	

}
