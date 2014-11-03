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
	
	private String appvPublisher;
	private String Name;
	private String packageVersion;
	private String appvPackageId;
	private String appvVersionId;
	private String displayName;
	private String publisherDisplayName;
	private String description;
	private String appVPackageDescription;
	private Boolean FullVFSWriteMode;
	private String  language;
	private String oSMinVersion;
	private String oSMaxVersionTested;
	private String sequencingStationProcessorArchitecture;
	
	private InputStream xmlInput;
	private Collection<AppvShortCut> appvShortcuts;
	private Collection<AppvAppPath> appvAppPaths;
	private Collection<AppvFileTypeAssociation> appvFileTypeAssociations;
	
	String appvCOMMode;
	boolean ComModeOutOfProcessEnabled;
	boolean ComModeInProcessEnabled;
	
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
	
	
	
	
	public String getAppvPublisher() {
		return appvPublisher;
	}
	public void setAppvPublisher(String appvPublisher) {
		this.appvPublisher = appvPublisher;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPackageVersion() {
		return packageVersion;
	}
	public void setPackageVersion(String packageVersion) {
		this.packageVersion = packageVersion;
	}
	public String getAppvPackageId() {
		return appvPackageId;
	}
	public void setAppvPackageId(String appvPackageId) {
		this.appvPackageId = appvPackageId;
	}
	public String getAppvVersionId() {
		return appvVersionId;
	}
	public void setAppvVersionId(String appvVersionId) {
		this.appvVersionId = appvVersionId;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getPublisherDisplayName() {
		return publisherDisplayName;
	}
	public void setPublisherDisplayName(String publisherDisplayName) {
		this.publisherDisplayName = publisherDisplayName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppVPackageDescription() {
		return appVPackageDescription;
	}
	public void setAppVPackageDescription(String appVPackageDescription) {
		this.appVPackageDescription = appVPackageDescription;
	}
	public Boolean getFullVFSWriteMode() {
		return FullVFSWriteMode;
	}
	public void setFullVFSWriteMode(Boolean fullVFSWriteMode) {
		FullVFSWriteMode = fullVFSWriteMode;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getoSMinVersion() {
		return oSMinVersion;
	}
	public void setoSMinVersion(String oSMinVersion) {
		this.oSMinVersion = oSMinVersion;
	}
	public String getoSMaxVersionTested() {
		return oSMaxVersionTested;
	}
	public void setoSMaxVersionTested(String oSMaxVersionTested) {
		this.oSMaxVersionTested = oSMaxVersionTested;
	}
	public String getSequencingStationProcessorArchitecture() {
		return sequencingStationProcessorArchitecture;
	}
	public void setSequencingStationProcessorArchitecture(
			String sequencingStationProcessorArchitecture) {
		this.sequencingStationProcessorArchitecture = sequencingStationProcessorArchitecture;
	}
	public Collection<AppvAppPath> getAppvAppPaths() {
		return appvAppPaths;
	}
	public void setAppvAppPaths(Collection<AppvAppPath> appvAppPaths) {
		this.appvAppPaths = appvAppPaths;
	}
	public Collection<AppvFileTypeAssociation> getAppvFileTypeAssociations() {
		return appvFileTypeAssociations;
	}
	public void setAppvFileTypeAssociations(
			Collection<AppvFileTypeAssociation> appvFileTypeAssociations) {
		this.appvFileTypeAssociations = appvFileTypeAssociations;
	}

	public void setAppvShortcuts(Collection<AppvShortCut> appvShortcuts) {
		this.appvShortcuts = appvShortcuts;
	}
	public String getAppvCOMMode() {
		return appvCOMMode;
	}

	public void setAppvCOMMode(String appvCOMMode) {
		this.appvCOMMode = appvCOMMode;
	}
	public boolean isComModeOutOfProcessEnabled() {
		return ComModeOutOfProcessEnabled;
	}

	public void setComModeOutOfProcessEnabled(boolean comModeOutOfProcessEnabled) {
		ComModeOutOfProcessEnabled = comModeOutOfProcessEnabled;
	}
	public boolean isComModeInProcessEnabled() {
		return ComModeInProcessEnabled;
	}

	public void setComModeInProcessEnabled(boolean comModeInProcessEnabled) {
		ComModeInProcessEnabled = comModeInProcessEnabled;
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
                    	if (childNodes.item(y).getNodeName().equalsIgnoreCase("Identity")) {
                    		parseIdentity(childNodes.item(y));                    	
                    	}
	            	
	                    if (childNodes.item(y).getNodeName().equalsIgnoreCase("appv:Extensions")) {
	                        parseAppvExtensions(childNodes.item(y));
	                    }
	                    if (childNodes.item(y).getNodeName().equalsIgnoreCase("appv:ExtensionsConfiguration")) {
	                    	parseAppvExtensionsConfiguration(childNodes.item(y).getChildNodes().item(1));
	                    }
	                                     
	                }            
	        }
	        
	    }
		 
	}

	 
	private void parseIdentity(Node node){
		
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
	
	private String getAttribByName(Node node, String attribName) {
		
		if (node.hasAttributes()){
			Element eElement = (Element) node;
			return eElement.getAttribute(attribName);
		}
		return null;
		
		
	}
	
	private void parseAppvExtensionsConfiguration(Node node){
		
		this.setAppvCOMMode(getAttribByName(node, "Mode"));
		System.out.print(node.getNodeName());
		System.out.print(node.getChildNodes().item(1).getNodeName());
		this.setComModeInProcessEnabled(Boolean.valueOf(getAttribByName(node.getChildNodes().item(1), "InProcessEnabled" )));
		this.setComModeOutOfProcessEnabled(Boolean.valueOf(getAttribByName(node.getChildNodes().item(1), "OutOfProcessEnabled" )));
		

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
				appvFileTypeAssociations.add(appvFileTypeAssociation);
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
