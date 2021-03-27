package algorithms.search;
import algorithms.mazeGenerators.Position;

public class MazeState extends AState{
    private Position m_position;

    public MazeState(Position p){
        this.m_position = p;
    }

    public Object getState() {
        return m_position;
    }
}
