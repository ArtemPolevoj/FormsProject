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
        H2 h2 = new H2("Входные данные");
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
        TextArea petition = new TextArea("Жалобы, предложения");
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        petition.setSuffixComponent(button);
        petition.setTooltipText("Запишите жалобы, пожелания оператора");
        Tooltip tooltip = petition.getTooltip().withManual(true);
        button.addClickListener(event -> {
            tooltip.setOpened(!tooltip.isOpened());
        });
        add(petition);

    }
    private void getPreparation() {
        Checkbox preparation = new Checkbox("Подготовьте машину к проверке");
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        preparation.setHelperComponent(button);
        preparation.setTooltipText("""
                Припаркуйте машину на ровном месте, свободном от людей и препятствий.
                Включите нейтральную передачу
                Включите стояночный тормоз
                Опустите рабочие инструменты на землю
                Поверните переключатель запуска двигателя в положение «ВЫКЛ» и выньте ключ.""");
        Tooltip tooltip = preparation.getTooltip().withManual(true);
        button.addClickListener(event -> {
            tooltip.setOpened(!tooltip.isOpened());
        });
        add(preparation);
    }

    private void getSafety(){
        Checkbox safety = new Checkbox("Проведение проверки безопасности/подготовительной проверки");
        Button button = new Button(new Icon(VaadinIcon.INFO_CIRCLE));
        button.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE,
                ButtonVariant.LUMO_ICON);
        safety.setHelperComponent(button);
        safety.setTooltipText("""
                Убедитесь, что под машиной, вокруг машины или на ней нет никого; убедиться, что в близи нет персонала/
                Обойдите машину и осмотрите нижнюю часть машины на наличие видимых признаков утечек жидкости.
                Проверьте уровень моторного масла, гидравлического масла, трансмиссионного масла и охлаждающей жидкости;
                при необходимости долейте масло или охлаждающую жидкость до минимального уровня для каждой системы..""");
        Tooltip tooltip = safety.getTooltip().withManual(true);
        button.addClickListener(event -> {
            tooltip.setOpened(!tooltip.isOpened());
        });
        add(safety);
    }

}
