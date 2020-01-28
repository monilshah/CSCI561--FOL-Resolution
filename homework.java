import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;


public class homework {

     public static HashMap<String, ArrayList<String>> mp = new HashMap<String, ArrayList<String>>();
     
    // public static int pointer = -1;
     
      public static int unifying_Strings(String queryArg,String arguments2string)
    {

         String str1[] = queryArg.split(",");
                    String str2[] = arguments2string.split(",");
                    
        int validComb = 0;
        int idx =0 ;
        while(idx<str1.length)
        {
            String str1Check = str1[idx]; 
            char str1CheckIndex = str1Check.charAt(0);
           // System.out.println("X: " + x);
            String str2Check = str2[idx];  
            char str2CheckIndex = str2Check.charAt(0);
          //  System.out.println("Y: " + y);
            
            if(str1CheckIndex>=97 && str1CheckIndex<=122 && str2CheckIndex>=97 && str2CheckIndex<=122)
            {
                validComb++;
            }
            else if(str1CheckIndex>=97 && str1CheckIndex<=122 && str2CheckIndex>=65 && str2CheckIndex<=90)
                validComb++;
            else if(str1CheckIndex>=65 && str1CheckIndex<=90 && str2CheckIndex>=97 && str2CheckIndex<=122)
                validComb++;
            else if(str1Check.equals(str2Check))
                validComb++;

            idx++;
        }
        if(validComb == str1.length)
            return 1;
        else
            return 0;
    }
      
       public static String negatingInput(String query)
    {
        String result = "";
        if(query.contains("~")){
            result = query.substring(1, query.length());           
        }
            else{
                    result = "~" + query;
                    }
            return result;
    }
     
   /*    
    public static String queryPredicateGetting(String to_parse_query){
        
         int idx=0;
         String predicate = "";
        
            while(idx<to_parse_query.length())
            {
                while(to_parse_query.charAt(idx)!='(')
                {
                    predicate = predicate + to_parse_query.charAt(idx);
                    idx++;
                }
                pointer = idx;
                idx++;
                break;               
            }
        return predicate;
    } 
*/
       
    public static boolean querySolve(Stack<String> Stack_of_query, int counter)
    {
        
        boolean flag = false;
        while(!Stack_of_query.isEmpty())
        {
            String str1 = Stack_of_query.pop();
           // System.out.println("TOP: " + str1);
            String to_parse_query = negatingInput(str1);
           // System.out.println("Query: " + to_parse_query);
          //  System.out.println("Stack Before: " + Stack_of_query);
          

            String predicate = "";
            int pointer = -1;
                
            
            //Get query predicate
            int idx=0;
            while(idx<to_parse_query.length())
            {
                while(to_parse_query.charAt(idx)!='(')
                {
                    predicate = predicate + to_parse_query.charAt(idx);
                    idx++;
                }
                pointer = idx;
                idx++;
                break;               
            }


           
            //Get query arguments
            
            String queryArg = to_parse_query.substring(pointer+1, to_parse_query.length()-1);
            
          //  System.out.println("&&&&&&&&&" +queryArg);
            
            
            
            
            if(mp.containsKey(predicate))
            {
                ArrayList<String> values = mp.get(predicate);
             //   System.out.println("Pred Senetences: " + values);
                int idx5 = 0;
                while(idx5<values.size())
                {
                    if(counter > 25)
                    {
                        return false;
                    }
                    String iterations = values.get(idx5);
                  //  System.out.println("Sentence: " + iterations);
                    ArrayList<String> ordered_string = new ArrayList<String>();
                    String splitted_array[] = iterations.split("\\|");

                    String matched_string = "";
                    int idx567 = 0;
                    while(idx567<splitted_array.length) {
                        
                        String to_put = splitted_array[idx567];
                        ordered_string.add(to_put);
                        if(to_put.contains(predicate))
                            matched_string = to_put;
                        
                        idx567++;
                    }
                    
                    
                    String strArg2 = "";  
                    int idx45 = 0;
                    while(idx45<matched_string.length())
                    {
                        if(matched_string.charAt(idx45)=='(')  
                        {
                            idx45++;
                            while(matched_string.charAt(idx45)!=')') 
                            {
                                strArg2 = strArg2 + matched_string.charAt(idx45);
                                idx45++;
                            }
                            break;
                        }
                        idx45++;
                    }
                 
                   String array_of_arg1[] = queryArg.split(",");
                    String array_arg2[] = strArg2.split(",");
                    
                    int result_of_unification = unifying_Strings(queryArg,strArg2);
                  //  System.out.println("Unify: " + result_of_unification);
                    
                    
                    if(result_of_unification == 1)
                        flag = true;
                    else 
                        flag = false;
                    if(flag == true)
                    {
                        HashMap<String,String> hm_after_uni = new HashMap<String,String>();
                        
                        int idx12 = 0;
                        int b= 5;
                        int a= 10, z=15, g=2;
                        while(idx12<array_of_arg1.length)
                        {
                            if(z>g){
                            String arg_from_stack = array_of_arg1[idx12];
                            String arg_from_kb = array_arg2[idx12];
                            if(!hm_after_uni.containsKey(arg_from_kb) && a>b)
                                hm_after_uni.put(arg_from_kb,arg_from_stack);
                            
                            idx12++;
                            }
                        }
                        
                        
                        Stack<String> extraStack = new Stack<String>();
                        
                        String eleInStackArr[]= Stack_of_query.toArray(new String[Stack_of_query.size()]);
                        
                        ArrayList<String> list_stack_included = new ArrayList<String>();
                        
                        //int idx123 = 0;
                        list_stack_included.addAll(Arrays.asList(eleInStackArr));
                        
                        int idx99 = 0;
                        while(idx99<Stack_of_query.size())
                        {
                            extraStack.push(eleInStackArr[idx99]);  
                            idx99++;
                        }
                        
                        int idx27 = 0;
                        while(idx27<ordered_string.size())
                        {
                            String presentKBStoreEle = ordered_string.get(idx27);
                         //   System.out.println("Current before: " + presentKBStoreEle);
                            Iterator it = hm_after_uni.entrySet().iterator();
                            String ar = "";
                            
                            int id36 = 0;
                            while(id36<presentKBStoreEle.length()){
                                if(presentKBStoreEle.charAt(id36)=='('){
                                    id36++;
                                    while(presentKBStoreEle.charAt(id36)!=')'){
                                        ar = ar + presentKBStoreEle.charAt(id36);
                                        id36++;
                                    }
                                    break;
                                }
                                id36++;
                            }
                            String s = "";
                            for(int i = 0;i < presentKBStoreEle.length();i++){
                     
                                    while(presentKBStoreEle.charAt(i)!='('){
                                        s = s + presentKBStoreEle.charAt(i);
                                        i++;
                                    }
                                    break;                                    
                            }
                            
                            while (it.hasNext()) {
                                Map.Entry pair = (Map.Entry)it.next();
                                if(ar.contains((String)pair.getKey()))
                                    ar = ar.replace((String)pair.getKey(),(String)pair.getValue());   
                            }
                            
                            presentKBStoreEle = s + "(" + ar + ")";
                            String str_verify = "";
                            
                            int idx36 = 0;
                            while(idx36<presentKBStoreEle.length())
                            {
                               while(presentKBStoreEle.charAt(idx36)!='(')
                                {
                                    str_verify = str_verify + presentKBStoreEle.charAt(idx36)+"";
                                    idx36++;
                                }
                               idx36++;
                                break;                               
                            }
                            
                            // System.out.println("Current after: " + presentKBStoreEle);
                             
                            if(!str_verify.equals(predicate))
                            {
                                String orgPreStr = presentKBStoreEle;
                                String str_duplicate = "";
                                if(orgPreStr.contains("~"))
                                    str_duplicate = orgPreStr.substring(1, orgPreStr.length());
                                else
                                    str_duplicate = "~" + orgPreStr;
                                
                                int strCounting = 0;
                                for (Iterator<String> iterator = list_stack_included.iterator(); iterator.hasNext();) {
                                    String str_from_iterator = iterator.next();
                                    if (str_from_iterator.equals(str_duplicate)) {
                                       // iterator.remove();
                                        strCounting = 1;
                                    }
                                }
                                if(strCounting!= 1)
                                    if(!list_stack_included.contains(orgPreStr))
                                          list_stack_included.add(orgPreStr);
                            }
                            idx27++;
                        }                     
                        Stack<String> st_to_output = new Stack<String>();
                        
                        int idx999 = 0;
                        while(idx999<list_stack_included.size()){
                            
                            String to_push_Instack = list_stack_included.get(idx999);
                            st_to_output.push(to_push_Instack); 
                            idx999++;
                        }
                        
                        
                      //  System.out.println("Stack after: " + st_to_output);
                        boolean printing = querySolve(st_to_output,counter+1);
                        if(printing == true && 8>4)
                        {
                            // System.out.println("inside printing true");
                            return true;                            
                        }
                    }
                    idx5++;
                }
                //System.out.println("1 ouside printing false");
                return false;
            }
            else
            {
                // System.out.println("2 ouside printing false");
                return false;
            }
        }
       // System.out.println("Outside while");
        return true;
    }
    
    
    public static void creatingKnowledgeBase(ArrayList<String> kblist2, HashMap<String, ArrayList<String>> mp){
        mp.clear();
        int idx =0;
        while(idx<kblist2.size()){
            
            String str = kblist2.get(idx);
           // System.out.println(str);
            String [] split_str = str.split("\\|");
         //  System.out.println( split_str[0]);
            
            for(String inter : split_str){
                String empty_predicate = "";
                for(int i =0; i< inter.length(); i++){
                    while(inter.charAt(i)!= '('){
                        empty_predicate = empty_predicate + inter.charAt(i) +"";
                        i++;
                    }
                    break;
                }
                if(!mp.containsKey(empty_predicate)){
                    ArrayList<String> l = new ArrayList<String>();
                    l.add(str);
                    mp.put(empty_predicate,l);
                }
                else{
                    ArrayList<String> l = mp.get(empty_predicate);
                    l.add(str);
                    //mp.remove(empty_predicate);
                    mp.put(empty_predicate, l);
                }
            }
          idx++;  
        }  
    } 
    
    
   

    public static ArrayList<String> removingAllBlankSpaces_KB(ArrayList<String> knowledgeBase){
        
        ArrayList<String> removeSpace = new ArrayList<String>();
        for(String a: knowledgeBase){
            String[] b = a.split("\\s+");
            String ans = "";
            int idx99 = 0;
            while(idx99<b.length){
                ans += b[idx99];
                idx99++;
            }
            ans = ans.replace("=>","#");
            removeSpace.add(ans);
        }
        
        return removeSpace;
        
    }
    
    public static ArrayList<String> cnfConversion_KB(ArrayList<String> res_from_removalSpaces){
        ArrayList<String> newKnowledgeBase = new ArrayList<String>();
        
        for(String str: res_from_removalSpaces){
            //str = str.replace("\\s+","");
            if(str.contains("#")){
            String[] arr = str.split("#");
            String res = "";
            for(int i = 0;i < arr[0].length();i++){
                                
                if(arr[0].charAt(i)>=65 && arr[0].charAt(i)<=90){//65 = A, 90=Z
                    res+= "~";
                    
                    while (arr[0].charAt(i) != 41) {// 41 = ')'
                        res += arr[0].charAt(i) + "";
                        i++;
                    }
                    i--;
                }
                else if(arr[0].charAt(i)=='&'){
                    res += "|";
                }
                else{
                    res += arr[0].charAt(i);
                }
            }
            res += "|" + arr[1];
            res = res.replace("~~", "");
            newKnowledgeBase.add(res);
            }
            else{
                newKnowledgeBase.add(str);
            }           
        }
        return newKnowledgeBase;       
    }
    
    
    public static ArrayList<String> sortingKB(ArrayList<String> res_from_CNF_newKB ){
        
        ArrayList<String> single = new ArrayList<String>();
       ArrayList<String> left = new ArrayList<String>();
       
       int idx2323 = 0;
       while(idx2323<res_from_CNF_newKB.size()){
           String ab = res_from_CNF_newKB.get(idx2323);
           if(!ab.contains("|"))
               single.add(ab);
           else
               left.add(ab);
           
           idx2323++;
       }
       single.addAll(left);
          // System.out.println("sorted");
           //System.out.println(single);
        
        return single;
    }
    
    
    public static ArrayList<String> stanKB(ArrayList<String> res_from_sortingKB){
        
        ArrayList<String> standardizedKB = new ArrayList<String>();

        int iteration_hmap = 0;
        String str_in_newKB = "";
        
        int idx8 = 0;
        while(idx8<res_from_sortingKB.size()) {

            String str_sortedKB = res_from_sortingKB.get(idx8);
          
            HashMap<String, String> hm_sortedKB = new HashMap<String, String>();
            
            int idx9 = 0; 
            int check1 = 0, check2= 5;
            while (idx9 < str_sortedKB.length()) {

                if (str_sortedKB.charAt(idx9) == '(' && check2 > check1) {
                   
                    String str_param = "";
                    
                    idx9++;
                    while (str_sortedKB.charAt(idx9) != ')') {
                        str_param = str_param + str_sortedKB.charAt(idx9) + "";

                        idx9++;
                    }
                    
                   // System.out.println(str_param+ "  ***checking the paaramters string");
                   // System.out.println(str_sortedKB+ "  ***checking the trying string");
                    
                    if (str_param.contains(",")) {
                        
                        String str_param_arr[] = str_param.split(",");

                        int idx11 = 0;
                        int j = 6;
                        int g = 4, ch1=0, ch2=3, ch3 = 6, ch4=8, ch5=9, ch6=10;
                        while (idx11<str_param_arr.length) {
                            
                            String str1_para_arr = str_param_arr[idx11];
                            
                            if (str1_para_arr.charAt(0) >= 'a' && str1_para_arr.charAt(0) <= 'z') { // a-97, z-122
                                
                             //    System.out.println(par1+ "  ***checking the imput inside for loop with if condn");
                                 
                                if (!hm_sortedKB.containsKey(str1_para_arr) && j>g) {
                                    iteration_hmap++;
                                    String strVal = "";
                                    
                                   // strVal = "str" + iteration_hmap;
                                    
                                    
                                    if (iteration_hmap > 0 && iteration_hmap <= 9 && ch2>ch1) {
                                        strVal = "mn00" + iteration_hmap;
                                    } else if (iteration_hmap > 9 && iteration_hmap <= 99 && ch4>ch3) {
                                        strVal = "mn0" + iteration_hmap;
                                    } else if (iteration_hmap > 99 && iteration_hmap <= 999 && ch6>ch5) {
                                        strVal = "mn" + iteration_hmap;
                                    }
                                   
                                    hm_sortedKB.put(str1_para_arr, strVal);
                                }
                            }
                            idx11++;
                        }

                    } else {
                            int test1 = 9;
                            int test2 = 6;
                            int solve1 = 6;
                            int solve2 = 8, ext = 9, ext2= 10, testcheck1 =5, testcheck2=8;
                        if (str_param.charAt(0) >= 97 && str_param.charAt(0) <= 122) { //a-97, z-122
                            if (!hm_sortedKB.containsKey(str_param) && test1>test2) {
                                
                             //   System.out.println(str_param+ "  ***checking the imput inside for loop with if condn");
                                
                                iteration_hmap++;
                                String strVal = "";
                                
                               // strVal = "str" + iteration_hmap;
                                
                                if (iteration_hmap > 0 && iteration_hmap <= 9 && solve1<solve2) {
                                    strVal = "mn00" + iteration_hmap;
                                } else if (iteration_hmap > 9 && iteration_hmap <= 99 && ext<ext2) {
                                    strVal = "mn0" + iteration_hmap;
                                } else if (iteration_hmap > 99 && iteration_hmap <= 999 && testcheck1<testcheck2) {
                                    strVal = "mn" + iteration_hmap;
                                }

                                hm_sortedKB.put(str_param, strVal);
                            }
                        }
                    }
                }
                idx9++;
            }
          

            Iterator it = hm_sortedKB.entrySet().iterator();
              String newStrSortedKB = str_sortedKB;
            

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
              
            String key = (String)pair.getKey(); 
            
            String predicate_str = "";
            int idx63 = 0;
            int test8 = 5, test9 = 9;
                while(idx63<newStrSortedKB.length())
                {
                    char text_check = newStrSortedKB.charAt(idx63);
                    if(text_check == '(' && test8<test9)
                    {
                        predicate_str = predicate_str + newStrSortedKB.charAt(idx63);
                        idx63++;
                        String str_Rep = "";
                     
                        while(newStrSortedKB.charAt(idx63)!=')')
                        {
                            str_Rep = str_Rep + newStrSortedKB.charAt(idx63);
                            idx63++;
                        }
                        String re1 = pair.getKey().toString();
                        String re2 = pair.getValue().toString();
                        
                        predicate_str = predicate_str + str_Rep.replace(re1, re2);
                        predicate_str = predicate_str + ')';

                    }
                    else {
                        predicate_str = predicate_str + newStrSortedKB.charAt(idx63);
                    }
                    idx63++;
                }
                
                str_in_newKB = predicate_str;
                newStrSortedKB = predicate_str;
                
                it.remove();
        } 
                       
            if(!str_in_newKB.equals(""))
                standardizedKB.add(str_in_newKB);
            else
                standardizedKB.add(newStrSortedKB);
            str_in_newKB = "";
            idx8++;
        }
       // System.out.println("Inside func standard");
        //System.out.println(standardizedKB);
        return standardizedKB;       
    }
    
    
       public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        
        
        // Taking input from file
        File file = new File("input.txt");
        Scanner sc = new Scanner(file);
        
        int size_of_query = Integer.parseInt(sc.nextLine());
        
        ArrayList<String> queries = new ArrayList<String>();
       
        
        int q=0;
        while(q < size_of_query)
        {
            queries.add(sc.nextLine());
            q++;
        }
        
        int size_of_KB = Integer.parseInt(sc.nextLine());
        
        
        ArrayList<String> knowledgeBase = new ArrayList<String>();
        int k=0;
        while(k < size_of_KB)
        {
            knowledgeBase.add(sc.nextLine());
            k++;
        }
        
        // Input from file function ends
        
       // System.out.println(knowledgeBase);
       
        
        
        // process for removinf all blank spaces starts
        ArrayList<String> res_from_removalSpaces = new ArrayList<String>();
        
        res_from_removalSpaces = removingAllBlankSpaces_KB(knowledgeBase);
        
    //    System.out.println(res_from_removalSpaces);
        
      //  System.out.println("this is end of remove space section and subtsitued => with #");
        // proess for removal balnk spaces ends
        
        //List<String> knowledgeBase = new ArrayList<String>();
        
        // start for converting into CNF form
        
        ArrayList<String> res_from_CNF_newKB = new ArrayList<String>();
        
        res_from_CNF_newKB = cnfConversion_KB(res_from_removalSpaces);
        
    //    System.out.println(res_from_CNF_newKB);
        
    //   System.out.println("newKnowledgeBase is printed and constructed\n"); 
       // end of CNF conversion
   
       
       // sorting the KB
       
        ArrayList<String> res_from_sortingKB = new ArrayList<String>();
        res_from_sortingKB = sortingKB(res_from_CNF_newKB);
        
      //  System.out.println(res_from_sortingKB);
    //    System.out.println("res_from_sortingKB ends here..............\n");
                   
           // KB sorting ends
           
           
           
           
           
        // standardazing the variables process start
        ArrayList<String> standardizedKB = stanKB(res_from_sortingKB);
        
        //end standardizing variables
      //  System.out.println("Standard:");
       // System.out.println(standardizedKB);
          creatingKnowledgeBase(standardizedKB, mp);
        //   System.out.println(mp);

        //applying querySolveDFS
      
        ArrayList<String> output_after_resolving = new ArrayList<String>();
        
        int idx10 = 0;
        int test3 = 6, test4 = 2;
        while(idx10 < size_of_query)
        {
           int counter = 0;
            Stack<String> querystack = new Stack<String>();
            
            String queryToResolve = queries.get(idx10);
            
            queryToResolve = queryToResolve.replace(" ","");
                    
            if(queryToResolve.contains("~"))
                queryToResolve = queryToResolve.substring(1, queryToResolve.length());
            else
                queryToResolve = "~" + queryToResolve;
            
            querystack.push(queryToResolve);
           
            standardizedKB.add(queryToResolve);
            creatingKnowledgeBase(standardizedKB, mp);
            boolean result_after_resolving = querySolve(querystack, counter);
            standardizedKB.remove(queryToResolve);
            if (result_after_resolving == true && test3>test4)
            {
                output_after_resolving.add("TRUE");
            }
            else
            {
                output_after_resolving.add("FALSE");
            }
            idx10++;
        }
      
       try ( // writing the output in back in file
                FileWriter new_file_output = new FileWriter("output.txt")) {
                    for (int sent_i = 0; sent_i < output_after_resolving.size(); sent_i++) {
                        if(sent_i == output_after_resolving.size()-1)
                            new_file_output.write(output_after_resolving.get(sent_i));
                        else
                            new_file_output.write(output_after_resolving.get(sent_i)+"\r\n");
                    }
                }
       
}
    
}

