package forms.application.views;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;

@PageTitle("Wishes")
@Route(value = "Wishes")
@Service
public class WishesView extends VerticalLayout {
    private EmailField emailField = new EmailField("Email адрес для отправки");
    private final InputDataView inputDataView;
    private final AttachmentsView attachmentsView;
    private final AverageLevelView averageLevelView;
    private final LowerLevelView lowerLevelView;
    private final UpperLevelView upperLevelView;
    private final PreInspectionView preInspectionView;

    public WishesView(InputDataView inputDataView,
                      AttachmentsView attachmentsView,
                      AverageLevelView averageLevelView,
                      LowerLevelView lowerLevelView,
                      UpperLevelView upperLevelView,
                      PreInspectionView preInspectionView) {

this.inputDataView = inputDataView;
this.attachmentsView = attachmentsView;
this.averageLevelView = averageLevelView;
this.lowerLevelView = lowerLevelView;
this.upperLevelView = upperLevelView;
this.preInspectionView = preInspectionView;
        setWishesView();
    }
    private void setWishesView(){
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Пожелания");
        Button proceed = new Button("Получить");
        proceed.addClickListener(e -> getAllJson());
//                proceed.getUI().ifPresent(ui ->
//                        ui.navigate("InputData")));

        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("Attachments")));

        add(h1);
        add(h2);

        wishes();

        add(new HorizontalLayout(back, proceed));
    }
    private void wishes(){
        TextArea wish = new TextArea("Пожелания, внедрения");
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        wish.setSuffixComponent(button);
        wish.setTooltipText("Напишите пожелания," +
                " предложения. Опишите внедрения введенные заказчиком");
        Tooltip tooltip = wish.getTooltip().withManual(true);
        button.addClickListener(event -> tooltip.setOpened(!tooltip.isOpened()));

        var buffer = new MultiFileMemoryBuffer();
        Button buttonPhoto = new Button("Загрузить фото");

        Upload setPhoto = new Upload(buffer);
        setPhoto.setDropAllowed(true);
        setPhoto.setUploadButton(buttonPhoto);
        setPhoto.setDropAllowed(false);

        NativeLabel photoLabel = new NativeLabel("Фото внедрения");
        setPhoto.setId("wish");
        photoLabel.setFor(setPhoto.getId().get());


        Div getPhoto = new Div(photoLabel, setPhoto);

        add(wish);
        add(getPhoto);
        email();

    }

    private void getAllJson(){
        JsonObject allJson = new JsonObject();
        allJson.add("inputData", inputDataView.getInputDataJsonObject());
//        allJson.add("attachments", attachmentsView.getAttachmentsJsonObject());
//        allJson.add("averageLevel", averageLevelView.getAverageLevelJsonObject());
//        allJson.add("lowerLevel", lowerLevelView.getLowerLevelJsonObject());
        allJson.add("upperLevel", upperLevelView.getUpperLevelJsonObject());
        allJson.add("preInspection", preInspectionView.getPreInspectionViewJsonObject());
        System.out.println(allJson);

    }
    private void email(){

        emailField.getElement().setAttribute("name", "email");
        emailField.setErrorMessage("Введите корректный email");
        emailField.setClearButtonVisible(true);
        add(emailField);
    }
}
