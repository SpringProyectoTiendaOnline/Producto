package tiendaOnline.Utilidades;

import org.hibernate.HibernateException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
 
/**
 * Responsable de crear un objeto sesi髇 (gestiona la conexión a BD de forma transparente
 * @author Laura
 *
 */
public class Utilidades {
	
	 
	    //Factoria de sesión para crear objeto sesión a partir de XML
	    private static SessionFactory sessionFactory;
	     
	    private static SessionFactory buildSessionFactory() {
	    	try {

				StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
						.configure("hibernate.cfg.xml").build();
				Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
				return metadata.getSessionFactoryBuilder().build();

			} catch (HibernateException he) {
				System.out.println("Session Factory creation failure");
				throw he;
			}
		
	    }
	    
	    /*
	     * Méodo est醫ico (Fachada) para crear la factoría de sesiones
	     */
	    public static SessionFactory getSessionFactory() {
	        if(sessionFactory == null) {
	        	sessionFactory = buildSessionFactory();
	        }
	        return sessionFactory;
	    }

}
