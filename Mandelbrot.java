import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Mandelbrot extends JPanel {

    private BufferedImage canvas;

    public Mandelbrot(int width, int height) {
        canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        fillCanvas(Color.WHITE);
        move(width, height);
    }

    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);

        
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        repaint();
    }

    public void move(int width, int height) {
    	
    	for(int i = 0;i < width;i++)
    		for(int j = 0;j < height;j++)
    			calc(i, j);

    }

    public void calc(int x, int y) {
    	
    	double x0 = (1.0/300.0)*x - (5.0/2.0);
    	double y0 = (1.0/300.0)*y - (1.0);

    	double z_x = 0.0;
    	double z_y = 0.0;

    	double x_temp = 0.0;

    	int iteration = 0;
    	int max_iterations = 765;

    	while(z_x*z_x + z_y*z_y < 4.0 && iteration < max_iterations)
    	{
    		x_temp = z_x*z_x - z_y*z_y + x0;
    		z_y = 2.0*z_x*z_y + y0;
    		z_x = x_temp;
    		iteration++;
    	}

    	if(iteration == max_iterations)
    	{
    		canvas.setRGB( x, y, (Color.BLACK).getRGB());
    	}
    	else
    	{
    		int r = 0,g = 0,b = 0;

    		int temp_iteration = iteration % 165;

    		if(temp_iteration <= 55)
    			r = temp_iteration*3;
    		else if(temp_iteration <= 55*2 && temp_iteration > 55)
    			g = (temp_iteration - 55) * 3;
    		else if(temp_iteration <= 55*3 && temp_iteration > 55*2)
    			b = (temp_iteration - 55*2 ) * 3;

    		Color color = new Color( r + 15, g + 15, b + 15 );

    		canvas.setRGB( x, y, color.getRGB());
    	}
    }

    public static void main(String[] args) {
        
        int width = 1050;
        int height = 600;

        JFrame frame = new JFrame("Mandelbrot Set");

        Mandelbrot panel = new Mandelbrot(width, height);

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}