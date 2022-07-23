package ru.oldjew.bankapp.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.oldjew.bankapp.exceptions.UserNotFoundException;
import ru.oldjew.bankapp.model.FinanceOperation;
import ru.oldjew.bankapp.model.ResponseJson;
import ru.oldjew.bankapp.service.FinanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserOperationController {

    private final FinanceService financeService;

    @Operation(summary = "Get balance of current user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    })
    })
    @GetMapping("/getBalance")
    public ResponseJson getBalance(@RequestParam(value = "id") Long userId) throws UserNotFoundException {
        Optional<BigDecimal> result = financeService.getBalance(userId);
        if (result.isPresent()) {
            return new ResponseJson(1, result.get().toString());
        } else {
            return new ResponseJson(-1, "Invalid id");
        }
    }

    @Operation(summary = "Take money from balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    })
    })
    @GetMapping("/takeMoney")
    public ResponseJson takeMoney(@RequestParam(value = "id") Long userId,
                                 @RequestParam(value = "amount") BigDecimal amount) throws UserNotFoundException {
        return financeService.takeMoney(userId, amount);
    }

    @Operation(summary = "Put money to balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    })
    })
    @GetMapping("/putMoney")
    public ResponseJson putMoney(@RequestParam(value = "id") Long userId,
                                 @RequestParam(value = "amount") BigDecimal amount) throws UserNotFoundException {
        return financeService.putMoney(userId, amount);
    }

    @Operation(summary = "List operations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    })
    })
    @GetMapping("/getOperationList")
    public List<FinanceOperation> getOperationList(@RequestParam(value = "id") Long userId,
                                                   @RequestParam(value= "from") @Nullable String fromStr,
                                                   @RequestParam(value= "to") @Nullable String toStr){
        LocalDate from = null;
        LocalDate to = null;
        if (fromStr != null){
            from = LocalDate.parse(fromStr);
        }
        if (toStr != null){
            to = LocalDate.parse(toStr);
        }
        return financeService.getOperationList(userId, from, to);
    }

    @Operation(summary = "Transfer money from sender to recipient")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseJson.class))
                    })
    })
    @GetMapping("/transferMoney")
    public ResponseJson transferMoney(@RequestParam(value = "senderId") Long senderId,
                                      @RequestParam(value = "recipientId") Long recipientId,
                                      @RequestParam(value = "amount") BigDecimal amount) throws UserNotFoundException {
        return financeService.transferMoney(senderId, recipientId, amount);
    }
}
