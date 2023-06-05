package configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:configs/browserstack.properties",
})
public interface BrowserstackConfig extends Config {

    @Key("browserstackUser")
    String getBSUser();

    @Key("browserstackKey")
    String getBSKey();

    @Key("browserstackApp")
    String getBSApp();

    @Key("browserstackProject")
    @DefaultValue("TuTu Demo Tests Project")
    String getBSProject();

    @Key("browserstackBuildPrefix")
    @DefaultValue("browserstack-build")
    String getBSBuildPrefix();

    @Key("browserstackName")
    @DefaultValue("tutu_demo_test")
    String getBSName();


}
