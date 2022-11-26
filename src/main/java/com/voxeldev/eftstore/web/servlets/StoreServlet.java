package com.voxeldev.eftstore.web.servlets;

import com.voxeldev.eftstore.dao.interfaces.StoreItemsRepository;
import com.voxeldev.eftstore.utils.ServletUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "Store", value = "")
public class StoreServlet extends HttpServlet {
    
    private StoreItemsRepository storeItemsRepository;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        
        storeItemsRepository = (StoreItemsRepository) config.getServletContext().getAttribute("StoreItemsRepository");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletUtils.setRequestUser(req, ServletUtils.getSessionUser(req));
        
        req.setAttribute("greeting", getGreetingLabel(LocalDateTime.now().getHour()));
        req.setAttribute("items", storeItemsRepository.getAllItems());
        
        getServletContext().getRequestDispatcher("/WEB-INF/view/store.jsp").forward(req, resp);
    }
    
    private String getGreetingLabel(int hour) {
        if (hour >= 0 && hour < 6) {
            return "label.goodNight";
        } else if (hour >= 6 && hour < 12) {
            return "label.goodMorning";
        } else if (hour >= 12 && hour < 18) {
            return "label.goodAfternoon";
        } else {
            return "label.goodEvening";
        }
    }
}
