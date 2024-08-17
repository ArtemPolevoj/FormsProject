package forms.application.util;

import forms.application.service.ModelService;
import forms.application.service.dto.ModelDto;
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
public class InitMachineQuestions {
    private final ModelService modelService;

    private final JdbcTemplate template;

    @EventListener(ContextRefreshedEvent.class)
    private void initModelQuestions() {
        Integer size = template.queryForObject("select count(*) from вопросы_модели_машин", Integer.class);
        if (size == null || size == 0) {
            modelService.update(getCrawlerBulldozer());
            modelService.update(getTrackedPipelayer());
            modelService.update(getCrawlerExcavator());
            modelService.update(getWheeledBulldozer());
            modelService.update(getWheeledExcavator());
            modelService.update(getBackhoeLoader());
            modelService.update(getLoader());
            modelService.update(getMotorGrader());
            modelService.update(getDumpTruck());
            modelService.update(getAnother());
        }
    }

    private ModelDto getModel(String model, Set<Integer> forthLevel, Set<Integer> fifthLevel) {
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
        return new ModelDto(model, union);
    }

    private ModelDto getCrawlerBulldozer() {
        String model = "Бульдозер гусеничный";
        Set<Integer> level4 = Set.of(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51);
        Set<Integer> level5 = Set.of(64, 65, 66, 67, 68, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getTrackedPipelayer() {
        String model = "Трубоукладчик гусеничный";
        Set<Integer> level4 = Set.of(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51);
        Set<Integer> level5 = Set.of(73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getCrawlerExcavator() {
        String model = "Экскаватор гусеничный";
        Set<Integer> level4 = Set.of(36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 51);
        Set<Integer> level5 = Set.of(65, 66, 69, 70, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getWheeledBulldozer() {
        String model = "Бульдозер колесный";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(64, 65, 66, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getWheeledExcavator() {
        String model = "Экскаватор колесный";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 53, 54, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(65, 66, 69, 70, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getBackhoeLoader() {
        String model = "Экскаватор-погрузчик";
        Set<Integer> level4 = Set.of(47, 48, 49, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(64, 65, 66, 69, 70, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getLoader() {
        String model = "Погрузчик";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(64, 65, 66, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getMotorGrader() {
        String model = "Автогрейдер";
        Set<Integer> level4 = IntStream.rangeClosed(47, 63)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> level5 = Set.of(64, 65, 66, 67, 68, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getDumpTruck() {
        String model = "Самосвал";
        Set<Integer> level4 = Set.of(47, 48, 49, 50, 51, 52, 58, 59, 60, 62, 63);
        Set<Integer> level5 = Set.of(71, 72, 73, 74);
        return getModel(model, level4, level5);
    }

    private ModelDto getAnother() {
        String model = "Другое";
        Set<Integer> level4 = IntStream.rangeClosed(36, 63)
                .boxed()
                .collect(Collectors.toSet());
        Set<Integer> level5 = Set.of(64, 65, 66, 67, 68, 73, 74);
        return getModel(model, level4, level5);
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

