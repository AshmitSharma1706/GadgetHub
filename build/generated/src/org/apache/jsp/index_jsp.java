package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import in.gadgethub.utility.*;
import java.util.*;
import in.gadgethub.pojo.ProductPojo;
import in.gadgethub.dao.impl.*;
import in.gadgethub.dao.*;
import in.gadgethub.pojo.*;
import javax.servlet.ServletOutputStream;
import java.io.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(1);
    _jspx_dependants.add("/footer.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>");
      out.print(AppInfo.APP_NAME);
      out.write(" Application</title>\n");
      out.write("        <meta charset=\"utf-8\" />\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n");
      out.write("        <link\n");
      out.write("            href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\"\n");
      out.write("            rel=\"stylesheet\"\n");
      out.write("            />\n");
      out.write("        <link rel=\"stylesheet\" href=\"mycss.css\" />\n");
      out.write("        <link\n");
      out.write("            rel=\"stylesheet\"\n");
      out.write("            href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\"\n");
      out.write("            />\n");
      out.write("    </head>\n");
      out.write("    <body style=\"background-color: #e6f9e6\">\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "header.jsp", out, false);
      out.write("\n");
      out.write("            \n");
      out.write("        <div class=\"text-center m-3 text-primary h3\">");
      out.print(request.getAttribute("message"));
      out.write("</div>\n");
      out.write("        <!-- Start of Product Items List -->\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row text-center\">\n");
      out.write("                ");

                    Map<String, Integer> mapList=(HashMap)request.getAttribute("map");
                    String username=(String)request.getAttribute("username");
                    List<ProductPojo> prodList=(ArrayList)request.getAttribute("products");
                    System.out.println(prodList);
                    for(ProductPojo product:prodList){
                        int cartQty=mapList.get(product.getProductId());
                
      out.write("\n");
      out.write("                <div class=\"col-sm-4\">\n");
      out.write("                    <div class=\"thumbnail mt-3 mb-3\">\n");
      out.write("                        <img\n");
      out.write("                            src=\"./ShowImageServlet?pid=");
      out.print(product.getProductId());
      out.write("\"\n");
      out.write("                            alt=\"Product\"\n");
      out.write("                            style=\"height: 150px; max-width: 180px\"\n");
      out.write("                            class=\"mt-3\"\n");
      out.write("                            />\n");
      out.write("                        <p class=\"productname\">");
      out.print(product.getProductName());
      out.write("\"%></p>\n");
      out.write("                        ");

                            String desc=product.getProductInfo();
                            desc=desc.substring(0,Math.min(100, desc.length()));
                        
      out.write("\n");
      out.write("                        <p class=\"productinfo\">");
      out.print(desc);
      out.write("</p>\n");
      out.write("                        <p class=\"price\">Rs ");
      out.print(product.getProductPrice());
      out.write("</p>\n");
      out.write("                        <form method=\"post\">\n");
      out.write("                            ");

                                if(cartQty==0){
                            
      out.write("\n");
      out.write("                            <button\n");
      out.write("                                type=\"submit\"\n");
      out.write("                                formaction=\"./AddToCartServlet?uid=");
      out.print(username);
      out.write("&pid=");
      out.print(product.getProductId());
      out.write("&pqty=1\"\n");
      out.write("                                class=\"btn btn-warning\"\n");
      out.write("                                >\n");
      out.write("                                Add to Cart\n");
      out.write("                            </button>\n");
      out.write("                            &nbsp;&nbsp;&nbsp;\n");
      out.write("                            <button\n");
      out.write("                                type=\"submit\"\n");
      out.write("                                formaction=\"./AddToCartServlet?uid=");
      out.print(username);
      out.write("&pid=");
      out.print(product.getProductId());
      out.write("&pqty=1\"\n");
      out.write("                                class=\"btn btn-primary\"\n");
      out.write("                                >\n");
      out.write("                                Buy Now\n");
      out.write("                            </button>\n");
      out.write("                            ");

                                }
                                else{
                            
      out.write("\n");
      out.write("                            <button\n");
      out.write("                                type=\"submit\"\n");
      out.write("                                formaction=\"./AddToCartServlet?uid=");
      out.print(username);
      out.write("&pid=");
      out.print(product.getProductId());
      out.write("&pqty=0\"\n");
      out.write("                                class=\"btn btn-danger\"\n");
      out.write("                                >\n");
      out.write("                                Remove From Cart\n");
      out.write("                            </button>\n");
      out.write("                            &nbsp;&nbsp;&nbsp;\n");
      out.write("                            <button\n");
      out.write("                                type=\"submit\"\n");
      out.write("                                formaction=\"cartDetails.jsp\"\n");
      out.write("                                class=\"btn btn-success\"\n");
      out.write("                                >\n");
      out.write("                                Checkout\n");
      out.write("                            </button>\n");
      out.write("                            ");
 } 
      out.write("\n");
      out.write("                        </form>\n");
      out.write("                        <br />\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- ENd of Product Items List -->\n");
      out.write("\n");
      out.write("        ");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"utf-8\" />\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n");
      out.write("    <title>Bootstrap demo</title>\n");
      out.write("    <link\n");
      out.write("      href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css\"\n");
      out.write("      rel=\"stylesheet\"\n");
      out.write("      integrity=\"sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH\"\n");
      out.write("      crossorigin=\"anonymous\"\n");
      out.write("    />\n");
      out.write("    <link\n");
      out.write("      rel=\"stylesheet\"\n");
      out.write("      href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css\"\n");
      out.write("      integrity=\"sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==\"\n");
      out.write("      crossorigin=\"anonymous\"\n");
      out.write("      referrerpolicy=\"no-referrer\"\n");
      out.write("    />\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("    <div class=\"container-fluid bg-primary text-white pb-3\">\n");
      out.write("      <div class=\"container\">\n");
      out.write("        <h2 class=\"text-center\">Contact</h2>\n");
      out.write("        <p class=\"text-center\">We love our fans!</p>\n");
      out.write("        <div class=\"row\">\n");
      out.write("          <div class=\"col-md-4 mt-1\">\n");
      out.write("            <p><i class=\"fa-solid fa-phone p-2\"></i> Phone : +91 9303890608</p>\n");
      out.write("            <p>\n");
      out.write("              <i class=\"fa-solid fa-envelope p-2\"></i> Email :\n");
      out.write("              rajnishkushwaha3757@gmail.com\n");
      out.write("            </p>\n");
      out.write("            <p>\n");
      out.write("              <i class=\"fa-solid fa-signs-post p-2\"></i> Postal Code : 462022\n");
      out.write("            </p>\n");
      out.write("          </div>\n");
      out.write("\n");
      out.write("          <div class=\"col-md-8 mt-1\">\n");
      out.write("            <form action=\"#\">\n");
      out.write("              <div class=\"row\">\n");
      out.write("                <div class=\"col-sm-6 form-group mt-1\">\n");
      out.write("                  <input\n");
      out.write("                    type=\"text\"\n");
      out.write("                    class=\"form-control\"\n");
      out.write("                    placeholder=\"name\"\n");
      out.write("                    required\n");
      out.write("                  />\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"col-sm-6 form-group mt-1\">\n");
      out.write("                  <input\n");
      out.write("                    type=\"email\"\n");
      out.write("                    class=\"form-control\"\n");
      out.write("                    placeholder=\"email\"\n");
      out.write("                    required\n");
      out.write("                  />\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("              <textarea\n");
      out.write("                name=\"\"\n");
      out.write("                id=\"\"\n");
      out.write("                rows=\"5\"\n");
      out.write("                class=\"form-control mt-1\"\n");
      out.write("                placeholder=\"Enter your message....\"\n");
      out.write("              ></textarea>\n");
      out.write("\n");
      out.write("              <div class=\"row\">\n");
      out.write("                <div class=\"col-md-12 form-group mt-1\">\n");
      out.write("                    <button type=\"button\" class=\"btn btn-warning\">Send</button>\n");
      out.write("                </div>\n");
      out.write("              </div>\n");
      out.write("            </form>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </div>\n");
      out.write("    </div>\n");
      out.write("    <script\n");
      out.write("      src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"\n");
      out.write("      integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\"\n");
      out.write("      crossorigin=\"anonymous\"\n");
      out.write("    ></script>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
