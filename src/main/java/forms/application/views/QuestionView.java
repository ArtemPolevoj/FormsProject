package forms.application.views;


import com.nimbusds.jose.shaded.gson.JsonObject;
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
import forms.application.service.ImageServiceImpl;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;

public class QuestionView extends Composite<VerticalLayout> {

    private ImageServiceImpl imageService;
    private final String number;
    private final String text;
    private final String explanations;
    private TextArea defect;
    private TextArea listWork;
    private  JsonObject jsonObject;
    private JsonObject question;
    private  VerticalLayout mainDefect;
    private  String fileName;

    public QuestionView(ImageServiceImpl imageService, String number, String text, String explanations) {
        this.imageService = imageService;
        this.number = number;
        this.text = text;
        this.explanations = explanations;
        setQuestion();
    }

    private void setQuestion() {
        TextArea textQuestion = setTextArea(number, explanations);
        textQuestion.setValue(text);
        textQuestion.setReadOnly(true);


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
        setPhoto.addSucceededListener(event -> {
            fileName = event.getFileName();

            InputStream inputStream = buffer.getInputStream(fileName);
            byte[] bytes;
            try {
                bytes = IOUtils.toByteArray(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

           imageService.uploadImage(fileName, bytes);

        });

        NativeLabel photoLabel = new NativeLabel("Фото неисправности");
        setPhoto.setId("defect");
        photoLabel.setFor(setPhoto.getId().get());
        Div getPhoto = new Div(photoLabel, setPhoto);
        HorizontalLayout defectLayout = new HorizontalLayout(defect);
        HorizontalLayout listWorkLayout = new HorizontalLayout(listWork);
        mainDefect = new VerticalLayout(defectLayout, listWorkLayout, getPhoto);
        mainDefect.setVisible(false);

        buttonYesComments.addClickListener(e -> {
            mainDefect.setVisible(true);
            jsonObject = new JsonObject();
            question = new JsonObject();
            question.addProperty(text, "Есть замечания");

        });

        Button buttonNoComments = new Button("Нет замечаний");
        //<theme-editor-local-classname>
        buttonNoComments.addClassName("question-view-button-1");
        buttonNoComments.addClickListener(e -> {
            mainDefect.setVisible(false);
            jsonObject = new JsonObject();
            jsonObject.addProperty(text, "Нет замечаний");
        });

        Button buttonNoInspection = new Button("Нет осмотра");
        //<theme-editor-local-classname>
        buttonNoInspection.addClassName("question-view-button-4");
        buttonNoInspection.addClickListener(e -> {
            mainDefect.setVisible(false);
            jsonObject = new JsonObject();
            jsonObject.addProperty(text, "Нет осмотра");
        });

        Button buttonAbsent = new Button("Отсутствует");
        //<theme-editor-local-classname>
        buttonAbsent.addClassName("question-view-button-3");
        buttonAbsent.addClickListener(e -> {
            mainDefect.setVisible(false);
            jsonObject = new JsonObject();
            jsonObject.addProperty(text, "Отсутствует");
        });

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
        var button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        textArea.setSuffixComponent(button);
        textArea.setTooltipText(textHelper);
        Tooltip tooltip = textArea.getTooltip().withManual(true);
        button.addClickListener(event -> tooltip.setOpened(!tooltip.isOpened()));
        return textArea;
    }
    public JsonObject getQuestion() {

        if (mainDefect.isVisible() && checkNull()) {
            question.addProperty("неисправность", defect.getValue());
            question.addProperty("материалы, работы", listWork.getValue());
            question.addProperty("Фото", fileName);
            jsonObject.add(text, question);
            return jsonObject;
        }else{
            return jsonObject;
        }
    }
    public boolean checkNull() {
        if (mainDefect.isVisible() && defect.isEmpty()) {
            Notification.show("Заполните неисправность");
            defect.focus();
            return false;        }
        if (mainDefect.isVisible() && listWork.isEmpty()) {
            Notification.show("Заполните материалы, работы");
            listWork.focus();
            return false;
        }
        return true;
    }
}
