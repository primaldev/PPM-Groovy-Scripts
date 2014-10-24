import java.util.zip.ZipEntry
import java.util.zip.ZipFile

//test values

String projectFolder = "D:/dev/projects/Application_Test-11.3-W7-x64"; //get from PPM env
String appvFolder = "D:/dev/projects/Application_Test-11.3-W7-x64/Deploy/APPV/1.0";
appvPackage = appvFolder + "/Firefox-27.0.1.appv";
///////////

 if (new File(appvPackage).exists() ){
	 extractAppvInfo(appvPackage);
 } else {
 	print "File not found";
 }

///
///////////////////////////Functions/////////////////////////

private void extractAppvInfo(String appvPackage) {
	ZipFile zipFile = new ZipFile(appvPackage);
	Enumeration<? extends ZipEntry> entries = zipFile.entries();
	while(entries.hasMoreElements()){
		ZipEntry entry = entries.nextElement();
		//InputStream stream = zipFile.getInputStream(entry);
	}

}