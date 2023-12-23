package day19;

public class Rule {
    
    private char category;
    private char sign;
    private int number;
    private Workflow workflow;

    public Rule(char category, char sign, int number) {
        this.category = category;
        this.sign = sign;
        this.number = number;
    }

    public boolean ruleExecute(int number) {
        return this.sign == '>' ? number > this.number : number < this.number;
    }

    public char getCategory() {
        return this.category;
    }

    public Workflow getWorkflow() {
        return this.workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }
}
