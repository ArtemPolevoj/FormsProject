package forms.application.views;

import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
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
    private final ComboBox<String> implementer = new ComboBox<>("Исполнитель");
    private final ComboBox<String> typeMachine = new ComboBox<>("Тип техники");
    private final ComboBox<String> division = new ComboBox<>("Подразделение");
    private final ComboBox<String> organization = new ComboBox<>("Заказчик");
    private final ComboBox<String> manufacturer = new ComboBox<>("Производитель");
    private final ComboBox<String> model = new ComboBox<>("Модель");
    private final ComboBox<String> businessNumber = new ComboBox<>("Хоз номер");
    private final ComboBox<String> serialNumber = new ComboBox<>("Сер номер");
    private final IntegerField operationTime = new IntegerField("Наработка");
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
        machineList = machineService.findAll();
        setInputDataView();
    }

    private void setInputDataView() {
        H1 h1 = new H1("ОСМОТР ТЕХНИКИ");
        H2 h2 = new H2("Входные данные");
        Button proceed = new Button("Продолжить");
        proceed.addClickListener(e -> {
            if (!checkNull()) {
                proceed.getUI().ifPresent(ui ->
                        ui.navigate("PreInspection"));
            }
        });


        Button back = new Button("Назад");
        back.addClickListener(e ->
                back.getUI().ifPresent(ui ->
                        ui.navigate("")));

        add(h1);
        add(h2);

        getImplementer();
        getDivision();
        getTypeMachine();
        getOrganization();
        getManufacturer();
        getModel();
        getBusinessNumber();
        getSerialNumber();
        getOperationTime();

        add(new HorizontalLayout(back, proceed));
    }

    private void getImplementer() {
        implementer.setAllowCustomValue(true);
//        implementer.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            implementerService.createByName(customValue);
//            implementer.setItems(implementerService.getAllNames());
//            implementer.setValue(customValue);
//        });
        implementer.setItems(implementerService.getAllNames());
        implementer.setHelperText("Выберете из списка или введите нового исполнителя");
        add(implementer);
    }

    private void getDivision() {
        division.setAllowCustomValue(true);
//        division.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//           // divisionService.createByName(customValue);
//            division.setItems(divisionService.getAllNames());
//            division.setValue(customValue);
//                });

        division.setItems(divisionService.getAllNames());
        //Настройка других полей
        division.addValueChangeListener(e -> setOrganization(division.getValue(), typeMachine.getValue()));

        division.setHelperText("Выберете подразделение из списка или введите новое подразделение");
        add(division);
    }
    private void getTypeMachine() {
        typeMachine.setItems(typeMachineService.getAllNames());
        typeMachine.addValueChangeListener(e -> setOrganization(division.getValue(), typeMachine.getValue())
        );
        typeMachine.setHelperText("Выберете тип техники из списка");
        add(typeMachine);
    }


    private void getOrganization() {

        organization.setAllowCustomValue(true);
//        organization.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            organizationService.createByName(customValue);
//            organization.setItems(organizationService.getAllNames());
//            organization.setValue(customValue);
//        });

      //  organization.setItems(organizationService.getAllNames());
        organization.setHelperText("Выберете из списка или введите нового заказчика");

        organization.addValueChangeListener(e -> setManufacturer(organization.getValue()));

        add(organization);
    }


    private void getManufacturer() {

        manufacturer.setAllowCustomValue(true);
//        manufacturer.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            manufacturer.setItems(manufacturers);
//            manufacturer.setValue(customValue);
//        });

        manufacturer.setHelperText("Выберете из списка или введите нового производителя");

        manufacturer.addValueChangeListener(e -> setModel(manufacturer.getValue()));

        add(manufacturer);
    }

    private void getModel() {

        model.setAllowCustomValue(true);
//        model.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            model.setItems(modelList);
//            model.setValue(customValue);
//        });

        model.setHelperText("Выберете из списка или введите новую модель");

        model.addValueChangeListener(e -> setBusinessNumber(model.getValue()));

        add(model);
    }

    private void getBusinessNumber() {

        businessNumber.setAllowCustomValue(true);
//        businessNumber.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            businessNumber.setItems(businessNumberList);
//            businessNumber.setValue(customValue);
//        });

        businessNumber.setHelperText("Выберете из списка или введите новый хоз номер");

        businessNumber.addValueChangeListener(e -> setSerialNumber(businessNumber.getValue()));

        add(businessNumber);
    }

    private void getSerialNumber() {

        serialNumber.setAllowCustomValue(true);
//        serialNumber.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            serialNumber.setItems(serialNumberList);
//            serialNumber.setValue(customValue);
//        });

        serialNumber.setHelperText("Выберете из списка или введите новый сер номер");
        add(serialNumber);
    }

    private void getOperationTime() {
        Div suffix = new Div();
        suffix.setText("м.ч.");
        operationTime.setSuffixComponent(suffix);
        operationTime.setHelperText("Введите время наработки(только целые числа)");
        add(operationTime);
    }

    private void setOrganization(String divisionName, String typeMachine) {
        List<String> organizationList = new ArrayList<>();
        for (MachineEntity machine : machineList) {
            if (machine.getDivision().getName().equals(divisionName)
                    && machine.getModel().getModel().equals(typeMachine)) {
                organizationList.add(machine.getOrganization().getName());
            }
        }
        if (organizationList.isEmpty()) {
            this.organization.clear();
            organization.setItems(organizationService.getAllNames());
        }else {
            if (organizationList.size() == 1) {
                this.organization.setValue(organizationList.getFirst());
            }else {
                this.organization.setItems(organizationList);
            }
         }
    }


    private void setManufacturer(String organizationName) {
        List<String> manufacturersList = new ArrayList<>();
        List<String> manufacturers = new ArrayList<>();
        machineList.forEach(m -> manufacturers.add(m.getManufacturer()));
        for (MachineEntity machine : machineList) {
            if (machine.getOrganization().getName().equals(organizationName)) {
                manufacturersList.add(machine.getManufacturer());
            }
        }
        if (manufacturersList.isEmpty()) {
            manufacturer.setItems(manufacturers);
        } else {
            if (manufacturersList.size() == 1) {
                this.manufacturer.setValue(manufacturersList.getFirst());
            } else {
                this.manufacturer.setItems(manufacturersList);
            }
        }
    }
    private void setModel(String manufacturerName) {
        List<String> modelListManufacturer = new ArrayList<>();
        List<String> modelList = new ArrayList<>();
        machineList.forEach(machine -> modelList.add((machine.getModel().getModel())));

        for (MachineEntity machine : machineList) {
            if (machine.getManufacturer().equals(manufacturerName)) {
                modelListManufacturer.add(machine.getModel().getModel());
            }
        }
        if (modelListManufacturer.isEmpty()) {
            model.setItems(modelList);
        } else {
            if (modelListManufacturer.size() == 1) {
                this.model.setValue(modelListManufacturer.getFirst());
            } else {
                this.model.setItems(modelListManufacturer);
            }
        }
    }

    private void setBusinessNumber(String modelName) {
        List<String> businessNumberList = new ArrayList<>();
        List<String> businessNumber = new ArrayList<>();
        machineList.forEach(machine -> businessNumberList.add(String.valueOf(machine.getBusinessNumber())));
        for (MachineEntity machine : machineList) {
            if (machine.getModel().getModel().equals(modelName)) {
                businessNumber.add(machine.getBusinessNumber().toString());
            }
        }
        if (businessNumber.isEmpty()) {
            this.businessNumber.setItems(businessNumberList);
        } else {
            if (businessNumber.size() == 1) {
                this.businessNumber.setValue(businessNumber.getFirst());
            } else {
                this.businessNumber.setItems(businessNumber);
            }
        }
    }

    private void setSerialNumber(String businessNumber) {
        List<String> serialNumberList = new ArrayList<>();
        List<String> serial = new ArrayList<>();
        machineList.forEach(machine -> serialNumberList.add(machine.getSerialNumber()));
        for (MachineEntity machine : machineList) {
            if (machine.getBusinessNumber().toString().equals(businessNumber)) {
                serial.add(machine.getSerialNumber());
            }
        }
        if (serial.isEmpty()) {
            this.serialNumber.setItems(serialNumberList);
        } else {
            if (serialNumberList.size() == 1) {
                this.serialNumber.setValue(serial.getFirst());
            } else {
                this.serialNumber.setItems(serial);
            }
        }
    }

    private boolean checkNull() {
        if (implementer.getValue() == null) {
            Notification.show("Заполните исполнителя");
            implementer.focus();
            return true;
        }
        if (division.getValue() == null) {
            Notification.show("Заполните подразделение");
            division.focus();
            return true;
        }

        if (typeMachine.getValue() == null) {
            Notification.show("Заполните тип техники");
            typeMachine.focus();
            return true;
        }

        if (organization.getValue() == null) {
            Notification.show("Заполните заказчика");
            organization.focus();
            return true;
        }
        if (manufacturer.getValue() == null) {
            Notification.show("Заполните производителя");
            manufacturer.focus();
            return true;
        }
        if (model.getValue() == null) {
            Notification.show("Заполните модель");
            model.focus();
            return true;
        }
        if (businessNumber.getValue() == null) {
            Notification.show("Заполните хоз номер");
            businessNumber.focus();
            return true;
        }
        if (serialNumber.getValue() == null) {
            Notification.show("Заполните сер номер");
            serialNumber.focus();
            return true;
        }
        if (operationTime.getValue() == null) {
            Notification.show("Заполните наработку");
            operationTime.focus();
            return true;
        }
        return false;
    }

}