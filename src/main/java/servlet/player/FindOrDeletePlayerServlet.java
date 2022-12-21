package servlet.player;

import entity.Player;
import entityManager.PlayerManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class FindOrDeletePlayerServlet extends HttpServlet {
    PlayerManager playerManager = PlayerManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Player player = playerManager.findById(Long.parseLong(req.getParameter("find_id")));
        resp.getWriter().println(player.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("delete_id"));
        playerManager.deleteById(id);

    }
}
