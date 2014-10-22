import java.nio.file.Path

//test values
String projectRoot = "D:/dev/projects"; //eventually. get from PPM env
String templateDir = "D:/dev/templates"; //eventually get from PPM

String projectName = "Application_Test-11.3-W7-x64"; //get from PPM env

String projectDiscription = "Application for doing work";
////////////////////////////

//Main

//Create folderStructure


new File(projectRoot + "/" + projectName + "/Build/1.0").mkdirs();
new File(projectRoot + "/" + projectName + "/Deploy/APPV/1.0").mkdirs();
new File(projectRoot + "/" + projectName + "/Deploy/MSI/1.0").mkdirs();
new File(projectRoot + "/" + projectName + "/Deploy/SCRIPT/1.0").mkdirs();
new File(projectRoot + "/" + projectName + "/Documents").mkdirs();
new File(projectRoot + "/" + projectName + "/Media").mkdirs();

//Create Discription file

fileDiscription = new File(projectRoot + "/" + projectName + "/" + projectName + ".txt");

if (!fileDiscription.exists()){    
    fileDiscription << projectName + '\r\n' +  projectDiscription;    
}

//Copy Template Files

java.nio.file.Path source = templateDir;
java.nio.file.Path dest = projectRoot + "/" + projectName + "/Documents";
java.nio.file.Files.copy(source, dest);