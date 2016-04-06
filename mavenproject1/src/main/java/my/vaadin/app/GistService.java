package my.vaadin.app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An in memory dummy "database" for the example purposes. In a typical Java app
 * this class would be replaced by e.g. EJB or a Spring based service class.
 * <p>
 * In demos/tutorials/examples, get a reference to this service class with
 * {@link GistService#getInstance()}.
 */
public class GistService {

	private static GistService instance;
        private static double sum;
	private static final Logger LOGGER = Logger.getLogger(GistService.class.getName());

	private final HashMap<Long, Gist> contacts = new HashMap<>();
	private long nextId = 0;

	private GistService() {
	}

	/**
	 * @return a reference to an example facade for Gist objects.
	 */
	public static GistService getInstance() {
		if (instance == null) {
			instance = new GistService();
			instance.ensureTestData();
		}
		return instance;
	}

	/**
	 * @return all available Gist objects.
	 */
	public synchronized List<Gist> findAll() {
		return findAll(null);
	}

	/**
	 * Finds all Gist's that match given filter.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @return list a Gist objects
	 */
	public synchronized List<Gist> findAll(String stringFilter) {
		ArrayList<Gist> arrayList = new ArrayList<>();
		for (Gist contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(GistService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Gist>() {

			@Override
			public int compare(Gist o1, Gist o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		return arrayList;
	}

	/**
	 * Finds all Gist's that match given filter and limits the resultset.
	 *
	 * @param stringFilter
	 *            filter that returned objects should match or null/empty string
	 *            if all objects should be returned.
	 * @param start
	 *            the index of first result
	 * @param maxresults
	 *            maximum result count
	 * @return list a Gist objects
	 */
	public synchronized List<Gist> findAll(String stringFilter, int start, int maxresults) {
		ArrayList<Gist> arrayList = new ArrayList<>();
		for (Gist contact : contacts.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
						|| contact.toString().toLowerCase().contains(stringFilter.toLowerCase());
				if (passesFilter) {
					arrayList.add(contact.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(GistService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		Collections.sort(arrayList, new Comparator<Gist>() {

			@Override
			public int compare(Gist o1, Gist o2) {
				return (int) (o2.getId() - o1.getId());
			}
		});
		int end = start + maxresults;
		if (end > arrayList.size()) {
			end = arrayList.size();
		}
		return arrayList.subList(start, end);
	}

	/**
	 * @return the amount of all customers in the system
	 */
	public synchronized long count() {
		return contacts.size();
	}
       
	/**
	 * Deletes a customer from a system
	 *
	 * @param value
	 *            the Gist to be deleted
	 */
	public synchronized void delete(Gist value) {
		contacts.remove(value.getId());
	}
        
        
	/**
	 * Persists or updates customer in the system. Also assigns an identifier
 for new Gist instances.
	 *
	 * @param entry
	 */
	public synchronized void save(Gist entry) {
		if (entry == null) {
			LOGGER.log(Level.SEVERE,
					"Form is empty");
			return;
		}
		if (entry.getId() == null) {
			entry.setId(nextId++);
		}
		try {
			entry = (Gist) entry.clone();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		contacts.put(entry.getId(), entry);
	}

	/**
	 * Sample data generation
	 */
	public void ensureTestData() {
		if (findAll().isEmpty()) {
			final String[] names = new String[] { "Oil Oil","Gas Gas","Tire Tire","Breaks Breaks"};
			Random r = new Random(0);
			for (String name : names) {
				String[] split = name.split(" ");
				Gist c = new Gist();
				c.setName(split[0]);
				c.setNote(split[1]);
				c.setPrice(r.nextInt(1000));
				c.setType(GistStatus.values()[r.nextInt(GistStatus.values().length)]);
				Calendar cal = Calendar.getInstance();
				c.setBuyDate(cal.getTime());
                                sum += c.getPrice();
				save(c);
			}
		}
	}

}