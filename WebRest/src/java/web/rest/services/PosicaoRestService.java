package web.rest.services;

import ejb.beans.UsuarioBeanRemote;
import ejb.entities.Personagem;
import ejb.entities.Usuario;
import java.io.IOException;
import java.security.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("/posicoes/")
public class PosicaoRestService {

    public PosicaoRestService() {
    }

    @GET
    @Path("/{login}")
    @Produces(MediaType.TEXT_HTML)
    public String buscaDadosPosicao(@Context SecurityContext sc, @PathParam("login") final String login) throws IOException {
        boolean isReader = sc.isUserInRole("reader");
        boolean isAdministrator = sc.isUserInRole("administrator");
        Principal p = sc.getUserPrincipal();
        System.out.println("Principal " + p.getName() + " belogns reader,administrator " + isReader + ", " + isAdministrator);
        javax.naming.Context ctx = null;
        try {
            ctx = new InitialContext();
        } catch (NamingException ex) {
            Logger.getLogger(PosicaoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioBeanRemote ub = null;
        try {
            ub = (UsuarioBeanRemote) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
        } catch (NamingException ex) {
            Logger.getLogger(PosicaoRestService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Buscando informações da posição: " + login);
        Usuario usuario = ub.buscaUsuarioPorLogin(login);
        if (usuario == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        Personagem personagem = ub.pegaPersonagem(usuario.getPersonagemMarvel());
        return "<p>" + usuario.getNome() + "</p>"
                + "<p>" + personagem.getNome() + "</p>"
                + "<img src='" + personagem.getImagem() + "'>";
    }

}
