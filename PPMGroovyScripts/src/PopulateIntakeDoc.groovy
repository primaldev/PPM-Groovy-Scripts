import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import word.utils.Utils;
import word.w2004.elements.Table
import word.w2004.elements.tableElements.TableEle;
import word.utils.TestUtils;
//test values
String projectRoot = "D:/dev/projects"; //eventually. get from PPM env
String templateDir = "D:/dev/templates"; //eventually get from PPM

String projectName = "Application_Test-11.3-W7-x64"; //get from PPM env
String projectDiscription = "Application for doing work";
String projectIntakeDocName = "ppmIntake.doc";


/////////////////////////////////////////////////////
// Documents must be saved as word 2003 xml format //
/////////////////////////////////////////////////////

def projectDocumentFolder =  projectRoot + "/" + projectName + "/Documents";

//modify document

 xmlTemplate = Utils.readFile(projectDocumentFolder + "/" + projectIntakeDocName);
 
 
 
 xmlTemplate = replacePh(xmlTemplate, "ppmAppName", projectName);
 xmlTemplate = replacePh(xmlTemplate, "ppmAppManufacturer", projectDiscription);
 xmlTemplate = replacePh(xmlTemplate, "ppmAppVersion", projectIntakeDocName);
 
 
 Table tbl = new Table();
 tbl.addTableEle(TableEle.TH, "Jira Number", "Description");
 tbl.addTableEle(TableEle.TD, "J2W-1234", "Read Templates nicelly");
 xmlTemplate = replacePh(xmlTemplate, "ppmChangeLog", tbl.getContent());
 
//Save Document

 File tempFile = File.createTempFile("ppm", ".doc");
 tempFile << xmlTemplate;
 
 Files.copy(tempFile.toPath(), new File(projectDocumentFolder + "/" + projectIntakeDocName).toPath(), StandardCopyOption.REPLACE_EXISTING );
 tempFile.delete();

 
////////////////Subs///////////////

 private String replacePh(String base, String placeHolder, String value) {
     if(!base.contains(placeHolder)) {
         //don't want to use log now because I want to keep it simple...
         System.out.println("### WARN: couldn't find the place holder: " + placeHolder);
         return base;
     }
     return base.replace(placeHolder, value);
 }
 
 