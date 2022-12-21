package servlet.player;

import entity.Currency;
import entity.Item;
import entity.Player;
import entityManager.CurrencyManager;
import entityManager.ItemManager;
import entityManager.PlayerManager;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.ParseUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PlayerServlet extends HttpServlet {
    PlayerManager playerManager = PlayerManager.getInstance();
    ItemManager itemManager = ItemManager.getInstance();
    CurrencyManager currencyManager = CurrencyManager.getInstance();

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action != null){
            List<Player> players = playerManager.getAll();
            for(Player player: players){
                resp.getWriter().println(player.toString());
            }
        } else
        getServletContext().getRequestDispatcher("/static/player-form.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        Long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("nickname");
        String item_id = req.getParameter("item_id");
        String currency_id = req.getParameter("currency_id");

        List<Long> itemsIdList = ParseUtils.parseIdsFromForm(item_id);
        List<Long> currencyIdList = ParseUtils.parseIdsFromForm(currency_id);
        List<Item> itemList = new ArrayList<>();
        List<Currency> currencyList = new ArrayList<>();

        for(Long FId: itemsIdList){
            itemList.add(itemManager.findById(FId));
        }

        for(Long FId: currencyIdList){
            currencyList.add(currencyManager.findById(FId));
        }

        Player player = new Player();
        player.setId(id);
        player.setNickname(name);
        if(!currencyList.isEmpty()) {
            player.setCurrencies(currencyList);
        }
        if(!itemList.isEmpty()) {
            player.setItems(itemList);
        }

        playerManager.save(player);

        resp.getWriter().println(playerManager.findById(id));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
    }
}
