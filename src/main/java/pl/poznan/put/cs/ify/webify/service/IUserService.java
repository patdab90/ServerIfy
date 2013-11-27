package pl.poznan.put.cs.ify.webify.service;

import pl.poznan.put.cs.ify.webify.data.entity.user.UserEntity;

/**
 * Interfejs BO użytkownika
 * 
 * 
 * @date 30-05-2013
 * 
 */
public interface IUserService extends IBaseService<UserEntity> {

	/**
	 * Sprawdzenie czy użytkownik ma zapewniony dostęp do danego widoku
	 * 
	 * 
	 * @date 31-05-2013
	 * 
	 * @param id
	 * @param view
	 * @return
	 */
	public boolean canAccessView(Long id, Object view);

	/**
	 * Sprawdzenie czy użytkownik ma zapewniony dostęp do danego widoku
	 * 
	 * 
	 * @date 31-05-2013
	 * 
	 * @param id
	 * @param view
	 * @return
	 */
	public boolean canAccessView(String username, Object view);

	/**
	 * Sprawdzenie czy użytkownik ma zapewniony dostęp do danego widoku
	 * 
	 * @date 31-05-2013
	 * 
	 * @param id
	 * @param view
	 * @return
	 */
	public boolean canAccessView(UserEntity user, Object view);

	/**
	 * Sprawdzanie loginu i hasła użytkownika
	 * 
	 * @author
	 * @author
	 * @date 31-05-2013
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password);

	/**
	 * Pobieranie encji po nazwie użytkownika
	 * 
	 * @param username
	 * @return
	 */
	public UserEntity getByUsername(String username);

}
