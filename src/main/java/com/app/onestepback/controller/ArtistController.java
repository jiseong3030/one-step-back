package com.app.onestepback.controller;

import com.app.onestepback.domain.ArtistDTO;
import com.app.onestepback.domain.ArtistPostDTO;
import com.app.onestepback.domain.SubscriptionVO;
import com.app.onestepback.domain.VideoDTO;
import com.app.onestepback.repository.ArtistPostDAO;
import com.app.onestepback.service.ArtistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
@RequestMapping("/artist/*")
@RequiredArgsConstructor
public class ArtistController {
    private final ArtistService artistService;

    @GetMapping("main")
    public void goToMainForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("posts", artistService.get3Posts(id));
        model.addAttribute("artist", artistService.getArtist(id).get());
    }

    @GetMapping("get-counts")
    @ResponseBody
    public int[] getCountOfSubscriber(@RequestParam("id") Long id) {
        int[] counts = new int[3];
        // 구독자 수
        counts[0] = artistService.getCountOfSubscriber(id);
        // 아티스트 포스트 + 비디오 포스트
        counts[1] = artistService.getCountOfPost(id);
        counts[2] = artistService.getCountOfVideo(id);

        return counts;
    }
    @GetMapping("get-3videos")
    @ResponseBody
    public List<VideoDTO> load3Videos(@RequestParam("id") Long id) {
        return artistService.get3Videos(id);
    }

    @GetMapping("sponsor")
    public void goToSponsorForm() {
        ;
    }

    @GetMapping("/post/edit")
    public void goToPostEditForm() {
        ;
    }

    @GetMapping("check-subscription")
    @ResponseBody
    public int checkSubscription(@RequestParam("subscriptionInfo") SubscriptionVO subscriptionVO){
        if (artistService.checkSubscription(subscriptionVO).isEmpty()){
            return -1;
        } else {
            return 1;
        }
    }
}
