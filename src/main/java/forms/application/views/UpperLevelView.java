package forms.application.views;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import forms.application.model.QuestionEntity;
import forms.application.service.ImageServiceImpl;
import forms.application.service.QuestionServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@PageTitle("UpperLevel")
@Route(value = "UpperLevel")
@Service
public class UpperLevelView extends VerticalLayout {
    private final JsonObject upperLevel = new JsonObject();
    private final List<QuestionEntity> questions;
    private final List<QuestionView> questionViews = new ArrayList<>();
    private final ImageServiceImpl imageService;
    private boolean flag = true;


    public UpperLevelView(QuestionServiceImpl questionService, ImageServiceImpl imageService) {
        this.imageService = imageService;
        questions = questionService.findAll();
        setUpperLevelView();
    }

    private void setUpperLevelView() {
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Осмотр верхнего уровня");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e -> {
            getValue();
            if (flag){
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("AverageLevel"));
            }else {
                getValue();
            }

        });


        Button back = new Button("Назад");
        back.addClickListener(e -> getUI().ifPresent(ui ->
                ui.navigate("PreInspection")));

        add(h1);
        add(h2);

        for (int i = 0; i < 5; i++) {
            String number = String.valueOf(i + 1);
            String text = questions.get(i).getDescription();
            QuestionView question = new QuestionView(imageService, number, text, "Пояснения");
            questionViews.add(question);
            add(question);
        }

        add(new HorizontalLayout(back, proceed));

    }

    public JsonObject getUpperLevelJsonObject() {
        return upperLevel;
    }

    private void getValue() {
        for (int i = 0; i < questionViews.size(); i++) {
            QuestionView questionView = questionViews.get(i);
            flag = questionView.checkNull();
            if (!flag){
                break;
            }
            upperLevel.add(String.valueOf(i + 1), questionView.getQuestion());
        }
    }

}