package iRailExamples;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

import api.IRail;
import api.datastructs.Liveboard;
import api.datastructs.Station;

/**
 *
 * @author frank@apsu.be
 */
public class LiveboardController extends Observable implements Runnable {
	private int MINIMAL_DELAY = 30;
	private IRail iRail;
	private Station station;
	private Liveboard liveBoard;
	private boolean running;
	private int delay;

	public LiveboardController(IRail iRail, Station station) {
		this.iRail = iRail;
		this.station = station;
		this.delay = this.MINIMAL_DELAY;
	}

	public int getDelay() {
		return this.delay;
	}

	public void setDelay(int delay) {
		this.delay = (delay > this.MINIMAL_DELAY ? delay : this.MINIMAL_DELAY);
	}

	public Station getStation() {
		return this.station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

	public void start() {
		Thread thisThread = new Thread(this);
		thisThread.setDaemon(true);
		thisThread.start();
	}

	public void stop() {
		this.running = false;
	}

	@Override
	public void run() {
		this.running = true;
		while (this.running) {
			try {
				this.liveBoard = this.iRail.getLiveboard(this.station);
				this.setChanged();
				this.notifyObservers(this.liveBoard);
			} catch (Exception ex) {
				Logger.getLogger(LiveboardController.class.getName()).log(Level.SEVERE, null, ex);
			}
			try {
				Thread.sleep(this.delay * 1000);
			} catch (InterruptedException ex) {
				Logger.getLogger(LiveboardController.class.getName()).log(Level.SEVERE, null, ex);
				this.running = false;
			}
		}
	}
}