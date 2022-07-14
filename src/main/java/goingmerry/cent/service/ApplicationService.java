package goingmerry.cent.service;

import goingmerry.cent.domain.ApplicationList;
import goingmerry.cent.domain.User;
import goingmerry.cent.dto.ApplicationListDto;
import goingmerry.cent.dto.UserDto;
import goingmerry.cent.repository.ApplicationListRepository;
import goingmerry.cent.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationListRepository applicationListRepository;

    private final UserRepository userRepository;

    // user -> team get
    // 신청 대기 상태 목록만 보는 것이기 때문에 status = W 조건을 붙여줘야 한다.
    public List<ApplicationListDto> getApplicationList_team(String teamName) {

        List<ApplicationListDto> applicationList = new ArrayList<>();
        List<ApplicationList> listEntity = applicationListRepository.findByTeamName(teamName, "W");

        for(int i=0;i<listEntity.size();i++) {

            applicationList.add(ApplicationListDto
                    .builder()
                    .entity(listEntity.get(i))
                    .build());

            log.info("@teamName : {}, Email : {}",listEntity.get(i).getTeamName(),listEntity.get(i).getFromEmail());
        }

        return applicationList;
    }

    // 팀 -> 유저 신청 정보 get
    public List<ApplicationListDto> getApplicationList_user(String email) {

        List<ApplicationListDto> applicationList = new ArrayList<>();
        List<ApplicationList> listEntity = applicationListRepository.findByToEmail(email);

        for(int i=0;i<listEntity.size();i++) {
            applicationList.add(ApplicationListDto
                    .builder()
                    .entity(listEntity.get(i))
                    .build());

        }
        return applicationList;
    }


    // 팀장이 선수를 찾기 위해서 검색
    public List<UserDto> getUserInfo(String infoString) {

        List<User> listEntity = userRepository.findUserInfo(infoString);
        List<UserDto> infoList = new ArrayList<>();

        for(int i=0;i<listEntity.size();i++) {
            infoList.add(UserDto
                    .builder()
                    .entity(listEntity.get(i))
                    .build());
        }

        return infoList;
    }

    // 유저 -> 팀 신청
    public Map<String ,Object> applyToTeam(Map<String, Object> applyInfo) {

        String fromEmail = applyInfo.get("fromEmail").toString(); // 신청한 사람, 유저
        String toEmail =  applyInfo.get("toEmail").toString(); // 받는 사람, 팀장
        String teamName = applyInfo.get("teamName").toString(); // 신청하는 팀 명

        ApplicationList entity = ApplyToEntity(fromEmail, toEmail, teamName);

        applicationListRepository.save(entity);

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("applyFrom", entity.getFromEmail());
        responseMap.put("applyTo", entity.getToEmail());
        responseMap.put("teamName", entity.getTeamName());

        return responseMap;
    }



    public ApplicationList ApplyToEntity(String fromEmail, String toEmail, String teamName) {

        return ApplicationList
                .builder()
                .fromEmail(fromEmail)
                .toEmail(toEmail)
                .status("W") // 신청 시 기본값, ==Wait
                .teamName(teamName)
                .build();
    }



}
