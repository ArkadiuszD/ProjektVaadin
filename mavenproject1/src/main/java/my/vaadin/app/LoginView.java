package my.vaadin.app;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;


public class LoginView extends Window{

    private LoginForm loginForm;
    private Notification welcomeNotification;

    public LoginView(){
        super("Vaadin app");
        this.setResizable(true);
        //this.setHeight("100%");
        this.setClosable(true);
        this.setModal(true);
        this.setSizeFull();
        
   //     this.setIcon(FontAwesome.SIGN_IN);
        loginForm = new LoginForm();
        setContent(loginForm);
        prepareWelcomeMessage();

        this.addCloseListener((CloseListener) (closeEvent) -> {
            welcomeNotification.show(Page.getCurrent());

        });

    }

    public void prepareWelcomeMessage(){
        welcomeNotification = new Notification("You're in", Notification.Type.HUMANIZED_MESSAGE);
        welcomeNotification.setDelayMsec(300);
    }
}