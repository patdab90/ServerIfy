package pl.poznan.put.cs.ify.webify.gui.session;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Dane sesji użytkownika
 * 

 * @date 15-06-2013
 * 
 */
@Component(value = "userSession")
@Scope(value = "session")
public class UserSession {

	/**
	 * Nazwa użytkownika (podane w formularzu)
	 */
	private String userName = "";
	/**
	 * Hasło użytkownika (podane w formularzu)
	 */
	private String userPassword = "";
	/**
	 * Czy użytkownik jest zalogowany w danej sesji
	 */
	private boolean logged = false;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the userPassword
	 */
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword){
		this.userPassword = userPassword;
	}
	
	/**
	 * @return the logged
	 */
	public boolean isLogged() {
		return logged;
	}
	
	public void setLogged(boolean logged){
		this.logged = logged;
	}

}
