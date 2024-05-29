package com.java.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.TransactionStatus;

@Slf4j
public class ActionService {
    /* ActionSet이 재고를 차감하는 경우 tayoClient.reduceStock() 호출
     * tayoClient가 실패하면 runActionSet이 호출되면 안됨
     * tayoClient가 실패했는데 runActionSet이 실패하면 롤백과 함께 tayoClient.compensate() 호출

     * ActionSet이 재고를 증가시키는 경우 tayoClient.increaseStock() 호출
     * tayoClient가 실패하면 runActionSet이 호출되면 안됨
     * tayoClient가 실패했는데 runActionSet이 실패하면 롤백과 함께 tayoClient.compensate() 호출

     * 어디서 if문을 넣으시겠어요?
     */

    private ResultSet runActionSet(final ActionSet actionSet, final TransactionStatus status) {
        try {
            log.debug("actionSet: {}", actionSet);
            return runActionSet(actionSet);
        } catch (final RuntimeException e) {
            status.setRollbackOnly();
            log.error("actionSet: {}", actionSet, e);
            return ResultSet.actionSetError(e.getMessage());
        }
    }


    private ResultSet runActionSet1(final ActionSet actionSet, final TransactionStatus status) {
        try {
            if (actionSet.isStockReduce()) {
                return runStockReduceActionSet(actionSet);
            }

            if (actionSet.isStockIncrease()) {
                return runStockIncreaseActionSet(actionSet);
            }

            return runActionSet(actionSet);
        } catch (final RuntimeException e) {
            log.error("actionSet: {}", actionSet, e);

            if (actionSet.isStockReduce()) {
                log.debug("stock reduce actionSet failed: {}", actionSet);
                return ResultSet.actionSetError(e.getMessage());
            }

            if (actionSet.isStockIncrease()) {
                log.debug("stock increase actionSet failed: {}", actionSet);
                return ResultSet.actionSetError(e.getMessage());
            }

            status.setRollbackOnly();
            return ResultSet.actionSetError(e.getMessage());
        }
    }

    private ResultSet runActionSet2(final ActionSet actionSet, final TransactionStatus status) {
        if (actionSet.isStockReduce()) {
            try {
                log.debug("actionSet: {}", actionSet);
                return runStockReduceActionSet(actionSet);
            } catch (final RuntimeException e) {
                log.error("actionSet: {}", actionSet, e);
                status.setRollbackOnly();
                return ResultSet.actionSetError(e.getMessage());
            }
        }

        if (actionSet.isStockIncrease()) {
            try {
                log.debug("actionSet: {}", actionSet);
                return runStockIncreaseActionSet(actionSet);
            } catch (final RuntimeException e) {
                log.error("actionSet: {}", actionSet, e);
                status.setRollbackOnly();
                return ResultSet.actionSetError(e.getMessage());
            }
        }

        try {
            log.debug("actionSet: {}", actionSet);
            return runActionSet(actionSet);
        } catch (final RuntimeException e) {
            log.error("actionSet: {}", actionSet, e);
            status.setRollbackOnly();
            return ResultSet.actionSetError(e.getMessage());
        }
    }

    private ResultSet runActionSet(final ActionSet actionSet) {
        throw new IllegalStateException("ActionService::runActionSet not implemented yet");
    }

    private ResultSet runStockIncreaseActionSet(final ActionSet actionSet) {
        throw new IllegalStateException("ActionService::runStockIncreaseActionSet not implemented yet");
    }


    private ResultSet runStockReduceActionSet(final ActionSet actionSet) {
        throw new IllegalStateException("ActionService::runStockReduceActionSet not implemented yet");
    }
}
