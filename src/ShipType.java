import java.util.EnumSet;
import java.util.Map;
import java.util.stream.Collectors;

public enum ShipType {
    P("P"), Q("Q");
    private String type;
    private static Map<String, ShipType> shipTypeMap = EnumSet.allOf(ShipType.class).stream().collect(Collectors.toMap(x -> x.getType(), x -> x));

    ShipType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static ShipType getShipType(String type){
        return shipTypeMap.get(type);
    }
}
