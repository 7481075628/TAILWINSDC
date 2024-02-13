package junit_runner;


/** A security exception thrown when user code or a test calls System.exit(). **/
public class JgrExitException69jh extends SecurityException  {


   /** Creates a new exit exception.
    *
    * @param status the exit status. **/
   JgrExitException69jh(final int status)  {
      super("System.exit(" + status + ") or Runtime.exit(" + status + ") was called");
   }
}
