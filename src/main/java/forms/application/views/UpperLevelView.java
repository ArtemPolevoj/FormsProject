package forms.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;



@PageTitle("UpperLevel")
@Route(value = "UpperLevel")
@Service
public class UpperLevelView extends VerticalLayout {
    private final InputDataView dataView;


    public UpperLevelView(InputDataView dataView) {
        this.dataView = dataView;


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
        QuestionView question = new QuestionView("1", "Вопрос", "Пояснения");

        add(paragraph);
        add(question);

        add(new HorizontalLayout(back, proceed));


    }


}