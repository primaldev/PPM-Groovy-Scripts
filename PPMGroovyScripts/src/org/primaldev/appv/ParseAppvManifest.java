package org.primaldev.appv;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParseAppvManifest {
	
	InputStream xmlInput;
	
	public ParseAppvManifest(InputStream xmlInput) {
		this.xmlInput = xmlInput;
		try {
			parseXml();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			xmlInput.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void parseXml() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(xmlInput);
	     
	    Element element = doc.getDocumentElement();
	    NodeList nList = doc.getElementsByTagName("Package");
	    
	    for (int i = 0; i < nList.getLength(); i++) {
	    
	        if(nList.item(i).hasChildNodes()){
	            NodeList childNodes = nList.item(i).getChildNodes();
	            for (int y = 0; y < childNodes.getLength(); y++) {	                    
	                    if (childNodes.item(y).getNodeName().equalsIgnoreCase("appv:Extensions")) {
	                        parseAppvExtensions(childNodes.item(y));
	                    }
	                    
	                }            
	        }
	        
	    }
		 
	}

	 
	private void parseAppvExtensions(Node node) {   
		    NamedNodeMap atttrib = node.getAttributes();    
		    if (node.hasChildNodes()) {
		    	NodeList childNodes = node.getChildNodes();
		    	for (int i = 0; i < childNodes.getLength(); i++) {
		        
		            if (childNodes.item(i).hasAttributes()){
		                Element eElement = (Element) childNodes.item(i);
		                String attrib  = eElement.getAttribute("Category");
		                //print attrib + "\n";
		                if (attrib.equalsIgnoreCase("AppV.Shortcut")){
		                    parseAppvShortcuts(eElement.getChildNodes());
		                }
		            }            
		        }
		   }
		  
		}


		private void parseAppvShortcuts(NodeList subnode){
		    //printNote(subnode);
		    
		    //using an class instead of writing directly to the document,
		    //just in case another doc API is needed in the future
		    Collection<AppvShortCut> appvShortcuts = new ArrayList<AppvShortCut>();
		   for (int i = 0; i < subnode.getLength(); i++) { 
		   
			   	if (subnode.item(i).hasChildNodes()) {    
					   AppvShortCut appvShortcut = new AppvShortCut();
					    NodeList lnode = subnode.item(i).getChildNodes();
					   for (int y = 0; y < lnode.getLength(); y++) { 
		                                 
						   
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:File")){
		                       AppvShortCut.setFile(lnode.item(y).getTextContent());
		                   }                   
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Target")){
		                       AppvShortCut.setTarget(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Icon")){
		                       AppvShortCut.setIcon(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Arguments")){
		                       AppvShortCut.setArguments(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:WorkingDirectory")){
		                       AppvShortCut.setWorkingDirectory(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:ShowCommand")){
		                       AppvShortCut.setShowCommand(Boolean.valueOf(lnode.item(y).getTextContent()));
		                   }
		                 
		                  // print "Gs "  + lnode.getNodeName() + ":" + lnode.getTextContent() + "\n";                   
		                   
		               }   
					   appvShortcuts.add(appvShortcut);
					   
					   
			   	}
		   }
		   
		   
		    
		}

	
	

}
