package com.hashedin.entity;

import java.util.HashMap;
import java.util.Map;

public enum TaskStatus {

	CREATED("Created"),
	INPROGRESS("Inprogress"),
	RESOLVED("Resolved"),
	CLOSED("Closed");
	
	private String status;
	
	private static Map<String, TaskStatus> taskStatusMap;
	
	static {
		taskStatusMap = new HashMap<String, TaskStatus>();
		for(TaskStatus status : TaskStatus.values()){
			taskStatusMap.put(status.toString().toLowerCase(), status);
		}
	}
	
	private TaskStatus(String status){
		this.status = status;
	}
	
	public static TaskStatus get(String status){
		return taskStatusMap.get(status.toLowerCase());
	}
	
	@Override
	public String toString(){
		return this.status;
	}
	
}
