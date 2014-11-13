package org.primaldev.appv;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
	private ArrayList<AppvShortCut> appvShortcuts = new ArrayList<AppvShortCut>();
	private Collection<AppvAppPath> appvAppPaths = new ArrayList<AppvAppPath>();
	private Collection<AppvFileTypeAssociation> appvFileTypeAssociations = new ArrayList<AppvFileTypeAssociation>();
	
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
		
		
		//testLoop();

	}
	
	
	
	private void testLoop(){
		for( AppvFileTypeAssociation fta:appvFileTypeAssociations) {
			if (fta.isShellCommds()) {
				for(AppvShellCommand ftacmd: fta.getAppvShellCommands()){
					System.out.print("FTyay " + fta.getName() + " : " + ftacmd.getName() + " : " + ftacmd.getCommandLine() + " : " + ftacmd.getApplicationId() + "\n");
				}
			}
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

	public void setAppvShortcuts(ArrayList<AppvShortCut> appvShortcuts) {
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

	public ArrayList<AppvShortCut> getAppvShortcuts() {
		return appvShortcuts;
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

	    			if (childNodes.item(y).getNodeName().equalsIgnoreCase("Properties")) {
	    				parseProperties(childNodes.item(y));                    	
	    			}

	    			if (childNodes.item(y).getNodeName().equalsIgnoreCase("Resources")) {
	    				parseResources(childNodes.item(y).getChildNodes().item(1));                    	
	    			}                    	

	    			if (childNodes.item(y).getNodeName().equalsIgnoreCase("Prerequisites")) {
	    				parsePrerequisites(childNodes.item(y));                    	
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
		this.setAppvVersionId(getAttribByName(node, "appv:VersionId"));
		this.setAppvPackageId(getAttribByName(node, "appv:PackageId"));
		this.setPackageVersion(getAttribByName(node, "Version"));
		this.setAppvPublisher(getAttribByName(node, "Publisher"));
		this.setName(getAttribByName(node, "Name"));
		
		//System.out.print("Packageid: " + this.getAppvPackageId() + "\n");
		
	}
	
	private void parseProperties(Node node) {
		 if (node.hasChildNodes()) {
			 NodeList childNodes = node.getChildNodes();
			 for (int i = 0; i < childNodes.getLength(); i++) {
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("DisplayName")){
					 this.setDisplayName(childNodes.item(i).getTextContent());
				 }
				 
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("PublisherDisplayName")){
					 this.setPublisherDisplayName(childNodes.item(i).getTextContent());
				 }
				 
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("Description")){
					 this.setDescription(childNodes.item(i).getTextContent());
				 }
				 
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("appv:AppVPackageDescription")){
					 this.setAppVPackageDescription(childNodes.item(i).getTextContent());
				 }	
				 
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("appv1.2:FullVFSWriteMode")){
					 this.setFullVFSWriteMode(Boolean.valueOf(childNodes.item(i).getTextContent()));
				 }				 

			 }
		 }
		 //System.out.print("Display Name: " + this.getDisplayName() + "\n");
	}
	
	
	private void parseResources(Node node){
		this.setLanguage(getAttribByName(node, "Language"));
		//System.out.print("Language:" + this.getLanguage() + "\n");
		
	}
	
	private void parsePrerequisites(Node node) {
		 if (node.hasChildNodes()) {
			 NodeList childNodes = node.getChildNodes();
			 for (int i = 0; i < childNodes.getLength(); i++) {
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("OSMinVersion")){
					 this.setoSMinVersion(childNodes.item(i).getTextContent());
				 }
				 
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("OSMaxVersionTested")){
					 this.setoSMaxVersionTested(childNodes.item(i).getTextContent());
				 }
				 if (childNodes.item(i).getNodeName().equalsIgnoreCase("appv:TargetOSes")){
					 this.setSequencingStationProcessorArchitecture(getAttribByName(childNodes.item(i),"SequencingStationProcessorArchitecture"));
				 }
			
			 }
		 }
		 
		 //System.out.print("OSMaxVersion:" + this.getoSMaxVersionTested() + "\n");
		 
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
		//System.out.print(node.getNodeName());
		//System.out.print(node.getChildNodes().item(1).getNodeName());
		this.setComModeInProcessEnabled(Boolean.valueOf(getAttribByName(node.getChildNodes().item(1), "InProcessEnabled" )));
		this.setComModeOutOfProcessEnabled(Boolean.valueOf(getAttribByName(node.getChildNodes().item(1), "OutOfProcessEnabled" )));
		

	}
	
	private void parseAppvFileTypeAssociation(NodeList subnode){
		
		
		for (int y = 0; y < subnode.getLength(); y++) { 
			if (subnode.item(y).hasChildNodes()) { 
				NodeList lnode = subnode.item(y).getChildNodes();
				AppvFileTypeAssociation appvFileTypeAssociation = new AppvFileTypeAssociation();
				//Some Fta's do not have extensions so we protect against null values
				appvFileTypeAssociation.setName("");
				appvFileTypeAssociation.setProgId("");
				boolean noExt=true;
				for (int i = 0; i < lnode.getLength(); i++) {
					
					if(lnode.item(i).hasChildNodes()){
						NodeList enodes = lnode.item(i).getChildNodes();
						
						//FileExtension
						if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:FileExtension")){	
							noExt=false;
							for (int x = 0; x < enodes.getLength(); x++) {
								if (enodes.item(x).hasChildNodes()){
									if(enodes.item(x).hasAttributes()){
										Element eElement = (Element) enodes.item(x);
										appvFileTypeAssociation.setMimeAssociation(Boolean.valueOf(eElement.getAttribute("MimeAssociation")));
									}

									if(enodes.item(x).getNodeName().equalsIgnoreCase("appv:Name")){
										appvFileTypeAssociation.setName(notNull(enodes.item(x).getTextContent()));
										
									}

									if(enodes.item(x).getNodeName().equalsIgnoreCase("appv:ProgId")){
										appvFileTypeAssociation.setProgId(notNull(enodes.item(x).getTextContent()));
									}

									
								}
							}
							
							
						}
						if(noExt){
							appvFileTypeAssociation.setName("ProgId");
							
						}
						
						//Progid ()
						if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:ProgId")){
							Collection<AppvShellCommand> appvShellCommands = null;
							boolean hasShell = false;
							if (lnode.item(i).hasChildNodes()) {
								NodeList exnodes = lnode.item(i).getChildNodes();
								for (int j = 0; j < exnodes.getLength(); j++) { 
									System.out.print("ProgIDName:" + exnodes.item(j) + "\n");
									if (exnodes.item(j).getNodeName().equalsIgnoreCase("appv:Name")){
										appvFileTypeAssociation.setProgIdName(exnodes.item(j).getTextContent());										
									}
									if (exnodes.item(j).getNodeName().equalsIgnoreCase("appv:Description")){
										appvFileTypeAssociation.setProgIdDescription(exnodes.item(j).getTextContent());										
									}
									
									
									if(exnodes.item(j).hasChildNodes()){ //Apppv.Shellcommands
										appvShellCommands = new ArrayList<AppvShellCommand>();
										
										NodeList mjnodes = exnodes.item(j).getChildNodes();
										for (int m = 0; m < mjnodes.getLength(); m++) { 
											
											if (mjnodes.item(m).hasChildNodes()){//Apppv.Shellcommand
												NodeList lenodes = mjnodes.item(m).getChildNodes();
												AppvShellCommand appvShellCommand = new AppvShellCommand();
												
												for (int l = 0; l < lenodes.getLength(); l++) { 
													if(lenodes.item(l).getNodeName().equalsIgnoreCase("appv:Name")){
														appvShellCommand.setName(notNull(lenodes.item(l).getTextContent()));
														hasShell = true;
													}

													if(lenodes.item(l).getNodeName().equalsIgnoreCase("appv:ApplicationId")){
														appvShellCommand.setApplicationId(notNull(lenodes.item(l).getTextContent()));
													}

													if(lenodes.item(l).getNodeName().equalsIgnoreCase("appv:CommandLine")){
														appvShellCommand.setCommandLine(notNull(lenodes.item(l).getTextContent()));
													}
													
													if(lenodes.item(l).getNodeName().equalsIgnoreCase("appv:FriendlyName")){
														appvShellCommand.setFriendlyName(notNull(lenodes.item(l).getTextContent()));
													}
													
												}
												if (hasShell) {
													appvShellCommands.add(appvShellCommand);
																										
												}
											}
										}

									}
								}

							}
							
							if (hasShell) {
								appvFileTypeAssociation.setAppvShellCommands(appvShellCommands);
								appvFileTypeAssociation.setShellCommds(true);
							} else{
								appvFileTypeAssociation.setShellCommds(false);
							}
							//System.out.print("FTA:" + appvFileTypeAssociation.getProgId() + "Name: " + appvFileTypeAssociation.getName() + "\n" );
						}
						
					}
					
				}
				appvFileTypeAssociations.add(appvFileTypeAssociation);
			}
			
		}
		
	}
	
	private String notNull(String string){
		if (string !=null){
			return string;
			
		}else{
			return "not defined";
		}
	}
	

	
	private void parseAppvAppPath(NodeList subnode) {
	
		//for (int y = 0; y < subnode.getLength(); y++) { 
			if (subnode.item(1).hasChildNodes()) { 
				NodeList lnode = subnode.item(1).getChildNodes();

				AppvAppPath appvAppPath = new AppvAppPath();
				for (int i = 0; i < lnode.getLength(); i++) {

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:Name")){
						appvAppPath.setName(notNull(lnode.item(i).getTextContent()));
					}

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:ApplicationPath")){
						appvAppPath.setApplicationPath(notNull(lnode.item(i).getTextContent()));
					}

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:PATHEnvironmentVariablePrefix")){
						appvAppPath.setPATHEnvironmentVariablePrefix(notNull(lnode.item(i).getTextContent()));
					}

					if(lnode.item(i).getNodeName().equalsIgnoreCase("appv:ApplicationId")){
						appvAppPath.setApplicationId(notNull(lnode.item(i).getTextContent()));
					}			

				}
				appvAppPaths.add(appvAppPath);
				
			}
		//}	

	}


		private void parseAppvShortcuts(NodeList subnode){
			   
			   	if (subnode.item(1).hasChildNodes()) {    
			   		
					    NodeList lnode = subnode.item(1).getChildNodes();
					    AppvShortCut appvShortcut = new AppvShortCut();
					   for (int y = 0; y < lnode.getLength(); y++) { 
						   
						   
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:File")){
		                       appvShortcut.setFile(notNull(lnode.item(y).getTextContent()));
		                   }                   
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Target")){
		                       appvShortcut.setTarget(notNull(lnode.item(y).getTextContent()));
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Icon")){
		                       appvShortcut.setIcon(notNull(lnode.item(y).getTextContent()));
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:Arguments")){
		                       appvShortcut.setArguments(notNull(lnode.item(y).getTextContent()));
		                   }
		                   if(lnode.item(y).getNodeName().equalsIgnoreCase("appv:WorkingDirectory")){
		                       appvShortcut.setWorkingDirectory(notNull(lnode.item(y).getTextContent()));
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
