package Main;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


	public class Information{


	   public void readXML(){
		   final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		   try {
			    final DocumentBuilder builder = factory.newDocumentBuilder();
			}
			catch (final ParserConfigurationException e) {
			   e.printStackTrace();
			}	  
	   }
	}

