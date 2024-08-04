package forms.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Wishes")
@Route(value = "Wishes")
//@Uses(Icon.class)
public class WishesView extends VerticalLayout {
    public WishesView() {


        setWishesView();


    }
    private void setWishesView(){
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Пожелания");
        Button proceed = new Button("Получить");
        proceed.addClickListener(e ->
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("InputData")));

        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("Attachments")));

        add(h1);
        add(h2);

        getWishes();

        add(new HorizontalLayout(back, proceed));
    }
    private void getWishes(){
        TextArea wish = new TextArea("Пожелания, внедрения");
        Button buttonWishShow = new Button("?");

        buttonWishShow.addClickListener(e -> Notification.show("Напишите пожелания," +
                " предложения. Опишите внедрения введенные заказчиком"));

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

        HorizontalLayout wishLayout = new HorizontalLayout(wish, buttonWishShow);

        add(wishLayout);
        add(getPhoto);
    }
}