package simpl.typing;

public class TypeResult {

    public Substitution s;
    public Type t;

    private TypeResult(Substitution s, Type t) {
        this.s = s;
        this.t = t;
    }

    public static TypeResult of(Type t) {
        //uses as a wrapper function 
        return TypeResult.of(Substitution.IDENTITY, t);
    }

    public static TypeResult of(Substitution s, Type t) {
       //create a new identity which is an a type t actually.
        return new TypeResult(s, t);
    }
}
