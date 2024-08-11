package forms.application.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;
import forms.application.model.MachineryEntity;
import forms.application.service.*;
import java.util.ArrayList;
import java.util.List;

@PageTitle("InputData")
@Route(value = "InputData")
public class InputDataView extends VerticalLayout {


    private final ImplementerEntityServiceImpl implementerEntityService;
    private final OrganizationEntityServiceImpl organizationEntityService;
    private final DivisionEntityServiceImpl divisionEntityService;
    private final TypeMachineEntityServiceImp typeMachineEntityService;
    private final MachineryEntityServiceImpl machineEntityService;
    private final ComboBox<String> typeMachine = new ComboBox<>("Тип техники");
    private final ComboBox<String> organization = new ComboBox<>("Заказчик");
    private final ComboBox<String> manufacturer = new ComboBox<>("Производитель");
    private final ComboBox<String> model = new ComboBox<>("Модель");
    private final ComboBox<String> businessNumber = new ComboBox<>("Хоз номер");
    private final ComboBox<String> serialNumber = new ComboBox<>("Сер номер");
    private final List<MachineryEntity> machineryEntityList;

    public InputDataView(OrganizationEntityServiceImpl organizationEntityService,
                         ImplementerEntityServiceImpl implementerEntityService,
                         DivisionEntityServiceImpl divisionEntityService,
                         TypeMachineEntityServiceImp typeMachineEntityService,
                         MachineryEntityServiceImpl machineEntityService) {

        this.implementerEntityService = implementerEntityService;
        this.organizationEntityService = organizationEntityService;
        this.divisionEntityService = divisionEntityService;
        this.typeMachineEntityService = typeMachineEntityService;
        this.machineEntityService = machineEntityService;
        machineryEntityList = machineEntityService.getAll();
        setInputDataView();
    }

    private void setInputDataView() {
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
        getDivision();
        getOrganization();
        getTypeMachine();
        getManufacturer();
        getModel();
        getBusinessNumber();
        getSerialNumber();
        getOperationTime();

        add(new HorizontalLayout(back, proceed));
    }

    private void getImplementer() {

        ComboBox<String> implementer = new ComboBox<>("Исполнитель");
        implementer.setAllowCustomValue(true);
        implementer.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            implementerEntityService.createByName(customValue);
            implementer.setItems(implementerEntityService.getImplementersNames());
            implementer.setValue(customValue);
        });
        add(implementer);
        implementer.setItems(implementerEntityService.getImplementersNames());
        implementer.setHelperText("Выберете из списка или введите нового исполнителя");
    }

    private void getDivision() {
        ComboBox<String> division = new ComboBox<>("Подразделение");
        // Пока не используется
//        division.setAllowCustomValue(true);
//        division.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            divisionEntityService.createByName(customValue);
//            division.setItems(divisionEntityService.getAllNames());
//            division.setValue(customValue);
//        });
        division.setItems(divisionEntityService.getAllNames());
        //Настройка других полей
        division.addValueChangeListener(e -> setOrganization(division.getValue()));

        division.setHelperText("Выберете подразделение из списка");
        add(division);
    }

    private void getOrganization() {

        organization.setAllowCustomValue(true);
        organization.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            organizationEntityService.createByName(customValue);
            organization.setItems(organizationEntityService.getAllNames());
            organization.setValue(customValue);
        });
        organization.setItems(organizationEntityService.getAllNames());
        organization.setHelperText("Выберете из списка или введите новую организацию");

        organization.addValueChangeListener(e -> setTypeMachine(organization.getValue()));

        add(organization);
    }

    private void getTypeMachine() {
        typeMachine.setItems(typeMachineEntityService.getAllNames());

        typeMachine.addValueChangeListener(e -> setManufacturer(organization.getValue()));

        typeMachine.setHelperText("Выберете тип техники из списка");
        add(typeMachine);
    }

    private void getManufacturer() {

        List<String> manufacturers = new ArrayList<>();
        machineryEntityList.forEach(machine -> manufacturers.add(machine.getManufacturer()));

        manufacturer.setAllowCustomValue(true);
        manufacturer.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            manufacturer.setItems(manufacturers);
            manufacturer.setValue(customValue);
        });
        manufacturer.setItems(manufacturers);
        manufacturer.setHelperText("Выберете из списка или введите нового производителя");

        manufacturer.addValueChangeListener(e -> setModel(manufacturer.getValue()));

        add(manufacturer);
    }

    private void getModel() {

        List<String> modelList = new ArrayList<>();
        machineryEntityList.forEach(machine -> modelList.add(machine.getModel()));
        model.setAllowCustomValue(true);
        model.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            model.setItems(modelList);
            model.setValue(customValue);
        });
        model.setItems(modelList);
        model.setHelperText("Выберете из списка или введите новую модель");

        model.addValueChangeListener(e -> setBusinessNumber(model.getValue()));

        add(model);
    }

    private void getBusinessNumber() {
        List<String> businessNumberList = new ArrayList<>();
        machineryEntityList.forEach(machine -> businessNumberList.add(machine.getBusinessNumber()));
        businessNumber.setAllowCustomValue(true);
        businessNumber.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            businessNumber.setItems(businessNumberList);
            businessNumber.setValue(customValue);
        });
        businessNumber.setItems(businessNumberList);
        businessNumber.setHelperText("Выберете из списка или введите новый хоз номер");

        businessNumber.addValueChangeListener(e -> setSerialNumber(businessNumber.getValue()));

        add(businessNumber);
    }

    private void getSerialNumber() {
        List<String> serialNumberList = new ArrayList<>();
        machineryEntityList.forEach(machine -> serialNumberList.add(machine.getSerialNumber()));
        serialNumber.setAllowCustomValue(true);
        serialNumber.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            serialNumber.setItems(serialNumberList);
            serialNumber.setValue(customValue);
        });
        serialNumber.setItems(serialNumberList);
        serialNumber.setHelperText("Выберете из списка или введите новый сер номер");
        add(serialNumber);
    }

    private void getOperationTime() {
        TextField operationTime = new TextField("Наработка");
        operationTime.setHelperText("Введите время наработки");
        add(operationTime);
    }

    private void setOrganization(String divisionName) {
        String organizationsName = machineryEntityList.stream().
                filter(organization -> organization.getDivision().equals(divisionName)).
                toList().getFirst().getOrganization();
        if (!organizationsName.isEmpty()) {
            organization.setValue(organizationsName);
        }

    }

    private void setTypeMachine(String organizationName) {
        List<MachineryEntity> machines = machineEntityService.getByOrganization(organizationName);
        String typeMachineName = machines.stream().
                filter(organization -> organization.getOrganization().equals(organizationName)).
                toList().getFirst().getType();

        if (!typeMachineName.isEmpty()) {
            typeMachine.setValue(machines.getFirst().getType());
        }
    }

    private void setManufacturer(String organizationName) {
        String manufacturersName = machineryEntityList.stream().
                filter(manufacturer -> manufacturer.getOrganization().equals(organizationName)).
                toList().getFirst().getManufacturer();
        if (!manufacturersName.isEmpty()) {
            manufacturer.setValue(manufacturersName);
        }
    }

    private void setModel(String manufacturerName) {
        String nameModel = machineryEntityList.stream().
                filter(modelName -> modelName.getManufacturer().equals(manufacturerName)).
                toList().getFirst().getModel();
        if (!nameModel.isEmpty()) {
            this.model.setValue(nameModel);
        }
    }

    private void setBusinessNumber(String modelName) {
        String businessName = machineryEntityList.stream().
                filter(businessNumberName -> businessNumberName.getModel().equals(modelName)).
                toList().getFirst().getBusinessNumber();
        if (!businessName.isEmpty()) {
            this.businessNumber.setValue(businessName);
        }
    }

    private void setSerialNumber(String businessNumber) {
        String serial = machineryEntityList.stream().
                filter(serialNumber -> serialNumber.getBusinessNumber().equals(businessNumber)).
                toList().getFirst().getSerialNumber();
        if (!serial.isEmpty()) {
            this.serialNumber.setValue(serial);

        }
    }
}