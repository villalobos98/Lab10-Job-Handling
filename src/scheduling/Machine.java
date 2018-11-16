package scheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The machine class by @author Isaias Vilalobos coded in Java 9
 */
public class Machine {

	private int ID;
	private int finshTime;
	private int time;
	private List<Item> items;

	/**
	 *
	 * @param ID integer of the machine
	 */
	public Machine(int ID) {
		this.ID = ID;
		this.items = new ArrayList<>();
	}

	/**
	 * Get the ID of the MAHCINE
	 * @return id
	 */
	public int getID() {
		return ID;
	}

	/**
	 *  Finish time
	 * @return integer
	 */
	public int getFinishTime() {
		return time;
	}

	/**
	 * @return items
	 */
	public List<Item> getItems() {
		return items;
	}

	/**
	 * Add a a job
	 * @param job  A job object
	 * @param map A map varilable
	 */
	public void addJob(Job job, Map<Job, Integer> map) {
		int startTime = 0;
		int finishTime = 0;

		for (Job neighbor : job.getInNeighbors()) {
			if (map.get(neighbor) > startTime) {
				startTime = map.get(neighbor); // maxim
			}
		}
		if (startTime < this.getFinishTime()) {
			startTime = getFinishTime();
		}
		finishTime = startTime + job.getCosts()[this.ID];
		this.time = finishTime;
		map.put(job, finishTime);
		items.add(new Item(finishTime, job));
	}

	/**
	 * A setup
	 * @param lst Arraylist
	 * @param time time
	 */
	public void setup(List<Item> lst, int time) {
		this.items = lst;
		this.time = time;
	}

	/**
	 * String representation
	 * @return String
	 */
	public String toString() {
		StringBuilder string = new StringBuilder();
		for (Item it : items) {
			string.append(it.toString());
		}
		return string.toString();
	}
}
