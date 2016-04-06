package my.vaadin.app;

import java.util.List;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@Theme("mytheme")
@Widgetset("my.vaadin.app.MyAppWidgetset")
public class MyUI extends UI implements Broadcaster.BroadcastListener {

	public TextField user = new TextField("Nickname:");
	public PasswordField pwd1 = new PasswordField("Password:");
	public PasswordField pwd2 = new PasswordField("Pass-check:");
	public Button register = new Button();
	public FormLayout regform;
	private VerticalLayout viewLayout;

	private Button logout;
	private VerticalLayout layout;
	private GistService service = GistService.getInstance();
	private Grid grid = new Grid();
	private TextField filterText = new TextField();
	GistForm form = new GistForm(this);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		//final VerticalLayout layout = new VerticalLayout();
                BeanItemContainer<User> users = new BeanItemContainer<User>(User.class);
		regform = new FormLayout();
		regform.addComponent(user);
		regform.addComponent(pwd1);
		regform.addComponent(pwd2);
                Broadcaster.register(this);
		

		register = new Button("register", (Button.ClickListener) (clickEvent) -> {
			String username = user.getValue();
			String password1 = pwd1.getValue();
			String password2 = pwd2.getValue();
			if (!pwd1.getValue().equals(pwd2.getValue())) {
				Notification errorNotification = new Notification("Wrong passwords", Notification.Type.ERROR_MESSAGE);
				errorNotification.setDelayMsec(200);
				errorNotification.show(Page.getCurrent());
			}
			else {
				users.addBean(new User(user.getValue(), pwd1.getValue()));
				LoginView loginPanelWindow = new LoginView();
				getUI().addWindow(loginPanelWindow);
				setContent(null);
			}
		});
		regform.addComponent(register);
		viewLayout = new VerticalLayout();
		viewLayout.setSizeFull();
		viewLayout.addComponent(regform);
		viewLayout.setComponentAlignment(regform, Alignment.MIDDLE_CENTER);

		layout = new VerticalLayout();
		// layout.setSizeUndefined();
		layout.setWidth("100%");
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		// MAIN

		logout = new Button("logout", (Button.ClickListener) (clickEvent) -> {
			getCurrentSession().removeAttribute("user");
			setContent(null);
			getUI().addWindow(new LoginView());
		});

		layout.addComponent(logout);

		filterText.setInputPrompt("Write part name");
		filterText.addTextChangeListener(e -> {
			grid.setContainerDataSource(new BeanItemContainer<>(Gist.class, service.findAll(e.getText())));
		});

		Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
		clearFilterTextBtn.setDescription("Clear");
		clearFilterTextBtn.addClickListener(e -> {
			filterText.clear();
			updateList();
		});

		CssLayout filtering = new CssLayout();
		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		Button addBtn = new Button("Add new gist");
		addBtn.addClickListener(e -> {
			grid.select(null);
			form.setGist(new Gist());
		});

		HorizontalLayout toolbar = new HorizontalLayout(filtering, addBtn);
		toolbar.setSpacing(true);

		grid.setColumns("name", "price","type", "buyDate");

		HorizontalLayout main = new HorizontalLayout(grid, form);
		main.setSpacing(true);
		main.setSizeFull();
		grid.setSizeFull();
		main.setExpandRatio(grid, 1);

		layout.addComponents(toolbar, main);

		updateList();

		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);

		form.setVisible(false);

		grid.addSelectionListener(event -> {
			if (event.getSelected().isEmpty()) {
				form.setVisible(false);
			} else {
				Gist gist = (Gist) event.getSelected().iterator().next();
				form.setGist(gist);
			}
		});
                
                if (!isLoggedIn()) {
			LoginView loginPanelWindow = new LoginView();
			getUI().addWindow(loginPanelWindow);
			setContent(null);
		} else {
			setContent(layout);
		}

	}
        
        public static boolean isRegistered() {
		WrappedSession currentSession = VaadinService.getCurrentRequest().getWrappedSession();
		return currentSession.getAttribute("register") != null;
	}

	public static boolean isLoggedIn() {
		WrappedSession currentSession = VaadinService.getCurrentRequest().getWrappedSession();
		return currentSession.getAttribute("user") != null;
	}

	public static WrappedSession getCurrentSession() {
		return VaadinService.getCurrentRequest().getWrappedSession();
	}

	public void updateList() {
		// fetch list of Customers from service and assign it to Grid
		List<Gist> customers = service.findAll(filterText.getValue());
		grid.setContainerDataSource(new BeanItemContainer<>(Gist.class, customers));
	}

        @Override
    public void receiveBroadcast(final String message) {
        // Must lock the session to execute logic safely
        access(new Runnable() {
            @Override
            public void run() {
                updateList();
            }
        });
    }
	@Override
	public void detach() {
		Broadcaster.unregister(this);
		super.detach();
	}
        
        public void setRegContent() {
		setContent(viewLayout);
	}

	public void setMainContent() {
		setContent(layout);
	}
        
	@WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
	@VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
	public static class MyUIServlet extends VaadinServlet {
	}
        

}
