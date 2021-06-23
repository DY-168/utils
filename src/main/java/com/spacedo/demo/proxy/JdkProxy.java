package com.spacedo.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.lang.reflect.UndeclaredThrowableException;

public final class JdkProxy extends Proxy implements IUserDao {
        private static Method m1;
        private static Method m4;
        private static Method m9;
        private static Method m3;
        private static Method m2;
        private static Method m7;
        private static Method m6;
        private static Method m8;
        private static Method m10;
        private static Method m0;
        private static Method m5;

        public JdkProxy(InvocationHandler var1) {
            super(var1);
        }

        public final void test() {
            try {
                super.h.invoke(this, m4, (Object[])null);
            } catch (RuntimeException | Error var2) {
                throw var2;
            } catch (Throwable var3) {
                throw new UndeclaredThrowableException(var3);
            }
        }

        public final void save() {
            try {
                super.h.invoke(this, m3, (Object[])null);
            } catch (RuntimeException | Error var2) {
                throw var2;
            } catch (Throwable var3) {
                throw new UndeclaredThrowableException(var3);
            }
        }

        static {
            try {
                m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
                m4 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("test");
                m9 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("notify");
                m3 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("save");
                m2 = Class.forName("java.lang.Object").getMethod("toString");
                m7 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("wait", Long.TYPE);
                m6 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("wait", Long.TYPE, Integer.TYPE);
                m8 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("getClass");
                m10 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("notifyAll");
                m0 = Class.forName("java.lang.Object").getMethod("hashCode");
                m5 = Class.forName("com.spacedo.demo.proxy.UserDao").getMethod("wait");
            } catch (NoSuchMethodException var2) {
                throw new NoSuchMethodError(var2.getMessage());
            } catch (ClassNotFoundException var3) {
                throw new NoClassDefFoundError(var3.getMessage());
            }
        }


}
