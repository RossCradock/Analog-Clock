import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.util.concurrent.TimeUnit.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.Calendar;

public class DrawPanelHands extends JPanel {
    
    private final ScheduledExecutorService executor = 
	Executors.newScheduledThreadPool(1);
    private int hourHand = -1;
    private int minuteHand = -1;
    private int hour, minute, second;
    private List hourXY, minuteXY, secondXY;

    public DrawPanelHands() {
	Calendar calendar = Calendar.getInstance();
	hour = calendar.get(Calendar.HOUR);
	minute = calendar.get(Calendar.MINUTE);
	second = calendar.get(Calendar.SECOND);

	final Runnable timer = new Runnable() {
		public void run() {
		    repaint();
		    second++;
		    if(second == 60){
			second = 0;
			minute++;
		    }
		    if(minute == 60){
			minute = 0;
			hour++;
		    }
		}
	    };
	
	final ScheduledFuture<?> timerHandle = 
	    executor.scheduleAtFixedRate(timer, 0, 1000,
					 TimeUnit.MILLISECONDS);
    }

    public void paintComponent(Graphics graphics) {
	super.paintComponent(graphics);
	
	for(int i = 1; i <= 12; i++){
	    List coordinates = getCoordinates(i * 15);
	    graphics.drawString(String.valueOf(i),
				(int)(coordinates.get(0)), 
				(int)(coordinates.get(1)));
	}

	secondXY = getCoordinates(second * 3);
	graphics.setColor(Color.RED);
	graphics.drawLine((int)secondXY.get(0),
			  (int)secondXY.get(1),
			  257, 247);

	minuteXY = getCoordinates(minute * 3);
	graphics.setColor(Color.BLACK);
	graphics.drawLine((int)minuteXY.get(0),
			  (int)minuteXY.get(1),
			  257, 247);
	
	hourXY = getCoordinates(hour * 15);
	graphics.drawLine((int)hourXY.get(0),
			  (int)hourXY.get(1),
			  257, 247);

	/*to see output of time in seconds minutes and hours
	System.out.printf("second = %d\nminute = %d\nhour = %d\n" + 
			  "x = %d\ny = %d\n\n",
			  second, minute, hour,
			  (int)secondXY.get(0), (int)secondXY.get(1));
	*/
	
    }
    
    public static List getCoordinates(double angle){
	angle = Math.toRadians(angle);
	double z = 450 * Math.sin(angle);
	double x = z * Math.sin(Math.toRadians(90) - angle);
	double y = Math.sqrt((Math.pow(z, 2)) - (Math.pow(x, 2)));
	List   coordinates = new ArrayList();
	int xInt = ((Double)(x)).intValue();
	int yInt = ((Double)(y)).intValue();
	coordinates.add(xInt + 250);
	coordinates.add(yInt + 30);
	return coordinates;
    }

}
