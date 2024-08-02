package forms.application.views;


import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

public class QuestionView extends Composite<VerticalLayout> {


    private final String number;
    private final String text;
    private final String explanations;

    public QuestionView(String number, String text, String explanations) {
        this.number = number;
        this.text = text;
        this.explanations = explanations;
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

        TextArea defect = new TextArea("Неисправность");
        Button buttonDefectShow = new Button("?");

        buttonDefectShow.addClickListener(e -> Notification.show("Опишите неисправность"));
        TextArea listWork = new TextArea("Список необходимых з.ч. и/или работ");
        Button buttonListWorkShow = new Button("?");
        buttonListWorkShow.addClickListener(e -> Notification.show("Добавьте необходимые з.ч. или опишите их\n" +
                "Добавьте необходимые работы"));
        var buffer = new MultiFileMemoryBuffer();
        Button buttonPhoto = new Button("Загрузить фото");

        Upload setPhoto = new Upload(buffer);
        setPhoto.setDropAllowed(true);
        setPhoto.setUploadButton(buttonPhoto);
        setPhoto.setDropAllowed(false);

        NativeLabel photoLabel = new NativeLabel("Фото неисправности");
        setPhoto.setId("defect");
        photoLabel.setFor(setPhoto.getId().get());

        Div getPhoto = new Div(photoLabel, setPhoto);

        HorizontalLayout defectLayout = new HorizontalLayout(defect, buttonDefectShow);

        HorizontalLayout listWorkLayout = new HorizontalLayout(listWork, buttonListWorkShow);

VerticalLayout mainDefect = new VerticalLayout(defectLayout, listWorkLayout, getPhoto);
mainDefect.setVisible(false);


        buttonYesComments.addClickListener(e -> mainDefect.setVisible(true));

        Button buttonNoInspection = new Button("Нет осмотра");
        buttonNoInspection.addClickListener(e -> mainDefect.setVisible(false) );
        //<theme-editor-local-classname>
        buttonNoInspection.addClassName("question-view-button-4");
        Button buttonAbsent = new Button("Отсутствует");
        buttonAbsent.addClickListener(e -> mainDefect.setVisible(false) );
        //<theme-editor-local-classname>
        buttonAbsent.addClassName("question-view-button-3");
        buttonExplanations.addClickListener(e -> Notification.show(explanations));
        buttonNoComments.addClickListener(e -> mainDefect.setVisible(false) );

        HorizontalLayout texts = new HorizontalLayout(textQuestion, buttonExplanations);
        HorizontalLayout firstLineButton = new HorizontalLayout(buttonNoComments, buttonYesComments);
        HorizontalLayout secondLineButton = new HorizontalLayout(buttonNoInspection, buttonAbsent);
        getContent().add(numberQuestion);
        getContent().add(texts);
        getContent().add(firstLineButton);
        getContent().add(secondLineButton);


        getContent().add(mainDefect);
    }
}
