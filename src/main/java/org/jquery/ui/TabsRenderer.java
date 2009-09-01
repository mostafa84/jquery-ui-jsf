package org.jquery.ui;

import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.component.UIComponent;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import java.io.IOException;
import java.util.List;

@FacesRenderer(componentFamily = "javax.faces.Panel", rendererType = "org.jquery.ui.TabsComponent")
@ResourceDependencies( {
    @ResourceDependency(
        name = "jquery.min.js",
        library = "jquery",
        target = "head"
    ),
    @ResourceDependency(
        name = "jquery-ui.min.js",
        library = "jquery",
        target = "head"
    ),
    @ResourceDependency(
        name = "ui-lightness.css",
        library = "jquery",
        target = "head"
    )
} )
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
        writer.startElement("div", component);
        writer.writeAttribute("id", component.getClientId(), "id");

        List<UIComponent> children = component.getChildren();
        for (UIComponent child:children) {
            if (child.isRendered()) {
                writer.startElement("li", null);
                writer.startElement("a", null);
                String id = new StringBuilder().append("#").append(child.getClientId()).toString();
                writer.writeAttribute("href", id, null);
                String title = (String)component.getAttributes().get("title");
                writer.writeText(title, null);
                writer.endElement("li");
            }
        }
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
