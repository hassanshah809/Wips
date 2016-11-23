package model.wips.forms;

import java.util.List;
import java.util.Set;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import model.Wips;
import model.user.EndUser;

public class CoupleForSending {

	private ListView<String> distinctValues;
	private ListView<EndUser> filteredEndUser;
	
	private ObservableList<String> distinctValuesOb;
	private ObservableList<EndUser> filteredEndUserOb;
	
	public CoupleForSending(Set<String> distinctVal) {
		distinctValuesOb.addAll(distinctValuesOb);
		distinctValues.setItems(distinctValuesOb);
	}
	public void setDistinctOb(List<String> disticnVal) {
		distinctValuesOb.addAll(distinctValuesOb);
		distinctValues.setItems(distinctValuesOb);
	}
	
	public void filterUser(String val) {
		Wips wips = Wips.getInstance();
		List<EndUser> allUser = wips.getEndUser();
		for(int i = 0; i<allUser.size(); i++) {
			if(allUser.get(i).checkValue(val))
				filteredEndUserOb.add(allUser.get(i));
		}
		filteredEndUser.setItems(filteredEndUserOb);
	}
	
	public EndUser getEndUser() {
		EndUser user = filteredEndUser.getSelectionModel().getSelectedItem();
		return user;
	}
	public void listner() {
		distinctValues.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String> () {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				String val = distinctValues.getSelectionModel().getSelectedItem();
				filterUser(val);
			}
			
		});
	}
	
	public CoupleForSending(){
		distinctValues = new ListView<>();
		filteredEndUser = new ListView<>();
	}
	
	public ListView<String> getdisVal(){
		return distinctValues;
	}
	
	public ListView<EndUser> getfilUser() {
		return filteredEndUser;
	}
	
}
