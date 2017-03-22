package simpl.typing;

public abstract class Type {

    public abstract boolean isEqualityType();
    //( S,{ a =s union q} -> [a=s] compose S, q[s/a])
    public abstract Type replace(TypeVar a, Type t);

    public abstract boolean contains(TypeVar tv);

    public abstract Substitution unify(Type t) throws TypeError;

    public static final Type INT = new IntType();
    public static final Type BOOL = new BoolType();
    public static final Type UNIT = new UnitType();
}
