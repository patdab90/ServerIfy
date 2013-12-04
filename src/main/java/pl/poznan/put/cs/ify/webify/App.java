package pl.poznan.put.cs.ify.webify;

/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import pl.poznan.put.cs.ify.webify.gui.session.UserSession;
import pl.poznan.put.cs.ify.webify.gui.windows.MainWindow;

import com.vaadin.Application;

@Configurable(preConstruction = true)
@org.springframework.stereotype.Component(value = "application")
public class App extends Application {

	private static final long serialVersionUID = 1L;

	public static final String APPLICATION_TITLE = "Vaading-Spring Demo";
	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MainWindow mainWindow;

	@Autowired
	private UserSession session;

	/**
	 * @see com.vaadin.Application#init()
	 */
	@Override
	public void init() {
		setTheme("runo");
		SpringContextHelper helper = new SpringContextHelper(this);
		if (mainWindow == null) {
			log.debug("mainWindow is null.");
			mainWindow = (MainWindow) helper.getBean("mainWindow");
		}
		setMainWindow(mainWindow);
		mainWindow.setApplication(this);
		mainWindow.setUserSession(session);
		mainWindow.init();

	}
}
