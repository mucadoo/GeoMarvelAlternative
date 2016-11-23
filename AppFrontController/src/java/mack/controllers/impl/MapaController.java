package mack.controllers.impl;

import ejb.beans.UsuarioBeanRemote;
import ejb.entities.Posicao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import mack.controllers.AbstractController;
import ejb.beans.PosicaoRemote;
import ejb.entities.Personagem;
import ejb.entities.Usuario;

public class MapaController extends AbstractController {

    public void execute() {
        try {
            Context ctx = new InitialContext();
            UsuarioBeanRemote usuarioRemote = (UsuarioBeanRemote) ctx.lookup("java:global/AppEnterprise/ModuloEJB/UsuarioBean");
            PosicaoRemote posicaoRemote = (PosicaoRemote) ctx.lookup("java:global/AppEnterprise/ModuloEJB/PosicaoBean");
            List<Usuario> usuarios = new ArrayList<Usuario>();
            List<Posicao> posicoes = new ArrayList<Posicao>();
            List<Personagem> personagens = new ArrayList<Personagem>();
            usuarios = usuarioRemote.list();
            posicoes = posicaoRemote.list();
            for (Usuario usu : usuarios) {
                String nome = usu.getPersonagemMarvel();
                Personagem personagem = usuarioRemote.pegaPersonagem(nome);
                personagens.add(personagem);
            }
            this.setReturnPage("/mapa.jsp");
            this.getRequest().setAttribute("usuarios", usuarios);
            this.getRequest().setAttribute("posicoes", posicoes);
            this.getRequest().setAttribute("personagens", personagens);
        } catch (Exception ex) {
            Logger.getLogger(MapaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
