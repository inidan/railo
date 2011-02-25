package railo.runtime.util;

import railo.commons.lang.StringUtil;
import railo.runtime.Component;
import railo.runtime.Mapping;
import railo.runtime.config.Config;
import railo.runtime.config.ConfigImpl;
import railo.runtime.exp.ApplicationException;
import railo.runtime.listener.ApplicationContextUtil;
import railo.runtime.net.s3.Properties;
import railo.runtime.orm.ORMConfiguration;
import railo.runtime.type.Scope;
import railo.runtime.type.dt.TimeSpan;

/**
 * 
 */
public class ApplicationContextImpl implements ApplicationContextPro {
   

	private static final long serialVersionUID = 940663152793150953L;


	

	private String name;
    private boolean setClientCookies;
    private boolean setDomainCookies;
    private boolean setSessionManagement;
    private boolean setClientManagement;
    private TimeSpan sessionTimeout=null; 
	private TimeSpan clientTimeout;
    private TimeSpan applicationTimeout=null;
    private int loginStorage=-1;
    private String clientstorage;
    private String sessionstorage;
	private int scriptProtect;
	private Mapping[] mappings;
	private Mapping[] ctmappings;
	private boolean secureJson;
	private String secureJsonPrefix="//";
	private boolean isDefault;
	private String defaultDataSource;
	private boolean ormEnabled;
	private String ormdatasource;
	private ORMConfiguration config;
	private Component component;
	private Properties s3;
	private String applicationtoken;
	private String cookiedomain;
	private int idletimeout=1800;


	private int localMode;
	private short sessionType;
    private boolean sessionCluster;
    private boolean clientCluster;

    
    /**
     * constructor of the class
     * @param config
     */
    public ApplicationContextImpl(Config config, Component component,boolean isDefault) {
    	ConfigImpl ci = ((ConfigImpl)config);
    	this.component=component;
    	setClientCookies=config.isClientCookies();
        setDomainCookies=config.isDomainCookies();
        setSessionManagement=config.isSessionManagement();
        setClientManagement=config.isClientManagement();
        sessionTimeout=config.getSessionTimeout();
        clientTimeout=ci.getClientTimeout();
        applicationTimeout=config.getApplicationTimeout();
        loginStorage=Scope.SCOPE_COOKIE;
        scriptProtect=config.getScriptProtect();
        this.isDefault=isDefault;
        this.defaultDataSource=ci.getDefaultDataSource();
        this.localMode=config.getLocalMode();
        this.sessionType=config.getSessionType();
        this.sessionCluster=ci.getSessionCluster();
        this.clientCluster=ci.getClientCluster();
    }


	public ApplicationContextImpl(Config config, boolean isDefault) {
    	this(config,null,isDefault);
    }
    
    /**
     * Constructor of the class, only used by duplicate method
     */
    private ApplicationContextImpl() {
    	
    }
    

	public ApplicationContext duplicate() {
		ApplicationContextImpl dbl = new ApplicationContextImpl();
		dbl.name=name;
		dbl.setClientCookies=setClientCookies;
		dbl.setDomainCookies=setDomainCookies;
		dbl.setSessionManagement=setSessionManagement;
		dbl.setClientManagement=setClientManagement;
		dbl.sessionTimeout=sessionTimeout;
		dbl.clientTimeout=clientTimeout;
		dbl.applicationTimeout=applicationTimeout;
		dbl.loginStorage=loginStorage;
		dbl.clientstorage=clientstorage;
		dbl.sessionstorage=sessionstorage;
		dbl.scriptProtect=scriptProtect;
		dbl.mappings=mappings;
		dbl.ctmappings=ctmappings;
		dbl.secureJson=secureJson;
		dbl.secureJsonPrefix=secureJsonPrefix;
		dbl.isDefault=isDefault;
		dbl.defaultDataSource=defaultDataSource;
		dbl.applicationtoken=applicationtoken;
		dbl.cookiedomain=cookiedomain;
		dbl.idletimeout=idletimeout;
		dbl.localMode=localMode;
		dbl.sessionType=sessionType;
		
		dbl.ormEnabled=ormEnabled;
		dbl.config=config;
		dbl.ormdatasource=ormdatasource;
		dbl.sessionCluster=sessionCluster;
		dbl.clientCluster=clientCluster;
		
		return dbl;
	}
    
    
    /**
     * @see railo.runtime.util.IApplicationContext#getApplicationTimeout()
     */
    public TimeSpan getApplicationTimeout() {
        return applicationTimeout;
    }
    /**
     * @param applicationTimeout The applicationTimeout to set.
     */
    public void setApplicationTimeout(TimeSpan applicationTimeout) {
        this.applicationTimeout = applicationTimeout;
    }
    /**
     * @see railo.runtime.util.IApplicationContext#getLoginStorage()
     */
    public int getLoginStorage() {
        return loginStorage;
    }
    /**
     * @param loginStorage The loginStorage to set.
     */
    public void setLoginStorage(int loginStorage) {
        this.loginStorage = loginStorage;
    }
    
    public void setLoginStorage(String strLoginStorage) throws ApplicationException {
    	strLoginStorage=strLoginStorage.toLowerCase().trim();
        if(strLoginStorage.equals("session"))setLoginStorage(Scope.SCOPE_SESSION);
        else if(strLoginStorage.equals("cookie"))setLoginStorage(Scope.SCOPE_COOKIE);
        else throw new ApplicationException("invalid loginStorage definition ["+strLoginStorage+"], valid values are [session,cookie]");
    }
    
    
    
    /**
     * @see railo.runtime.util.IApplicationContext#getFullName()
     */
    public String getName() {
        return name;
    }
    /**
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @see railo.runtime.util.IApplicationContext#getSessionTimeout()
     */
    public TimeSpan getSessionTimeout() {
        return sessionTimeout;
    }
    
    /**
     * @param sessionTimeout The sessionTimeout to set.
     */
    public void setSessionTimeout(TimeSpan sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }


    public TimeSpan getClientTimeout() {
        return clientTimeout;
    }
    
    /**
     * @param sessionTimeout The sessionTimeout to set.
     */
    public void setClientTimeout(TimeSpan clientTimeout) {
        this.clientTimeout = clientTimeout;
    }
    
    /**
     * @see railo.runtime.util.IApplicationContext#isSetClientCookies()
     */
    public boolean isSetClientCookies() {
        return setClientCookies;
    }
    /**
     * @param setClientCookies The setClientCookies to set.
     */
    public void setSetClientCookies(boolean setClientCookies) {
        this.setClientCookies = setClientCookies;
    }
    /**
     * @see railo.runtime.util.IApplicationContext#isSetClientManagement()
     */
    public boolean isSetClientManagement() {
        return setClientManagement;
    }
    /**
     * @param setClientManagement The setClientManagement to set.
     */
    public void setSetClientManagement(boolean setClientManagement) {
        this.setClientManagement = setClientManagement;
    }
    /**
     * @see railo.runtime.util.IApplicationContext#isSetDomainCookies()
     */
    public boolean isSetDomainCookies() {
        return setDomainCookies;
    }
    /**
     * @param setDomainCookies The setDomainCookies to set.
     */
    public void setSetDomainCookies(boolean setDomainCookies) {
        this.setDomainCookies = setDomainCookies;
    }
    /**
     * @see railo.runtime.util.IApplicationContext#isSetSessionManagement()
     */
    public boolean isSetSessionManagement() {
        return setSessionManagement;
    }
    /**
     * @param setSessionManagement The setSessionManagement to set.
     */
    public void setSetSessionManagement(boolean setSessionManagement) {
        this.setSessionManagement = setSessionManagement;
    }
    /**
     * @see railo.runtime.util.IApplicationContext#getClientstorage()
     */
    public String getClientstorage() {
        return clientstorage;
    }
    public String getSessionstorage() {
        return sessionstorage;
    }
    /**
     * @param clientstorage The clientstorage to set.
     */
    public void setClientstorage(String clientstorage) {
        this.clientstorage = clientstorage;
    }
    public void setSessionstorage(String sessionstorage) {
        this.sessionstorage = sessionstorage;
    }

    /**
     * @see railo.runtime.util.IApplicationContext#hasName()
     */
    public boolean hasName() {
        return name!=null;
    }

    /**
     * @param scriptProtect The scriptProtect to set.
     */
    public void setScriptProtect(String strScriptProtect) {
		this.scriptProtect=ApplicationContextUtil.translateScriptProtect(strScriptProtect);
	}
    
    /**
     * @param scriptProtect The scriptProtect to set.
     */
    public void setScriptProtect(int scriptProtect) {
		this.scriptProtect=scriptProtect;
	}

	/**
	 * @see railo.runtime.util.ApplicationContext#getScriptProtect()
	 */
	public int getScriptProtect() {
		//if(isDefault)print.err("get:"+scriptProtect);
		return scriptProtect;
	}

	


	public void setMappings(Mapping[] mappings) {
		if(mappings.length>0)this.mappings=mappings;
	}

	/**
	 * @return the mappings
	 */
	public Mapping[] getMappings() {
		return mappings;
	}

	public void setCustomTagMappings(Mapping[] ctmappings) {
		this.ctmappings=ctmappings;
	}

	public Mapping[] getCustomTagMappings() {
		return ctmappings;
	}

	public void setSecureJson(boolean secureJson) {
		this.secureJson=secureJson;
	}
	
	public void setSecureJsonPrefix(String secureJsonPrefix) {
		this.secureJsonPrefix=secureJsonPrefix;
	}

	/**
	 * @return the secureJson
	 */
	public boolean getSecureJson() {
		return secureJson;
	}

	/**
	 * @return the secureJsonPrefix
	 */
	public String getSecureJsonPrefix() {
		return secureJsonPrefix;
	}

	 /**
	 * @return the defaultDataSource
	 */
	public String getDefaultDataSource() {
		return defaultDataSource;
	}

	/**
	 * @param defaultDataSource the defaultDataSource to set
	 */
	public void setDefaultDataSource(String defaultDataSource) {
		this.defaultDataSource = defaultDataSource;
	}
	
	public void setORMDataSource(String ormdatasource) {
		this.ormdatasource = ormdatasource;
	}

	public boolean isORMEnabled() {
		return ormEnabled;
	}

	public String getORMDatasource() {
		return ormdatasource;
	}

	public ORMConfiguration getORMConfiguration() {
		return config;
	}
	public void setORMConfiguration(ORMConfiguration config) {
		//if(config==null)print.dumpStack();
		this.config= config;
	}

	public void setORMEnabled(boolean ormEnabled) {
		this.ormEnabled=ormEnabled;
	}

	/**
	 * @see railo.runtime.util.ApplicationContextPro#getComponent()
	 */
	public Component getComponent() {
		return component;
	}


	public void setS3(String accessKeyId, String awsSecretKey, String defaultLocation, String host) {
		this.s3=new Properties();
		if(!StringUtil.isEmpty(accessKeyId))s3.setAccessKeyId(accessKeyId);
		if(!StringUtil.isEmpty(awsSecretKey))s3.setSecretAccessKey(awsSecretKey);
		if(!StringUtil.isEmpty(defaultLocation))s3.setDefaultLocation(defaultLocation);
		if(!StringUtil.isEmpty(host))s3.setHost(host);
	}


	/**
	 * @return the s3
	 */
	public Properties getS3() {
		if(s3==null) s3=new Properties();
		return s3;
	}


	public void setSecuritySettings(String applicationtoken, String cookiedomain, int idletimeout) {
		this.applicationtoken=applicationtoken;
		this.cookiedomain=cookiedomain;
		this.idletimeout=idletimeout;
		
	}
	public String getSecurityApplicationToken() {
		if(StringUtil.isEmpty(applicationtoken,true)) return getName();
		return applicationtoken;
	}
	public String getSecurityCookieDomain() {
		if(StringUtil.isEmpty(applicationtoken,true)) return null;
		return cookiedomain;
	}
	public int getSecurityIdleTimeout() {
		if(idletimeout<1) return 1800;
		return idletimeout;
	}


	/**
	 * @return the localMode
	 */
	public int getLocalMode() {
		return localMode;
	}


	/**
	 * @param localMode the localMode to set
	 */
	public void setLocalMode(int localMode) {
		this.localMode = localMode;
	}



    /**
	 * @return the sessionType
	 */
	public short getSessionType() {
		return sessionType;
	}

    /**
	 * @return the sessionType
	 */
	public void setSessionType(short sessionType) {
		this.sessionType= sessionType;
	}


	/**
	 * @return the sessionCluster
	 */
	public boolean getSessionCluster() {
		return sessionCluster;
	}


	/**
	 * @param sessionCluster the sessionCluster to set
	 */
	public void setSessionCluster(boolean sessionCluster) {
		this.sessionCluster = sessionCluster;
	}


	/**
	 * @return the clientCluster
	 */
	public boolean getClientCluster() {
		return clientCluster;
	}


	/**
	 * @param clientCluster the clientCluster to set
	 */
	public void setClientCluster(boolean clientCluster) {
		this.clientCluster = clientCluster;
	}

	
}