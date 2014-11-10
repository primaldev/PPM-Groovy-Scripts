import java.util.zip.ZipEntry
import java.util.zip.ZipFile

import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document;

import org.primaldev.appv.AppvShortCut
import org.primaldev.appv.ParseAppvManifest
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Document
import org.w3c.dom.Element

import word.utils.Utils;
import word.w2004.elements.Table
import word.w2004.elements.tableElements.TableEle;
import word.utils.TestUtils;

import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

//test values//

String projectFolder = "D:/dev/projects/Application_Test-11.3-W7-x64"; //get from PPM env
String templateDir = "D:/dev/templates"; //eventually get from PPM

String appvFolder = "D:/dev/projects/Application_Test-11.3-W7-x64/Deploy/APPV/1.0";
appvPackage = appvFolder + "/Photoshop-CS6-01.appv";

String projectName = "Application_Test-11.3-W7-x64";
String templateAppvDocName = "ppmAppvDoc.doc";

String projectDocumentFolder = projectFolder + "/Documents";

//////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////

 if (new File(appvPackage).exists() ){
     extractAppvInfo(appvPackage);
 } else {
     print "File not found";
 }
 
 
 /////////////////////////////////////////////////////
 // Documents must be saved as word 2003 xml format //
 /////////////////////////////////////////////////////
 
 
 //read template
  
  String xmlTemplate;
  
BufferedReader br = new BufferedReader(new FileReader(projectFolder + "/" + templateAppvDocName));
try {
	StringBuilder sb = new StringBuilder();
	String line = br.readLine();

	while (line != null) {
		sb.append(line);
		sb.append(System.lineSeparator());
		line = br.readLine();
	}
	xmlTemplate = sb.toString();
} finally {
	br.close();
}


// Replace template values
  
xmlTemplate = replacePh(xmlTemplate, "ppmAppName", projectName);
xmlTemplate = replacePh(xmlTemplate, "ppmAppvMountRoot", InnerValues.appvMountRoot);

xmlTemplate = replacePh(xmlTemplate, "ppmAppvCOMMode", InnerValues.getAppvManifest().appvCOMMode);

  
  //Save Document

def docpath;

if (new File(projectDocumentFolder + "/" +  projectName + "-Appv_Package.doc").exists()){
	docpath = projectDocumentFolder + "/" +  projectName + "-Appv_Package-New-Generated.doc"
} else {
	docpath = projectDocumentFolder + "/" +  projectName + "-Appv_Package.doc"

}

  
   File tempFile = File.createTempFile("ppm", ".doc");
   tempFile << xmlTemplate;
   
   Files.copy(tempFile.toPath(), new File(docpath).toPath(), StandardCopyOption.REPLACE_EXISTING );
   tempFile.delete();
  
  

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
            //parseAppxManifest(zipFile.getInputStream(entry));
			ParseAppvManifest parseAppvManifest = new ParseAppvManifest(zipFile.getInputStream(entry));
			InnerValues.setAppvManifest(parseAppvManifest);
        }
        
        //InputStream stream = zipFile.getInputStream(entry);
    }        

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
    
    static ParseAppvManifest appvManifest;
    
    public static void setAppvMountRoot(String appvMountRoot) {
       this.appvMountRoot = appvMountRoot;
    }
    
    public static String getAppvMountRoot() {
        return appvMountRoot;
    }
    
  
    public static void setAppvManifest(ParseAppvManifest appvManifest) {
       this.appvManifest = appvManifest;
    }
	
	public static ParseAppvManifest getAppvManifest() {
		return appvManifest;
	}
    
}
 
 private String replacePh(String base, String placeHolder, String value) {
	 if(!base.contains(placeHolder)) {
		 //don't want to use log now because I want to keep it simple...
		 System.out.println("### WARN: couldn't find the place holder: " + placeHolder);
		 return base;
	 }
	 return base.replace(placeHolder, value);
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