package AndroidMobile;

import java.io.File;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class AppiumServer {
	AppiumDriverLocalService service;
	AppiumServiceBuilder builder;
	
	public void start(String params[]) {
		try {
			builder = new AppiumServiceBuilder();
			builder
			.usingDriverExecutable(new File(params[0]))
			.withAppiumJS(new File(params[1]))
			.withIPAddress(params[2]);
			if(params[3].equalsIgnoreCase("Default") || params[3].equalsIgnoreCase("Others")) {
				builder.usingPort(Integer.parseInt(params[4]));
			}
			else {
				builder.usingAnyFreePort();
			}
			//Start the server with the builder
			service = AppiumDriverLocalService.buildService(builder);
			service.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getURL() {
		String url="No URL";
		if(service.isRunning()) {
		url=service.getUrl().toString();
		}
		return url;
	}
	
	public boolean isRunning() {
		return service.isRunning();
	}
	
	public void stop() {
		service.stop();
	}
}
