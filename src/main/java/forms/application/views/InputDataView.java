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
import forms.application.model.MachineEntity;
import forms.application.service.*;
import java.util.ArrayList;
import java.util.List;

@PageTitle("InputData")
@Route(value = "InputData")
public class InputDataView extends VerticalLayout {


    private final ImplementerServiceImpl implementerService;
    private final OrganizationServiceImpl organizationService;
    private final DivisionEntityServiceImpl divisionService;
    private final TypeMachineEntityServiceImp typeMachineService;
    private final MachineServiceImpl machineService;
    private final ComboBox<String> typeMachine = new ComboBox<>("Тип техники");
    private final ComboBox<String> organization = new ComboBox<>("Заказчик");
    private final ComboBox<String> manufacturer = new ComboBox<>("Производитель");
    private final ComboBox<String> model = new ComboBox<>("Модель");
    private final ComboBox<String> businessNumber = new ComboBox<>("Хоз номер");
    private final ComboBox<String> serialNumber = new ComboBox<>("Сер номер");
    private final List<MachineEntity> machineList;

    public InputDataView(OrganizationServiceImpl organizationEntityService,
                         ImplementerServiceImpl implementerService,
                         DivisionEntityServiceImpl divisionService,
                         TypeMachineEntityServiceImp typeMachineService,
                         MachineServiceImpl machineService) {

        this.implementerService = implementerService;
        this.organizationService = organizationEntityService;
        this.divisionService = divisionService;
        this.typeMachineService = typeMachineService;
        this.machineService = machineService;
        machineList = machineService.findAll();
        setInputDataView();
    }

    private void setInputDataView() {
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Входные данные");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e ->{proceed.getUI().ifPresent(ui ->
                ui.navigate("PreInspection"));
            // TODO сделать проверку на заполнение;
        });


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
            implementerService.createByName(customValue);
            implementer.setItems(implementerService.getAllNames());;
            implementer.setValue(customValue);
        });
        add(implementer);
        implementer.setItems(implementerService.getAllNames());
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
        division.setItems(divisionService.getAllNames());
        //Настройка других полей
        division.addValueChangeListener(e -> setOrganization(division.getValue()));

        division.setHelperText("Выберете подразделение из списка");
        add(division);
    }

    private void getOrganization() {

        organization.setAllowCustomValue(true);
        organization.addCustomValueSetListener(e -> {
            String customValue = e.getDetail();
            organizationService.createByName(customValue);
            organization.setItems(organizationService.getAllNames());
            organization.setValue(customValue);
        });
        organization.setItems(organizationService.getAllNames());
        organization.setHelperText("Выберете из списка или введите новую организацию");

        organization.addValueChangeListener(e -> setTypeMachine(organization.getValue()));

        add(organization);
    }

    private void getTypeMachine() {


        typeMachine.addValueChangeListener(e ->{

            typeMachine.setItems(typeMachineService.getAllNames());
            setManufacturer(organization.getValue());
        } );

        typeMachine.setHelperText("Выберете тип техники из списка");
        add(typeMachine);
    }

    private void getManufacturer() {
        List<String> manufacturers = new ArrayList<>();
        machineList.forEach(machine -> manufacturers.add(machine.getManufacturer()));
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
        machineList.forEach(machine -> modelList.add(String.valueOf(machine.getModel())));
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
        machineList.forEach(machine -> businessNumberList.add(String.valueOf(machine.getBusinessNumber())));
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
        machineList.forEach(machine -> serialNumberList.add(machine.getSerialNumber()));
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
        String organizationsName = String.valueOf(machineList.stream().
                filter(organization -> organization.getDivision().equals(divisionName)).
                toList().getFirst().getOrganization());
        if (!organizationsName.isEmpty()) {
            organization.setValue(organizationsName);
        }

    }

    private void setTypeMachine(String organizationName) {
        List<MachineEntity> machines = machineService.getAll();
        String typeMachineName = String.valueOf(machines.stream().
                filter(organization -> organization.getOrganization().equals(organizationName)).
                toList().getFirst());

        if (!typeMachineName.isEmpty()) {
            typeMachine.setValue(String.valueOf(machines.getFirst()));
        }
    }

    private void setManufacturer(String organizationName) {
        String manufacturersName = machineList.stream().
                filter(manufacturer -> manufacturer.getOrganization().equals(organizationName)).
                toList().getFirst().getManufacturer();
        if (!manufacturersName.isEmpty()) {
            manufacturer.setValue(manufacturersName);
        }
    }

    private void setModel(String manufacturerName) {
        String nameModel = String.valueOf(machineList.stream().
                filter(modelName -> modelName.getManufacturer().equals(manufacturerName)).
                toList().getFirst().getModel());
        if (!nameModel.isEmpty()) {
            this.model.setValue(nameModel);
        }
    }

    private void setBusinessNumber(String modelName) {
        String businessName = String.valueOf(machineList.stream().
                filter(businessNumberName -> businessNumberName.getModel().equals(modelName)).
                toList().getFirst().getBusinessNumber());
        if (!businessName.isEmpty()) {
            this.businessNumber.setValue(businessName);
        }
    }

    private void setSerialNumber(String businessNumber) {
        String serial = machineList.stream().
                filter(serialNumber -> serialNumber.getBusinessNumber().equals(businessNumber)).
                toList().getFirst().getSerialNumber();
        if (!serial.isEmpty()) {
            this.serialNumber.setValue(serial);

        }
    }
}