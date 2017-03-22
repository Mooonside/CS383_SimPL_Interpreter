package simpl.interpreter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Mem {

    public HashMap<Integer , memCont> memMap = new HashMap<Integer,memCont>(){
        private static final long serialVersionUID = 1517654669000677591L;
    };
    public Stack<Integer> freeList = new Stack<Integer>(); 
    public Stack<Integer> tmp = new Stack<Integer>(); 
    public HashSet<Integer> alloList = new HashSet<Integer>(){
        private static final long serialVersionUID = 3032335706122811691L;  };
       
        
   public boolean gc_enable(){
       return alloList.size() > 20 && freeList.size()==0 ;
   }
        
    
    public int get_pointer(){
        if(!freeList.isEmpty()){
            int p = freeList.pop();
            return p;
        }else
            return -1;
    }
     
   public void put(int pointer,Value v){
       alloList.add(pointer);
       memCont m = new memCont(v);
       memMap.put(pointer, m);
   }
    
   public Value  get(int pointer){
      memCont m=  memMap.get(pointer);
      if(m!=null)
          return m.value;
      else
          return null;
   }
   
   public void mark(int pointer){
      // System.out.println("mark:"+pointer);
       memCont m = memMap.get(pointer);
       m.mark = true;
       memMap.put(pointer, m);
   }
   
   public void demark(int pointer){
       memCont m = memMap.get(pointer);
       m.mark = false;
       memMap.put(pointer, m);
   }
   
   public void sweep(){
      for(Integer p:alloList){
          memCont m = memMap.get(p);
          if(m.mark) {
              demark(p);
          }else{
              tmp.push(p);
          }
      }
      while(!tmp.isEmpty()){
          Integer p = tmp.pop();
          delete(p);
      }
   }
   
   public void delete(int pointer){
       System.out.println("remove space of:"+pointer);
       alloList.remove(pointer);
       freeList.push(pointer);
       memMap.put(pointer, null);
   }   
}
