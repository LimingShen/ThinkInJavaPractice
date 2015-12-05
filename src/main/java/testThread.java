import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * Created by limshen on 2015/10/20.
 */
public class testThread {
    public static void main(String[] args){
        testThread t = new testThread();
        boolean s = t.runShell();
        System.out.println(s);
    }

    private boolean runShell(){
        final Semaphore testSuccess = new Semaphore(1);
        try {
            String scriptPath = "D:\\Cygwin\\bin\\bash.exe D:\\test.sh";

            Process grabTableFilesProcess = Runtime.getRuntime().exec(scriptPath);

            //clear input stream of the grabTableFilesProcess to avoid deadlock
            new Thread(){
                public void run(){
                    System.out.println("Begin: Start input stream watch thread for shell process");
                    final InputStream inputStream = grabTableFilesProcess.getInputStream();
                    BufferedReader br1 = new  BufferedReader(new InputStreamReader(inputStream));
                    try{
                        String line1 = null ;
                        while ((line1 = br1.readLine()) !=  null ) {}
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("End: End input stream watch thread for shell process");
                }
            }.start();

            //clear error stream of the grabTableFilesProcess to avoid deadlock
            ExecutorService executor = Executors.newCachedThreadPool();
            Callable<Boolean> errorClearThread = () -> {
                System.out.println("Begin: Start error stream watch thread for shell process");
                final InputStream errorStream = grabTableFilesProcess.getErrorStream();
                BufferedReader br2 = new  BufferedReader(new  InputStreamReader(errorStream));
                Boolean isSuccess = true;
                try{
                    String errorLine = null ;
                    while ((errorLine = br2.readLine()) !=  null ) {
                        if (errorLine != null){
                            System.out.println("Error occurred when execute shell script to copy table files: " + errorLine);
                            isSuccess =false;
                            testSuccess.acquire();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        errorStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        grabTableFilesProcess.destroy();
                        System.out.println("End: End error stream watch thread for shell process");
                        return isSuccess;
                    }
                }
            };
            Future<Boolean> isSuccess = executor.submit(errorClearThread);

            //Waiting for the shell executing complete
            System.out.println("Waiting for shell execute ...");
            grabTableFilesProcess.waitFor();
            System.out.println("Shell execute completed ...");

            if(0 == testSuccess.availablePermits()) {
                System.out.println("error stream executing ended: "+isSuccess.isDone());
                System.out.println("=======================Error occurred when executing script===========================");
                testSuccess.release();
                return false;
            }
            else{
                System.out.println("Update script executed successfully!");
                return true;
            }

//            if(!isSuccess.isDone()){
//                System.out.println("isSuccess is not done!");
//                return true;
//            }else if(null != isSuccess.get()){
//                System.out.println("isSuccess did not get null!");
//                return isSuccess.get();
//            }else{
//                System.out.println("do not know what happened");
//                return true;
//            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
