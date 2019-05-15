//Program: Infix Postfix and Prefix
/*
***
MENU
1. Infix to Prefix
2. Infix to Postfix
3. Prefix to Postfix
Enter your choice 1
Enter the infix expression this Prefix expression : this Press y to continue y MENU
1. Infix to Prefix
2. Infix to Postfix
3. Prefix to Postfix Enter your choice
****
*/
import java.io.*;
import java.util.LinkedList;



public class two
{
    public static void main(String args[])throws IOException
    {
        String s,check = "y";
        int n;
        Evaluation inToPre;
        Evaluation inToPost;
        Evaluation preToPost;
        Evaluation preToIn;
        Evaluation postToPre;
        Evaluation postToIn;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(check.equals("y"))
        {
            System.out.println("MENU");
            System.out.println("1. From Infix");
            System.out.println("2. From Prefix");
            System.out.println("3. From Postfix");
            System.out.println("Enter your choice");

            n = Integer.parseInt(br.readLine());
            switch(n)
            {
                case 1:
                    System.out.println("Enter the Infix expression ");
                    //String input = br.readLine();
                    s = br.readLine();
                    //s.replaceAll("\\s", "");
                    inToPre = new Evaluation(s);
                    inToPost = new Evaluation(s);
                    //System.out.println("Prefix expression : "+inToPre.inToPre());
                    //System.out.println("Postfix expression : "+inToPost.inToPost());
//                    String result = inToPre.inToPre();
//                    System.out.println("result: "+result);
                    LinkedList <String> llist = new LinkedList<>();
                    //llist = inToPre.two_inToPre();
                    inToPre.two_inToPre();
                    // System.out.println(llist);
                    //infix_evaluate_expression(llist);

                    break;

                case 2:
                    System.out.println("Enter the Prefix expression ");
                    s = br.readLine();
                    preToPost = new Evaluation(s);
                    preToIn = new Evaluation(s);
                    String infix = preToIn.preToIn();
                    //System.out.println("Infix expression : "+infix);
                    if(infix.equals("SYNTAX ERROR 1")){
                        System.out.println(infix);
                        break;
                    }
                    prefix_evaluate_expression(string_to_list(s));
                    //System.out.println("Postfix expression : "+preToPost.preToPost());
                    break;

                case 3:
                    System.out.println("Enter the Postfix expression ");
                    s = br.readLine();
                    postToPre = new Evaluation(s);
                    postToIn = new Evaluation(s);
                    String postToPre_exp = postToPre.postToPre();
                    if(postToPre_exp.equals("SYNTAX ERROR 2")){
                        System.out.println(postToPre_exp);
                        break;
                    }
                    postfix_evaluate_expression(string_to_list(s));
                    break;

                default:
                    System.out.println("Invalid input");
            }
            System.out.println("Press y to continue");
            check = br.readLine();
        }
    }



    private static void prefix_evaluate_expression(LinkedList<String> strings) {
        java.util.Stack<String> stack = new java.util.Stack<>();
        int op1 = 0;
        int op2 = 0;
        int temp = 0;

        for(int i=strings.size()-1; i>=0;i--){
            if(strings.get(i).equals("+") || strings.get(i).equals("-") || strings.get(i).equals("*") ||
                    strings.get(i).equals("/") || strings.get(i).equals("^") || strings.get(i).equals("%") ){
                op1 = Integer.parseInt(stack.pop());
                op2 = Integer.parseInt(stack.pop());
                if(op1 < op2 && !(strings.get(i).equals("^"))){
                    //switch
                    temp = op1;
                    op1 = op2;
                    op2 = temp;
                    if(strings.get(i).equals("%")){
                        temp = op1;
                        op1 = op2;
                        op2 = temp;
                    }
                }

                stack.push(solve(op1,strings.get(i),op2));

            }
            else{
                stack.push(strings.get(i));
            }
        }
        System.out.println("ANSWER : " +stack.peek());
    }

    private static void postfix_evaluate_expression(LinkedList<String> strings) {
        java.util.Stack<String> stack = new java.util.Stack<>();
        int op1 = 0;
        int op2 = 0;
        int temp = 0;

        for(int i=0; i<strings.size();i++){
            if(strings.get(i).equals("+") || strings.get(i).equals("-") || strings.get(i).equals("*") ||
                    strings.get(i).equals("/") || strings.get(i).equals("^") || strings.get(i).equals("%") ){
                op1 = Integer.parseInt(stack.pop());
                op2 = Integer.parseInt(stack.pop());
                if(op1 < op2 || (strings.get(i).equals("^"))){// && !(strings.get(i).equals("^"))){
                    //switch
                    temp = op1;
                    op1 = op2;
                    op2 = temp;
                }


                stack.push(solve(op1,strings.get(i),op2));

            }
            else{
                stack.push(strings.get(i));
            }
        }
        System.out.println("ANSWER : " +stack.peek());
    }


    private static String solve(int op1, String s, int op2) {
        int ans = 0;
        if(s.equals("+")){
            ans = op1+op2;
        }
        else if(s.equals("-")){
            ans = op1-op2;
        }
        else if(s.equals("*")){
            ans = op1*op2;
        }
        else if(s.equals("/")){
            ans = op1/op2;
        }
        else if(s.equals("^")){
            ans = (int) Math.pow(op1,op2);
        }
        else if(s.equals("%")){
            System.out.println("==== "+op1+" % "+op2);
            ans = op1%op2;
        }
        System.out.println("solve: " + op1+" "+s+" "+op2+" = "+ans);
        return ""+ans;
    }

    private static LinkedList<String> string_to_list(String s) {
        //System.out.println(s);
        //String[] string = s.trim().split("\\s+");
        LinkedList<String> string = new LinkedList<>();
        String append = "";
        for(int i=0; i<s.length();i++){
            // System.out.println("char at "+i+" : "+s.charAt(i));
            if(s.charAt(i) == ' '){
                //do nothing
                //  System.out.println("space found");
            }
            else{
                append = append + s.charAt(i);
                try {
                    if(s.charAt(i+1) == ' '){
                        string.add(append);
                        append = "";
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        string.add(append);
        // System.out.println(string);
        return string;

    }


}

