package edu.pucp.mechatronics.controller;

import java.io.IOException;

import edu.pucp.mechatronics.dao.ProductDAO;
import edu.pucp.mechatronics.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private ProductDAO productDAO = new ProductDAO();

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        if (action.equalsIgnoreCase("list")) {

            request.setAttribute("products", productDAO.findAll());

            request.getRequestDispatcher("views/list.jsp")
                   .forward(request, response);

        } else if (action.equalsIgnoreCase("goAdd")) {

            request.getRequestDispatcher("views/add.jsp")
                   .forward(request, response);

        } else if (action.equalsIgnoreCase("goEdit")) {

            int id = Integer.parseInt(request.getParameter("id"));

            Product product = productDAO.find(id);

            request.setAttribute("product", product);

            request.getRequestDispatcher("views/edit.jsp")
                   .forward(request, response);

        } else if (action.equalsIgnoreCase("delete")) {

            int id = Integer.parseInt(request.getParameter("id"));

            productDAO.delete(id);

            response.sendRedirect("Controller?action=list");
        }
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("add")) {

            String name = request.getParameter("name");
            int stock = Integer.parseInt(request.getParameter("stock"));

            Product product = new Product(name, stock);

            productDAO.save(product);

            response.sendRedirect("Controller?action=list");

        } else if (action.equalsIgnoreCase("update")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int stock = Integer.parseInt(request.getParameter("stock"));

            Product product = new Product();

            product.setId(id);
            product.setName(name);
            product.setStock(stock);

            productDAO.update(product);

            response.sendRedirect("Controller?action=list");
        }
    }
}