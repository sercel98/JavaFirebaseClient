package controller;

import model.Dulce;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import db.Conexion;
import sun.rmi.server.InactiveGroupException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class ServicioDulce {

    
    private ServicioDulce(){
        conexion = new Conexion();
    }

    private static ServicioDulce servicio;

    private Conexion conexion;

    public static  ServicioDulce getServicioDulce(){

        if(servicio==null){
           servicio = new ServicioDulce();
        }
           return servicio;
         
    }

    //funciona tambien para editar
    public void adicionarDulce(int idProveedor, String codigo, String nombre, double precio, String marca){


        Map<String, Object> docData = new HashMap<>();
        docData.put("identificacion", codigo);
        docData.put("nombre", nombre);
        docData.put("precio", precio);
        docData.put("marca",marca);
        ApiFuture<WriteResult> future = conexion.getDb().collection("Proveedores").document(idProveedor+"").collection("Dulces").document(codigo).set(docData);
    }

    public Dulce buscarDulce(int idProveedor, String codigo){
        ApiFuture<QuerySnapshot> query = conexion.getDb().collection("Proveedores").document(idProveedor+"").collection("Dulces").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {

                if(document.getId().equals(codigo)){

                    Dulce dulce = document.toObject(Dulce.class);
                    return dulce;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void eliminarDulce(int idProveedor, String codigo){
        ApiFuture<WriteResult> writeResult = conexion.getDb().collection("Proveedores").document(idProveedor+"").collection("Dulces").document(codigo).delete();
    }


    public ArrayList<Dulce> listarDulcesDeProveedor(int idProveedor){

        ArrayList dulces = new ArrayList();

        ApiFuture<QuerySnapshot> query = conexion.getDb().collection("Proveedores").document(idProveedor+"").collection("Dulces").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();


            for (QueryDocumentSnapshot document : documents) {

                Dulce dulce = document.toObject(Dulce.class);
                dulces.add(dulce);
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return dulces;
    }



    public ArrayList<Proveedor> listarProveedores(){

        ArrayList proveedores = new ArrayList();

        ApiFuture<QuerySnapshot> query = conexion.getDb().collection("Proveedores").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {

                    Proveedor proveedor = document.toObject(Proveedor.class);
                proveedores.add(proveedor);
                }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return proveedores;
    }




    //funciona tambien para editar
    public void adicionarProveedor(int id, String nombre, String direccion, int telefono){

        Map<String, Object> docData = new HashMap<>();
        docData.put("id", id);
        docData.put("nombre", nombre);
        docData.put("direccion", direccion);
        docData.put("telefono",telefono);
        ApiFuture<WriteResult> future = conexion.getDb().collection("Proveedores").document(id+"").set(docData);
        try {
            System.out.println("Update time : " + future.get().getUpdateTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public Proveedor buscarProveedor(int id){
        ApiFuture<QuerySnapshot> query = conexion.getDb().collection("Proveedores").get();
        QuerySnapshot querySnapshot = null;
        try {
            querySnapshot = query.get();

            List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            for (QueryDocumentSnapshot document : documents) {

                if(document.getId().equals(id+"")){

                    Proveedor proveedor = document.toObject(Proveedor.class);
                    return proveedor;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void eliminarProveedor(int id){
        ApiFuture<WriteResult> writeResult = conexion.getDb().collection("Proveedores").document(id+"").delete();
    }

}
