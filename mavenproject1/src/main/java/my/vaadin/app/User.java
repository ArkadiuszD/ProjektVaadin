package my.vaadin.app;



import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.io.Serializable;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;



public class User implements Serializable  {

    @NotEmpty
    @Size(min = 5, max = 12)
    private String name;
    
    @NotEmpty
    @Size(min = 5)
    private String pass;
    
    public User(String string, String string2) {
		this.name = string;
		this.pass = string2;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}