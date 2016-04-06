package my.vaadin.app;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class RegisterForm extends CustomComponent {

	public TextField user = new TextField("Input nick");
    public PasswordField pwd1 = new PasswordField("Password:");
    public PasswordField pwd2 = new PasswordField("Pass-check:");
    public Button submit = new Button();
    public FormLayout form;
    BeanItemContainer<User> users =
    		new BeanItemContainer<User>(User.class); 

    public RegisterForm(){
       		form = new FormLayout();
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