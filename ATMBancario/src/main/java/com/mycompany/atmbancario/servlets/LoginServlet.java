package com.mycompany.atmbancario.servlets;

import com.mycompany.atmbancario.db.DatabaseConnection;
import com.mycompany.atmbancario.models.Usuario;
import com.mycompany.atmbancario.models.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");
        
        UsuarioDAO dao = new UsuarioDAO();
        
        Usuario usuario = dao.buscarPorCpfSenha(cpf, senha);
        
        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("id_usuario", usuario.getIdUsuario());
            response.sendRedirect("atm.jsp");
        } else {
            response.sendRedirect("index.jsp?error=1");
        }
            
    }
}