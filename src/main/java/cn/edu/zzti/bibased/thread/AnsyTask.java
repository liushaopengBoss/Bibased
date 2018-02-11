package cn.edu.zzti.bibased.thread;

import java.util.concurrent.Callable;

public class AnsyTask implements Callable {
   private  Executer executer;
   public static AnsyTask newTask(){
       return new AnsyTask();
   }
   public AnsyTask registExecuter(Executer executer){
       this.executer = executer;
       return this;
   }
    @Override
    public Object call() throws Exception {
        return executer.call();
    }

    public interface Executer{
        Object call() throws Exception;
    }
}
