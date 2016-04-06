package my.vaadin.app;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;


public class RegisterPanelWindow extends Window{

    private LoginForm RegisterForm;
    private Notification welcomeNotification;

    public RegisterPanelWindow(){
        super("WELCOME");
        this.setResizable(false);
        this.setClosable(false);
        this.setModal(true);
        this.setIcon(FontAwesome.SIGN_IN);
        RegisterForm = new LoginForm();
        setContent(RegisterForm);
        prepareWelcomeMessage();

        this.addCloseListener((CloseListener) (closeEvent) -> {
            welcomeNotification.show(Page.getCurrent());

        });

    }

    public void prepareWelcomeMessage(){
        welcomeNotification = new Notification("Logged successfully", Notification.Type.HUMANIZED_MESSAGE);
        welcomeNotification.setDelayMsec(300);
    }
}