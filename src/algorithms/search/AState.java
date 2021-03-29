package algorithms.search;

public abstract class AState
        /**
         * This Class describe a state in a any Searchable problem
         */
{
   public abstract Object getState();
   public abstract boolean legalState();
   public abstract boolean compStates(AState state);
}
