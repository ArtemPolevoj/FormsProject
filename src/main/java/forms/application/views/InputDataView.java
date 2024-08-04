package forms.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import forms.application.model.ImplementerEntity;
import forms.application.service.ImplementerEntityServiceImpl;
import forms.application.service.OrganizationEntityServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@PageTitle("InputData")
@Route(value = "InputData")
public class InputDataView extends VerticalLayout {


    private final ImplementerEntityServiceImpl implementerEntityService;

    public InputDataView(OrganizationEntityServiceImpl organizationEntity,
                          ImplementerEntityServiceImpl implementerEntityService) {

        this.implementerEntityService = implementerEntityService;
        setInputDataView();
    }
    private void setInputDataView(){
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Входные данные");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("UpperLevel")));

        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("")));

        add(h1);
        add(h2);

        getImplementer();
        getOrganization();

        add(new HorizontalLayout(back, proceed));
    }
    private void getOrganization(){
        /* Тестовое поле для получения из БД данных. При отсутствии позволят записать новое значение.
         Кнопка "test" для проверки работы
         */
        List<String> items = new ArrayList<>(
                Arrays.asList("Северсталь", "Лафарж", "Кареллеспром"));
        Paragraph paragraph = new Paragraph("Тестовое поле для получения из БД данных. При отсутствии позволят записать новое значение.\n" +
                "         Кнопка \"test\" для проверки работы");
        add(paragraph);
        ComboBox<String> organization = new ComboBox<>("Заказчик");
        organization.setAllowCustomValue(true);
        organization.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            items.add(customValue);
            organization.setItems(items);
            organization.setValue(customValue);
        });
        add(organization);
        organization.setItems(items);
        organization.setHelperText("Выберете из списка или введите новую организацию");


        Button test = new Button("test");
        test.addClickListener(event -> Notification.show(organization.getValue()));
        add(test);
    }
    private void getImplementer(){
        List<String> implementerList = new ArrayList<>();
        for (ImplementerEntity impl: implementerEntityService.getAll()){
            implementerList.add(impl.getFullName());
        }
        ComboBox<String> implementer = new ComboBox<>("Исполнитель");
        implementer.setAllowCustomValue(true);
        implementer.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
         //   implementerEntityService.create(new ImplementerEntity().setFullName(customValue));
            implementer.setItems(implementerList);
            implementer.setValue(customValue);
        });
        add(implementer);
        implementer.setItems(implementerList);
        implementer.setHelperText("Выберете из списка или введите нового исполнителя");
    }
}
