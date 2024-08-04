package forms.application.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.*;
import com.vaadin.flow.component.html.H1;


/**
 * The main view is a top-level placeholder for other views.
 */

@PageTitle("MainPage")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class MainLayout extends VerticalLayout {


    public MainLayout() {
        setMainLayout();
    }

    private void setMainLayout() {
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        TextArea warning = new TextArea("ПРЕДУПРЕЖДЕНИЕ");
        warning.setValue("Сервис предназначен для осмотра техники." +
                "В данный момент сервис находится в разработке." +
                "Нажимая кнопку продолжить вы соглашаетесь со всеми условиями");
        warning.setReadOnly(true);
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("InputData")));


        Button exit = new Button("Выход");
         exit.addClickListener(e->{
             UI ui = getUI().get();
             ui.getPage().executeJs("window.location.href='logout.html'");
             ui.getSession().close();
         });

        HorizontalLayout buttonLayout = new HorizontalLayout(exit, proceed);
        add(h1);
        add(warning);
        add(buttonLayout);
    }

}


