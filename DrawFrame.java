import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;
import java.util.List;

public class DrawFrame extends JFrame {
    
    public DrawFrame() {
	super("Analog Clock");
	setLayout(null);
	DrawPanelHands panelHands = new DrawPanelHands();
	panelHands.setBounds(0, 0, 500, 500);
	add(panelHands);
    }
}
	
			    
