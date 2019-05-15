import java.util.LinkedList;

class Evaluation
{
    private Stack s;
    private java.util.Stack<String> string_stack = new java.util.Stack<>();
    String input;
    private String output = "";
    LinkedList<String> linkedList = new LinkedList<>();
    String answer ="";

    public Evaluation(String str)
    {
        input = str;
        s = new Stack(str.length());
    }

    public String inToPre()
    {
        int i;
        input = input.replaceAll("\\s", "");

        for(i = (input.length()-1); i >= 0; i--)
        {
            char ch = input.charAt(i);
            switch(ch)
            {
                case '+':
                case '-':
                    gotOperator(ch,1,')');
                    break;
                case '*':
                case '/':
                case '%':
                    gotOperator(ch,2,')');
                    break;
                case '^':
                    gotOperator(ch,3,')');
                    break;
                case ')':
                    s.push(ch);
                    break;
                case '(':
                    gotParenthesis(')');
                    break;
                default:output=ch+output;
            }
        }

        while(!s.isEmpty())
            output = s.pop() + output;
        return output;
    }

    public void two_inToPre()
    {
        LinkedList<String> result = new LinkedList<>();
        int i;
        //input = input.replaceAll("\\s", "");

        LinkedList<String> string = new LinkedList<>();
        String append = "";

        for(int j=0; j<input.length();j++){
            // System.out.println("char at "+j+" : "+input.charAt(j));
            if(input.charAt(j) == ' '){
                //do nothing
                //    System.out.println("space found");
            }
            else{
                append = append + input.charAt(j);
                try {
                    if(input.charAt(j+1) == ' '){
                        string.add(append);
                        append = "";
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }
        string.add(append);
        //  System.out.println(string);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        for(i = (string.size()-1); i >= 0; i--)
        {
            //char ch = input.charAt(i);
            String str = string.get(i);
            // System.out.println("String evaluated: "+string.get(i));
            switch(str)
            {
                case "+":
                case "-":
                    two_gotOperator(str,1,")");
                    break;
                case "*":
                case "/":
                case "%":
                    two_gotOperator(str,2,")");
                    break;
                case "^":
                    two_gotOperator(str,3,")");
                    break;
                case ")":
                    //s.push(str);
                    string_stack.push(str);
                    break;
                case "(":
                    two_gotParenthesis(")");
                    break;
                default:
                    output = str+output;
                    result.add(output);
                    output = "";
            }
            //System.out.println("output: "+output);
            //output = "";
            // System.out.println("result update: "+result);
        }

        while(!string_stack.isEmpty()) {
            //output = string_stack.pop() + output;
            //  System.out.println("add to result: "+string_stack.peek()+output);
            result.add(string_stack.pop()+output);
        }
        //return output;
//        return result.getLast();

        LinkedList<String> return_list = new LinkedList<>();
        String add_to_result="";
        for(int k=result.size()-1; k>=0; k--){
            //   System.out.println("check: "+result.get(k));
            for(int l=0; l<result.get(k).length();l++){
                //    System.out.println("character: "+result.get(k).charAt(l));


                if(result.get(k).charAt(l) == '+' || result.get(k).charAt(l) == '-' || result.get(k).charAt(l) == '*' ||
                        result.get(k).charAt(l)== '/' || result.get(k).charAt(l) == '%' || result.get(k).charAt(l) == '^'){
                    if(add_to_result.equals("")){
                        //return_list.add(""+result.get(k).charAt(l));
                        return_list.add(""+result.get(k).charAt(l));
                        //  System.out.println("added : "+result.get(k).charAt(l));
                    }
                    else{
                        //  System.out.println("inner else");
                        return_list.add(add_to_result);
                        //   System.out.println("added : "+add_to_result);
                        add_to_result="";
                        return_list.add(""+result.get(k).charAt(l));
                        //   System.out.println("added : "+result.get(k).charAt(l));
                    }

                }
                else{
                    add_to_result = add_to_result+result.get(k).charAt(l);
                    //  System.out.println("add to result: "+add_to_result);
                }

            }
            if(!add_to_result.equals("")){
                return_list.add(add_to_result);
                //  System.out.println("added : "+add_to_result);
                add_to_result="";
            }
        }

        // System.out.println("return list: "+return_list);
        this.linkedList = return_list;
        //return return_list;


    }

    public LinkedList<String> string_to_list(String s) {
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

    public void infix_evaluate_expression(LinkedList<String> strings) {

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
                }

                stack.push(solve(op1,strings.get(i),op2));

            }
            else{
                stack.push(strings.get(i));
            }
        }
        System.out.println("ANSWER : " +stack.peek());
        this.answer = stack.peek();
    }

    public void prefix_evaluate_expression(LinkedList<String> strings) {
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
        this.answer = stack.peek();
    }

    public void postfix_evaluate_expression(LinkedList<String> strings) {
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
        this.answer = stack.peek();
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

    public String preToIn(){
        String op1 = "";
        String op2 = "";
        String new_exp = "";
        LinkedList<String> f = new LinkedList<>();
        //input = input.replaceAll("\\s", "");
        String append="";
        try {
            for(int i=input.length()-1;i>=0;i--){
                System.out.println("check: " + input.charAt(i));
                if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' ||
                        input.charAt(i) == '/' || input.charAt(i) == '^' || input.charAt(i) == '%'){
                    System.out.println("found operation");
                    op1 = ""+f.removeLast();
                    op2 = ""+f.removeLast();
                    System.out.println("op1 : "+op1);
                    System.out.println("op2 : "+op2);
                    new_exp = "("+op1+input.charAt(i)+op2+")";
                    f.add(new_exp);
                    System.out.println("f added: "+new_exp);
                }
                else if (input.charAt(i) == ' '){
                    if (!append.equals("")) {
                        f.add(append);
                        System.out.println("f added: "+append);
                    }
                    append = "";
                }
                else{
                    append = input.charAt(i) + append;
//                    f.add(""+input.charAt(i));
                    //System.out.println("f added in else: "+f.getLast());
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        //output = f.getFirst();
        try {
            //System.out.println(f);
            //System.out.println("f size: "+f.size());
            if (f.size()!=1){
                return "SYNTAX ERROR 1";
            }
            return f.getFirst();
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return output;
    }

    private void two_gotOperator(String opThis,int prec1,String x)
    {
        while(!string_stack.isEmpty())
        {
            String opTop = string_stack.pop();
            if(opTop.equals(x))
            {
                string_stack.push(opTop);
                break;
            }
            else
            {
                int prec2;
                if(opTop.equals("+")||opTop.equals("-"))
                    prec2 = 1;
                else if(opTop.equals("*")|| opTop.equals("/")|| opTop.equals("%")|| opTop.equals("^"))
                    prec2 = 2;
                else
                    prec2 = 3;
                if(prec2 < prec1 && opTop.equals("("))
                {
                    string_stack.push(opTop);
                    break;
                }
                else if(prec2 <= prec1 && x.equals(")"))
                {
                    string_stack.push(opTop);
                    break;
                }
                else
                {
                    if(x.equals(")"))
                        output = opTop + output;
                    else
                        output = output + opTop;
                }
            }
        }
        string_stack.push(opThis);
    }

    private void gotOperator(char opThis,int prec1,char x)
    {
        while(!s.isEmpty())
        {
            char opTop = s.pop();
            if(opTop == x)
            {
                s.push(opTop);
                break;
            }
            else
            {
                int prec2;
                if(opTop == '+' || opTop == '-')
                    prec2 = 1;
                else if(opTop == '*' || opTop == '/' || opTop == '%' || opTop == '^')
                    prec2 = 2;
                else
                    prec2 = 3;
                if(prec2 < prec1 && x == '(')
                {
                    s.push(opTop);
                    break;
                }
                else if(prec2 <= prec1 && x == ')')
                {
                    s.push(opTop);
                    break;
                }
                else
                {
                    if(x == ')')
                        output = opTop + output;
                    else
                        output = output + opTop;
                }
            }
        }
        s.push(opThis);
    }

    private void gotParenthesis(char x)
    {
        while(!s.isEmpty())
        {
            char ch = s.pop();
            if(ch == x)
                break;
            else
            {
                if(x == ')')
                    output = ch + output;
                else
                    output = output + ch;
            }
        }
    }

    private void two_gotParenthesis(String x)
    {
        while(!string_stack.isEmpty())
        {
            String str = string_stack.pop();
            if(str.equals(x))
                break;
            else
            {
                if(x.equals(")"))
                    output = str + output;
                else
                    output = output + str;
            }
        }
    }

    public String postToIn() {
        String op1 = "";
        String op2 = "";
        String new_exp = "";
        LinkedList<String> f = new LinkedList<>();
        //input = input.replaceAll("\\s", "");
        String append="";
        try {
            for(int i=0;i<=input.length();i++){
                System.out.println("check: "+input.charAt(i));
                if(input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*' ||
                        input.charAt(i) == '/' || input.charAt(i) == '^' || input.charAt(i) == '%'){

                    op2 = ""+f.removeLast();
                    op1 = ""+f.removeLast();
                    System.out.println("op1: "+op1);
                    System.out.println("op2: "+op2);
//                    if(input.charAt(i) == '%'){
//
//                    }
                    new_exp = "("+op1+input.charAt(i)+op2+")";
                    //System.out.println("new exp: "+new_exp);
                    f.add(new_exp);
                    System.out.println("f added: "+new_exp);
                }
                else if (input.charAt(i) == ' '){

                    if (!append.equals("")) {
                        f.add(append);
                        System.out.println("f added: "+append);
                    }
                    append = "";
                }
                else{
                    append = append + input.charAt(i);
                    System.out.println("append: "+append);
                    //f.add(""+input.charAt(i));
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

//        System.out.println("f size= "+f.size());
//        System.out.println(f);
        if(f.size()!=1){
            return "SYNTAX ERROR 2";
        }
        return f.getFirst();
    }

    public String postToPre() {
        //input = input.replaceAll("\\s", "");
        Evaluation postToPre = new Evaluation(input);
        String get_infix = postToPre.postToIn();
        if(get_infix.equals("SYNTAX ERROR 2")){
            return get_infix;
        }
        Evaluation preToIn = new Evaluation(get_infix);
        return preToIn.inToPre();
    }
}