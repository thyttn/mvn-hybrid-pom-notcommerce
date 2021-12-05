package environmentConfig;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"classpath:${env}.properties"})
public interface Environment extends Config{
	
	@Key("app.url")
	String getUrlApp();
	
	@Key("app.username")
	String getUsernameApp();
	
	@Key("app.password")
	String getPasswordApp();
}
