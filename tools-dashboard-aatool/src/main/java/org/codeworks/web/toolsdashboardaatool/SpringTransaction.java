package org.codeworks.web.toolsdashboardaatool;

import org.jooq.Transaction;
import org.springframework.transaction.TransactionStatus;

/**
 * Created by benjaminkc on 17/10/26.
 */
public class SpringTransaction implements Transaction {
    final TransactionStatus tx;
    SpringTransaction(TransactionStatus tx) {
        this.tx = tx;
    }
}
