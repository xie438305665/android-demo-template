package com.android.library.bridge.util;

/**
 * @author xcl
 * @create 2019-05-13
 */
public class Safe {
    private Safe() {
    }

    public interface Function1<A> {
        void action(A it);
    }

    public interface Function2<A, B> {
        void action(A a, B b);
    }

    public interface Function3<A, B, C> {
        void action(A a, B b, C c);
    }

    public static <A> void notNull(A o, Function1<A> function1) {
        if (UIUtils.checkNotNull(o)) {
            function1.action(o);
        }
    }

    public static <A, B> void notNull(A a, B b, Function2<A, B> function2) {
        if (UIUtils.checkNotNull(a) && UIUtils.checkNotNull(b)) {
            function2.action(a, b);
        }
    }

    public static <A, B, C> void notNull(A a, B b, C c, Function3<A, B, C> function3) {
        if (UIUtils.checkNotNull(a) && UIUtils.checkNotNull(b) && UIUtils.checkNotNull(c)) {
            function3.action(a, b, c);
        }
    }

}
