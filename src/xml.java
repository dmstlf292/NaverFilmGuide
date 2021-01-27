import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class xml{
	public static String director="";
	public static String actor="";
	public static String title="";
	public static String image="film";
	public static String rate="";
	public static String subtitle="";
	public static String pubDate="";
	public static String link="http://movie.naver.com";
	
	private DocumentBuilderFactory dbf;
	private DocumentBuilder db;
	private Document doc;
	private NodeList nodeList;
	
	private void SubNode(Node node){
		NodeList list=node.getChildNodes();
		for(int i=0; i<list.getLength(); i++){
			if(list.item(i).getNodeType()==Node.ELEMENT_NODE){
				if(list.item(i).getNodeName()=="director"){
					director=list.item(i).getTextContent();
				}
				if(list.item(i).getNodeName()=="actor"){
					actor=list.item(i).getTextContent();
					if(actor.length()>50)
						actor=actor.substring(0, 50) +"...";
				}
				if(list.item(i).getNodeName()=="title"){
					title=list.item(i).getTextContent();
				}
				if(list.item(i).getNodeName()=="image"){
					image=list.item(i).getTextContent();
				}
				if(list.item(i).getNodeName()=="userRating"){
					rate=list.item(i).getTextContent();
				}
				if(list.item(i).getNodeName()=="pubDate"){
					pubDate=list.item(i).getTextContent();
				}
				if(list.item(i).getNodeName()=="subtitle"){
					if(list.item(i).getTextContent()=="")
						subtitle="";
					else
						subtitle="("+list.item(i).getTextContent()+")";
				}
				if(list.item(i).getNodeName()=="link"){
					link=list.item(i).getTextContent();
				}
			}
			SubNode(list.item(i));
		}
	}
	
	public void xmlRead(String key, int intStart){
		String start=Integer.toString(intStart); String display="1";
		String url="http://openapi.naver.com/search?key=c4ff59a1b994430d9e4acdc06122b1c7&query="+key+ "&display="+display+"&start="+start+"&target=movie";
		
		try{
			dbf=DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			doc = db.parse(url);
			doc.getDocumentElement().normalize();
			nodeList = doc.getElementsByTagName("item");
			//for(int i = 0; i < nodeList.getLength(); i++)
			//{	
				SubNode((Element)nodeList.item(0));
			//}
		}catch(NullPointerException nex){title="nullpointer";}
		catch(Exception ex){System.out.println("!"+ex.toString()+"!");}
	}
}