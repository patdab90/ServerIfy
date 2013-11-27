package pl.poznan.put.cs.ify.webify.utils;

import java.util.List;

import com.vaadin.data.util.BeanItemContainer;

public class BeanUtils {

	/**
	 * Zamiana listy na ItemContainer
	 * 
	 * @param list
	 * @param cls
	 * @return
	 */
	public static <T> BeanItemContainer<T> getItemContainer(List<T> list,
			Class<T> cls) {
		BeanItemContainer<T> container = new BeanItemContainer<T>(cls);
		container.addAll(list);
		return container;
	}
}