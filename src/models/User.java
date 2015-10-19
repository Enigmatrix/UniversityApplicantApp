package models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User extends Entity {

	private StringProperty userNameProperty;
	// for the sake of simplicity im just storing it as cleartext
	private StringProperty userPasswordProperty;
	private StringProperty phoneNumberProperty;
	private StringProperty emailAddressProperty;
	private ObjectProperty<Byte[]> imageBytesProperty;

	public User() {
		userNameProperty = new SimpleStringProperty();
		userPasswordProperty = new SimpleStringProperty();
		phoneNumberProperty = new SimpleStringProperty();
		emailAddressProperty = new SimpleStringProperty();
		imageBytesProperty = new SimpleObjectProperty<>();
	}

	public Byte[] getImageBytes() {
		return imageBytesProperty.get();
	}

	public void setImageBytes(Byte[] imgBytes) {
		imageBytesProperty.set(imgBytes);
	}

	public String getUserName() {
		return userNameProperty.get();
	}

	public String getUserPassword() {
		return userPasswordProperty.get();
	}

	public String getPhoneNumber() {
		return phoneNumberProperty.get();
	}

	public String getEmailAddress() {
		return emailAddressProperty.get();
	}

	public void setUserName(String name) {
		userNameProperty.set(name);
	}

	public void setUserPassword(String password) {
		userPasswordProperty.set(password);
	}

	public void setPhoneNumber(String ph) {
		phoneNumberProperty.set(ph);
	}

	public void setEmailAddress(String eaddr) {
		emailAddressProperty.set(eaddr);
	}

	@JsonIgnore
	public ObjectProperty<Byte[]> getImageBytesProperty(){
		return imageBytesProperty;
	}
	
	@JsonIgnore
	public StringProperty getUserNameProperty() {
		return userNameProperty;
	}

	@JsonIgnore
	public StringProperty getUserPasswordProperty() {
		return userPasswordProperty;
	}

	@JsonIgnore
	public StringProperty getPhoneNumberProperty() {
		return phoneNumberProperty;
	}

	@JsonIgnore
	public StringProperty getEmailAddressProperty() {
		return emailAddressProperty;
	}

}
