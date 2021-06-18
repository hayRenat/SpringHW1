import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet (name = "FirstServlet", urlPatterns = "/first_servlet")

public class FirstServlet implements Servlet {

    private static Logger logger = LoggerFactory.getLogger(FirstServlet.class);
    private transient ServletConfig servletConfig;

    // Метод вызывается контейнером после того как был создан класс сервлета
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
    }

    @Override
    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    // Метод вызывается для каждого нового HTTP запроса к данному сервлету
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        logger.info("New request");
        List<Product> products = new ArrayList<>();
        products.add(new Product(1, "apple", 10.9f));
        products.add(new Product(2, "orange", 25.5f));
        products.add(new Product(3, "cucumber", 65.6f));
        products.add(new Product(4, "cupcake", 45.8f));
        products.add(new Product(5, "ice cream", 34.7f));

        for (Product product: products) {
            response.getWriter().println("<h1>" + product.toString() + "</h1>");
        }
    }

    @Override
    public String getServletInfo() {
        return "FirstServlet";
    }

    @Override
    public void destroy() {
        logger.info("Servlet {} destroyed", getServletInfo());
    }
}
