import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class Thumnail extends Canvas
{
	public void paint(Graphics g)
    {
        Toolkit myToolkit = Toolkit.getDefaultToolkit();
        try{
        	if(xml.image==""){
        		Image myImage=myToolkit.getImage(".//noimage.gif");
        		g.drawImage(myImage,0,0,this);
        	}
        	else if(xml.image=="film"){
        		Image myImage=myToolkit.getImage(".//film.gif");
        		g.drawImage(myImage,0,0,this);
        	}
        	else{
	        	URL url=new URL(xml.image);
	        	Image myImage = myToolkit.getImage(url);
	        	g.drawImage(myImage , 0, 0, this);
        	}
        }catch(Exception e){}
    }
}