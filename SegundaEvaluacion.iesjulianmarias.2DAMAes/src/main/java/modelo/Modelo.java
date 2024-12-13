package modelo;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import org.hibernate.Transaction;

import clasesHibernate.Departamentos;
import clasesHibernate.Empleados;
import jakarta.persistence.TypedQuery;

public class Modelo {

	private static final Configuration cfg = new Configuration().configure();
	private static final SessionFactory sf = cfg.buildSessionFactory();
	
	
	public static void main(String[] args) {
		//anadirDpto("Propaganda", "Zamora");
		/*System.out.println("");
		ArrayList<Departamentos> dptos = listarDptos();
		for(Departamentos dpto: dptos) {
			System.out.println(dpto);
		}
		
		System.out.println("");
		dptos = listarDptos("Zamora");
		for(Departamentos dpto: dptos) {
			System.out.println(dpto);
		}
		
		System.out.println("");
		dptos = listarDptosNombre("Logística");
		for(Departamentos dpto: dptos) {
			System.out.println(dpto);
		}
		
		Boolean hecho = modificarDpto(44, "nuevoNombre", "nuevaLocalidad");
		
		borrarDpto(46);
		
		anadirEmpleado("María", "Sanz", "Sanz", "Ventas");
		
		listarEmpleados();

		cambiarEmpleado("Producción");
		
		 
		 */
		
		//eliminarEmpleado();
		listarEmpleados("Producción");
	}

	private static Boolean modificarDpto(int id, String nuevoNombre, String nuevaLocalidad) {
		Boolean flag = false;
		Session sesion = sf.openSession();
		Transaction t = sesion.beginTransaction();
		Departamentos dpto = sesion.get(Departamentos.class, id);
		if (nuevoNombre != null) {
			dpto.setDnombre(nuevoNombre);
			flag = true;
		}
		
		if (nuevaLocalidad != null) {
			dpto.setLoc(nuevaLocalidad);
			flag = true;
		}
		
		sesion.merge(dpto);
		sesion.close();
		return flag;
	}

	private static ArrayList<Departamentos> listarDptos() {
		Session sesion = sf.openSession();
		String hql = "from Departamentos";
		TypedQuery<Departamentos> consulta = sesion.createQuery(hql, Departamentos.class);
		ArrayList<Departamentos> dptos = (ArrayList<Departamentos>) consulta.getResultList();	
		sesion.close();
		return dptos;
	}
	
	private static ArrayList<Departamentos> listarDptos(String localidad) {
		Session sesion = sf.openSession();
		String hql = "from Departamentos where loc='" + localidad + "'";
		TypedQuery<Departamentos> consulta = sesion.createQuery(hql, Departamentos.class);
		ArrayList<Departamentos> dptos = (ArrayList<Departamentos>) consulta.getResultList();	
		sesion.close();
		return dptos;
	}
	
	private static Departamentos listarDptos(int dptoId) {
		Session sesion = sf.openSession();
		String hql = "from Departamentos where deptNo='" + dptoId + "'";
		TypedQuery<Departamentos> consulta = sesion.createQuery(hql, Departamentos.class);
		sesion.close();
		return consulta.getSingleResult();
	}
	
	private static ArrayList<Departamentos> listarDptosNombre(String nombre) {
		Session sesion = sf.openSession();
		String hql = "from Departamentos where dnombre='" + nombre + "'";
		TypedQuery<Departamentos> consulta = sesion.createQuery(hql, Departamentos.class);
		ArrayList<Departamentos> dptos = (ArrayList<Departamentos>) consulta.getResultList();	
		sesion.close();
		return dptos;
	}

	private static void anadirDpto(String dptoNombre, String dptoLocalidad) {
		Session sesion = sf.openSession();
		Transaction t = sesion.beginTransaction();
		Departamentos dpto = new Departamentos(dptoNombre, dptoLocalidad, null);
		sesion.persist(dpto);
		
		t.commit();
		sesion.close();
	}
	
	private static void borrarDpto(int dptoId) {
		Scanner in = new Scanner(System.in);
		Session sesion = sf.openSession();
		Transaction t = sesion.beginTransaction();
		Departamentos dpto = sesion.get(Departamentos.class, dptoId);
		System.out.println("¿Desea eliminar el departamento " + dpto.getDnombre() + " en " + dpto.getLoc() + "?");
		if (in.next().toLowerCase().equals("s")) {
			sesion.remove(dpto);
			System.out.println("Departamento eliminado con éxito");
		}
		
		t.commit();
		sesion.close();
		in.close();
	}
	
	private static void borrarDpto(String dptoNombre) {
		Scanner in = new Scanner(System.in);
		Session sesion = sf.openSession();
		Transaction t = sesion.beginTransaction();
		String hql = "from Departamentos where dnombre='" + dptoNombre +"'";
		TypedQuery<Departamentos> consulta = sesion.createQuery(hql, Departamentos.class);
		ArrayList<Departamentos> dptos = (ArrayList<Departamentos>) consulta.getResultList();
		System.out.println("Hay " + dptos.size() +  " departamentos " + dptoNombre);
		for (Departamentos dpto: dptos) {
			System.out.println("¿Desea eliminar el departamento" + dpto.getDnombre() + "en" + dpto.getLoc() + "?");
			if (in.next().toLowerCase().equals("s")) {
				sesion.remove(dpto);
				System.out.println("Departamento eliminado con éxito");
			}
		}
		
		t.commit();
		sesion.close();
		in.close();
	}
	
	/**
     * dpto puede corresponderse con varios. Preguntar cuál mostrando los dptos
     * con ese nombre dpto no existe. Confirmar que es correcto, si lo es, lo
     * añadimos preguntando la localidad
     *
     * Añadir el empleado al dpto.
     *
     *
     * @param nombre
     * @param apellido1
     * @param apellido2
     * @param dptoName
     * @return
     */
    private static boolean anadirEmpleado(String nombre, String apellido1, String apellido2, String dptoName) {
        Scanner sn = new Scanner(System.in);
        ArrayList<Departamentos> dptos = listarDptosNombre(dptoName);
        Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();

        if (dptos.isEmpty()) {
            System.out.println("El departamento " + dptoName + " no existe");
            System.out.println("¿Desea incluir el departamento " + dptoName + " en la base de datos? (S/N): ");
            if (sn.next().toLowerCase().equals("s")) {
                System.out.println("Introduce la localidad del nuevo departamento:");
                String localidad = sn.next();

                Departamentos nuevoDpto = new Departamentos(dptoName, localidad, null);
                Integer idGenerado = (Integer) sesion.save(nuevoDpto);
                //Hacer comprobación de que el empleado no existe ya en la db. Posibilidad de commit

                Empleados nuevoEmpleado = new Empleados(sesion.get(Departamentos.class, idGenerado), nombre, apellido1, apellido2);
                sesion.persist(nuevoEmpleado);

                t.commit();
                System.out.println("Empleado añadido exitosamente al nuevo departamento");
                return true;
            } else {
                System.out.println("Operación cancelada. No se añadió el empleado");
            }
        } else if (dptos.size() == 1) {
            Departamentos dpto = dptos.get(0);
            System.out.println("Añadiendo empleado al departamento: " + dpto.getDnombre() + " en " + dpto.getLoc());

            Empleados nuevoEmpleado = new Empleados(dpto, nombre, apellido1, apellido2);
            sesion.persist(nuevoEmpleado);

            t.commit();
            System.out.println("Empleado añadido exitosamente al departamento existente");
            return true;
        } else {
            System.out.println("Existen varios departamentos con el nombre " + dptoName + ":");
            for (int i = 0; i < dptos.size(); i++) {
                System.out.println((i + 1) + ". " + dptos.get(i));
            }
            System.out.println("Elija el número del departamento al que desea añadir el empleado:");
            int opcion = sn.nextInt();

            if (opcion > 0 && opcion <= dptos.size()) {
                Departamentos dptoSeleccionado = dptos.get(opcion - 1);
                Empleados nuevoEmpleado = new Empleados(dptoSeleccionado, nombre, apellido1, apellido2);
                sesion.persist(nuevoEmpleado);

                t.commit();
                System.out.println("Empleado añadido exitosamente al departamento seleccionado");
                return true;
            } else {
                System.out.println("Opción no válida");
            }
        }

        sesion.close();

        return false;
    }
    
    //Cambiar empleado de departamento
    //Eliminar empleado. Comprobar si el dpto no tiene más empleados, si es así se da la opción de borrar el dpto
    //Listar Empleados con nombre de departamento. Sobrecargar método para que al pasar nombre de dpto se muestren empleados del mismo
    
    private static void listarEmpleados() {
    	Session sesion = sf.openSession();
		String hql = "from Empleados";
		
		TypedQuery<Empleados> consulta = sesion.createQuery(hql, Empleados.class);
		ArrayList<Empleados> empleados = (ArrayList<Empleados>) consulta.getResultList();	
		
		for (Empleados empleado : empleados) {
			System.out.println(empleado);
		}
		
		sesion.close();
    }
    
    private static void listarEmpleados(String dptoNombre) {
    	Session sesion = sf.openSession();

        ArrayList<Departamentos> departamentos = listarDptosNombre(dptoNombre);
        if (departamentos.isEmpty()) {
            System.out.println("El departamento " + dptoNombre + " no existe.");
        } else if (departamentos.size() > 1) {
            System.out.println("Existen varios departamentos " + dptoNombre);
        } else {
            Departamentos departamento = departamentos.get(0);
            String hql = "from Empleados where departamentos.deptNo = :deptNo";

            TypedQuery<Empleados> consulta = sesion.createQuery(hql, Empleados.class);
            consulta.setParameter("deptNo", departamento.getDeptNo());

            ArrayList<Empleados> empleados = (ArrayList<Empleados>) consulta.getResultList();
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados en el departamento " + dptoNombre + "");
            } else {
                for (Empleados empleado : empleados) {
                    System.out.println(empleado);
                }
            }
        }

        sesion.close();
    }
    
    private static void cambiarEmpleado(String nuevoDpto) {
    	Scanner in = new Scanner(System.in);
    	Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
    	
    	listarEmpleados();
    	
    	System.out.println("Introduce el id del empleado que quieras cambiar al departamento " + nuevoDpto + ":");
    	int empleadoId = in.nextInt();
    	
    	Empleados empleado = sesion.get(Empleados.class, empleadoId);
    	
    	if (empleado == null) {
            System.out.println("El empleado con ID " + empleadoId + " no existe.");
    	} else {
    		ArrayList<Departamentos> departamentos = listarDptosNombre(nuevoDpto);
            if (departamentos.isEmpty()) {
                System.out.println("El departamento con nombre '" + nuevoDpto + "' no existe.");
            } else if (departamentos.size() > 1) {
                System.out.println("Existen varios departamentos con el nombre '" + nuevoDpto + "'. No se puede proceder automáticamente.");
            } else {
            	Departamentos nuevoDepartamento = departamentos.get(0);
                empleado.setDepartamentos(nuevoDepartamento);
                sesion.merge(empleado);
                t.commit();
            }
    	}
    	sesion.close();
    }
    
    private static void eliminarEmpleado() {
    	Scanner in = new Scanner(System.in);
    	Session sesion = sf.openSession();
        Transaction t = sesion.beginTransaction();
        
        listarEmpleados();
    	
    	System.out.println("Introduce el id del empleado que quieras eliminar");
    	int empleadoId = in.nextInt();
    	
    	Empleados empleado = sesion.get(Empleados.class, empleadoId);
        if (empleado == null) {
        	System.out.println("El empleado con ID " + empleadoId + " no existe");
        }
        
        Departamentos departamento = empleado.getDepartamentos();

        sesion.remove(empleado);
        System.out.println("Empleado eliminado con éxito");

        String hql = "from Empleados where departamentos.deptNo = :deptNo";
        TypedQuery<Empleados> consulta = sesion.createQuery(hql, Empleados.class);
        consulta.setParameter("deptNo", departamento.getDeptNo());

        ArrayList<Empleados> empleadosRestantes = (ArrayList<Empleados>) consulta.getResultList();
        if (empleadosRestantes.isEmpty()) {
            System.out.println("El departamento '" + departamento.getDnombre() + "' no tiene más empleados");
            System.out.println("¿Desea eliminar también el departamento? (S/N): ");
            Scanner scanner = new Scanner(System.in);
            String respuesta = scanner.nextLine();

            if (respuesta.equalsIgnoreCase("S")) {
                sesion.remove(departamento);
                System.out.println("Departamento eliminado con éxito.");
            }
        }

        t.commit();
        sesion.close();
    }
}
