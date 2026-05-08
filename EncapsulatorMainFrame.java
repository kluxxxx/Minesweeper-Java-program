import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.time.*;

/**
    Write a description of class MainFrame here.
    
    @author (your name)
    @version (a version number or a date)
*/
public class EncapsulatorMainFrame extends JFrame
{
    static final String[] CONTRIBUTORS = new String[] {
        "Darrell Leung",
        "Nathan Huang",
        "Sharon Li"
        //Add other contributors here
    };
    
    private JScrollPane inputScrollBox;
    private JTextPane inputTextBox;
    private JLabel inputLabel;
    
    private JScrollPane outputScrollBox;
    private JTextPane outputTextBox;
    private JLabel outputLabel;
    private String outputText;
    
    private JButton generateAll;
    private JButton generateConstructor;
    private JButton generateGetters;
    private JButton generateSetters;
    
    private JRadioButton toggleDefaultConstructor;
    private JRadioButton toggleComments;
    private JRadioButton toggleAbbreviations;
    private JRadioButton toggleList;
    private JRadioButton toggleCompression;
    private JRadioButton toggleIsBoolean;
    
    private ArrayList<Field> instanceVariables;
    private ArrayList<Field> superConstructor;
    private ArrayList<Field> otherParameters;
    
    private String className;

    
    
    
    
    public EncapsulatorMainFrame() {
        super("Encapsulator v1.0");
        
        final Font DEFAULT_FONT = new Font("monospaced",Font.PLAIN,14);
        
        this.setSize(960,500);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.show();
        
        this.inputTextBox = new JTextPane();
        this.inputTextBox.setFont(DEFAULT_FONT);
        
        this.inputScrollBox = new JScrollPane(inputTextBox);
        this.inputScrollBox.setSize(230,400);
        this.inputScrollBox.setLocation(20,40);
        this.add(inputScrollBox); 
        
        this.inputLabel = new JLabel("INPUT");
        this.inputLabel.setSize(230,30);
        this.inputLabel.setLocation(20,10);
        this.add(inputLabel);
        
        this.outputTextBox = new JTextPane();
        this.outputTextBox.setEditable(false);
        this.outputTextBox.setFont(DEFAULT_FONT);
        
        this.outputScrollBox = new JScrollPane(outputTextBox);
        this.outputScrollBox.setSize(650,320);
        this.outputScrollBox.setLocation(270,40);
        this.add(outputScrollBox);
        
        this.outputLabel = new JLabel("OUTPUT");
        this.outputLabel.setSize(500,30);
        this.outputLabel.setLocation(270,10);
        this.add(outputLabel);
        
        this.toggleDefaultConstructor = new JRadioButton("default constructor", true);
        this.toggleDefaultConstructor.setSize(150,30);
        this.toggleDefaultConstructor.setLocation(380,380);
        this.add(toggleDefaultConstructor);
        
        this.toggleComments = new JRadioButton("write comments", true);
        this.toggleComments.setSize(150,30);
        this.toggleComments.setLocation(380,410);
        this.add(toggleComments);
        
        this.toggleAbbreviations = new JRadioButton("abbreviate parameters", true);
        this.toggleAbbreviations.setSize(200,30);
        this.toggleAbbreviations.setLocation(530,380);
        this.add(toggleAbbreviations);
        
        this.toggleList = new JRadioButton("list parameters", false);
        this.toggleList.setSize(150,30);
        this.toggleList.setLocation(530,410);
        this.add(toggleList);
        
        this.toggleCompression = new JRadioButton("compress getters/setters", false);
        this.toggleCompression.setSize(200,30);
        this.toggleCompression.setLocation(730,380);
        this.add(toggleCompression);

        this.toggleIsBoolean = new JRadioButton("replace getBol.() w/ isBol.()", true);
        this.toggleIsBoolean.setSize(200,30);
        this.toggleIsBoolean.setLocation(730,410);
        this.add(toggleIsBoolean);

        
        this.generateAll = new JButton("GENERATE");
        this.generateAll.setSize(100,60);
        this.generateAll.setLocation(270,380);
        this.generateAll.setBackground(new Color(89, 212, 78));
        this.generateAll.addActionListener(e -> {
            
            this.compileUserInput();
            
            this.createFields();
            this.createConstructor(false);
            if (this.toggleDefaultConstructor.isSelected()) {
                this.createConstructor(true);
            }
            this.generateGetters();
            this.generateSetters();
            this.generateToString();
            
            
            this.finalizeClass();
            
            
        });
        this.add(generateAll);
        
        
        
        this.className = "Class";
        
        this.repaint();
    }
    
    private String readRestOfLine(ArrayList<String>currentLineList, int start) {
        String str = "";
        for (int i = start; i < currentLineList.size();i++) {
            
            str += currentLineList.get(i);
            if (i != currentLineList.size() - 1) {
                str +=  " ";
            }
        }
        return str;
        
    }
    
    private void compileUserInput() {
        this.outputText= "";
        
        Scanner compiler = new Scanner(this.inputTextBox.getText());
        Scanner lineCompiler;
        
        String currentToken;
        int count = 0;
        boolean isFinal, isSwing, isSuper;
        boolean startSuper = false;
        
        String currentLine;
        ArrayList<String> currentLineList;
        
        ArrayList<Field> targetList;
        
        Field template;
        String lastToken;
        
        this.instanceVariables = new ArrayList<Field>();
        this.superConstructor = null;
        this.otherParameters = new ArrayList<Field>();
        
        while(compiler.hasNextLine()) {
            currentLine = compiler.nextLine();
            
            lineCompiler = new Scanner(currentLine);
            
            currentLineList = new ArrayList<String>();
            
            while(lineCompiler.hasNext()) {
                
                currentToken = lineCompiler.next();
                
                currentLineList.add(currentToken);

                System.out.print(currentToken + ", ");
                
                //System.out.print(currentLineList.size());
                
            }
            
            targetList = this.instanceVariables;
            isFinal = false;
            isSwing = false;
            
            if (startSuper) {
                if (this.superConstructor == null) {
                    this.superConstructor = new ArrayList<Field>();
                }
                targetList = this.superConstructor;
            }
            
            if (!currentLineList.isEmpty()) {
                lastToken = currentLineList.get(currentLineList.size() - 1);

                if (lastToken.charAt(lastToken.length() - 1) == ';') {
                    currentLineList.set(currentLineList.size() - 1, lastToken.substring(0, lastToken.length() - 1));
                }
                
                if (currentLineList.get(0).equals("private") || currentLineList.get(0).equals("public")) {
                    currentLineList.remove(0);
                }
                
                if (currentLineList.get(0).equals("final")) {
                    currentLineList.remove(0);
                    isFinal = true;
                }
                else if(currentLineList.get(0).equals("super")) {
                    if (this.superConstructor == null) {
                        this.superConstructor = new ArrayList<Field>();
                    }
                    currentLineList.remove(0);
                    targetList = this.superConstructor;
                }
                else if(currentLineList.get(0).equals("param") || currentLineList.get(0).equals("parameter")) {
                    currentLineList.remove(0);
                    targetList = this.otherParameters;
                }
                
                if (currentLineList.get(0).equals("swing")) {
                    currentLineList.remove(0);
                    isSwing = true;
                }
                
            }
          
            
            if (currentLineList.size() == 1) {
                if (count == 0) {
                    this.className = currentLineList.get(0);
                }
                else {
                    template = new Field(
                        currentLineList.get(0), 
                        "String", 
                        isSwing
                    );
                    targetList.add(template);     
                }
                
            }
            else if (currentLineList.size() == 2) {
                
                if (currentLineList.get(0).equals("start")) {
                    if (currentLineList.get(1).equals("super")) {
                        startSuper = true;
                    }
                }
                else if (currentLineList.get(0).equals("end")) {
                    if (currentLineList.get(1).equals("super")) {
                        startSuper = false;
                    }
                }
                else {
                    template = new Field(
                        currentLineList.get(1), 
                        currentLineList.get(0), 
                        isSwing
                    );
                    targetList.add(template);            
                }
                
            }
            else if (currentLineList.size() > 3) {
                
                boolean isObject = false;
                
                if (currentLineList.get(3).equals("new")) {
                    currentLineList.remove(3);
                    isObject = true;
                }
                
                if (currentLineList.get(2).equals("=")) {
                    //String setValue = String.concat();
                    
                    if (currentLineList.get(3).equals("default")) {
                        template = new Field(
                            currentLineList.get(1), 
                            currentLineList.get(0), 
                            isSwing
                        );
                        template.setValue(template.getDefault(), isObject);
                    }
                    else {
                        template = new Field(
                            currentLineList.get(1), 
                            currentLineList.get(0), 
                            isSwing, currentLineList.get(3)
                        );
                        template.setValue(readRestOfLine(currentLineList, 3), isObject);
                    }
                    targetList.add(template);   
                }
                else if(currentLineList.get(2).equals("d=") || currentLineList.get(2).equals("default")) {
                    template = new Field(
                        currentLineList.get(1), 
                        currentLineList.get(0), 
                        isSwing, readRestOfLine(currentLineList, 3)
                    );
                    targetList.add(template);   
                }
                else if(currentLineList.get(2).equals("v=") || currentLineList.get(2).equals("value")) {
                    template = new Field(
                        currentLineList.get(1), 
                        currentLineList.get(0), 
                        isSwing
                    );
                    template.setValue(readRestOfLine(currentLineList, 3), isObject);
                    targetList.add(template);   
                }
                else if(currentLineList.get(2).equals("clamp") && currentLineList.size() > 4) {
                    template = new Field(
                        currentLineList.get(1), 
                        currentLineList.get(0), 
                        isSwing
                    );
                    template.setMinMax(currentLineList.get(3), currentLineList.get(4));
                    targetList.add(template);   
                }
                
                   
            }
            
            count ++;
            System.out.println();
        }
        
        HashMap<String, Field> abbreviations = new HashMap<>();
        String abrev;
        String methodName;
        Field r;
        int id;
        
        ArrayList<Field> allFields = new ArrayList<Field>();
        if (this.superConstructor != null) {
            allFields.addAll(this.superConstructor);
        }
        //allFields.addAll(this.otherParameters);
        allFields.addAll(this.instanceVariables);
        
        for (Field f : this.otherParameters) {
            f.setAbbreviation(f.getName());
            abbreviations.put(f.getName(), f);
        }
        
        //Generate abbreviations
        for (Field f : allFields) {
            
            
            
            methodName = generateAbbreviation(f.getName())[1];
            
            if (!f.getValue().isEmpty()) {
                f.setMethodName(methodName);
                if (this.toggleAbbreviations.isSelected()) {
                    f.setAbbreviation(generateAbbreviation(f.getName())[0]);
                }
                else {
                    f.setAbbreviation(f.getName());
                }
                continue;
            }
            
            if (this.toggleAbbreviations.isSelected()) {
                abrev = generateAbbreviation(f.getName())[0];
            
                if (abbreviations.containsKey(abrev)) {
                    r = abbreviations.get(abrev);
                    
                    id = 1;
                    while (abbreviations.containsKey(abrev + id)) {
                        id++;
                    }
                    
                    if (id == 1) {
                        abbreviations.put(abrev + "1", r);
                        r.setAbbreviation(abrev + "1");
                        
                        abbreviations.put(abrev + "2", f);
                        f.setAbbreviation(abrev + "2");
                    }
                    else {
                        abbreviations.put(abrev + id, f);
                        f.setAbbreviation(abrev + id);
                    }
        
                }
                else {
                    abbreviations.put(abrev, f);
                    f.setAbbreviation(abrev);
                }
            }
            else {
                f.setAbbreviation(f.getName());
            }
            
            
            f.setMethodName(methodName);
            
        }
        
        
    }
    
    
    
    private String[] generateAbbreviation(String camelCase) {
        
        
        String currentToken = "";
        
        String abbrev = "";
        
        String methodName  ="";
        
        for (char c : camelCase.toCharArray()) {
            
            if (Character.isUpperCase(c)) {
                if (currentToken.length() > 0 && !currentToken.equals("is")) {
                    abbrev += currentToken.toLowerCase().charAt(0);
                    methodName += currentToken.substring(0,1).toUpperCase() + currentToken.substring(1);
                    //System.out.println(methodName + ", " + currentToken);
                }
                
                currentToken = "";
            }
            
            currentToken += c;
        }
        
        if (currentToken.length() > 0) {
            abbrev += currentToken.toLowerCase().charAt(0);
            methodName += currentToken.substring(0,1).toUpperCase() + currentToken.substring(1);
        }
        
        return new String[] {abbrev, methodName};
    }
    
    private String createFields() {
        String fields = "";
        if (this.toggleComments.isSelected()) {
            fields += String.format("\n    /**DECLARE %s INSTANCE VARIABLES**/\n", this.className.toUpperCase());
        }
        
        for (Field f : this.instanceVariables) {
           
            fields += String.format("    private %s %s;\n", f.getType(), f.getName());
            
        }
        
        this.outputText += fields;
        
        return fields;
    }
    
    private String createConstructor(boolean isDefault) {
        String constructor = "";
        
        if (this.toggleComments.isSelected()) {
            if (isDefault) {
                constructor += "\n    /**DEFAULT CONSTRUCTOR**/";
            }
            else {
                constructor += "\n    /**PARAMETERIZED CONSTRUCTOR**/";
            }
        }
        
        constructor += String.format("\n    public %s(", this.className);
            
        int totalIndex = 0;
        
        if (!isDefault) {
            if (this.superConstructor != null) {
                for (int i = 0; i < this.superConstructor.size(); i++) {
                    
                    Field f = this.superConstructor.get(i);
                    
                    if (f.getValue().isEmpty()) {
                                        
                        if (totalIndex != 0) {
                            constructor += ", ";
                        }
                        if (this.toggleList.isSelected()) {
                            constructor += "\n        ";
                        }
                        constructor += f.getType() + " " + f.getAbbreviation();
                        totalIndex ++;
                    }
                 
                }
            }
            
            for (int i = 0; i < this.otherParameters.size(); i++) {
                Field f = this.otherParameters.get(i);
              
                if (totalIndex != 0) {
                    constructor += ", ";
                }
                if (this.toggleList.isSelected()) {
                    constructor += "\n        ";
                }
                constructor += f.getType() + " " + f.getAbbreviation();
                totalIndex ++;
                
            }
            
            for (int i = 0; i < this.instanceVariables.size(); i++) {
                
                Field f = this.instanceVariables.get(i);
                
                if (f.getValue().isEmpty()) {
                    if (totalIndex != 0) {
                        constructor += ", ";
                    }
                    if (this.toggleList.isSelected()) {
                        constructor += "\n        ";
                    }
                    constructor += f.getType() + " " + f.getAbbreviation();
                    totalIndex ++;
                }
              
                
            }
            
            if (this.toggleList.isSelected()) {
                constructor += "\n    ";
            }
        }
        
        constructor +=") {\n";
        
        if (this.superConstructor != null) {
            if (this.toggleComments.isSelected()) {
                constructor += "        //Call the superclass constructor\n";
            }
            constructor +="        super(";
            
            if (!isDefault) {
                for (int i = 0; i < this.superConstructor.size(); i++) {
                    if (i != 0) {
                        constructor += ", ";
                    }
                    
                    Field f = this.superConstructor.get(i);
                    
                    String equalsTo;
                    
                    if (isDefault) {
                        equalsTo = f.getDefault();
                    }
                    else if (!f.getValue().isEmpty()) {
                        equalsTo = f.getValue();
                    }
                    else{
                        equalsTo = f.getAbbreviation();
                    }
                    
                    constructor += equalsTo;
                }
            }
            
            constructor += ");";
            
            constructor += "\n";
        }
            
        for (Field f : this.instanceVariables) {
            
            String equalsTo;
            
            if (isDefault) {
                equalsTo = f.getDefault();
            }
            else if (!f.getValue().isEmpty()) {
                equalsTo = f.getValue();
            }
            else{
                equalsTo = f.getAbbreviation();
                if (!f.getMin().isEmpty()) {
                    equalsTo = String.format("Math.clamp(%s, %s, %s)",equalsTo,f.getMin(),f.getMax());
                }
            }
            
            constructor += String.format("        this.%s = %s;\n",f.getName(),equalsTo);
        }
        
        constructor += "    }";
        
        this.outputText += constructor + "\n";
        
        return constructor;
    }
    
    //public void getText() {return outputText;}
    
    /** **/
    
    private String generateGetters() {
        String getters = "";
        
        if (this.toggleComments.isSelected()) {
            getters += "\n    /**GETTERS**/";
        }
        
        for (Field f : this.instanceVariables) {
            if (this.toggleIsBoolean.isSelected() && f.getName().substring(0,2).equals("is")) {
                getters += String.format("\n    public %s is%s() {", f.getType(), f.getMethodName());
            }
            else {
                getters += String.format("\n    public %s get%s() {", f.getType(), f.getMethodName());
            }
            
            if (!this.toggleCompression.isSelected()) {
                getters +="\n        ";
            }   
            getters += String.format("return this.%s;", f.getName());
            if (!this.toggleCompression.isSelected()) {
                getters +="\n    ";
            }
            getters += "}";
        }
        
        this.outputText += getters + "\n";
        return getters;
    }
    
    private String generateSetters() {
        String setters = "";
        String setValue;
        
        if (this.toggleComments.isSelected()) {
            setters += "\n    /**SETTERS**/";
        }
        
        for (Field f : this.instanceVariables) {
            setters += String.format("\n    public void set%s(%s %s) {", f.getMethodName(), f.getType(), f.getAbbreviation());
            if (!this.toggleCompression.isSelected()) {
                setters +="\n        ";
            }  
            setValue = f.getAbbreviation();
            if (!f.getMin().isEmpty()) {
                setValue = String.format("Math.clamp(%s, %s, %s)",setValue,f.getMin(),f.getMax());
            }
            setters += String.format("this.%s = %s;", f.getName(), setValue);
            if (!this.toggleCompression.isSelected()) {
                setters +="\n    ";
            }
            setters += "}";
        }
        
        this.outputText += setters + "\n";
        return setters;
    }
    
    private String generateToString() {
        String toString = "";
        
        if (this.toggleComments.isSelected()) {
            toString += "\n    /**TO STRING**/";
        }
        
        toString += "\n    @Override";
        toString += "\n    public String toString() {\n        return ";
        toString += String.format("\"%s:\"", this.className.toUpperCase());
        
        if (this.superConstructor != null ) { //&& !this.superConstructor.isEmpty()
            toString += " + \n               \"\\ninherited from \" + super.toString()";
            toString += " + \n               \"private fields: \"";
        }
        
        for (Field f : this.instanceVariables) {
            toString += String.format(" + \n               \"\\n    %s: \" + this.%s", f.getName(), f.getName());
            
        }
        
        toString += " + \"\\n\";\n    }";
            
        this.outputText += toString + "\n";
        return toString;
    }
    
    private String generateClone() {
        String toString = "";
        
        if (this.toggleComments.isSelected()) {
            toString += "\n    /**CLONE**/";
        }
        
        toString += "\n    @Override";
        toString += "\n    public "+ this.className +" clone() {\n        return ";
        toString += String.format("\"%s:\"", this.className.toUpperCase());
        
        if (this.superConstructor != null ) { //&& !this.superConstructor.isEmpty()
            toString += " + \n               \"\\ninherited from \" + super.toString()";
            toString += " + \n               \"private fields: \"";
        }
        
        for (Field f : this.instanceVariables) {
            toString += String.format(" + \n               \"\\n    %s: \" + this.%s", f.getName(), f.getName());
            
        }
        
        toString += " + \"\\n\";\n    }";
            
        this.outputText += toString + "\n";
        return toString;
    }
    
    private void finalizeClass() {
        
        LocalDate today = LocalDate.now();
        
        String signature = "/**";
        
        signature += String.format("\n Write a description of %s here.\n", this.className);
        
        
        
       
        
        if (CONTRIBUTORS.length < 1) {
             signature += String.format("\n @author (Your name)");
        }
        else if (CONTRIBUTORS.length > 1) {
            signature += String.format("\n @author (Your name)");
            signature += String.format("\n @CONTRIBUTORS: (");
                for (int i = 0; i < CONTRIBUTORS.length; i++) {
                signature += CONTRIBUTORS[i];
                if (i < CONTRIBUTORS.length - 1) {
                    signature += ", ";
                }
            }
        }
        else {
            signature += String.format("\n @author (%s)", CONTRIBUTORS[0]);
        }
        
        
        
        signature += ")";
        
        signature += String.format("\n @date (%s)", today.toString().replace('-','/'));
        signature += "\n*/";
        
        signature += String.format("\npublic class %s {", this.className);
            
        this.outputText = signature + "\n" + this.outputText + "\n}";
        
        this.outputTextBox.setText(this.outputText);
        
    }
        
    
}