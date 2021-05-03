package com.ixigo.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties pro;
	public ConfigDataProvider() throws Exception {
		
		File src = new File("./Config/Config.properties");
		
		try {
			FileInputStream fis= new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		}catch(Exception e) {
		throw new Exception("Propties file not found or loaded"+ e.getMessage());
		}
		
	}
	
	public String getBrowser() {
		return pro.getProperty("Browser");
	}
	
	public String getUrl() {
		return pro.getProperty("DevUrl");
	}

}
