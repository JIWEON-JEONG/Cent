package goingmerry.cent.service;

import goingmerry.cent.domain.DesignateFormation;
import goingmerry.cent.repository.DesignFormationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class FormationDesignService {

    private final DesignFormationRepository designFormationRepository;

    // 포메이션 이름을 받아 해당 포메이션 리스트 11개 테이블에서 select
    public List<Map<String, String>> getSelectedFormation(String formationName) {

        List<Map<String, String>> selectedFormation = designFormationRepository.findFormationByFormationName(formationName);

        return selectedFormation;
    }

    // 4421 등의 포메이션 이름을 데이터베이스에서 꺼내오는 메소드
    public List<String> getFormationName() {
        List<String> formationList = null;
        formationList = designFormationRepository.getFormationList();
        return formationList;
    }
}
