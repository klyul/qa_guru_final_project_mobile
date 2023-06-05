package configs;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:configs/${device}.properties",
        "classpath:configs/defaultDevice.properties",
})
public interface DeviceAndAppConfig extends Config {

    @Key("appiumServerUrl")
    @DefaultValue("http://localhost:4723/wd/hub")
    String getAppiumServerUrl();

    @Key("deviceName")
    @DefaultValue("Pixel 4 API 30")
    String getDeviceName();

    @Key("platformVersion")
    @DefaultValue("11.0")
    String getPlatformVersion();

    @Key("appPackage")
    @DefaultValue("ru.tutu.tutu_emp")
    String getAppPackage();

    @Key("appActivity")
    @DefaultValue("ru.tutu.tutu_emp.presentation.main.PttActivity")
    String getAppActivity();


}