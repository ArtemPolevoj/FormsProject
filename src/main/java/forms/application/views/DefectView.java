package forms.application.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("Defect")
@Route(value = "Defect", layout = MainLayout.class)
//@Uses(Icon.class)
public class DefectView extends Composite<VerticalLayout> {
    public DefectView() {
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
        getContent().add(defectLayout);
        getContent().add(listWorkLayout);
       getContent().add(getPhoto);
    }
}
