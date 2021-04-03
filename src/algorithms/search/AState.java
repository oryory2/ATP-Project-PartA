package algorithms.search;

public abstract class AState
        /**
         * This Class describe a state in a any Searchable problem
         */
{
   protected Object pose;
   protected AState prevState;

   public abstract boolean legalState();
   public abstract boolean compStates(AState state);
   public Object getState() { return this.pose; }
   public AState getPrevState()
   {
      return prevState;
   }
   public void setPrevState(AState prevState)
   {
      if(prevState == null)
      {
         throw new RuntimeException("The AState that supplied is not legal (null)");
      }
      this.prevState = prevState;
   }
}
