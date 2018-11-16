package scheduling;

public class Item {
	private int time;
	private Job job;

	public Item(int time, Job job) {
		this.time = time;
		this.job = job;
	}

	public int getTime() {
		return time;
	}
	public Job getJob() {
		return job;
	}
	public String getName(){
		return job.getName();
	}

	@Override
	public String toString() {
		return ("(" + getName() + "," + getTime() + ")");
	}
}
