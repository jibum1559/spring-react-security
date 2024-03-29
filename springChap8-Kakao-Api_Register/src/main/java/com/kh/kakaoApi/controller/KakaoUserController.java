package com.kh.kakaoApi.controller;

import com.kh.kakaoApi.dto.KakaoDTO;
import com.kh.kakaoApi.common.MsgEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.kakaoApi.service.KakaoUserService;
import com.kh.kakaoApi.vo.KakaoUser;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("kakao")
@RequiredArgsConstructor
public class KakaoUserController {
	private final KakaoUserService kakaoService;
	@GetMapping("/callback")
	public String callback(HttpServletRequest request,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String birthdate,
			Model model) throws Exception {
		//kakao/callback 을 작성하면 JSON 형식으로 이동했지만 register html 파일로 이동하게 해줄 것
		KakaoDTO kakaoInfo = kakaoService.getKakaoInfo(request.getParameter("code"), name, birthdate);
		model.addAttribute("kakaoInfo",kakaoInfo);
		return "register";
	}
	
	// 프론트엔드에서 가지고오는 회원가입의 대한 결과를 전달해주는 PostMapping
	@PostMapping("/register")
	public ResponseEntity<MsgEntity> registerUser(
			@RequestParam String email,											
			@RequestParam String nickname,													
			@RequestParam String name,													
			@RequestParam String birthdate) {
		
		KakaoDTO kakaoDTO = KakaoDTO.builder()
							.email(email)
							.nickname(nickname)
							.name(name)
							.birthdate(birthdate)
							.build();
		KakaoUser registeredUser = kakaoService.registerUser(kakaoDTO);
		
		return ResponseEntity.ok()
				.body(new MsgEntity("Success", registeredUser));
		
		
	}
	

}