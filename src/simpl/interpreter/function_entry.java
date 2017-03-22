package simpl.interpreter;


public class function_entry {
        Value fun;
        Value para;
        Value result;
        
        public function_entry(Value expr,Value p) {
            fun = expr;
            para = p;
        }
        
        public boolean equal(function_entry f){
            return para.equals(f.para)&& fun.equals(f.fun);        
        }
        
        public void set_result(Value r){
            this.result = r;
        }
}
