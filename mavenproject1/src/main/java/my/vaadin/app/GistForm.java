package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;

public class GistForm extends GistFormDesign {

	GistService service = GistService.getInstance();
	private Gist gist;
	private MyUI myUI;
        private Notification addNotification;

	public GistForm(MyUI myUI) {
		this.myUI = myUI;
		type.addItems(GistStatus.values());
		save.setClickShortcut(KeyCode.ENTER);
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
	}

	public void setGist(Gist gist) {
		this.gist = gist;
		BeanFieldGroup.bindFieldsUnbuffered(gist, this);

		// Show delete button for only customers already in the database
		delete.setVisible(gist.isPersisted());
		setVisible(true);
		name.selectAll();
	}

	private void delete() {
		service.delete(gist);
                Broadcaster.broadcast("");
                prepareDeleteMessage();
		myUI.updateList();
		setVisible(false);
	}

	private void save() {
		service.save(gist);
                Broadcaster.broadcast("");
                prepareAddMessage();
		myUI.updateList();
		setVisible(false);

       
	}
        
        public void prepareAddMessage(){
        addNotification = new Notification("Row added", Notification.Type.HUMANIZED_MESSAGE);
        addNotification.setDelayMsec(50);
        addNotification.show(Page.getCurrent());
    }
        
         public void prepareDeleteMessage(){
        addNotification = new Notification("Row deleted", Notification.Type.HUMANIZED_MESSAGE);
        addNotification.setDelayMsec(50);
        addNotification.show(Page.getCurrent());
    }
        
}
