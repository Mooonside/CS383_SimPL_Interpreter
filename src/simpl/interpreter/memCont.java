package simpl.interpreter;

public class memCont{
    public Value value;
    public boolean mark;
    public memCont(Value value) {
        this.value = value;
        mark = false;
    }
}
