package controllers;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import model.Usuario;
import model.Password;
import model.Controlador;
import java.util.Map;
import java.util.List;
import views.html.*;
import static play.libs.Json.toJson;

public class Application extends Controller{
    
    Controlador ctrl= new Controlador();
    public boolean isLog=false;
    public boolean tryAgain=false;
    public String nombreUsuario="";
    
    public Result index() {
        return ok(index.render("Your new application is ready.",nombreUsuario, isLog));
    }
    
    public Result login() {
        System.out.println("Login");
        return ok(login.render(isLog, tryAgain));
    }
    
    public Result passwordsMain(){
                
        System.out.println("cargando info");
        ctrl.cargarInformacion("descriptor.txt");
        return ok(passwordsMain.render(nombreUsuario, isLog));
    }
    
    public Result postPassword(){
        Password formPass= Form.form(Password.class).bindFromRequest().get();
        String plataforma= formPass.getPlataforma();
        String cuenta= formPass.getCuenta();
        String password= formPass.getPassword();
        ctrl.agregarNuevoPassword(plataforma, cuenta, password);
        return ok(passwordsMain.render(nombreUsuario, isLog));
    }
    
    public Result getBusqueda(){
        //Password formPass= Form.form(Password.class).bindFromRequest().get();
        //System.out.println("plataforma a buscar: " + formPass.getPlataforma());
        //System.out.println(ctrl.buscarPassword(formPass.getPlataforma()).toString());
        List<Password> passwords= ctrl.getListaDePasswords();
        return ok(toJson(passwords));
    }
    
    public Result postLogin(){
        System.out.println("Entre");
        String usuario= "juliNata@hotmail.com";
        String password= "juliNata";
        Usuario formUser= Form.form(Usuario.class).bindFromRequest().get();
        
        System.out.println(formUser.getEmail());
        System.out.println(formUser.getPassword());
        
        if(usuario.equalsIgnoreCase(formUser.getEmail()) && password.equalsIgnoreCase(formUser.getPassword())){
            System.out.println("Usuario Correcto");
            isLog=true;
            nombreUsuario=formUser.getEmail();
            tryAgain=false;
            return redirect(routes.Application.passwordsMain());
        }
        
        //return redirect(routes.Application.passwordsMain());
        tryAgain=true;
        isLog=false;
        return ok(login.render(isLog, tryAgain));
    }
    
    public Result logout(){
        System.out.println("Logout");
        isLog=false;
        nombreUsuario="";
        return ok(index.render("Inicio",nombreUsuario, isLog));
    }
    
    
}