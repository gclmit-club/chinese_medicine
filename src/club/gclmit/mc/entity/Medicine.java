package club.gclmit.mc.entity;

/**
 *  药材实体类
 */
public class Medicine {

    /**
     *  药材 id
     */
    private long id;

    /**
     *  名字
     */
    private String name;

    /**
     *  五味
     */
    private String taste;

    /**
     *  药性
     */
    private String properties;

    /**
     *  颜色
     */
    private String color;

    /**
     *  临床药用
     */
    private String clinical;

    public Medicine() {
    }

    public Medicine(String name, String taste, String properties, String color, String clinical) {
        this.name = name;
        this.taste = taste;
        this.properties = properties;
        this.color = color;
        this.clinical = clinical;
    }

    public Medicine(long id, String name, String taste, String properties, String color, String clinical) {
        this.id = id;
        this.name = name;
        this.taste = taste;
        this.properties = properties;
        this.color = color;
        this.clinical = clinical;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getClinical() {
        return clinical;
    }

    public void setClinical(String clinical) {
        this.clinical = clinical;
    }

    @Override
    public String toString() {
        String medicinesToString = "%s,%s,%s,%s,%s,%s\n";
        return String.format(medicinesToString,id,name,taste,properties,color,clinical);
    }
}
