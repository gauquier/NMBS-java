package api;

public class BillBoard {
private int id;
private int delay;
private boolean canceled;
private String direction;
private String date;
private String time;
private String train;
private int platform;
public BillBoard(int id, int delay, boolean canceled, String direction, String date, String time, String train, int platform)
{
	super();
	this.id = id;
	this.delay = delay;
	this.canceled = canceled;
	this.direction = direction;
	this.date = date;
	this.time = time;
	this.train = train;
	this.platform = platform;
}
@Override
public String toString() {
	return id+1 + "[delay=" + delay + ", canceled=" + canceled + ", direction=" + direction + ", date=" + date
			+ ", time=" + time + ", train=" + train + ", platform=" + platform + "]";
}


}
