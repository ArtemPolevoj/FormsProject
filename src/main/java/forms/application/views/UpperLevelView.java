package forms.application.views;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@PageTitle("FirstLevel")
@Route(value = "FirstLevel", layout = MainLayout.class)
@Uses(Icon.class)
public class UpperLevelView extends Composite<VerticalLayout> {

    public UpperLevelView() {

        getContent().add(new QuestionView("201", "Внутренний осмотр кабины",
                """
                        Правильная работа сиденья и ремня безопасности
                        Правильная работа двери и замков
                        Изношенные или сломанные оконные защелки или направляющие.
                        Поврежденные или неработоспособные приборы и органы управления"""));
        getContent().add(new QuestionView("202", "Работа климатической установки",
                "Проверить работу климатической установки," +
                " зависимого и независимого отопителя кабины"));
        getContent().add(new QuestionView("203", "Внешний осмотр кабины",
                """
                        "Погнутая или поврежденная конструкция кабины
                        Треснувшее или разбитое стекло
                        Поврежденные или отсутствующие зеркала
                        Изношен или сломан рычаг или щетка стеклоочистителя."""));
        getContent().add(new QuestionView("204", "Осмотр аккумуляторов и аккумуляторных кабелей",
                """
                        Клеммы и кабели аккумуляторной батареи на наличие коррозии или повреждений.
                        Крепление аккумулятора для правильной работы и герметичности
                        Не трутся ли жгуты проводов\
                        Закреплены ли жгуты проводов должным образом"""));
        getContent().add(new QuestionView("205", "Осмотр  отсеков фильтров гидравлики и трансмиссии",
                """
                        Утечки в местах соединения шлангов и масляных фильтров.
                        Потертые, поврежденные или изношенные шланги
                        Потертые, поврежденные или изношенные жгуты проводов или разъемы."""));


    }

}