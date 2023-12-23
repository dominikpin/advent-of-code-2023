package day19;

import java.util.ArrayList;

public class Workflow {
    
    private String name;
    private ArrayList<Rule> rules;

    public Workflow(String name) {
        this.name = name;
        this.rules = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Rule getRule(int number) {
        return this.rules.get(number);
    }

    public int getRuleListSize() {
        return this.rules.size();
    }

    public void addRule(Rule rule) {
        rules.add(rule);
    }
}
