package Run;

import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.SmartphoneRemote;
import dao.UserRemote;
import entities.Smartphone;
import entities.User;

public class Test {

	public static UserRemote UserLookup() throws NamingException {
		Hashtable<Object, Object> conf = new Hashtable<Object, Object>();
		conf.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		conf.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(conf);
		String appName="";
		String moduleName="GestionUsers";
		String beanName="USR";
		String remoteInterface=UserRemote.class.getName();
		
		String name= "ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
		return (UserRemote) context.lookup("ejb:/GestionUsers/UserImpl!dao.UserRemote");
	}

	public static SmartphoneRemote SmartphoneLookup() throws NamingException {
		Hashtable<Object, Object> conf = new Hashtable<Object, Object>();
		conf.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		conf.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(conf);
		String appName="";
		String moduleName="GestionUsers";
		String beanName="SMARTPHONES";
		String remoteInterface=UserRemote.class.getName();
		
		String name= "ejb:"+appName+"/"+moduleName+"/"+beanName+"!"+remoteInterface;
		return (SmartphoneRemote) context.lookup("ejb:/GestionUsers/SmartphoneImpl!dao.SmartphoneRemote");
		
		
	}

	
	
	public static void main(String[] args) {
		try {
			UserRemote ur = UserLookup();
			User u = new User("nom","prenom","email","00000",new Date());
			ur.Create(u);
			SmartphoneRemote sr = SmartphoneLookup();
			Smartphone s = new Smartphone("new");
			User uuu = ur.findByEmail("00000");
			System.out.println("this is the user email "  + uuu.getEmail());
			s.setUser(uuu);
         	sr.Create(s);
         	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
