package forms.application.util;

import forms.application.service.TypeMachineServiceImpl;
import forms.application.service.dto.TypeMachinelDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
@RequiredArgsConstructor

public class InitTypeMachineQuestions {
    private final TypeMachineServiceImpl typeService;

    private final JdbcTemplate template;

    @EventListener(ContextRefreshedEvent.class)
    private void initTypeMachineQuestions() {
        Integer size = template.queryForObject("select count(*) from вопросы_типы_машин", Integer.class);
        if (size == null || size == 0) {
            typeService.update(getCrawlerBulldozer());
            typeService.update(getTrackedPipelayer());
            typeService.update(getCrawlerExcavator());
            typeService.update(getWheeledBulldozer());
            typeService.update(getWheeledExcavator());
            typeService.update(getBackhoeLoader());
            typeService.update(getLoader());
            typeService.update(getMotorGrader());
            typeService.update(getDumpTruck());
            typeService.update(getAnother());
        }
    }

    private TypeMachinelDto getTypeMachine(String model, Set<Integer> forthLevel, Set<Integer> fifthLevel) {
        //100-300
        Set<Long> general = LongStream.rangeClosed(1, 35)
                .boxed()
                .collect(Collectors.toSet());
        //400
        Set<Long> level4 = forthLevel.stream()
                .map(i -> (long) i).collect(Collectors.toSet());

        //500
        Set<Long> level5 = fifthLevel.stream()
                .map(i -> (long) i).collect(Collectors.toSet());

        //600
        Set<Long> sixthLevel = Set.of(74L);

        Set<Long> union = union(general, level4, level5, sixthLevel);
        return new TypeMachinelDto(model, union);
    }

    private TypeMachinelDto getCrawlerBulldozer() {
        String model = "Бульдозер гусеничный";
        Set<Integer> level4 = Set.of(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51);
        Set<Integer> level5 = Set.of(64, 65, 66, 67, 68, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getTrackedPipelayer() {
        String model = "Трубоукладчик гусеничный";
        Set<Integer> level4 = Set.of(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51);
        Set<Integer> level5 = Set.of(73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getCrawlerExcavator() {
        String model = "Экскаватор гусеничный";
        Set<Integer> level4 = Set.of(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51);
        Set<Integer> level5 = Set.of(65, 66, 69, 70, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getWheeledBulldozer() {
        String model = "Бульдозер колесный";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(64, 65, 66, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getWheeledExcavator() {
        String model = "Экскаватор колесный";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 53, 54, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(65, 66, 69, 70, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getBackhoeLoader() {
        String model = "Экскаватор-погрузчик";
        Set<Integer> level4 = Set.of(47, 48, 49, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(64, 65, 66, 69, 70, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getLoader() {
        String model = "Погрузчик";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(64, 65, 66, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getMotorGrader() {
        String model = "Автогрейдер";
        Set<Integer> level4 = IntStream.rangeClosed(47, 63)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> level5 = Set.of(64, 65, 66, 67, 68, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getDumpTruck() {
        String model = "Самосвал";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(71, 72, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    private TypeMachinelDto getAnother() {
        String model = "Другое";
        Set<Integer> level4 = IntStream.rangeClosed(36, 63)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> level5 = Set.of(64, 65, 66, 67, 68, 73, 74);
        return getTypeMachine(model, level4, level5);
    }

    @SafeVarargs
    public static <T> Set<T> union(Set<T>... sets) {
        Set<T> unionSet = new HashSet<>();
        for (Set<T> set : sets) {
            unionSet.addAll(set);
        }
        return unionSet;
    }
}

