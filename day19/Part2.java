package day19;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Part2 {
    
    public Part2() throws FileNotFoundException {
        File myObj = new File("day19\\input.txt");
        Scanner myReader = new Scanner(myObj);
        Workflow in = getWorkflows(myReader);
        int[][] intervals = {{1, 4000}, {1, 4000}, {1, 4000}, {1, 4000}};
        long sum = analyseWorkFlows(intervals, in, 0);
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

    private static long analyseWorkFlows(int[][] intervals, Workflow workflow, int ruleNumber) {
        long sum = 0;
        if (workflow.getName().equals("A")) {
            return calculateNumberOfParts(intervals);
        }
        if (workflow.getName().equals("R")) {
            return 0;
        }
        Rule rule = workflow.getRule(ruleNumber);
        char category = rule.getCategory();
        char sign = rule.getSign();
        int number = rule.getNumber();
        int num = -1;
        switch (category) {
            case 'x':
                num = 0;
                break;
            case 'm':
                num = 1;
                break;
            case 'a':
                num = 2;
                break;
            case 's':
                num = 3;
                break;
        }
        if (workflow.getRuleListSize() - 1 == ruleNumber) {
            sum += analyseWorkFlows(intervals, rule.getWorkflow(), 0);
        } else if (sign == '<') {
            int[][] newInterval = makeNewInterval(intervals, num, false, number - 1);
            sum += analyseWorkFlows(newInterval, rule.getWorkflow(), 0);
            newInterval = makeNewInterval(intervals, num, true, number);
            sum += analyseWorkFlows(newInterval, workflow, ruleNumber + 1);
        } else if (sign == '>') {
            int[][] newInterval = makeNewInterval(intervals, num, false, number);
            sum += analyseWorkFlows(newInterval, workflow, ruleNumber + 1);
            newInterval = makeNewInterval(intervals, num, true, number + 1);
            sum += analyseWorkFlows(newInterval, rule.getWorkflow(), 0);
        }
        return sum;
    }

    private static long calculateNumberOfParts(int[][] interval) {
        long sum = 1;
        for (int i = 0; i < interval.length; i++) {
            sum *= interval[i][1] - interval[i][0] + 1;
        }
        return sum;
    }

    private static int[][] makeNewInterval(int[][] interval, int num, boolean biggerHalf, int number) {
        int[][] newInterval = new int[4][2];
        for (int i = 0; i < 4; i++) {
            if (i == num) {
                newInterval[i][0] = biggerHalf ? number : interval[i][0];
                newInterval[i][1] = biggerHalf ? interval[i][1] : number;
                continue;
            }
            newInterval[i][0] = interval[i][0];
            newInterval[i][1] = interval[i][1];
        }
        return newInterval;
    }
}