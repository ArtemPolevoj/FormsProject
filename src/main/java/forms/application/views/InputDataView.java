package forms.application.views;

import com.nimbusds.jose.shaded.gson.JsonObject;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.combobox.ComboBox;

import forms.application.model.MachineEntity;
import forms.application.service.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@PageTitle("InputData")
@Route(value = "InputData")
@Service
public class InputDataView extends VerticalLayout {


    private final ImplementerServiceImpl implementerService;
    private final OrganizationServiceImpl organizationService;
    private final DivisionEntityServiceImpl divisionService;
    private final TypeMachineServiceImpl typeMachineService;
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
                         TypeMachineServiceImpl typeMachineService,
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
            if (checkNull() && checkOperationTime()) {
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

        implementer();
        division();
        typeMachine();
        organization();
        manufacturer();
        model();
        businessNumber();
        serialNumber();
        operationTime();

        add(new HorizontalLayout(back, proceed));
    }

    private void implementer() {
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

    private void division() {
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

    private void typeMachine() {

        typeMachine.setItems(typeMachineService.getAllTypeMachines());
        typeMachine.addValueChangeListener(e -> setOrganization(division.getValue(), typeMachine.getValue()));
        typeMachine.setHelperText("Выберете тип техники из списка");
        add(typeMachine);
    }


    private void organization() {

        organization.setAllowCustomValue(true);
//        organization.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            organizationService.createByName(customValue);
//            organization.setItems(organizationService.getAllNames());
//            organization.setValue(customValue);
//        });

        //  organization.setItems(organizationService.getAllNames());
        organization.setHelperText("Выберете из списка или введите нового заказчика");

        organization.addValueChangeListener(e -> setManufacturer(organization.getValue(), division.getValue()));

        add(organization);
    }


    private void manufacturer() {

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

    private void model() {

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

    private void businessNumber() {

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

    private void serialNumber() {

        serialNumber.setAllowCustomValue(true);
//        serialNumber.addCustomValueSetListener(e -> {
//            String customValue = e.getDetail();
//            serialNumber.setItems(serialNumberList);
//            serialNumber.setValue(customValue);
//        });

        serialNumber.setHelperText("Выберете из списка или введите новый сер номер");
        add(serialNumber);
    }

    private void operationTime() {
        Div suffix = new Div();
        suffix.setText("м.ч.");
        operationTime.setSuffixComponent(suffix);
        operationTime.setHelperText("Введите время наработки(только целые числа)");
        operationTime.addValueChangeListener(e -> checkOperationTime());
        add(operationTime);
    }

    private void setOrganization(String divisionName, String typeMachine) {
        List<String> organizationList = new ArrayList<>();
        for (MachineEntity machine : machineList) {
            if (machine.getDivision().getName().equals(divisionName)
                    && machine.getTypeMachine().getTypeMachine().equals(typeMachine)) {
                organizationList.add(machine.getOrganization().getName());
            }
        }
        if (organizationList.isEmpty()) {
            this.organization.clear();
            organization.setItems(organizationService.getAllNames());
        } else {
            this.organization.setValue(organizationList.getFirst());
            this.organization.setItems(organizationList);
        }
    }


    private void setManufacturer(String organizationName, String divisionName) {
        List<String> manufacturersList = new ArrayList<>();
        List<String> manufacturers = new ArrayList<>();
        machineList.forEach(m -> manufacturers.add(m.getManufacturer()));
        for (MachineEntity machine : machineList) {
            if (machine.getOrganization().getName().equals(organizationName) && machine.getDivision().getName().equals(divisionName)) {
                manufacturersList.add(machine.getManufacturer());
            }
        }
        if (manufacturersList.isEmpty()) {
            manufacturer.setItems(manufacturers);
        } else {
            this.manufacturer.setItems(manufacturersList);
            this.manufacturer.setValue(manufacturersList.getFirst());

        }
    }

    private void setModel(String manufacturerName) {
        List<String> modelListManufacturer = new ArrayList<>();
        List<String> modelList = new ArrayList<>();
        machineList.forEach(machine -> modelList.add((machine.getModel())));

        for (MachineEntity machine : machineList) {
            if (machine.getManufacturer().equals(manufacturerName)) {
                modelListManufacturer.add(machine.getModel());
            }
        }
        if (modelListManufacturer.isEmpty()) {
            model.setItems(modelList);
        } else {
            this.model.setItems(modelListManufacturer);
            this.model.setValue(modelListManufacturer.getFirst());

        }
    }

    private void setBusinessNumber(String modelName) {
        List<String> businessNumberList = new ArrayList<>();
        List<String> businessNumber = new ArrayList<>();
        machineList.forEach(machine -> businessNumberList.add(machine.getBusinessNumber()));
        for (MachineEntity machine : machineList) {
            if (machine.getModel().equals(modelName)) {
                businessNumber.add(machine.getBusinessNumber());
            }
        }
        if (businessNumber.isEmpty()) {
            this.businessNumber.setItems(businessNumberList);
        } else {
            this.businessNumber.setItems(businessNumber);
            this.businessNumber.setValue(businessNumber.getFirst());

        }
    }

    private void setSerialNumber(String businessNumber) {
        List<String> serialNumberList = new ArrayList<>();
        List<String> serial = new ArrayList<>();
        machineList.forEach(machine -> serialNumberList.add(machine.getSerialNumber()));
        for (MachineEntity machine : machineList) {
            if (machine.getBusinessNumber().equals(businessNumber)) {
                serial.add(machine.getSerialNumber());
            }
        }
        if (serial.isEmpty()) {
            this.serialNumber.setItems(serialNumberList);
        } else {
            this.serialNumber.setItems(serial);
            this.serialNumber.setValue(serial.getFirst());
        }
    }

    private boolean checkNull() {
        if (implementer.getValue() == null) {
            Notification.show("Заполните исполнителя");
            implementer.focus();
            return false;
        }
        if (division.getValue() == null) {
            Notification.show("Заполните подразделение");
            division.focus();
            return false;
        }

        if (typeMachine.getValue() == null) {
            Notification.show("Заполните тип техники");
            typeMachine.focus();
            return false;
        }

        if (organization.getValue() == null) {
            Notification.show("Заполните заказчика");
            organization.focus();
            return false;
        }
        if (manufacturer.getValue() == null) {
            Notification.show("Заполните производителя");
            manufacturer.focus();
            return false;
        }
        if (model.getValue() == null) {
            Notification.show("Заполните модель");
            model.focus();
            return false;
        }
        if (businessNumber.getValue() == null) {
            Notification.show("Заполните хоз номер");
            businessNumber.focus();
            return false;
        }
        if (serialNumber.getValue() == null) {
            Notification.show("Заполните сер номер");
            serialNumber.focus();
            return false;
        }
        if (operationTime.getValue() == null) {
            Notification.show("Заполните наработку");
            operationTime.focus();
            return false;
        }
        return true;
    }


    private boolean checkOperationTime() {
        boolean flag = true;
        MachineEntity machine = new MachineEntity();
        machine.setManufacturer(manufacturer.getValue());
        machine.setModel(model.getValue());
        machine.setBusinessNumber(businessNumber.getValue());
        machine.setSerialNumber(serialNumber.getValue());
        for (MachineEntity m : machineList) {
            if (m.equals(machine) && m.getOperatingTime() >= operationTime.getValue()) {
                flag = false;
                Notification.show("Внесённая наработка меньше или равна предыдущей наработке("
                        + m.getOperatingTime() + ").");
                operationTime.focus();
                operationTime.clear();
                break;
            }
        }
        return flag;
    }

    public String getTypeMachine() {
        return typeMachine.getValue();
    }
    public JsonObject getInputDataJsonObject() {
        JsonObject jsonObject = new JsonObject();
        JsonObject machineList = new JsonObject();
        machineList.addProperty("division", division.getValue());
        machineList.addProperty( "typeMachine", typeMachine.getValue());
        machineList.addProperty( "organization", organization.getValue());
        machineList.addProperty( "manufacturer", manufacturer.getValue());
        machineList.addProperty("model", model.getValue());
        machineList.addProperty( "businessNumber", businessNumber.getValue());
        machineList.addProperty( "serialNumber", serialNumber.getValue());
        machineList.addProperty( "operationTime", operationTime.getValue());
        jsonObject.addProperty("implementer", implementer.getValue());
        jsonObject.add("machine", machineList);
        return jsonObject;
    }

}