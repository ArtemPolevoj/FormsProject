package forms.application.views;


import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

public class QuestionView extends Composite<VerticalLayout> {


    private final String number;
    private final String text;
    private final String explanations;
    private TextArea defect;
    private TextArea listWork;

    public QuestionView(String number, String text, String explanations) {
        this.number = number;
        this.text = text;
        this.explanations = explanations;
        setQuestion();
    }

    private void setQuestion() {
        TextArea textQuestion = setTextArea(number, explanations);
        textQuestion.setValue(text);
        textQuestion.setReadOnly(true);
        Button buttonNoComments = new Button("Нет замечаний");
        //<theme-editor-local-classname>
        buttonNoComments.addClassName("question-view-button-1");
        Button buttonYesComments = new Button("Есть замечания");
        //<theme-editor-local-classname>
        buttonYesComments.addClassName("question-view-button-2");

        defect = setTextArea("Неисправность", "Опишите неисправность");

        listWork = setTextArea("Список необходимых з.ч. и/или работ",
                "Добавьте необходимые з.ч. или опишите их. Добавьте необходимые работы");
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

        HorizontalLayout defectLayout = new HorizontalLayout(defect);

        HorizontalLayout listWorkLayout = new HorizontalLayout(listWork);

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

        buttonNoComments.addClickListener(e -> mainDefect.setVisible(false) );

        HorizontalLayout texts = new HorizontalLayout(textQuestion);
        HorizontalLayout firstLineButton = new HorizontalLayout(buttonNoComments, buttonYesComments);
        HorizontalLayout secondLineButton = new HorizontalLayout(buttonNoInspection, buttonAbsent);
        getContent().add(texts);
        getContent().add(firstLineButton);
        getContent().add(secondLineButton);


        getContent().add(mainDefect);
    }
    private TextArea setTextArea(String label, String textHelper) {
        TextArea textArea = new TextArea(label);
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        textArea.setSuffixComponent(button);
        textArea.setTooltipText(textHelper);
        Tooltip tooltip = textArea.getTooltip().withManual(true);
        button.addClickListener(event -> tooltip.setOpened(!tooltip.isOpened()));
        return textArea;
    }
    public String getDefect(){
        return defect.getValue();
    }
    public String getListWork(){
        return listWork.getValue();
    }
}
