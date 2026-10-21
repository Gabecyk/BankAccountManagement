CREATE TABLE transactions (
    id UUID PRIMARY KEY,
    sender_account_id UUID NOT NULL,
    receiver_account_id UUID NOT NULL,
    occurred_at TIMESTAMP NOT NULL,
    amount NUMERIC(10, 2) NOT NULL,
    transaction_type VARCHAR(30) NOT NULL,
    CONSTRAINT fk_transactions_sender_account
        FOREIGN KEY (sender_account_id) REFERENCES accounts(id),
    CONSTRAINT fk_transactions_receiver_account
        FOREIGN KEY (receiver_account_id) REFERENCES accounts(id)
);