package ru.oldjew.bankapp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.oldjew.bankapp.model.FinanceOperation;
import ru.oldjew.bankapp.model.ResponseJson;
import ru.oldjew.bankapp.service.FinanceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController("/api")
@RequiredArgsConstructor
public class UserOperationController {

    private final FinanceService financeService;

    @GetMapping("/getBalance")
    public ResponseJson getBalance(@RequestParam(value = "id") Long userId) {
        Optional<BigDecimal> result = financeService.getBalance(userId);
        if (result.isPresent()) {
            return new ResponseJson(1, result.get().toString());
        } else {
            return new ResponseJson(-1, "Invalid id");
        }
    }

    @GetMapping("/takeMoney")
    public ResponseJson takeMoney(@RequestParam(value = "id") Long userId,
                                 @RequestParam(value = "amount") BigDecimal amount){
        return financeService.takeMoney(userId, amount);
    }

    @GetMapping("/putMoney")
    public ResponseJson putMoney(@RequestParam(value = "id") Long userId,
                                 @RequestParam(value = "amount") BigDecimal amount){
        return financeService.putMoney(userId, amount);
    }

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

    @GetMapping("/transferMoney")
    public ResponseJson transferMoney(@RequestParam(value = "senderId") Long senderId,
                                      @RequestParam(value = "recipientId") Long recipientId,
                                      @RequestParam(value = "amount") BigDecimal amount){
        return financeService.transferMoney(senderId, recipientId, amount);
    }
}
