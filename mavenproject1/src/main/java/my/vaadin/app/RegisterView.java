package my.vaadin.app;

import java.awt.List;

import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.data.util.PropertysetItem;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

public class RegisterView extends CustomComponent implements View {

    public TextField user = new TextField("Input nick");
    public PasswordField pwd1 = new PasswordField();
    public PasswordField pwd2 = new PasswordField();
    public Button submit = new Button();
    BeanItemContainer<User> users =
    		new BeanItemContainer<User>(User.class); 
    
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	public RegisterView(){
		
		
		FormLayout form = new FormLayout();
		form.addComponent(user);
		form.addComponent(pwd1);
		form.addComponent(pwd2);
		form.addComponent(submit);
		

		VerticalLayout viewLayout = new VerticalLayout(form);
		viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		setCompositionRoot(viewLayout);
	}
	public void buttonClick(ClickEvent event) {
		String username = user.getValue();
        String password1 = this.pwd1.getValue();
        String password2 = this.pwd2.getValue();
        if(pwd1.equals(pwd2)){
        	users.addBean(new User(user.getValue(), pwd1.getValue()));
	}
	}
}
