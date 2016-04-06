package my.vaadin.app;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction.KeyCode;

public class GistForm extends GistFormDesign {

	GistService service = GistService.getInstance();
	private Gist gist;
	private MyUI myUI;

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
		myUI.updateList();
		setVisible(false);
	}

	private void save() {
		service.save(gist);
		myUI.updateList();
		setVisible(false);
	}
}
