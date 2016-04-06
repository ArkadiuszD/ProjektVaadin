package my.vaadin.app;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

public class RegistrationForm extends CustomComponent {

	public TextField user = new TextField("username:");
    public PasswordField pass = new PasswordField("Password:");
    public PasswordField confpass = new PasswordField("Confirm password:");
    public Button accept = new Button();
    public FormLayout form;
    BeanItemContainer<User> users =
    		new BeanItemContainer<User>(User.class); 

    public RegistrationForm(){
       		form = new FormLayout();
    		form.addComponent(user);
    		form.addComponent(pass);
    		form.addComponent(confpass);
    		form.addComponent(accept);
    		

    		VerticalLayout viewLayout = new VerticalLayout(form);
    		viewLayout.setSizeFull();
            viewLayout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
    		setCompositionRoot(viewLayout);
    	}
    	public void buttonClick(ClickEvent event) {
    		String username = user.getValue();
            String password1 = this.pass.getValue();
            String password2 = this.confpass.getValue();
            if(pass.equals(confpass)){
            	users.addBean(new User(user.getValue(), pass.getValue()));
    	}
    	}


}