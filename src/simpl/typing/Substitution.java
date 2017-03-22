package simpl.typing;

import simpl.parser.Symbol;

public abstract class Substitution {
    // used to construct and de-construct Substitution
    public abstract Type apply(Type t);

    
    //identity.apply(t) returns t
    private static final class Identity extends Substitution {
        public Type apply(Type t) {
            return t;
        }
        
        @Override
        public String toString() {
            return "";
        }
    }

    //Replace.apply(t) uses the relation info: TypeVar a is t to do substitution in b
    private static final class Replace extends Substitution {
        private TypeVar a;
        private Type t;

        public Replace(TypeVar a, Type t) {
            this.a = a;
            this.t = t;
        }

        public Type apply(Type b) {
            //replace occurs of a in b with t
            return b.replace(a, t);
        }
        
        @Override
        public String toString() {
            return "replace "+a+" with "+t+'\n';
        }
    }
    
    //the general substitution form , Compose.apply(t) using the upper two class to substitute type
    private static final class Compose extends Substitution {
        private Substitution f, g;

        public Compose(Substitution f, Substitution g) {
            this.f = f;
            this.g = g;
        }
        
        public Type apply(Type t) {
            return f.apply(g.apply(t));
        }
        
        @Override
        public String toString() {
            return f.toString()+g.toString();
        }
    }

    public static final Substitution IDENTITY = new Identity();

    public static Substitution of(TypeVar a, Type t) {
        return new Replace(a, t);
    }
    
    // create a new substitution of this and inner
    public Substitution compose(Substitution inner) {
        return new Compose(this, inner);
    }

    public TypeEnv compose(final TypeEnv E) {
        return new TypeEnv() {
            public Type get(Symbol x) {
                return apply(E.get(x));
            }
        };
    }
}
