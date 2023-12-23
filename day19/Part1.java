package day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part1 {
    
    public Part1() throws FileNotFoundException {
        File myObj = new File("day19\\input.txt");
        Scanner myReader = new Scanner(myObj);
        Workflow in = getWorkflows(myReader);
        int sum = calculateNumberOfAcceptedParts(in, myReader);
        myReader.close();
        System.out.println(sum);
    }

    private static Workflow getWorkflows(Scanner myReader) {
        Workflow in = null;
        ArrayList<Workflow> workflows = new ArrayList<>();
        workflows.add(new Workflow("A"));
        workflows.add(new Workflow("R"));
        Map<String, Rule> noWorkflowRules = new HashMap<>();
        while(true) {
            String line = myReader.nextLine();
            if (line.isEmpty()) {
                break;
            }
            String name = line.split("\\{")[0];
            Workflow newWorkflow = new Workflow(name);
            if (name.equals("in")) {
                in = newWorkflow;
            }
            workflows.add(newWorkflow);
            String[] stringRules = line.split("\\{")[1].substring(0, line.length() - 2 - name.length()).split(",");
            for (String stringRule : stringRules) {
                String findWorkflow = "";
                Rule newRule = null;
                if (!stringRule.contains(":")) {
                    newRule = new Rule('x','>', -1);
                    findWorkflow = stringRule;
                } else {
                    char category = stringRule.charAt(0);
                    char sign = stringRule.charAt(1);
                    int number = Integer.parseInt(stringRule.split(":")[0].substring(2));
                    
                    newRule = new Rule(category, sign, number);
                    findWorkflow = stringRule.split(":")[1];
                }
                boolean found = false;
                for (Workflow workflow : workflows) {
                    if (workflow.getName().equals(findWorkflow)) {
                        found = true;
                        newRule.setWorkflow(workflow);
                    }
                }
                if (!found) {
                    noWorkflowRules.put(findWorkflow, newRule);
                }
                newWorkflow.addRule(newRule);
            }
        }
        for (String findWorkflow : noWorkflowRules.keySet()) {
            for (Workflow workflow : workflows) {
                if (workflow.getName().equals(findWorkflow)) {
                    Rule rule = noWorkflowRules.get(findWorkflow);
                    rule.setWorkflow(workflow);
                }
            }
        }

        return in;
    }

    private static int calculateNumberOfAcceptedParts(Workflow in, Scanner myReader) {
        int sum = 0;
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            String[] categories = line.substring(1, line.length() - 1).split(",");
            if (checkWorkflow(categories, in, 0)) {
                for (int i = 0; i < 4; i++) {
                    sum += Integer.parseInt(categories[i].substring(2));
                }
            }
        }

        return sum;
    }

    public static boolean checkWorkflow(String[] categories, Workflow workflow, int number) {
        if (workflow.getName().equals("A")) {
            return true;
        }
        if (workflow.getName().equals("R")) {
            return false;
        }
        Rule rule = workflow.getRule(number);
        char category = rule.getCategory();
        int num = -1;
        switch (category) {
            case 'x':
                num = Integer.parseInt(categories[0].substring(2));
                break;
            case 'm':
                num = Integer.parseInt(categories[1].substring(2));
                break;
            case 'a':
                num = Integer.parseInt(categories[2].substring(2));
                break;
            case 's':
                num = Integer.parseInt(categories[3].substring(2));
                break;
        }
        if (rule.ruleExecute(num)) {
            return checkWorkflow(categories, rule.getWorkflow(), 0);
        }
        return checkWorkflow(categories, workflow, number + 1);
    }
}