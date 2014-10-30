package org.primaldev.appv;

public class AppvAppPath {
	static String Name;
	static String ApplicationPath;
	static String PATHEnvironmentVariablePrefix;
	static String ApplicationId;
	
	public static String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public  String getApplicationPath() {
		return ApplicationPath;
	}
	public  void setApplicationPath(String applicationPath) {
		ApplicationPath = applicationPath;
	}
	public String getPATHEnvironmentVariablePrefix() {
		return PATHEnvironmentVariablePrefix;
	}
	public  void setPATHEnvironmentVariablePrefix(
			String pATHEnvironmentVariablePrefix) {
		PATHEnvironmentVariablePrefix = pATHEnvironmentVariablePrefix;
	}
	public String getApplicationId() {
		return ApplicationId;
	}
	public  void setApplicationId(String applicationId) {
		ApplicationId = applicationId;
	}
	
	
	

}
