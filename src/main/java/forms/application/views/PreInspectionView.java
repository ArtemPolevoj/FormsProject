package forms.application.views;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.shared.Tooltip;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.stereotype.Service;


@PageTitle("PreInspection")
@Route(value = "PreInspection")
@Service
public class PreInspectionView extends VerticalLayout {
    private TextArea petition = new TextArea();
    private Checkbox preparation = new Checkbox();
    private Checkbox safety = new Checkbox();
    private TextArea codes = new TextArea();
    private TextArea colorGaze = new TextArea();
    private TextArea noise = new TextArea();
    private TextArea checkMotion = new TextArea();

    public PreInspectionView() {
        setPreInspectionView();
    }

    private void setPreInspectionView() {
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Предварительный осмотр");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->{
            if (checkNull()) {
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("UpperLevel"));
            }
        });
        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("InputData")));

        add(h1);
        add(h2);

        photo();
        petition();
        preparation();
        safety();
        errorСodes();
        сolorGaze();
        noise();
        checkMotion();

        add(new HorizontalLayout(back, proceed));
    }

    private void photo() {
        var buffer = new MultiFileMemoryBuffer();
        Button buttonPhoto = new Button("Загрузить фото");
        Upload setPhoto = new Upload(buffer);
        setPhoto.setDropAllowed(true);
        setPhoto.setUploadButton(buttonPhoto);
        setPhoto.setDropAllowed(false);
        NativeLabel photoLabel = new NativeLabel("Загрузите несколько фото общего вида");
        setPhoto.setId("photo");
        photoLabel.setFor(setPhoto.getId().get());
        Div getPhoto = new Div(photoLabel, setPhoto);
        add(getPhoto);
    }

    private void petition() {
        String label = "Жалобы, предложения";
        String textHelper = "Запишите жалобы, пожелания оператора. При их отсутствии напишите \"нет\"";
        petition = setTextArea(label, textHelper);
        add(petition);
    }

    private void preparation() {
        String label = "Подготовка к проверке";
        String textHelper = """
                Припаркуйте машину на ровном месте, свободном от людей и препятствий.
                Включите нейтральную передачу
                Включите стояночный тормоз
                Опустите рабочие инструменты на землю
                Поверните переключатель запуска двигателя в положение «ВЫКЛ» и выньте ключ.""";
        preparation = setCheckbox(label, textHelper);
        add(preparation);
    }

    private void safety() {
        String label = "Проверка безопасности";
        String textHelper = """
                Убедитесь, что под машиной, вокруг машины или на ней нет никого; убедиться, что в близи нет персонала
                Обойдите машину и осмотрите нижнюю часть машины на наличие видимых признаков утечек жидкости.
                Проверьте уровень моторного масла, гидравлического масла, трансмиссионного масла и охлаждающей жидкости; при необходимости долейте масло или охлаждающую жидкость до минимального уровня для каждой системы.""";
        safety = setCheckbox(label, textHelper);
        add(safety);
    }

    private void errorСodes() {
        String label = "Коды неисправностей";
        String textHelper = "Запишите  неисправности машины из журнала ошибок" +
                " на панели приборов";
        codes = setTextArea(label, textHelper);
        add(codes);
    }

    private void сolorGaze() {
        String label = "Цвет выхлопных газов двигателя";
        String textHelper = "Запустите машину и пронаблюдайте за цветом выхлопных газов.\n" +
                "Запишите цвет выхлопных газов";
        colorGaze = setTextArea(label, textHelper);
        add(colorGaze);
    }

    private void noise() {
        String label = "Необычные шумы";
        String textHelper = """
                Повышенный шум, исходящий от гидравлического насоса
                Повышенный шум из моторного отсека
                Повышенный шум, исходящий от передних и задних дифференциалов/конечных передач и трансмиссии""";
        noise = setTextArea(label, textHelper);
        add(noise);
    }

    private void checkMotion() {
        String label = "Проверка в движении";
        String textHelper = "Удары, толчки при переключении направлений хода, передач, выполнении поворотов";
        checkMotion = setTextArea(label, textHelper);
        add(checkMotion);
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

    private Checkbox setCheckbox(String label, String textHelper) {
        Checkbox checkbox = new Checkbox(label);
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        checkbox.setHelperComponent(button);
        checkbox.setTooltipText(textHelper);
        Tooltip tooltip = checkbox.getTooltip().withManual(true);
        button.addClickListener(event -> tooltip.setOpened(!tooltip.isOpened()));
        return checkbox;
    }

    public JsonObject getPreInspectionViewJsonObject() {
        JsonObject jsonObject = new JsonObject();
        JsonObject preInspection = new JsonObject();
        preInspection.addProperty("petition", petition.getValue());
        preInspection.addProperty("preparation", preparation.getValue());
        preInspection.addProperty("safety", safety.getValue());
        preInspection.addProperty("codes", codes.getValue());
        preInspection.addProperty("colorGaze", colorGaze.getValue());
        preInspection.addProperty("noise", noise.getValue());
        preInspection.addProperty("checkMotion", checkMotion.getValue());
        jsonObject.add("preInspection", preInspection);
        return jsonObject;
    }

    private boolean checkNull() {
        if (petition.isEmpty()) {
            Notification.show("Заполните жалобы. предложения");
            petition.focus();
            return false;
        }
        if (preparation.isEmpty()) {
            Notification.show("Подготовьте технику к проверке");
            preparation.focus();
            return false;
        }

        if (safety.isEmpty()) {
            Notification.show("Выполните меры безопасности");
            safety.focus();
            return false;
        }

        if (codes.isEmpty()) {
            Notification.show("Заполните коды неисправностей");
            codes.focus();
            return false;
        }
        if (colorGaze.isEmpty()) {
            Notification.show("Заполните цвет выхлопных газов");
            colorGaze.focus();
            return false;
        }
        if (noise.isEmpty()) {
            Notification.show("Заполните необычные шумы");
            noise.focus();
            return false;
        }
        if (checkMotion.isEmpty()) {
            Notification.show("Заполните проверку в движении");
            checkMotion.focus();
            return false;
        }
        return true;
    }

}
