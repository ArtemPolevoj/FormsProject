package forms.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
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


@PageTitle("PreInspection")
@Route(value = "PreInspection")
public class PreInspectionView extends VerticalLayout {
    public PreInspectionView() {
        setPreInspectionView();
    }

    private void setPreInspectionView() {
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Предварительный осмотр");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("UpperLevel")));

        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("InputData")));

        add(h1);
        add(h2);

        getPhoto();
        getPetition();
        getPreparation();
        getSafety();
        getCode();
        getColorGaze();
        getNoise();
        checkMotion();

        add(new HorizontalLayout(back, proceed));
    }

    private void getPhoto() {
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

    private void getPetition() {
        String label = "Жалобы, предложения";
        String textHelper = "Запишите жалобы, пожелания оператора";
        TextArea petition = setTextArea(label, textHelper);
        add(petition);
    }

    private void getPreparation() {
        String label = "Подготовка к проверке";
        String textHelper = """
                Припаркуйте машину на ровном месте, свободном от людей и препятствий.
                Включите нейтральную передачу
                Включите стояночный тормоз
                Опустите рабочие инструменты на землю
                Поверните переключатель запуска двигателя в положение «ВЫКЛ» и выньте ключ.""";
        Checkbox preparation = setCheckbox(label, textHelper);
        add(preparation);
    }

    private void getSafety(){
        String label = "Проверка безопасности";
        String textHelper = """
                Убедитесь, что под машиной, вокруг машины или на ней нет никого; убедиться, что в близи нет персонала
                Обойдите машину и осмотрите нижнюю часть машины на наличие видимых признаков утечек жидкости.
                Проверьте уровень моторного масла, гидравлического масла, трансмиссионного масла и охлаждающей жидкости; при необходимости долейте масло или охлаждающую жидкость до минимального уровня для каждой системы.""";
        Checkbox safety = setCheckbox(label, textHelper);
        add(safety);
    }

    private void  getCode(){
        String label = "Коды неисправностей";
        String textHelper = "Запишите  неисправности машины из журнала ошибок" +
                " на панели приборов";
        TextArea codes = setTextArea(label, textHelper);
        add(codes);
    }

    private void getColorGaze(){
        String label = "Цвет выхлопных газов двигателя";
        String textHelper = "Запустите машину и пронаблюдайте за цветом выхлопных газов.\n" +
                "Запишите цвет выхлопных газов";
        TextArea colorGaze = setTextArea(label, textHelper);
        add(colorGaze);
    }

    private  void getNoise(){
        String label = "Необычные шумы";
        String textHelper = """
                Повышенный шум, исходящий от гидравлического насоса
                Повышенный шум из моторного отсека
                Повышенный шум, исходящий от передних и задних дифференциалов/конечных передач и трансмиссии""";
        TextArea noise = setTextArea(label, textHelper);
        add(noise);
    }

    private void checkMotion(){
        String label = "Проверка в движении";
        String textHelper = "Удары, толчки при переключении направлений хода, передач, выполнении поворотов";
        TextArea checkMotion = setTextArea(label, textHelper);
        add(checkMotion);
    }

    private TextArea setTextArea(String label, String textHelper){
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

    private Checkbox setCheckbox(String label, String textHelper){
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

}
