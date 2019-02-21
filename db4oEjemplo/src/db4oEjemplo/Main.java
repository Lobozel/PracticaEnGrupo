
//https://drive.google.com/file/d/1Jk03lB3lKKkGH1rhE1CpA_qHWnYd6VLn/view?usp=sharing

package db4oEjemplo;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Constraint;
import com.db4o.query.Query;
import java.util.List;

/**
 *
 * @author windiurno
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ObjectContainer db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "nombre.db4o");
        //La base de datos fisica es el fichero nombre.db4o almacenado en la carpeta raiz del proyecto

        try {
            guardar(db, "99999999Z", "OdioTodoEsto", "ote@gmail.com", 200);
            //borrar(db, "00000000A");
            //consultarPonentes(db);
            //consultaSODAponentes(db);
            //consultaSODAponentesCache200(db);
            //consultaSODAponentesCacheEntre50_200(db);
            consultartPonenteNQcache200(db);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            db.close();
        }

    }

    public static void guardar(ObjectContainer db, String dni, String nombre, String email, float cache) {
        ObjectSet res = db.queryByExample(new Ponente(dni, null, null, 0));

        if (res.size() > 0) {
            System.out.println("Usuario existente.");
        } else {
            Ponente p1 = new Ponente(dni, nombre, email, cache);
            db.store(p1);
            System.out.println("Usuario guardado con exito.");
        }
    }

    public static void borrar(ObjectContainer db, String nif) {
        ObjectSet res = db.queryByExample(new Ponente(nif, null, null, 0));
        if (res.size() <= 0) {
            System.out.println("No existe el usuario.");
        } else {
            Ponente p = (Ponente) res.next();
            db.delete(p);
            System.out.println("Borrado con exito.");
        }
    }
    
    public static void actualizarEmailPonente(ObjectContainer db, String nif, String email) {
        try{
        //Primero se consulta por el nif pasado
        ObjectSet res = db.queryByExample(new Ponente(nif, null, null, 0));
        if (res.size() == 0) {
            System.out.println("Error, no existe el nif");
        } else if (res.size() > 1) {
            System.out.println("Error, nif repetido");
        } else {
            Ponente p = (Ponente) res.next(); //Se obtiene el objeto consultado en p
            p.setEmail(email);//Se cambia el email del objeto
            db.store(p);//Se almacena de nuevo el objeto ponente
        }
        //Si hay un error, devuelve Exception !!!
        //Si hay repetidos, se cambian los repetidos ?
        }catch(IllegalStateException e){
            System.out.println("Error, no existe el Nif");
        }
    }

    public static void consultarPonentes(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta
        while (res.hasNext()) {
            System.out.println(((Ponente) res.next()).getNombre());
        }
    }
    
    //nombre de los ponentes con mas 5 caracteres QE 
    public static void consultarPonentesNombreMas5Caracteres(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta

        for (int i = 0; i < res.size(); i++) {
            Ponente pResult = (Ponente) res.get(i);
            String nombre = pResult.getNombre().split(" ")[0];
            if (nombre.length() > 5) {
                System.out.println(pResult.getNombre());
            }
        }

    }

    public static void consultaSODAponentes(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        while (res.hasNext()) {
            System.out.println(((Ponente) res.next()).toString());
        }
    }

    public static void consultaSODAponentesCache200(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se añade la restriccion
        q.descend("cache").constrain(200);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        while (res.hasNext()) {
            System.out.println(((Ponente) res.next()).toString());
        }
    }
    
    public static void consultaSODAponentesCacheEntre50_200(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se crea un "camino" resticcion
        Constraint c1 = q.descend("cache").constrain(201).smaller();
        //Juntamos las restricciones/caminos
        q.descend("cache").constrain(49).greater().and(c1);
        //smaller y greater no incluyen el numero en cuestión (mayor que, no mayor o igual)
        ObjectSet res = q.execute();//Se ejecuta la consulta
        while (res.hasNext()) {
            System.out.println(((Ponente) res.next()).toString());
        }

    }
    
    public static void consultartPonenteNQcache200(ObjectContainer db) {
        List res = db.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente p) {
                return p.getCache() == 200;
            }

            //metodo abstracto
            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Error");
            }
            
        });
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i).toString());
        }
       
    }

    //Metodo para almacenar datos
    //public static void almacenarPonentes(ObjectContainer db, String dni, String nombre, String email, float cache) {
    /*Ponente p1 = new Ponente("66F", "Milaine Finister", "mf@hotmail.com", 600);
        Ponente p2 = new Ponente("22B", "Camila Nohr", "cn@gmail.com", 100);
        Ponente p3 = new Ponente("33C", "Sofia Sanchez", "ss@gmail.com", 200);
        Ponente p4 = new Ponente("44D", "Coco Muñoz", "cm@gmail.com", 90);
        //Persistir Objetos: almacenar los objetos con el metodo store()
        db.store(p1);
        db.store(p2);
        db.store(p3);
        db.store(p4);*/
 /*
        ObjectSet res = db.queryByExample(new Ponente(dni, null, null, 0));
        if (res.size() > 0) {
            System.out.println("Error, nif existente");
        } else {
            Ponente p = new Ponente(dni, nombre, email, cache);
            db.store(p);//Se almacena de nuevo el objeto ponente
        }
    }

    public static void almacenarCharla(ObjectContainer db, String titulo, int duracion, Ponente p) {
        ObjectSet res = db.queryByExample(new Charla(titulo, 0));
        if (res.size() > 0) {
            System.out.println("Error, charla existente");
        } else {
            Charla c = new Charla(titulo, duracion);
            c.setP(p);
            db.store(c);//Se almacena de nuevo el objeto ponente
        }
    }

    public static void borrarPonentePorNif(ObjectContainer db, String nif) {
        ObjectSet res = db.queryByExample(new Ponente(nif, null, null, 0));
        Ponente p = (Ponente) res.next();
        db.delete(p);
    }

    public static void consultarPonentes(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta
        mostrarConsulta(res);
    }

    public static void consultarPonentes200(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 200);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta
        mostrarConsulta(res);
    }

    public static void consultarPonentesPorNombre(ObjectContainer db, String nombre) {
        Ponente p = new Ponente(null, nombre, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta
        mostrarConsulta(res);
    }

    //Mostrar nombre ponentes que tienen correo de gmail
    public static void consultarPonentesCorreoGmail(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta

        mostrarConsulta(res);
    }//NO FUNCIONA

    //Mostrar nombre ponentes con cache > 200
    public static void consultarPonentesCacheSup200(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta
        mostrarConsulta(res);
    }//NO FUNCIONA

    public static void consultaSODAponentes(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);
    }

    public static void consultaSODAponentesCache200(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se añade la restriccion
        q.descend("cache").constrain(200);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);

    }

    public static void consultaSODAponentesCacheEntre50_200(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se crea un "camino" resticcion
        Constraint c1 = q.descend("cache").constrain(200).smaller();
        //Juntamos las restricciones/caminos
        q.descend("cache").constrain(50).greater().and(c1);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);

    }

    public static void consultaSODAponentesCache50OR200(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se crea un "camino" resticcion
        Constraint c1 = q.descend("cache").constrain(200);
        //Juntamos las restricciones/caminos
        q.descend("cache").constrain(50).or(c1);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);

    }

    //nombre de los ponentes con mas 5 caracteres QE y SODA
    public static void consultarPonentesNombreMas5Caracteres(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta

        for (int i = 0; i < res.size(); i++) {
            Ponente pResult = (Ponente) res.get(i);
            String nombre = pResult.getNombre().split(" ")[0];
            if (nombre.length() > 5) {
                System.out.println(pResult.getNombre());
            }
        }

    }

    public static void consultaSODAponentesNombreMayor5(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        ObjectSet res = q.execute();//Se ejecuta la consulta

        for (int i = 0; i < res.size(); i++) {
            Ponente pResult = (Ponente) res.get(i);
            String nombre = pResult.getNombre().split(" ")[0];
            if (nombre.length() > 5) {
                System.out.println(pResult.getNombre());
            }
        }

    }

    //correos de ponentes cache > 200
    public static void consultarCorreosPonentesCacheMayor200(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta

        for (int i = 0; i < res.size(); i++) {
            Ponente pResult = (Ponente) res.get(i);
            if (pResult.getCache() > 200) {
                System.out.println(pResult.getNombre() + "\t" + pResult.getEmail() + "\t" + pResult.getCache());
            }
        }

    }

    public static void consultaSODAcorreosPonentesCacheMayor200(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se crea un "camino" resticcion
        q.descend("cache").constrain(200).greater();
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);
    }

    //Nombre y correo cache < 500 o tengan como letra NIF F
    public static void consultarPonentesCacheMenor500YletraF(ObjectContainer db) {
        Ponente p = new Ponente(null, null, null, 0);//Prototipo de busqueda (criterios)
        ObjectSet res = db.queryByExample(p);//Realizacion de la consulta

        for (int i = 0; i < res.size(); i++) {
            Ponente pResult = (Ponente) res.get(i);
            char letra = pResult.getNif().charAt(pResult.getNif().length() - 1);
            if ((pResult.getCache() < 500) || (letra == 'F')) {
                System.out.println(pResult.getNombre() + "\t" + pResult.getEmail() + "\t" + pResult.getCache());
            }
        }

    }

    public static void consultaSODAcacheMenor500YletraF(ObjectContainer db) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Ponente.class);
        //Se crea un "camino" resticcion
        Constraint c1 = q.descend("nif").constrain("F").contains();
        //Juntamos las restricciones/caminos
        q.descend("cache").constrain(500).smaller().or(c1);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);
    }

    public static void consultartPonenteNQcache200(ObjectContainer db) {
        List res = db.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente p) {
                return p.getCache() == 200;
            }

            //metodo abstracto
            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Error");
            }
        });
        mostrarConsulta((ObjectSet) res);
    }

    public static void consultarNombrePonenteMayor5Native(ObjectContainer db) {
        List res = db.query(new com.db4o.query.Predicate() {
            public boolean match(Ponente p) {
                return (p.getNombre().split(" ")[0].length() >= 5);
            }

            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Error");
            }
        });

        mostrarConsulta((ObjectSet) res);
    }

    public static void actualizarEmailPonente(ObjectContainer db, String nif, String email) {
        //try{
        //Primero se consulta por el nif pasado
        ObjectSet res = db.queryByExample(new Ponente(nif, null, null, 0));
        if (res.size() == 0) {
            System.out.println("Error, no existe el nif");
        } else if (res.size() > 1) {
            System.out.println("Error, nif repetido");
        } else {
            Ponente p = (Ponente) res.next(); //Se obtiene el objeto consultado en p
            p.setEmail(email);//Se cambia el email del objeto
            db.store(p);//Se almacena de nuevo el objeto ponente
        }
        //Si hay un error, devuelve Exception !!!
        //Si hay repetidos, se cambian los repetidos ?
        /*}catch(IllegalStateException e){
            System.out.println("Error, no existe el Nif");
        }
    }
/*
    public static void consultaSODAdevolverNIFPonenteCharla(ObjectContainer db, String titulo, String nif) {
        Query q = db.query();//Se declara objeto query
        //Se indica la clase
        q.constrain(Charla.class);
        //Se crea un "camino" resticcion
        Constraint c1 = q.descend("titulo").constrain(titulo);
        Constraint c2 = q.descend("p").descend("nif").constrain(nif);
        ObjectSet res = q.execute();//Se ejecuta la consulta
        mostrarConsulta(res);
    }

    //Nombre de los ponentes que tiene un cache de mas de 200 e imparten la charla 1
    public static void consultaNativaDevolverNombrePonenteCache200CharlaTitulo(ObjectContainer db, String titulo, double cache) {
        List res = db.query(new com.db4o.query.Predicate() {
            public boolean match(Charla c) {
                if ((c.getTitulo().equals(titulo)) && (c.getP().getCache() <= cache)) {
                    return true;
                }else{
                    return false;
                }
            }
            @Override
            public boolean match(Object et) {
                throw new UnsupportedOperationException("Error");
            }
        });

        mostrarConsulta((ObjectSet) res);
    }

    public static void mostrarConsulta(ObjectSet result) {
        System.out.println("Recuperados: " + result.size() + " Objetos");

        while (result.hasNext()) {
            System.out.println(((Charla)(result.next())).getP().getNombre());
        }
    }*/
}
