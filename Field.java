import java.util.*;

/**
 * Write a description of class InstanceVariable here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Field
{
    public static final HashMap<String, String> DEFAULT_VALUES = new HashMap<>();
    
    private String name;
    
    private String dataType;
    
    private String abbreviation;
    
    private boolean isSwing;

    private String defaultValue;
    
    private String value;
    
    private String methodName;
    
    private String minValue, maxValue;
    
    public Field(String n, String dt) {
        this(n,dt,false,"");
        
        this.calculateDefault();
        
    }
    
    
    public Field(String n, String dt, boolean s) {
        this(n,dt,s,"");
        
        this.calculateDefault();
        
    }
    
    public Field(String n, String dt, boolean s, String dv) {
        this.name = n;
        this.dataType = dt;
        this.isSwing = s;
        this.abbreviation = "n/a";
        this.methodName = "Name";
        this.defaultValue = dv;
        this.value = "";
        this.minValue = "";
        this.maxValue = "";
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getType() {
        return this.dataType;
    }
    
    public String getDefault() {
        return this.defaultValue;
    }
    
    public boolean isSwing() {
        return this.isSwing;
    }
    
    public String getAbbreviation() {
        return this.abbreviation;
    }
    
    public String getMethodName() {
        return this.methodName;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getMin() {
        return this.minValue;
    }
    
    public String getMax() {
        return this.maxValue;
    }
    
    public void setMinMax(String min, String max) {
        this.minValue = min;
        this.maxValue = max;
    }
    
    public void setAbbreviation(String a) {
        this.abbreviation = a;
    }
    
    public void setMethodName(String n) {
        this.methodName = n;
    }
    
    public Field setValue(String v) {
        this.value = v;
        return this;
    }
    
    public Field setValue(String v, boolean isObject) {
        if (isObject) {
            this.value = "new "+v;
            // if (v.charAt(v.length() - 1) != ')') {
                // this.value += "()";    
            // }
        }
        else {
            this.value = v;
        }
        return this;
    }
    
    private void calculateDefault() {
        if (DEFAULT_VALUES.containsKey(this.dataType)) {
            this.defaultValue = DEFAULT_VALUES.get(this.dataType);
        }
        else {
            if (this.dataType.charAt(this.dataType.length() - 1) == ']') {
                if (this.dataType.charAt(this.dataType.length() - 2) != '[') {
                    this.defaultValue = "new "+this.dataType;
                    String newDataType = "";
                    boolean erase = false;
                    for (int i = 0; i < this.dataType.length(); i++) {
                        if (this.dataType.charAt(i) == ']') {
                            erase = false;
                        }
                        if (!erase) {
                            newDataType += this.dataType.charAt(i);
                        }
                        if (this.dataType.charAt(i) == '[') {
                            erase = true;
                        }
                    }
                    
                    this.dataType = newDataType;
                }
                else {
                    this.defaultValue = String.format("new %s", this.dataType.replace("[]","[0]"));
                }
                
                
            }
            else {
                this.defaultValue = String.format("new %s()", this.dataType);
            }
            
        }
    }
    
    public static HashMap<String, String> generateDefaultValues(String defaultNum, String defaultString) {
        DEFAULT_VALUES.clear();
        DEFAULT_VALUES.put("byte",defaultNum);
        DEFAULT_VALUES.put("short",defaultNum);
        DEFAULT_VALUES.put("int",defaultNum);
        DEFAULT_VALUES.put("long",defaultNum);
        
        DEFAULT_VALUES.put("float",defaultNum);
        DEFAULT_VALUES.put("double",defaultNum);
        
        DEFAULT_VALUES.put("String","\"" + defaultString + "\"");
        
        DEFAULT_VALUES.put("boolean","false");
        DEFAULT_VALUES.put("char","'?'");
        
        return DEFAULT_VALUES;
    }
    
}