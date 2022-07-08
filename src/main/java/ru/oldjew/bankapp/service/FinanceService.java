package ru.oldjew.bankapp.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.oldjew.bankapp.model.FinanceOperation;
import ru.oldjew.bankapp.model.ResponseJson;
import ru.oldjew.bankapp.model.User;
import ru.oldjew.bankapp.repository.FinanceOperationRepository;
import ru.oldjew.bankapp.repository.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FinanceService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final FinanceOperationRepository financeOperationRepository;


    public Optional<BigDecimal> getBalance(Long userId){
        BigDecimal balance = null;
        try {
            balance =userRepository.findById(userId).get().getBalance();
        } catch (Exception e){
            log.info(e.getMessage());
        }
        Optional<BigDecimal> result = Optional.of(balance);
        return result;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseJson takeMoney(Long userId, BigDecimal amount){
        if (userRepository.existsById(userId)){
            User target = userRepository.findById(userId).get();
            if(target.getBalance().compareTo(amount) < 0){
                return new ResponseJson(0, "Недостаточно средств");
            } else {
                target.setBalance(target.getBalance().subtract(amount));
                FinanceOperation operation = new FinanceOperation(target, "withdraw", amount, LocalDate.now());
                userRepository.save(target);
                financeOperationRepository.save(operation);
                return new ResponseJson(1);
            }
        }

        return new ResponseJson(0, "Пользователь не найден");
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseJson putMoney(Long userId, BigDecimal amount){
        if (userRepository.existsById(userId)){
            User target = userRepository.findById(userId).get();
            target.setBalance(target.getBalance().add(amount));
            FinanceOperation operation = new FinanceOperation(target, "deposit", amount, LocalDate.now());
            userRepository.save(target);
            financeOperationRepository.save(operation);
            return new ResponseJson(1);
        }
        return new ResponseJson(0, "Пользователь не найден");
    }

    @Transactional(readOnly = true)
    public List<FinanceOperation> getOperationList(Long userId, @Nullable LocalDate from, @Nullable LocalDate to){
        List<FinanceOperation> operationsList = financeOperationRepository.findOperationsByUserId(userId);
        if (from != null && to != null){
            operationsList = operationsList
                    .stream()
                    .filter(f -> f.getDate().isAfter(from) || f.getDate().isEqual(from))
                    .filter(f -> f.getDate().isBefore(to) || f.getDate().isEqual(to))
                    .collect(Collectors.toList());
        } else if (from == null && to != null){
            operationsList = operationsList
                    .stream()
                    .filter(f -> f.getDate().isBefore(to) || f.getDate().isEqual(to))
                    .collect(Collectors.toList());
            } else if (from != null && to == null){
            operationsList = operationsList
                    .stream()
                    .filter(f -> f.getDate().isAfter(from) || f.getDate().isEqual(from))
                    .collect(Collectors.toList());
                }
            return operationsList;
    }
}
