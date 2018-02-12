package cn.edu.zzti.bibased.thread;

import java.util.concurrent.Callable;

/**
 * 异步执行线程 解决
 */
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
        return executer.executer();
    }

    public interface Executer{
        Object executer() throws Exception;
    }
}
