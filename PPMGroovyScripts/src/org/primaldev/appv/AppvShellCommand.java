package org.primaldev.appv;

public class AppvShellCommand {
  private String name;
  private String applicationId;
  private String commandLine;
  private String friendlyName;
  
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getApplicationId() {
	return applicationId;
}
public void setApplicationId(String applicationId) {
	this.applicationId = applicationId;
}
public String getCommandLine() {
	return commandLine;
}
public void setCommandLine(String commandLine) {
	this.commandLine = commandLine;
}
  
public String getFriendlyName() {
	return friendlyName;
}

public void setFriendlyName(String friendlyName) {
	this.friendlyName = friendlyName;
}
  
}
