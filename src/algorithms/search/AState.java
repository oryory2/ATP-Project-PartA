package algorithms.search;

public abstract class AState /** This Class describe a state in a any Searchable problem */
{
   protected Object pose;
   protected AState prevState;

    /**
    * Returns whether a particular state is possible under the conditions of the problem
    * will return true if its valid and false if not
    * @return whether the state is valid or not
    */
   public abstract boolean legalState();

    /**
    * Returns whether other state is equal to this one
    * Compares between states
    * Each state class will write differently the way it compares other states towards it
    * @param state state we want to compare to
    * @return equal or not
    */
   public abstract boolean compStates(AState state);

    /**
    * Returns the pose field of the state
    * Each state class will define differently the state Object
    * @return pose field
    */
   public Object getState() { return this.pose; }

    /**
    * Returns the state from which we have reached the current state
    * during a particular SearchingAlgorithm run
    * @return prevState field
    */
   public AState getPrevState()
   {
      return prevState;
   }

    /**
    * Sets the previous state field to a certain state
    * While solving When we discover a new state we will update
    * its prevState field to be the state from which we discovered the new state
    * @param prevState the state we want to set as prev
    */
   public void setPrevState(AState prevState)
   {
      if(prevState == null)
      {
         throw new RuntimeException("The AState that supplied is not legal (null)");
      }
      this.prevState = prevState;
   }
}
