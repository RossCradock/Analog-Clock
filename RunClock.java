import javax.swing.JFrame;

public class RunClock {
    public static void main(String[] args) {
	DrawFrame frame = new DrawFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(510, 520);
	frame.setVisible(true);
    }
}
