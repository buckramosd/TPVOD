
package entidades;

import java.util.ArrayList;


public class Usuarios {
    private ArrayList<Usuario> listaUsuarios;

    public Usuarios() {
        listaUsuarios = new ArrayList();
    }
    
    public void addUsuario(Usuario user){
        listaUsuarios.add(user);
    }
    
    public Usuario getUsuario(String nombreUsuario) {
        Usuario encontrado = null;
        
        for(int i = 0; i < listaUsuarios.size(); i++) {
            // Guardo en un string el nombre de los usuarios de la lista
            String compara = listaUsuarios.get(i).getNombreUsuario();
            if(compara.equals(nombreUsuario)) {
                encontrado = listaUsuarios.get(i);
            }
        }
        return encontrado;
    }
    
    public boolean removeUsuario(String nombreUsuario) {
        return listaUsuarios.remove(this.getUsuario(nombreUsuario));
    }
    
    public int size() {
        return listaUsuarios.size();
    }
}
