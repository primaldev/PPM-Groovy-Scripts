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
	Collection<AppvShortCut> appvShortcuts;
	Collection<AppvAppPath> appvAppPaths;
	Collection<AppvFileTypeAssociation> appvFileTypeAssociations;
	
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
	
	public Collection<AppvShortCut> getAppvShortcuts() {
		return appvShortcuts;
	}
	
	public Collection<AppvAppPath> getAppvAppPath() {
		return appvAppPaths;
	}
	
	private void parseXml() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		
	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    Document doc = dBuilder.parse(xmlInput);
	     
	    
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
		   
		    if (node.hasChildNodes()) {
		    	NodeList childNodes = node.getChildNodes();
		    	for (int i = 0; i < childNodes.getLength(); i++) {
		        
		            if (childNodes.item(i).hasAttributes()){
		                Element eElement = (Element) childNodes.item(i);
		                String attrib  = eElement.getAttribute("Category");
		                
		                if (attrib.equalsIgnoreCase("AppV.Shortcut")){
		                    parseAppvShortcuts(eElement.getChildNodes());
		                }
		                
		                if (attrib.equalsIgnoreCase("AppV.AppPath")){
		                	parseAppvAppPath(eElement.getChildNodes());
		                }
		                
		                if (attrib.equalsIgnoreCase("AppV.FileTypeAssociation")){
		                	parseAppvFileTypeAssociation(eElement.getChildNodes());
		                }
		                
		            }            
		        }
		   }
		  
		}
	
	
	private void parseAppvFileTypeAssociation(NodeList subnode){
		appvFileTypeAssociations = new ArrayList<AppvFileTypeAssociation>();
		
		for (int y = 0; y < subnode.getLength(); y++) { 
			if (subnode.item(y).hasChildNodes()) { 
				NodeList lnode = subnode.item(y).getChildNodes();
				AppvFileTypeAssociation appvFileTypeAssociation = new AppvFileTypeAssociation();	
				
				for (int i = 0; i < lnode.getLength(); i++) {
					if(lnode.item(i).hasChildNodes()){
						NodeList enodes = lnode.item(i).getChildNodes();
						
						//FileExtension
						if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:FileExtension")){	
							
							for (int x = 0; x < enodes.getLength(); x++) {
								if (enodes.item(x).hasChildNodes()){
									if(enodes.item(x).hasAttributes()){
										Element eElement = (Element) enodes.item(x);
										appvFileTypeAssociation.setMimeAssociation(Boolean.valueOf(eElement.getAttribute("MimeAssociation")));
									}

									if(enodes.item(x).getNodeName().equalsIgnoreCase("appv:Name")){
										appvFileTypeAssociation.setName(enodes.item(x).getTextContent());
									}

									if(enodes.item(x).getNodeName().equalsIgnoreCase("ProgId")){
										appvFileTypeAssociation.setProgId(enodes.item(x).getTextContent());
									}

									
								}
							}
							
						}
						
						
						//Progid (shell extensions not really needed)
						if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:ProgId")){
							Collection<AppvShellCommand> appvShellCommands = new ArrayList<AppvShellCommand>();
							if (lnode.item(i).hasChildNodes()) {
								NodeList exnodes = lnode.item(i).getChildNodes();
								for (int j = 0; j < exnodes.getLength(); j++) { 

									if(exnodes.item(j).hasChildNodes()){ //Apppv.Shellcommands
										NodeList mjnodes = exnodes.item(j).getChildNodes();
										for (int m = 0; m < mjnodes.getLength(); m++) { 
											if (mjnodes.item(m).hasChildNodes()){//Apppv.Shellcommand
												NodeList lenodes = mjnodes.item(m).getChildNodes();
												AppvShellCommand appvShellCommand = new AppvShellCommand();
												System.out.print(mjnodes.item(m).getNodeName() + "\n");
												for (int l = 0; l < lenodes.getLength(); l++) { 
													if(lenodes.item(l).getNodeName().equalsIgnoreCase("Appv:Name")){
														appvShellCommand.setName(lenodes.item(l).getTextContent());
													}

													if(lenodes.item(l).getNodeName().equalsIgnoreCase("appv:ApplicationId")){
														appvShellCommand.setApplicationId(lenodes.item(l).getTextContent());
													}

													if(lenodes.item(l).getNodeName().equalsIgnoreCase("appv:CommandLine")){
														appvShellCommand.setCommandLine(lenodes.item(l).getTextContent());
													}
													
												}
												appvShellCommands.add(appvShellCommand);
											}
										}

									}
								}

							}
							
							appvFileTypeAssociation.setAppvShellCommands(appvShellCommands);
						}
						
					}
					
				}
			}
		}
		
	}
	
	private void parseAppvAppPath(NodeList subnode) {
		appvAppPaths = new ArrayList<AppvAppPath>();
		for (int y = 0; y < subnode.getLength(); y++) { 
			if (subnode.item(y).hasChildNodes()) { 
				NodeList lnode = subnode.item(y).getChildNodes();

				AppvAppPath appvAppPath = new AppvAppPath();
				for (int i = 0; i < lnode.getLength(); i++) {


					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:Name")){
						appvAppPath.setName(lnode.item(i).getTextContent());
					}

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:ApplicationPath")){
						appvAppPath.setApplicationPath(lnode.item(i).getTextContent());
					}

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:PATHEnvironmentVariablePrefix")){
						appvAppPath.setPATHEnvironmentVariablePrefix(lnode.item(i).getTextContent());
					}

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:ApplicationId")){
						appvAppPath.setApplicationId(lnode.item(i).getTextContent());
					}				


				}
				appvAppPaths.add(appvAppPath);
				System.out.print(appvAppPath.getApplicationPath());
			}
		}	

	}


		private void parseAppvShortcuts(NodeList subnode){
		    
		    appvShortcuts = new ArrayList<AppvShortCut>();
		   for (int i = 0; i < subnode.getLength(); i++) { 
		   
			   	if (subnode.item(i).hasChildNodes()) {    
					   AppvShortCut appvShortcut = new AppvShortCut();
					    NodeList lnode = subnode.item(i).getChildNodes();
					   for (int y = 0; y < lnode.getLength(); y++) { 
		                                 
						   
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:File")){
		                       appvShortcut.setFile(lnode.item(y).getTextContent());
		                   }                   
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Target")){
		                       appvShortcut.setTarget(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Icon")){
		                       appvShortcut.setIcon(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Arguments")){
		                       appvShortcut.setArguments(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:WorkingDirectory")){
		                       appvShortcut.setWorkingDirectory(lnode.item(y).getTextContent());
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:ShowCommand")){
		                       appvShortcut.setShowCommand(Boolean.valueOf(lnode.item(y).getTextContent()));
		                   }
		                 
		                  //System.out.print("Gs "  + lnode.item(y).getNodeName() + ":" + lnode.item(y).getTextContent() + "\n");                   
		                   
		               }   
					   appvShortcuts.add(appvShortcut);
					
			   	}
		   }
		   
		}

}
