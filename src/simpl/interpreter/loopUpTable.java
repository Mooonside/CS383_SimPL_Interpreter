package simpl.interpreter;

import java.util.Stack;

public class loopUpTable {
    private Stack<function_entry> table ;
        
        public loopUpTable() {
            table = new Stack<function_entry>();
        }
               
        public Value get_result(function_entry fe){
            for (function_entry f:table){
                if (f.equal(fe)){
                        //System.out.println("found previos result"+fe.fun+fe.para);
                        return f.result;
                }
            }
            return null;
        }
        
        public void put(function_entry fe,Value result) {
            fe.set_result(result);
            table.push(fe);
            clear();
        }
        
        public void clear(){
            if(table.size()>200){
                while(table.size()>200){
                    table.pop();
                }
            }
        }
        
        
}
