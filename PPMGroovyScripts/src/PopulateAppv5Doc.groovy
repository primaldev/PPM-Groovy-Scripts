import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document
import org.w3c.dom.Element

//test values//

String projectFolder = "D:/dev/projects/Application_Test-11.3-W7-x64"; //get from PPM env
String appvFolder = "D:/dev/projects/Application_Test-11.3-W7-x64/Deploy/APPV/1.0";
appvPackage = appvFolder + "/Photoshop-CS6-01.appv";
//////////////////////////////////////////////////////////////





//////////////////////////////////////////////////////////////

 if (new File(appvPackage).exists() ){
     extractAppvInfo(appvPackage);
 } else {
     print "File not found";
 }

///////////////////////////Functions/////////////////////////

private void extractAppvInfo(String appvPackage) {
    ZipFile zipFile = new ZipFile(appvPackage);
    
    Enumeration<? extends ZipEntry> entries = zipFile.entries();
    while(entries.hasMoreElements()){
        ZipEntry entry = entries.nextElement();
        //Get xml data:
        if (entry.name.equalsIgnoreCase("FilesystemMetadata.xml")){
            parseFileMetaData(zipFile.getInputStream(entry));            
        }
        if (entry.name.equalsIgnoreCase("AppxManifest.xml")){
            parseAppxManifest(zipFile.getInputStream(entry));
        }
        
        //InputStream stream = zipFile.getInputStream(entry);
    }
    
    

}

private void parseAppxManifest(InputStream stream){
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(stream);
     
    Element element = doc.getDocumentElement();
    stream.close();
    
    NodeList nList = doc.getElementsByTagName("Package");
    
    for ( Node node : nList) {
        if(node.hasChildNodes()){
            NodeList childNodes = node.getChildNodes();
                for (Node cnode : childNodes) {    
                    if (cnode.getNodeName().equalsIgnoreCase("appv:Extensions")) {
                        parseAppvExtensions(cnode);
                    }
                    
                }            
        }
        
    }
    
    //printNote(nList);
    
    
}


private void parseAppvExtensions(Node node) {   
   NamedNodeMap atttrib = node.getAttributes();    
    if (node.hasChildNodes()) {
        for (Node gnode : node.getChildNodes()) {
            if (gnode.hasAttributes()){
                Element eElement = (Element) gnode;
                String attrib  = eElement.getAttribute("Category");
                //print attrib + "\n";
                if (attrib.equalsIgnoreCase("AppV.Shortcut")){
                    parseAppvShortcuts(eElement.childNodes);
                }
            }            
        }
   }
  
}


private void parseAppvShortcuts(NodeList subnode){
    //printNote(subnode);
    
    //using an class instead of writing directly to the document,
    //just in case another doc API is needed in the future
    Collection<AppvShortcut> appvShortcuts = new ArrayList<AppvShortcut>();
    
   for (Node snode: subnode){
	   	if (snode.hasChildNodes()) {    
			   AppvShortcut appvShortcut = new AppvShortcut();
               for(Node lnode : snode.getChildNodes()){                   
				   
                   if(lnode.getNodeName().equalsIgnoreCase("appv:File")){
                       appvShortcut.setFile(lnode.getTextContent());
                   }                   
                   if(lnode.getNodeName().equalsIgnoreCase("appv:Target")){
                       appvShortcut.setTarget(lnode.getTextContent());
                   }
                   if(lnode.getNodeName().equalsIgnoreCase("appv:Icon")){
                       appvShortcut.setIcon(lnode.getTextContent());
                   }
                   if(lnode.getNodeName().equalsIgnoreCase("appv:Arguments")){
                       appvShortcut.setArguments(lnode.getTextContent());
                   }
                   if(lnode.getNodeName().equalsIgnoreCase("appv:WorkingDirectory")){
                       appvShortcut.setWorkingDirectory(lnode.getTextContent());
                   }
                   if(lnode.getNodeName().equalsIgnoreCase("appv:ShowCommand")){
                       appvShortcut.setShowCommand(Boolean.valueOf(lnode.getTextContent()));
                   }
                 
                  // print "Gs "  + lnode.getNodeName() + ":" + lnode.getTextContent() + "\n";                   
                   
               }   
			   appvShortcuts.add(appvShortcut);
			   print appvShortcut.getFile() + "\n";
			   
	   	}
   }
   InnerValues.setAppvShorcuts(appvShortcuts);
   
    
}


private void parseFileMetaData(InputStream stream) {
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();     
    Document doc = dBuilder.parse(stream);     
    Element element = doc.getDocumentElement();
    stream.close();
     
    NodeList nList = doc.getElementsByTagName("Filesystem");
                    
    for ( Node node : nList) {
        Element eElement = (Element) node;
        InnerValues.setAppvMountRoot(eElement.getAttribute("Root"));        
    }
    
}

//Global class to collect values

 class InnerValues {
    static String appvMountRoot
    static Collection<AppvShortcut> appvShorcuts;
    
    
    public static void setAppvMountRoot(String appvMountRoot) {
       this.appvMountRoot = appvMountRoot;
    }
    
    public static String getAppvMountRoot() {
        return appvMountRoot;
    }
    
    public static Collection<AppvShortcut> getAppvShorcuts() {
        return appvShorcuts;
    }
    public static void setAppvShorcuts(Collection<AppvShortcut> appvShorcuts) {
       this.appvShorcuts = appvShorcuts;
    }
    
}
 
 class AppvShortcut {
    static String File;  //the actual lnk file
    static String Target;
    static String Icon;
    static String Arguments;
    static String WorkingDirectory;
    static boolean ShowCommand;
    
    public static void setFile(String file) {
        File = file;
    }
    
    public static void setArguments(String arguments) {
        Arguments = arguments;
    }
    public static void setIcon(String icon) {
        Icon = icon;
    }
    public static void setShowCommand(boolean showCommand) {
        ShowCommand = showCommand;
    }
    public static void setTarget(String target) {
        Target = target;
    }
    public static void setWorkingDirectory(String workingDirectory) {
        WorkingDirectory = workingDirectory;
    }
        
    public static String getWorkingDirectory() {
        return WorkingDirectory;
    } 
    
    public static String getFile() {
        return File;
    }
    public static String getArguments() {
        return Arguments;
    }
    public static String getIcon() {
        return Icon;
    }
    public static String getTarget() {
        return Target;
    }
    public static boolean isShowCommand() {
        return ShowCommand;
    }
    
    
      
 }

 //For testing
private static void printNote(NodeList nodeList) {
    
       for (int count = 0; count < nodeList.getLength(); count++) {
    
       Node tempNode = nodeList.item(count);
    
       // make sure it's element node.
       if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
    
           // get node name and value
           System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
           System.out.println("Node Value =" + tempNode.getTextContent());
    
           if (tempNode.hasAttributes()) {
    
               // get attributes names and values
               NamedNodeMap nodeMap = tempNode.getAttributes();
    
               for (int i = 0; i < nodeMap.getLength(); i++) {
    
                   Node node = nodeMap.item(i);
                   System.out.println("attr name : " + node.getNodeName());
                   System.out.println("attr value : " + node.getNodeValue());
    
               }
    
           }
    
           if (tempNode.hasChildNodes()) {
    
               // loop again if has child nodes
               printNote(tempNode.getChildNodes());
    
           }
    
           System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
    
       }
    
       }
    
     }