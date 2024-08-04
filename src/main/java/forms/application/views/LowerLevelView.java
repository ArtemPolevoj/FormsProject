package forms.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("LowerLevel")
@Route(value = "LowerLevel")
public class LowerLevelView extends VerticalLayout {
    public LowerLevelView() {


        setLowerLevelView();
    }
    private void setLowerLevelView(){
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Осмотр нижнего уровня");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("Attachments")));
        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("AverageLevel")));
        add(h1);
        add(h2);

        //генерим серию вопросов
        Paragraph paragraph = new Paragraph();
        paragraph.setText("Здесь генерим серию вопросов");
        add(paragraph);

        add(new HorizontalLayout(back, proceed));
    }
}
