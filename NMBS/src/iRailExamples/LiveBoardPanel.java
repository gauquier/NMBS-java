package iRailExamples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import api.datastructs.ArrivalDeparture;
import api.datastructs.Liveboard;

/**
 *
 * @author frank@apsu.be
 */
public class LiveBoardPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5448686831494956912L;
	private static final Color BACKGROUND_COLOR = new Color(48, 45, 59);
	private static final Color HEADER_COLOR = new Color(27, 118, 248);
	private static final Color LINE_COLOR = new Color(82, 102, 155);
	private static final Color TEXT_COLOR = new Color(255, 255, 0);
	private static final int NUM_LINES = 15;
	private static final int CHARS_IN_TITLE = 36;
	private static final int CHARS_IN_LINE = 50;
	private static final int MAX_CHARS_IN_STATION = 28;
	private static final String FONT = "Courier";
	private static final int MIN_FONT_SIZE = 2;
	private static final int MAX_FONT_SIZE = 72;
	private static final int TIME_COLUMN = 0;
	private static final int STATION_COLUMN = 1;
	private static final int TYPE_COLUMN = 2;
	private static final int PLATFORM_COLUMN = 3;
	private static final int DELAY_COLUMN = 4;

	private Liveboard liveBoard;
	private int width, height, borderThickness, imageableXPos, imageableYPos, imageableWidth, imageableHeight,
			lineHeight, dataYPos;
	private int titleBaselineOffset, lineBaselineOffset, titleImageableHeight, lineImageableHeight;
	private int[] lineColumns;
	private Font titleFont, lineFont;
	private Calendar calendar;
	private Dimension oldSize;
	private String captionLine;

	/*
	 * A LiveBoardPanel is an Observer that will show Liveboard instances in an
	 * NMBS-like "small station" display Since it's a JPanel, add it to any
	 * Container. The Liveboard prefers to be an arbitrary 640x480 but will
	 * resize to any reasonable size and remain readable as long as possible.
	 * Beware that the tricks for determining Font size can be computationally
	 * expensive.
	 */
	public LiveBoardPanel() {
		super();
		this.calendar = Calendar.getInstance();
		this.setDoubleBuffered(false);
		this.setBackground(BACKGROUND_COLOR);
		this.setPreferredSize(new Dimension(640, 480));
	}

	public void setCaptionLine(String captionLine) {
		this.captionLine = captionLine;
	}

	@Override
	public boolean isOpaque() {
		return true;
	}

	/*
	 * paints the liveboard into ourselves.
	 */
	@Override
	protected void paintComponent(Graphics graphics) {
		// recalculate metrics first time or when resized
		if (this.oldSize == null || this.oldSize.getHeight() != this.getHeight()
				|| this.oldSize.getWidth() != this.getWidth()) {
			this.calculateMetrics(graphics);
			this.oldSize = new Dimension(this.getWidth(), this.getHeight());
		}

		this.drawBorders(graphics);

		if (this.liveBoard == null) {
			return;
		}

		this.drawTitle(graphics);

		int line = 0;
		for (ArrivalDeparture event : this.liveBoard.getArrivalsAndDepartures()) {
			if (!event.hasLeft()) {
				this.calendar.setTime(event.getDate());
				this.drawArrivalDeparture(graphics, event, line++);
				if (line > (NUM_LINES - 3)) {
					break;
				}
			}
		}

		// remaining empty lines ; one less if there is a caption line to
		// display
		for (; line <= (this.captionLine != null ? (NUM_LINES - 4) : (NUM_LINES - 3)); line++) {
			this.drawLineBackground(graphics, line);
		}

		// This is just drawing a simple line for advertisement *evil*
		if (this.captionLine != null) {
			this.drawString(graphics, this.captionLine, (NUM_LINES - 3));
		}
	}

	/*
	 * calculates various places to draw, and Fonts to use to fit all the info
	 * on the current size
	 */
	private void calculateMetrics(Graphics g2d) {
		FontMetrics metrics = null;

		this.width = this.getWidth();
		this.height = this.getHeight();
		this.borderThickness = (this.height / NUM_LINES) / 4;
		this.imageableXPos = this.borderThickness;
		this.imageableYPos = this.borderThickness;
		this.imageableWidth = this.width - (this.borderThickness * 2);
		this.imageableHeight = this.height - (this.borderThickness * 3);
		this.lineHeight = this.imageableHeight / NUM_LINES;
		this.dataYPos = this.imageableYPos + (this.lineHeight * 2) + this.borderThickness;

		this.titleImageableHeight = (this.lineHeight * 2) - (this.lineHeight / 16);
		this.lineImageableHeight = this.lineHeight - (this.lineHeight / 32);

		this.titleFont = this.fontThatFits(g2d, this.imageableWidth, this.titleImageableHeight, CHARS_IN_TITLE);
		if (this.titleFont != null) {
			metrics = g2d.getFontMetrics(this.titleFont);
			this.titleBaselineOffset = metrics.getAscent() + (this.titleImageableHeight - metrics.getHeight()) / 2;
		}

		this.lineFont = this.fontThatFits(g2d, this.imageableWidth, this.lineImageableHeight, CHARS_IN_LINE);
		if (this.lineFont != null) {
			metrics = g2d.getFontMetrics(this.lineFont);
			this.lineBaselineOffset = metrics.getAscent() + (this.lineImageableHeight - metrics.getHeight()) / 2;
		}

		if (metrics != null) {
			this.lineColumns = new int[5];
			this.lineColumns[TIME_COLUMN] = this.imageableXPos + metrics.getMaxAdvance();
			this.lineColumns[STATION_COLUMN] = this.lineColumns[TIME_COLUMN] + (6 * metrics.getMaxAdvance());
			this.lineColumns[TYPE_COLUMN] = this.lineColumns[STATION_COLUMN]
					+ ((MAX_CHARS_IN_STATION + 1) * metrics.getMaxAdvance());
			this.lineColumns[PLATFORM_COLUMN] = this.lineColumns[TYPE_COLUMN] + (5 * metrics.getMaxAdvance());
			this.lineColumns[DELAY_COLUMN] = this.lineColumns[PLATFORM_COLUMN] + (4 * metrics.getMaxAdvance());
		}
	}

	// draw border and separator between title and lines
	private void drawBorders(Graphics g2d) {
		g2d.setColor(BACKGROUND_COLOR);
		g2d.fillRect(0, 0, this.width, this.borderThickness);
		g2d.fillRect(0, this.height - (this.borderThickness * 2), this.width, (this.borderThickness * 2));

		g2d.fillRect(0, 0, this.borderThickness, this.height);
		g2d.fillRect(this.width - this.borderThickness, 0, this.borderThickness, this.height);

		g2d.fillRect(0, this.imageableYPos + (2 * this.lineHeight), this.width, this.borderThickness);

	}

	private void drawLineBackground(Graphics graphics, int line) {
		graphics.setColor(line % 2 == 0 ? LINE_COLOR : BACKGROUND_COLOR);
		graphics.fillRect(this.imageableXPos, this.dataYPos + (line * this.lineHeight), this.imageableWidth,
				this.lineHeight);
	}

	// draw title at the top, displays timestamp and station name
	private void drawTitle(Graphics graphics) {
		graphics.setColor(HEADER_COLOR);
		graphics.fillRect(this.imageableXPos, this.imageableYPos, this.imageableWidth, this.lineHeight * 2);
		if (this.titleFont != null) {
			graphics.setFont(this.titleFont);
			graphics.setColor(Color.white);
			this.calendar.setTime(this.liveBoard.getTimeStamp());
			graphics.drawString(
					String.format("%1$2d:%2$02d (%3$s)", this.calendar.get(Calendar.HOUR_OF_DAY),
							this.calendar.get(Calendar.MINUTE), this.liveBoard.getStation().getName()),
					this.lineColumns[TIME_COLUMN], this.imageableYPos + this.titleBaselineOffset);
		}
	}

	private void drawString(Graphics graphics, String text, int line) {
		this.drawLineBackground(graphics, line);

		if (this.lineFont != null) {
			graphics.setFont(this.lineFont);
			graphics.setColor(TEXT_COLOR);
			graphics.drawString((text.length() > CHARS_IN_LINE ? text.substring(0, (CHARS_IN_LINE - 2)) + ".." : text),
					this.lineColumns[TIME_COLUMN], this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));
		}
	}

	// draw one departure line: time, station name, vehicle type, platform, and
	// optionally delay
	// will vehicles that are supposed to to departed are displayed in light
	// gray
	private void drawArrivalDeparture(Graphics graphics, ArrivalDeparture departure, int line) {
		this.drawLineBackground(graphics, line);

		if (departure != null && this.lineFont != null) {
			this.calendar.setTime(departure.getDate());

			graphics.setFont(this.lineFont);

			if (departure.shouldHaveLeftAt(this.liveBoard.getTimeStamp())) {
				graphics.setColor(TEXT_COLOR);
			} else {
				graphics.setColor(Color.LIGHT_GRAY);
			}

			graphics.drawString(
					String.format("%1$2d:%2$02d", this.calendar.get(Calendar.HOUR_OF_DAY),
							this.calendar.get(Calendar.MINUTE)),
					this.lineColumns[TIME_COLUMN], this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));

			graphics.drawString(
					(departure.getStation().getName().length() > MAX_CHARS_IN_STATION
							? departure.getStation().getName().substring(0, (MAX_CHARS_IN_STATION - 2)) + ".."
							: departure.getStation().getName()),
					this.lineColumns[STATION_COLUMN],
					this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));

			if (departure.getVehicle().getType() != null) {
				graphics.drawString(String.format("%1$-4s", departure.getVehicle().getType()),
						this.lineColumns[TYPE_COLUMN],
						this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));
			} else {
				graphics.drawString("---", this.lineColumns[TYPE_COLUMN],
						this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));
			}

			if (departure.getPlatform() != null) {
				graphics.drawString(String.format("%1$2s", departure.getPlatform()), this.lineColumns[PLATFORM_COLUMN],
						this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));
			} else {
				graphics.drawString("--", this.lineColumns[PLATFORM_COLUMN],
						this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));
			}

			if (departure.getDelay() > 0) {
				graphics.setColor(Color.red);
				graphics.drawString(
						String.format("+%1$1dH%2$02d", (departure.getDelay() / 60) / 60,
								(departure.getDelay() / 60) % 60),
						this.lineColumns[DELAY_COLUMN],
						this.dataYPos + this.lineBaselineOffset + (line * this.lineHeight));
			}
		}
	}

	// hack to find a Font instance that will fit the number of characters in
	// our current size
	private Font fontThatFits(Graphics g2d, int width, int height, int terminalWidth) {
		Font testFont, font = null;

		for (int size = MAX_FONT_SIZE; size > MIN_FONT_SIZE; size--) {
			testFont = new Font(FONT, Font.PLAIN, size);
			FontMetrics metrics = g2d.getFontMetrics(testFont);
			if ((metrics.getMaxAdvance() * terminalWidth) < width && metrics.getHeight() < height) {
				font = testFont;
				break;
			}
		}

		return font;
	}

	// called by a LiveboardController when a new Liveboard is available
	@Override
	public void update(Observable observable, Object observed) {
		this.liveBoard = (Liveboard) observed;
		this.repaint();
	}
}