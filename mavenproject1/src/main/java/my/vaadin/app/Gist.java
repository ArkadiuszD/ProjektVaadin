package my.vaadin.app;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Gist implements Serializable, Cloneable {

	private Long id;

	private String name = "";

	private String note = "";

	private Date buyDate;

	private GistStatus type;

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