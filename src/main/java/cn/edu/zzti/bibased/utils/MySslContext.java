package cn.edu.zzti.bibased.utils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLContextSpi;
import java.security.Provider;

public class MySslContext extends SSLContext {
    protected MySslContext(SSLContextSpi sslContextSpi, Provider provider, String s) {
        super(sslContextSpi, provider, s);
    }
}
