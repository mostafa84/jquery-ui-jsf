package org.jquery.ui;

import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIComponent;
import java.io.IOException;
import java.util.List;

@FacesRenderer(componentFamily = "javax.faces.Panel", rendererType = "org.jquery.ui.TabsComponent")
public class TabsRenderer extends Renderer {

    /**
     * <p>Render the beginning specified {@link javax.faces.component.UIComponent} to the
     * output stream or writer associated with the response we are creating.
     * If the conversion attempted in a previous call to
     * <code>getConvertedValue()</code> for this component failed, the state
     * information saved during execution
     * of <code>decode()</code> should be used to reproduce the incorrect
     * input.</p>
     *
     * @param context {@link javax.faces.context.FacesContext} for the request we are processing
     * @param component {@link javax.faces.component.UIComponent} to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     * @throws NullPointerException if <code>context</code>
     * or <code>component</code> is null
     */
    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        super.encodeBegin(context, component);
        ResponseWriter writer = context.getResponseWriter();

        //output the javascript script
        writer.startElement("script", null);
        writer.writeAttribute("type", "text/javascript", null );
        Boolean prependId = (Boolean)component.getAttributes().get("prependId");
        String renderId = prependId ? component.getClientId() : component.getId();
        StringBuilder script = new StringBuilder()
                .append("$(function() { ").append("$(\"#")
                .append(renderId).append("\".replace(/:/g,\"\\\\:\")).tabs(); ")
                .append("});");
        writer.writeText( script.toString(), null);
        writer.endElement("script");

        writer.startElement("div", component);
        writer.writeAttribute("id", component.getClientId(), "id");
        writer.startElement("ul", null);
        List<UIComponent> children = component.getChildren();
        for (UIComponent child:children) {
            if (child.isRendered()) {
                writer.startElement("li", null);
                writer.startElement("a", null);
                String href = (String)child.getAttributes().get("href");
                if (href!=null && href.trim().length()>0) {
                    writer.writeAttribute("href", href, null);
                } else {
                    Boolean cPrependId = (Boolean)child.getAttributes().get("prependId");
                    String cRenderId = cPrependId ? child.getClientId() : child.getId();
                    String id = new StringBuilder().append("#").append(cRenderId).toString();
                    writer.writeAttribute("href", id, null);
                }
                String title = (String)child.getAttributes().get("title");
                writer.writeText(title, null);
                writer.endElement("a");
                writer.endElement("li");
            }
        }
        writer.endElement("ul");
    }

    /**
     * <p>Render the ending of the current state of the specified
     * {@link javax.faces.component.UIComponent}, following the rules described for
     * <code>encodeBegin()</code> to acquire the appropriate value
     * to be rendered.</p>
     *
     * @param context {@link javax.faces.context.FacesContext} for the response we are creating
     * @param component {@link javax.faces.component.UIComponent} to be rendered
     * @throws java.io.IOException if an input/output error occurs while rendering
     * @throws NullPointerException if <code>context</code>
     * or <code>component</code> is <code>null</code>
     */
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        super.encodeEnd(context, component);
        ResponseWriter writer = context.getResponseWriter();
        writer.endElement("div");
    }
}
