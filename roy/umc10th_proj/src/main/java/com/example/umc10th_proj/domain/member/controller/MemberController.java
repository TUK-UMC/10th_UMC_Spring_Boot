package com.example.umc10th_proj.domain.member.controller;

import com.example.umc10th_proj.domain.member.dto.MemberReqDTO;
import com.example.umc10th_proj.global.apiPayload.ApiResponse;
import com.example.umc10th_proj.global.apiPayload.code.GeneralSuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    // 1. 회원 가입 하기
    @PostMapping
    public ApiResponse<String> join(@RequestBody MemberReqDTO.JoinDto request) {
        // TODO: memberService.join(request) 호출 로직 작성
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "회원가입 성공");
    }

    // 2. 홈 화면 조회
    @GetMapping("/me/home-summary")
    public ApiResponse<String> getHomeSummary() {
        // TODO: memberService.getHomeSummary() 호출 로직 작성
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "홈 화면 조회 성공");
    }

    // 3. 미션 목록 조회 (진행중, 진행완료)
    @GetMapping("/me/missions")
    public ApiResponse<String> getMissions(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ) {
        // TODO: Pagination 로직 작성
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, "미션 목록 조회 성공");
    }
}

//public class MemberController {
//
//    private final MemberService memberService;
//
//    // 아무것도 받지 않은 경우
//    @GetMapping("/test")
//    public String test(){
//        throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
//    }
//
//    // Query Parameter
//    @PostMapping("/query-parameter")
//    public ApiResponse<String> exception(
//            @RequestParam String queryParameter
//    ){
//        BaseSuccessCode code = GeneralSuccessCode.OK;
//        return ApiResponse.onSuccess(code, memberService.singleParameter(queryParameter));
//    }
//
//    // Request Body
//    @PostMapping("/request-body")
//    public ApiResponse<MemberResDTO.RequestBody> requestBody(
//            @RequestBody MemberReqDTO.RequestBody dto
//    ){
//        BaseSuccessCode code = GeneralSuccessCode.OK;
//        return ApiResponse.onSuccess(code, memberService.requestBody(dto));
//    }
//
//    // Path Variable
//    @PostMapping("/{pathVariable}")
//    public String pathVariable(
//            @PathVariable String pathVariable
//    ){
//        return memberService.singleParameter(pathVariable);
//    }
//
//    // Header
//    @PostMapping("/header")
//    public String header(
//            @RequestHeader("test") String test
//    ){
//        return memberService.singleParameter(test);
//    }
//}