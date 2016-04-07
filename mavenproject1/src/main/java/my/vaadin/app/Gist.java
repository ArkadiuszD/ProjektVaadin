package my.vaadin.app;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
public class Gist implements Serializable, Cloneable {

	private Long id;
        
        @NotEmpty(message = "This field can't be empty")
        @Size(min = 3, max = 20, message = "Name must have 3 or more character")
	private String name = "";

      //  @NotEmpty(message = "This field can't be empty")
        @Size(min = 3, max = 100, message = "Note must have 3 or more character")
	private String note = "";
        
        @Past(message = "Date can't be from future")
	private Date buyDate;
        
        @NotEmpty(message = "This field can't be empty")
	private GistStatus type;
        
        @Min(value=0, message = "Only possitive value" )
	private double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}
        

	public GistStatus getType() {
		return type;
	}

	public void setType(GistStatus type) {
		this.type = type;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}


	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPersisted() {
		return id != null;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (this.id == null) {
			return false;
		}

		if (obj instanceof Gist && obj.getClass().equals(getClass())) {
			return this.id.equals(((Gist) obj).id);
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 43 * hash + (id == null ? 0 : id.hashCode());
		return hash;
	}

	@Override
	public Gist clone() throws CloneNotSupportedException {
		return (Gist) super.clone();
	}

	@Override
	public String toString() {
		return name + " " + note;
	}
}