package scheduling;

import backtracker.Configuration;

import java.util.*;

/**
 * A way to find the best way to schedule jobs, uses various methods to do this.
 */
public class JobSchedulingConfig implements Configuration {


	private Map<Job, Integer> jobs;
	private int timeLimit;
	private ArrayList<Machine> numMachines;
	private TreeSet<Job> tree;
	private Job current;

	/**
	 * A constructor
	 *
	 * @param JOBS        map
	 * @param timeLimit   integer
	 * @param numMachines numMachines
	 */
	public JobSchedulingConfig(Map<String, Job> JOBS, int timeLimit, int numMachines) {
		this.jobs = jobs;
		this.timeLimit = timeLimit;
		this.tree = new TreeSet<Job>(new Comparator<Job>() {
			@Override
			public int compare(Job o1, Job o2) {
				return o1.getRank() < o2.getRank() ? -1 : 1;
			}
		});
		for (String j : JOBS.keySet()) {
			tree.add(JOBS.get(j));
		}
		this.numMachines = new ArrayList<Machine>();
		for (int i = 0; i < numMachines; i++) {
			this.numMachines.add(new Machine(i));
		}

	}

	/**
	 * A copy constructor
	 *
	 * @param other Configuration
	 */
	public JobSchedulingConfig(JobSchedulingConfig other) {
		this.jobs = new HashMap<>();
		this.current = other.current;
		this.numMachines = new ArrayList<>();
		for (int i = 0; i < other.numMachines.size(); i++) {
			this.numMachines.add(new Machine(i));
			this.numMachines.get(i).setup(other.numMachines.get(i).getItems(), other.numMachines.get(i).getFinishTime());
		}

	}

	/**
	 * Creates the different configurations
	 *
	 * @return list
	 */
	public Collection<Configuration> getSuccessors() {
		current = tree.pollFirst();
		ArrayList<Configuration> successors = new ArrayList<>();

		for (Machine m : numMachines) {
			JobSchedulingConfig copy = new JobSchedulingConfig(this);
			m.addJob(current, jobs);
			successors.add(copy);
		}
		return new ArrayList<>();

	}

	/**
	 * checks to see if its valid
	 *
	 * @return boolean
	 */
	@Override
	public boolean isValid() {
		if (current == null) {
			return true;
		}
		return this.jobs.get(current) <= timeLimit;
	}

	/**
	 * Check to find the end.
	 *
	 * @return boolean
	 */
	@Override
	public boolean isGoal() {
		if (numMachines.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * String representation of objects
	 * @return String
	 */
	public String toString() {
		StringBuilder string = new StringBuilder();

		for (Machine m : numMachines) {

			string.append("Machine" + "(" + m.getID() + "," + m.getFinishTime() + ")" + "\n");
		}
		return string.toString();
	}

	/**
	 * Prints out the name in order.
	 */
	public void displayJobsAssignmentOrder() {
		for(Job j: tree){
			System.out.println(j.getName());
		}
	}


}
