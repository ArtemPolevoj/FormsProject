package forms.application.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.awt.*;


@PageTitle("UpperLevel")
@Route(value = "UpperLevel")
public class UpperLevelView extends VerticalLayout {

    public UpperLevelView() {


        setUpperLevelView();
    }
    private void setUpperLevelView(){
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Осмотр верхнего уровня");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("AverageLevel")));

        Button back = new Button("Назад");
        back.addClickListener(e -> getUI().ifPresent(ui ->
                ui.navigate("PreInspection")));

        add(h1);
        add(h2);
        //генерим серию вопросов
        Paragraph paragraph = new Paragraph();
        paragraph.setText("Здесь генерим серию вопросов");
        add(paragraph);

        add(new HorizontalLayout(back, proceed));
    }

}