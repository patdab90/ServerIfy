package pl.poznan.put.cs.ify.webify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.vaadin.Application;

public class Inject {

	static Application application;
	static ApplicationContext applicationContext;

	
	@SuppressWarnings("deprecation")
	public static void inject(final Object object) {
		if (applicationContext == null) {
			ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
					.currentRequestAttributes();
			HttpServletRequest request = requestAttributes.getRequest();
			HttpSession session = request.getSession(false);
			applicationContext = WebApplicationContextUtils
					.getRequiredWebApplicationContext(session
							.getServletContext());
		}

		AutowireCapableBeanFactory beanFactory = applicationContext
				.getAutowireCapableBeanFactory();
		beanFactory.autowireBeanProperties(object,
				AutowireCapableBeanFactory.AUTOWIRE_AUTODETECT, false);

	}

}