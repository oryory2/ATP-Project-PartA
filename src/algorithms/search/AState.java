package algorithms.search;

public abstract class AState
        /**
         * This Class describe a state in a any Searchable problem
         */
{
   protected AState prevState;

   public void setPrevState(AState prevState)
   {
      this.prevState = prevState;
   }

   public AState getPrevState()
   {
      return prevState;
   }

   public abstract Object getState();
   public abstract boolean legalState();
   public abstract boolean compStates(AState state);

}
