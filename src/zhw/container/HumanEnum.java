package zhw.container;

public enum HumanEnum {
    YellowMaleHuman("zhw.impl.factory.YellowMaleHuman"),
    YellowFemaleHuman("zhw.impl.factory.YellowFemaleHuman"),
    WhiteMaleHuman("zhw.impl.factory.WhiteMaleHuman"),
    WhiteFemaleHuman("zhw.impl.factory.WhiteFemaleHuman"),
    BlackMaleHuman("zhw.impl.factory.BlackMaleHuman"),
    BlackFemaleHuman("zhw.impl.factory.BlackFemaleHuman");

    private String value = "";

    private HumanEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
