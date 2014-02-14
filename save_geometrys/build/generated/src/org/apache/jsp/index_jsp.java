package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE html>\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Map</title>\n");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" href=\"libs/libs/leaflet.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"libs/dist/leaflet.draw.css\" />\n");
      out.write("        <link rel=\"stylesheet\" href=\"libs/dist/css/bootstrap.css\" />\n");
      out.write("        <script src=\"https://code.jquery.com/jquery-1.10.2.min.js\"></script>\n");
      out.write("        <script src=\"libs/libs/leaflet-src.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/src/Leaflet.draw.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/src/edit/handler/Edit.Poly.js\"></script>\n");
      out.write("        <script src=\"libs/src/edit/handler/Edit.SimpleShape.js\"></script>\n");
      out.write("        <script src=\"libs/src/edit/handler/Edit.Circle.js\"></script>\n");
      out.write("        <script src=\"libs/src/edit/handler/Edit.Rectangle.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.Feature.js\"></script>\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.Polyline.js\"></script>\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.Polygon.js\"></script>\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.SimpleShape.js\"></script>\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.Rectangle.js\"></script>\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.Circle.js\"></script>\n");
      out.write("        <script src=\"libs/src/draw/handler/Draw.Marker.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/src/ext/LatLngUtil.js\"></script>\n");
      out.write("        <script src=\"libs/src/ext/GeometryUtil.js\"></script>\n");
      out.write("        <script src=\"libs/src/ext/LineUtil.Intersect.js\"></script>\n");
      out.write("        <script src=\"libs/src/ext/Polyline.Intersect.js\"></script>\n");
      out.write("        <script src=\"libs/src/ext/Polygon.Intersect.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/src/Control.Draw.js\"></script>\n");
      out.write("        <script src=\"libs/src/Tooltip.js\"></script>\n");
      out.write("        <script src=\"libs/src/Toolbar.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/src/draw/DrawToolbar.js\"></script>\n");
      out.write("        <script src=\"libs/src/edit/EditToolbar.js\"></script>\n");
      out.write("        <script src=\"libs/src/edit/handler/EditToolbar.Edit.js\"></script>\n");
      out.write("        <script src=\"libs/src/edit/handler/EditToolbar.Delete.js\"></script>\n");
      out.write("\n");
      out.write("        <script src=\"libs/dist/js/bootstrap.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/style.css\" />\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"map\" ></div>\n");
      out.write("        <div id=\"info\">\n");
      out.write("            <form class=\"form\" method=\"post\"  action=\"");
      out.print(request.getContextPath());
      out.write("/SGeometry\">\n");
      out.write("                <div id=\"numero_objects\">\n");
      out.write("                    <h5>Objetos a registrar :<span id=\"num_objects_label\"> 0 </span></h5>\n");
      out.write("                    <input type=\"text\" id=\"num_objects\" name=\"num_objects\" value=\"0\" style=\"display: none\"/>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"objects\">\n");
      out.write("                    <table class=\"table table-striped table-bordered table-condensed\" >\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>#</th>\n");
      out.write("                                <th>Objeto</th>\n");
      out.write("\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody id=\"objects_table\">\n");
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                </div>\n");
      out.write("                <div id=\"marker\" class=\"well\" style=\"display: none\" >\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                \n");
      out.write("                <div id=\"rectangle\" class=\"well\" style=\"display: none\" >\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div id=\"polygon\" class=\"well\" style=\"display: none\">\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div id=\"circle\" class=\"well\" style=\"display: none\">\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"polyline\" class=\"well\" style=\"display: none\">\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <button class=\"btn btn-primary\" type=\"submit\">Registrar</button>\n");
      out.write("                <a id=\"cancelar\" class=\"btn btn-default\" href=\"index.jsp\">Cancelar</a>\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <script src=\"js/app.js\"></script>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
