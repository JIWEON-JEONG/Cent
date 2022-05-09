package goingmerry.cent.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/player")
public class PlayerController {
/*
선수 관리 페이지에서 이루어질 Api
 */

    // 새로운 선수를 저장하는 api. 이메일 받아서 검색해서 정보 끌어와서 저장?
    @PostMapping(value = "/save")
    public void playerSave(@RequestBody Map<String, String> playerInfo) {

    }
}
