package com.qatestlab.properties;

public enum PropertiesNames {
    BROWSER("browser"),
    HUB("hub"),
    PLATFORM("platform"),
    DRIVERS_DIR("drivers.dir"),
    CONFIG_DIR("config.dir"),
    BASE_URL("base.url"),
    LOGIN("login"),
    PASSWORD("password");

    private String value;

    private PropertiesNames(String value) {
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }

}
