package forms.application.views;


import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;


public class QuestionView extends Composite<VerticalLayout> {


    private final String number;
    private final String text;
    private final String absent;

    public QuestionView(String number, String text, String absent) {
        this.number = number;
        this.text = text;
        this.absent = absent;
        setQuestion();
    }

    private void setQuestion() {
        TextField numberQuestion = new TextField("Номер вопроса");
        numberQuestion.setValue(number);
        numberQuestion.setReadOnly(true);
        TextArea textQuestion = new TextArea("Вопрос");
        textQuestion.setValue(text);
        textQuestion.setReadOnly(true);
        Button buttonExplanations = new Button("?");
        Button buttonNoComments = new Button("Нет замечаний");
        //<theme-editor-local-classname>
        buttonNoComments.addClassName("question-view-button-1");
        Button buttonYesComments = new Button("Есть замечания");
        //<theme-editor-local-classname>
        buttonYesComments.addClassName("question-view-button-2");
        buttonYesComments.addClickListener(e ->
                buttonYesComments.getUI().ifPresent(ui ->
                        ui.navigate("Defect"))
        );

        Button buttonNoInspection = new Button("Нет осмотра");
        //<theme-editor-local-classname>
        buttonNoInspection.addClassName("question-view-button-4");
        Button buttonAbsent = new Button("Отсутствует");
        //<theme-editor-local-classname>
        buttonAbsent.addClassName("question-view-button-3");
        buttonExplanations.addClickListener(e -> Notification.show(absent));
        HorizontalLayout texts = new HorizontalLayout(textQuestion, buttonExplanations);
        HorizontalLayout firstLineButton = new HorizontalLayout(buttonNoComments, buttonYesComments);
        HorizontalLayout secondLineButton = new HorizontalLayout(buttonNoInspection, buttonAbsent);
        getContent().add(numberQuestion);
        getContent().add(texts);
        getContent().add(firstLineButton);
        getContent().add(secondLineButton);
    }
}
