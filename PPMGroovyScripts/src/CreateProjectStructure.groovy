

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
//We gonna pick em one by one

//File src = new File(templateDir); 
//File dest = new File(projectRoot + "/" + projectName + "/Documents");

//try {
//	copyFolder(src, dest);
//} catch (Exception e) {
//	print "Templates not copied";
//	e.printStackTrace();
//}

//////////////////////Classes//////////////////////////


private void copyFolder(File src, File dest)
throws IOException{
	
	

if(src.isDirectory()){

	//if directory not exists, create it
	if(!dest.exists()){
	   dest.mkdir();
	   System.out.println("Directory copied from "
					  + src + "  to " + dest);
	}

	//list all the directory contents
	def files = src.list();

	for (String file : files) {
	   //construct the src and dest file structure
	   File srcFile = new File(src, file);
	   File destFile = new File(dest, file);
	   //recursive copy
	   copyFolder(srcFile,destFile);
	}
	
} else {
    		// file, then copy it
    		//Use bytes stream to support all file types
    		InputStream ina = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
 
    	        byte[] buffer = new byte[1024];
 
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = ina.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        ina.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
	

}