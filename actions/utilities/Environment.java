package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({ "file:resources/${env}.properties" })
public interface Environment extends Config {
	@Key("app.url")
	String getAppUrl();

	@Key("db.url")
	String getDbUrl();

	@Key("db.username")
	String getDbUsername();

	@Key("db.password")
	String getDbPassword();
}
