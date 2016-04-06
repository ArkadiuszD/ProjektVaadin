package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

public class LoginForm extends CustomComponent {

	private TextField login;
	private PasswordField password;
	private FormLayout formLayout;
	private FieldGroup binder;
	private BeanItem<User> item;
	private User user;
	private Notification errorNotification;
	private Button submit;
	private Button register;

	public LoginForm() {
		formLayout = new FormLayout();
		user = new User();
		item = new BeanItem<>(user);
		binder = new BeanFieldGroup<>(User.class);
		binder.setItemDataSource(item);
		binder.setBuffered(true);
		this.addFormFieldsToLayout();
		formLayout.setMargin(true);
		this.setCompositionRoot(formLayout);
	}

	private void addFormFieldsToLayout() {
		login = binder.buildAndBind("login", "name", TextField.class);
		password = binder.buildAndBind("password", "pass", PasswordField.class);
		password.setValue("");
		login.setValue("");
		submit = new Button("submit", (Button.ClickListener) (clickEvent) -> {

			try {
				binder.commit();

				BeanItemContainer<User> users = new BeanItemContainer<User>(User.class);
				// TODO: remove it to validation class after db integration
				if ((!user.getName().equals("test1") && !user.getPass().equals("test1")) &&
						(!user.getName().equals("test2") && !user.getPass().equals("test2"))){
					showLoginErrorMessage();
					return;
				}
				LoginPanelWindow parent = (LoginPanelWindow) getParent();

				MyUI root = (MyUI) parent.getParent();
				parent.close();
                                root.setMainContent();
				MyUI.getCurrentSession().setAttribute("user", "user");
			} catch (FieldGroup.CommitException e) {
				e.printStackTrace();
			}
		});
		register = new Button("register", (Button.ClickListener) (clickEvent) -> {
			LoginPanelWindow parent = (LoginPanelWindow) getParent();
			MyUI root = (MyUI) parent.getParent();
			parent.close();
                        root.setRegContent();
			MyUI.getCurrentSession().setAttribute("register", "register");
			

		});
		formLayout.addComponents(login, password, submit, register);
	}

	private void showLoginErrorMessage() {
		errorNotification = new Notification("Bad login or password", Notification.Type.ERROR_MESSAGE);
		errorNotification.setDelayMsec(200);
		errorNotification.show(Page.getCurrent());
	}

}