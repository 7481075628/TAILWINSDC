package junit_runner;

import java.security.Permission;


/** A security manager that throws an exception when System.exit() is called. Since SecurityManager is deprecated,
 * this should only be used through reflection for Java versions where SecurityManager still exists. **/
public class BlockExitSecurityManager extends SecurityManager {


   /** {@inheritDoc} **/
   public void checkPermission(final Permission perm) {
   }


   /** {@inheritDoc} **/
   public void checkPermission(final Permission perm, final Object context)  {
   }   

   
   /** {@inheritDoc} **/
   public void checkExit(final int status) {
      super.checkExit(status);
      throw new JgrExitException69jh(status);
   }
}


