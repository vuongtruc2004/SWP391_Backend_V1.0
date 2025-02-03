package com.controller.api.v1;

import com.dto.response.ApiResponse;
import com.service.OTPService;
import com.util.annotation.ApiMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/otp")
public class OtpController {

    private final OTPService otpService;

    public OtpController(OTPService otpService) {
        this.otpService = otpService;
    }

    @ApiMessage("Mã OTP hợp lệ!")
    @GetMapping
    public ResponseEntity<ApiResponse<Void>> checkOtp(@RequestParam(name = "code") String code) {
        return ResponseEntity.ok(otpService.checkOTP(code));
    }
}
