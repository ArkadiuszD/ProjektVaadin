package my.vaadin.app;

import java.io.Serializable;
import java.util.Date;

/**
 * A entity object, like in any other Java application. In a typical real world
 * application this could for example be a JPA entity.
 */
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

	/**
	 * Get the value of price
	 *
	 * @return the value of price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Set the value of price
	 *
	 * @param price
	 *            new value of price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Get the value of type
	 *
	 * @return the value of type
	 */
	public GistStatus getType() {
		return type;
	}

	/**
	 * Set the value of type
	 *
	 * @param type
	 *            new value of type
	 */
	public void setType(GistStatus type) {
		this.type = type;
	}

	/**
	 * Get the value of buyDate
	 *
	 * @return the value of buyDate
	 */
	public Date getBuyDate() {
		return buyDate;
	}

	/**
	 * Set the value of buyDate
	 *
	 * @param buyDate
	 *            new value of buyDate
	 */
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	/**
	 * Get the value of note
	 *
	 * @return the value of note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Set the value of note
	 *
	 * @param note
	 *            new value of note
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Get the value of name
	 *
	 * @return the value of name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the value of name
	 *
	 * @param name
	 *            new value of name
	 */
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